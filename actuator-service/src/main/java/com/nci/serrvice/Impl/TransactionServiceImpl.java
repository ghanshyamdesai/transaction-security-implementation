package com.nci.serrvice.Impl;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nci.actuatorservice.PDFTableStripper;
import com.nci.serrvice.TransactionService;

import technology.tabula.Rectangle;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Value("${pdf.file.locarion}")
	private String fileLoc;
	
	@Value("${pdf.file.name}")
	private String fileName;

	@Override
	public void readPdfFile() throws Exception {
		String fileAbsolutePath = fileLoc+fileName;
		try (PDDocument document = PDDocument.load(new File(fileAbsolutePath))) {
			final double res = 72; // PDF units are at 72 DPI
			PDFTableStripper stripper = new PDFTableStripper();
			stripper.setSortByPosition(true);

			// Choose a region in which to extract a table (here a 6"wide, 9" high rectangle
			// offset 1" from top left of page)
			stripper.setRegion(new Rectangle((int) Math.round(1.0 * res), (int) Math.round(1 * res),
					(int) Math.round(6 * res), (int) Math.round(9.0 * res)));

			// Repeat for each page of PDF
			for (int page = 0; page < document.getNumberOfPages(); ++page) {
				System.out.println("Page " + page);
				PDPage pdPage = document.getPage(page);
				try {
					stripper.extractTable(pdPage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int c = 0; c < stripper.getColumns(); ++c) {
					System.out.println("Column " + c);
					for (int r = 0; r < stripper.getRows(); ++r) {
						System.out.println("Row " + r);
						System.out.println(stripper.getText(r, c));
					}
				}
			}
		}

	}

}
