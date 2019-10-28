package com.nci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nci.serrvice.TransactionService;

import cm.nci.pdf.PdfDetails;

@Controller
public class PDFReaderController {

	@Autowired
	TransactionService transactionService;

	@GetMapping("/readPdf")
	@ResponseBody
	public PdfDetails sayHello() {

		try {
			transactionService.readPdfFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new PdfDetails();
	}

}
