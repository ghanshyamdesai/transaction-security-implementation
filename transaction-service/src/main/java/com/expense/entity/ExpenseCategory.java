package com.expense.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "expenseCategory" ,schema="nci01")
public class ExpenseCategory implements Serializable{

	
	private static final long serialVersionUID = 1177567915274607012L;
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "clothes")
	private Double clothes;
	
	@Column(name = "eatingOut")
	private Double eatingOut;
	@Column(name = "entertainment")
	private Double entertainment;
	@Column(name = "fuel")
	private Double fuel;
	@Column(name = "grocery")
	private Double grocery;
	@Column(name = "householdItems")
	private Double householdItems;
	@Column(name = "gifts")
	private Double gifts;
	@Column(name = "holidays")
	private Double holidays;
	@Column(name = "kids")
	private Double kids;
	@Column(name = "shopping")
	private Double shopping;
	@Column(name = "sports")
	private Double sports;
	@Column(name = "travel")
	private Double travel;
	@Column(name = "bills")
	private Double bills;
	@Column(name = "cash")
	private Double cash;
	@Column(name = "date_of_transaction")
	private String date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getClothes() {
		return clothes;
	}

	public void setClothes(Double clothes) {
		this.clothes = clothes;
	}

	public Double getEatingOut() {
		return eatingOut;
	}

	public void setEatingOut(Double eatingOut) {
		this.eatingOut = eatingOut;
	}

	public Double getEntertainment() {
		return entertainment;
	}

	public void setEntertainment(Double entertainment) {
		this.entertainment = entertainment;
	}

	public Double getFuel() {
		return fuel;
	}

	public void setFuel(Double fuel) {
		this.fuel = fuel;
	}

	public Double getGrocery() {
		return grocery;
	}

	public void setGrocery(Double grocery) {
		this.grocery = grocery;
	}

	public Double getHouseholdItems() {
		return householdItems;
	}

	public void setHouseholdItems(Double householdItems) {
		this.householdItems = householdItems;
	}

	public Double getBills() {
		return bills;
	}

	public void setBills(Double bills) {
		this.bills = bills;
	}

	public Double getGifts() {
		return gifts;
	}

	public void setGifts(Double gifts) {
		this.gifts = gifts;
	}

	public Double getHolidays() {
		return holidays;
	}

	public void setHolidays(Double holidays) {
		this.holidays = holidays;
	}

	public Double getKids() {
		return kids;
	}

	public void setKids(Double kids) {
		this.kids = kids;
	}

	public Double getShopping() {
		return shopping;
	}

	public void setShopping(Double shopping) {
		this.shopping = shopping;
	}

	public Double getSports() {
		return sports;
	}

	public void setSports(Double sports) {
		this.sports = sports;
	}

	public Double getTravel() {
		return travel;
	}

	public void setTravel(Double travel) {
		this.travel = travel;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getCash() {
		return cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}	

}