package com.expense.request;

import java.util.List;

import cm.nci.pdf.PdfDetails;

public class DisplayDetailsRequest {
	
	private List<PdfDetails> pdfList;
	
	private List<String> categoryList;

	public List<PdfDetails> getPdfList() {
		return pdfList;
	}

	public void setPdfList(List<PdfDetails> pdfList) {
		this.pdfList = pdfList;
	}

	public List<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}
	
	
	
	
	
}
