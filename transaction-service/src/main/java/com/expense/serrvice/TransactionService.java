package com.expense.serrvice;

import java.util.List;

import com.expense.request.SubmitExpenseRequest;

import cm.nci.pdf.PdfDetails;

public interface TransactionService {

	List<PdfDetails> readPdfFile() throws Exception;
	
	void submitDetails(SubmitExpenseRequest request);
}
