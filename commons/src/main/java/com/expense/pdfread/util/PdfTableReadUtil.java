package com.expense.pdfread.util;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import org.springframework.web.multipart.MultipartFile;

import cm.nci.pdf.PdfDetails;

import com.expense.pdfread.PDFTableParsing;

public final class PdfTableReadUtil {
	
	// Suppresses default constructor, ensuring non-instantiability.
    private PdfTableReadUtil() {
    }


	public static List<PdfDetails> readPdfFile(MultipartFile file) throws IOException {

		List<PdfDetails> itemList = new ArrayList<PdfDetails>();

		try (PDDocument document = PDDocument.load(file.getBytes())) {
			final double res = 72; 
			PDFTableParsing stripper = new PDFTableParsing();
			stripper.setSortByPosition(true);

			
			stripper.setRegion(new Rectangle((int) Math.round(1.0 * res),
					(int) Math.round(1 * res), (int) Math.round(6 * res),
					(int) Math.round(9.0 * res)));

			
			for (int page = 0; page < document.getNumberOfPages(); ++page) {
				
				PDPage pdPage = document.getPage(page);
				stripper.extractTable(pdPage);
				String dateArray[] = new String[stripper.getRows()];
				String descriptionArray[] = new String[stripper.getRows()];
				String amountArray[] = new String[stripper.getRows()];
				for (int c = 0; c < stripper.getColumns(); ++c) {
					
					for (int r = 0; r < stripper.getRows(); ++r) {
						

						if (stripper.getText(r, c).toString().contains("Date")) {

							for (int i = 0; i < dateArray.length; i++) {
								String newDate = stripper
										.getText(r + i, c)
										.toString()
										.substring(
												0,
												stripper.getText(r + i, c)
														.toString().length() - 3);
								if (!newDate.isEmpty()) {
									dateArray[i] = stripper.getText(r + i, c);
								} else {
									break;
								}

							}

							System.out.println("pdf date " + dateArray);

						}
						if (stripper.getText(r, c).toString()
								.contains("Description")) {

							for (int i = 0; i < descriptionArray.length; i++) {
								String newDate = stripper
										.getText(r + i, c)
										.toString()
										.substring(
												0,
												stripper.getText(r + i, c)
														.toString().length() - 2);
								if (!newDate.isEmpty()) {
									descriptionArray[i] = stripper.getText(r
											+ i, c);
								} else {
									break;
								}

							}

							System.out.println("pdf date " + descriptionArray);

						}

						if (stripper.getText(r, c).toString()
								.contains("Amount")) {

							for (int i = 0; i < amountArray.length; i++) {
								String newDate = stripper
										.getText(r + i, c)
										.toString()
										.substring(
												0,
												stripper.getText(r + i, c)
														.toString().length() - 2);
								if (!newDate.isEmpty()) {
									amountArray[i] = stripper.getText(r + i, c);
								} else {
									break;
								}

							}

							System.out.println("pdf date " + amountArray);

						}

					}

				}

				for (int i = 1; i < amountArray.length; i++) {
					if (amountArray[i] != null) {
						PdfDetails pdfDetails = new PdfDetails();
						pdfDetails.setDate(dateArray[i]);
						pdfDetails.setAmount(amountArray[i]);
						pdfDetails.setDescription(descriptionArray[i]);
						itemList.add(pdfDetails);
					}

				}
			}

			System.out.println("List of items " + itemList);
		}
		return itemList;
	}

}
