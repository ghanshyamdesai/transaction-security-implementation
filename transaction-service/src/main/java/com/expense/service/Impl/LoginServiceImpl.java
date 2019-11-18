package com.expense.service.Impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.dao.AccountDetailsDao;
import com.expense.dao.CategoryListDao;
import com.expense.dao.UserDetailsDao;
import com.expense.entity.AccountDetails;
import com.expense.entity.CategoryList;
import com.expense.entity.UserDetails;
import com.expense.request.LoginForm;
import com.expense.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserDetailsDao userDetailsDao;
	
	@Autowired
	AccountDetailsDao accountDetailsDao;
	
	@Autowired
	CategoryListDao categoryListDao;

	@Override
	public boolean userIsPresent(LoginForm loginRequest) {

		UserDetails userdetailsInDB =userDetailsDao.findByPhoneNumber(loginRequest.getPhoneNumber());
		
		if (null != userdetailsInDB) {
			if(loginRequest.getLastName().equalsIgnoreCase(userdetailsInDB.getLastName())){
				if(loginRequest.getPhoneNumber().equalsIgnoreCase(userdetailsInDB.getPhoneNumber())){
					return true;
				}
				return false;
			}
			return false;
		} 
		
		return false;
	}

	@Override
	public void saveNewUser(LoginForm loginForm) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
		java.util.Date dateStr = null;
		try {
			dateStr = formatter.parse(loginForm.getDateOfBirth());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
		
		UserDetails userDetails = new UserDetails();
		  userDetails.setFirstName(loginForm.getFirstName());
		  userDetails.setLastName(loginForm.getLastName());
		  userDetails.setDateOfBirth(dateDB);
		  userDetails.setPdfPassword(loginForm.getPassword());
		  userDetails.setPhoneNumber(loginForm.getPhoneNumber());
		  userDetails.setCreatedOn(new Timestamp(new Date().getTime()));
		userDetailsDao.save(userDetails);
		
		AccountDetails accountDetails = new AccountDetails();
		accountDetails.setUserDetails(userDetails);
		accountDetails.setMonth(loginForm.getSalaryMonth());
		accountDetails.setSalary(loginForm.getSalary());
		accountDetails.setThresholdExpense(loginForm.getThresholdExpense());
		accountDetailsDao.save(accountDetails);
		
		CategoryList categoryList = new CategoryList();
		categoryList.setUserDetails(userDetails);
		List<String> category = new ArrayList<String>();
		category.add("Clothes");
		category.add("Eating Out");
		category.add("Entertainment");
		category.add("Fuel");
		category.add("Grocery");
		category.add("Household Items");
		category.add("Gifts");
		category.add("Holidays");
		category.add("Kids");
		category.add("Shopping");
		category.add("Sports");
		category.add("Travel");
		category.add("Bills");
		category.add("Cash");
		categoryList.setCategory(category);
		categoryListDao.save(categoryList);
		
		
	}

}
