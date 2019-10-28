package com.example.actuatorservice;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.jbig2.io.SubInputStream;
import org.apache.pdfbox.jbig2.segments.Table;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;

import technology.tabula.Rectangle;



@SpringBootApplication
public class ActuatorServiceApplication {

	public static void main(String[] args) throws InvalidPasswordException, IOException {
		

    	/*try (PDDocument document = PDDocument.load(new File("E:/Transaction.pdf"))) {

			document.getClass();

			if (!document.isEncrypted()) {

				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);

				PDFTextStripper tStripper = new PDFTextStripper();

				String pdfFileInText = tStripper.getText(document);
				// System.out.println("Text:" + st);

				// split by whitespace
				String lines[] = pdfFileInText.split("\\r?\\n");
				for (String line : lines) {
					PdfDetails pdfDetails = new PdfDetails();
					System.out.println(line);
				}

			}

		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try (PDDocument document = PDDocument.load(new File("E:/Transaction.pdf")))
		{
			final double res = 72; // PDF units are at 72 DPI
			PDFTableStripper stripper = new PDFTableStripper();
			stripper.setSortByPosition(true);
			
			// Choose a region in which to extract a table (here a 6"wide, 9" high rectangle offset 1" from top left of page)
			stripper.setRegion(new Rectangle((int) Math.round(1.0*res), (int) Math.round(1*res), (int) Math.round(6*res), (int) Math.round(9.0*res)));
			
			// Repeat for each page of PDF
			for (int page = 0; page < document.getNumberOfPages(); ++page)
			{
				System.out.println("Page " + page);
				PDPage pdPage = document.getPage(page);
				stripper.extractTable(pdPage);
				for(int c=0; c<stripper.getColumns(); ++c) {
					System.out.println("Column " + c);
					for(int r=0; r<stripper.getRows(); ++r) {
						System.out.println("Row " + r);
						System.out.println(stripper.getText(r, c));
					}
				}
			}
		}
		
	}
		
		

}
