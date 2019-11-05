package com.expense.request;

import java.util.Map;

public class SubmitExpenseRequest {
	
	private String date;
	private Map<String , String> details;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Map<String, String> getDetails() {
		return details;
	}
	public void setDetails(Map<String, String> details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "SubmitExpenseRequest [date=" + date + ", details=" + details
				+ "]";
	}
	
	
	

}
