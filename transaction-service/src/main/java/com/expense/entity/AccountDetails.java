package com.expense.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "AccountDetails" ,schema="nci01")
public class AccountDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1384368707692197314L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "month_of_transaction")
	private String month;
	
	@NotNull
	@Column(name = "salary")
	private String salary;
	
	@NotNull
	@Column(name = "threshold_expense")
	private String thresholdExpense;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@NotNull
    private UserDetails userDetails;
	
		
	public AccountDetails() {}
	 
    

	public Long getId() {
		return id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
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



	public UserDetails getUserDetails() {
		return userDetails;
	}



	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}



	@Override
	public String toString() {
		return "AccountDetails [id=" + id + ", month=" + month + ", salary="
				+ salary + ", thresholdExpense=" + thresholdExpense
				+ ", userDetails=" + userDetails + "]";
	}

	
	

}
