package com.example.actuatorservice;

import java.util.Date;

public class PdfDetails {
	
	private Date date;
	private String description;
	private String amount;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "PdfDetails [date=" + date + ", description=" + description
				+ ", amount=" + amount + "]";
	}
	
	

}
