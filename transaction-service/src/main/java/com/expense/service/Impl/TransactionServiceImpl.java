package com.expense.service.Impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import technology.tabula.Rectangle;
import cm.nci.pdf.PdfDetails;

import com.expense.actuatorservice.PDFTableStripper;
import com.expense.dao.ExpenseCategoryDao;
import com.expense.entity.ExpenseCategory;
import com.expense.request.SubmitExpenseRequest;
import com.expense.serrvice.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Value("${pdf.file.locarion}")
	private String fileLoc;

	@Value("${pdf.file.name}")
	private String fileName;

	@Autowired
	private ExpenseCategoryDao<ExpenseCategory> expenseCategoryDao;

	@Override
	public List<PdfDetails> readPdfFile() throws Exception {

		List<PdfDetails> itemList = new ArrayList<PdfDetails>();

		String fileAbsolutePath = fileLoc + fileName;

		try (PDDocument document = PDDocument.load(new File(fileAbsolutePath))) {
			final double res = 72; // PDF units are at 72 DPI
			PDFTableStripper stripper = new PDFTableStripper();
			stripper.setSortByPosition(true);

			// Choose a region in which to extract a table (here a 6"wide, 9"
			// high rectangle offset 1" from top left of page)
			stripper.setRegion(new Rectangle((int) Math.round(1.0 * res),
					(int) Math.round(1 * res), (int) Math.round(6 * res),
					(int) Math.round(9.0 * res)));

			// Repeat for each page of PDF
			for (int page = 0; page < document.getNumberOfPages(); ++page) {
				// System.out.println("Page " + page);
				PDPage pdPage = document.getPage(page);
				stripper.extractTable(pdPage);
				String dateArray[] = new String[stripper.getRows()];
				String descriptionArray[] = new String[stripper.getRows()];
				String amountArray[] = new String[stripper.getRows()];
				for (int c = 0; c < stripper.getColumns(); ++c) {
					// System.out.println("Column " + c);
					for (int r = 0; r < stripper.getRows(); ++r) {
						// System.out.println("Row " + r);
						// System.out.println(stripper.getText(r, c));

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

	@Override
	public void submitDetails(SubmitExpenseRequest request) {
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setDate(request.getDate());

		Map<String, String> details = request.getDetails();

		for (Map.Entry<String, String> entry : details.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = "
					+ entry.getValue());

			if ("clothes".equals(entry.getKey())) {
				expenseCategory.setClothes(Double.valueOf(entry.getValue()));
			}

			if ("eatingOut".equals(entry.getKey())) {
				expenseCategory.setEatingOut(Double.valueOf(entry.getValue()));
			}

			if ("entertainment".equals(entry.getKey())) {
				expenseCategory.setEntertainment(Double.valueOf(entry
						.getValue()));
			}

			if ("fuel".equals(entry.getKey())) {
				expenseCategory.setFuel(Double.valueOf(entry.getValue()));
			}

			if ("grocery".equals(entry.getKey())) {
				expenseCategory.setGrocery(Double.valueOf(entry.getValue()));
			}

			if ("householdItems".equals(entry.getKey())) {
				expenseCategory.setHouseholdItems(Double.valueOf(entry
						.getValue()));
			}

			if ("gifts".equals(entry.getKey())) {
				expenseCategory.setGifts(Double.valueOf(entry.getValue()));
			}

			if ("holidays".equals(entry.getKey())) {
				expenseCategory.setHolidays(Double.valueOf(entry.getValue()));
			}

			if ("kids".equals(entry.getKey())) {
				expenseCategory.setKids(Double.valueOf(entry.getValue()));
			}

			if ("shopping".equals(entry.getKey())) {
				expenseCategory.setShopping(Double.valueOf(entry.getValue()));
			}

			if ("sports".equals(entry.getKey())) {
				expenseCategory.setSports(Double.valueOf(entry.getValue()));
			}

			if ("travel".equals(entry.getKey())) {
				expenseCategory.setTravel(Double.valueOf(entry.getValue()));
			}

			if ("bills".equals(entry.getKey())) {
				expenseCategory.setBills(Double.valueOf(entry.getValue()));
			}

			if ("cash".equals(entry.getKey())) {
				expenseCategory.setCash(Double.valueOf(entry.getValue()));
			}

		}

		expenseCategoryDao.save(expenseCategory);

	}

}
