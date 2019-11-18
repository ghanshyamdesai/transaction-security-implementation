package com.expense.pdfread;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType3Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.pdfbox.text.TextPosition;


public class PDFTableParsing extends PDFTextStripper{
	
	private AffineTransform flip;
    private AffineTransform rotate;
  
    private Set<Rectangle2D> boxes;

 
    private double deltax = 1.0; 
	private double deltay = 0.000; 
	
	
	private Rectangle2D rectangleArea;
	
	
    private int numberOfRows=0;

    private int numberOfCols=0;
    
   
	private PDFTextStripperByArea pDFTextStripperByArea; 
	
	
    public static class Interval {
    	double start;
    	double end;
		public Interval(double start, double end) {
			this.start=start; this.end = end;
		}
		public void add(Interval col) {
			if(col.start<start)
				start = col.start;
			if(col.end>end)
				end = col.end;
		}
		public static void addTo(Interval x, LinkedList<Interval> columns) {
    		int p = 0;
			Iterator<Interval> it = columns.iterator();
			
			while(it.hasNext()) {
				Interval col = it.next();
				if(x.end>=col.start) {
					if(x.start<=col.end) { 
						x.add(col);
						it.remove();
					}
					break;
				}
				++p;
			}
			while(it.hasNext()) {
				Interval col = it.next();
				if(x.start>col.end)
					break;
				x.add(col);
				it.remove();
			}
			columns.add(p, x);			
		}

	}
	
	
   
    public PDFTableParsing() throws IOException
    {
    	super.setShouldSeparateByBeads(false);
        pDFTextStripperByArea = new PDFTextStripperByArea();
        pDFTextStripperByArea.setSortByPosition( true );
    }
    
    
    public void setRegion(Rectangle2D rect )
    {
        rectangleArea = rect;
    }
    
    public int getRows()
    {
        return numberOfRows;
    }
    
    public int getColumns()
    {
        return numberOfCols;
    }
   
   
    public String getText(int row, int col)
    {
        return pDFTextStripperByArea.getTextForRegion("el"+col+"x"+row);
    }

    public void extractTable(PDPage pdPage) throws IOException
    {        
        setStartPage(getCurrentPageNo());
        setEndPage(getCurrentPageNo());
        
        boxes = new HashSet<Rectangle2D>();
        
        flip = new AffineTransform();
        flip.translate(0, pdPage.getBBox().getHeight());
        flip.scale(1, -1);

       
        rotate = new AffineTransform();
        int rotation = pdPage.getRotation();
        if (rotation != 0)
        {
            PDRectangle mediaBox = pdPage.getMediaBox();
            switch (rotation)
            {
                case 90:
                    rotate.translate(mediaBox.getHeight(), 0);
                    break;
                case 270:
                    rotate.translate(0, mediaBox.getWidth());
                    break;
                case 180:
                    rotate.translate(mediaBox.getWidth(), mediaBox.getHeight());
                    break;
                default:
                    break;
            }
            rotate.rotate(Math.toRadians(rotation));
        }
       
        try (Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream())) {
        	super.output = dummy;
        	super.processPage(pdPage);
        }                

        Rectangle2D[][] regions = calculateTableRegions();
        for(int i=0; i<numberOfCols; ++i) {
        	for(int j=0; j<numberOfRows; ++j) {
        		final Rectangle2D region = regions[i][j];
				pDFTextStripperByArea.addRegion("el"+i+"x"+j, region);
        	}
        }

        pDFTextStripperByArea.extractRegions(pdPage); 
    }

   
    private Rectangle2D[][] calculateTableRegions() {
    	
    	
    	LinkedList<Interval> columns = new LinkedList<Interval>();
    	LinkedList<Interval> rows = new LinkedList<Interval>();
    	
    	for(Rectangle2D box: boxes) {
    		Interval x = new Interval(box.getMinX(), box.getMaxX());
    		Interval y = new Interval(box.getMinY(), box.getMaxY());
    		
    		Interval.addTo(x, columns);
    		Interval.addTo(y, rows);    	
    	}
        
    	numberOfRows = rows.size();
    	numberOfCols = columns.size();
    	Rectangle2D[][] regions = new Rectangle2D[numberOfCols][numberOfRows];
    	int i=0;
    	for(Interval column: columns) {
    		int j=0;
    		for(Interval row: rows) {
				regions[numberOfCols-i-1][numberOfRows-j-1] = new Rectangle2D.Double(column.start, row.start, column.end - column.start, row.end - row.start);    			
    			++j;
    		}
    		++i;
    	}
    	
    	return regions;
	}

	
    @Override
    protected void writeString(String string, List<TextPosition> textPositions) throws IOException
    {        
        for (TextPosition text : textPositions)
        {
          
            AffineTransform at = text.getTextMatrix().createAffineTransform();
            PDFont font = text.getFont();
            BoundingBox bbox = font.getBoundingBox();

            float xadvance = font.getWidth(text.getCharacterCodes()[0]); // todo: should iterate all chars
            Rectangle2D.Float rect = new Rectangle2D.Float(0, bbox.getLowerLeftY(), xadvance, bbox.getHeight());
            
            if (font instanceof PDType3Font)
            {
               
                at.concatenate(font.getFontMatrix().createAffineTransform());
            }
            else
            {
              
                at.scale(1/1000f, 1/1000f);
            }
            Shape s = at.createTransformedShape(rect);
            s = flip.createTransformedShape(s);
            s = rotate.createTransformedShape(s);
            
            
            
            Rectangle2D bounds = s.getBounds2D();
            Rectangle2D hitbox = bounds.getBounds2D();
            hitbox.add(bounds.getMinX() - deltax , bounds.getMinY() - deltay);
            hitbox.add(bounds.getMaxX() + deltax , bounds.getMaxY() + deltay);
            
          
            List<Rectangle2D> intersectList = new ArrayList<Rectangle2D>();
            for(Rectangle2D box: boxes) {
            	if(box.intersects(hitbox)) {
            		intersectList.add(box);
            	}
            }
            
          
            for(Rectangle2D box: intersectList) {
            	bounds.add(box);
            	boxes.remove(box);
            }
            boxes.add(bounds);
            
        }
        
    }
    
    
    @Override
    public final void setShouldSeparateByBeads(boolean aShouldSeparateByBeads)
    {
    }

   
    @Override
    protected void processTextPosition( TextPosition text )
    {
        if(rectangleArea!=null && !rectangleArea.contains( text.getX(), text.getY() ) ) {
        	
        } else {
            super.processTextPosition( text );
        }
    }

}