package com.expense.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class LoginForm {
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String phoneNumber;
	
	@NotNull
	private String password;
	
	@NotNull
	private String dateOfBirth;
	
	@NotNull
	private String salaryMonth;
	
	@NotNull
	private String salary;
	
	@NotNull
	private String thresholdExpense;
	
	public LoginForm(){
		super();
	}

	
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getSalaryMonth() {
		return salaryMonth;
	}


	public void setSalaryMonth(String salaryMonth) {
		this.salaryMonth = salaryMonth;
	}


	public String getSalary() {
		return salary;
	}


	public void setSalary(String salary) {
		this.salary = salary;
	}


	public String getThresholdExpense() {
		return thresholdExpense;
	}


	public void setThresholdExpense(String thresholdExpense) {
		this.thresholdExpense = thresholdExpense;
	}
	

}
