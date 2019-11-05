package com.expense.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.nci.pdf.PdfDetails;

import com.expense.request.SubmitExpenseRequest;
import com.expense.serrvice.TransactionService;

@Controller
public class PDFReaderController {

	@Autowired
	TransactionService transactionService;

	@GetMapping("/readPdf")
	@ResponseBody
	public List<PdfDetails> readPdf() {
		List<PdfDetails> itemList = null;
		try {
			 itemList = transactionService.readPdfFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemList;
	}
	
	@PostMapping("/submit")
	@ResponseBody
	public void submit(HttpServletRequest request,SubmitExpenseRequest submitExpenseRequest) {
		submitExpenseRequest.setDate("2019.11.05");
		Map<String, String> details = new HashMap<String, String>();
		details.put("clothes", "2.00");
		submitExpenseRequest.setDetails(details);
		
		System.out.println(submitExpenseRequest);
			transactionService.submitDetails(submitExpenseRequest);
		
	}
	
	
	/*@GetMapping("/submit")
	@ResponseBody
	public void submit() {
		
		SubmitExpenseRequest submitExpenseRequest = new SubmitExpenseRequest();
		submitExpenseRequest.setDate("2019.11.05");
		Map<String, String> details = new HashMap<String, String>();
		details.put("clothes", "2.00");
		submitExpenseRequest.setDetails(details);
		
		System.out.println(submitExpenseRequest);
			transactionService.submitDetails(submitExpenseRequest);
		
	}*/

}
