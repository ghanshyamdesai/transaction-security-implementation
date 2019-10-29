package com.nci.serrvice;

import java.util.List;

import cm.nci.pdf.PdfDetails;

public interface TransactionService {

	List<PdfDetails> readPdfFile() throws Exception;
}
