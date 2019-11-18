package com.expense.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cm.nci.pdf.PdfDetails;

import com.expense.dao.CategoryListDao;
import com.expense.dao.ExpenseCategoryDao;
import com.expense.dao.UserDetailsDao;
import com.expense.entity.CategoryList;
import com.expense.entity.ExpenseCategory;
import com.expense.entity.UserDetails;
import com.expense.pdfread.util.PdfTableReadUtil;
import com.expense.request.CategoryListRequest;
import com.expense.request.SubmitExpenseRequest;
import com.expense.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {


	/*@Autowired
	private ExpenseCategoryDao<ExpenseCategory> expenseCategoryDao;*/
	@Autowired
	CategoryListDao categoryListDao;
	
	@Autowired
	UserDetailsDao userDetailsDao;

	@Override
	public List<PdfDetails> readPdfFile(MultipartFile file) throws Exception {
		
		List<PdfDetails> itemList = PdfTableReadUtil.readPdfFile(file);;
		return itemList;
	}
	

	@Override
	public List<String> getCategories(String phoneNumber) {
		
		UserDetails userdetailsInDB = userDetailsDao.findByPhoneNumber(phoneNumber);
		// TODO Auto-generated method stub
		CategoryList categoryList = categoryListDao.findByUserDetailsId(userdetailsInDB.getId());
		
		List<String> category = categoryList.getCategory();
		return category;
	}

	
	@Override
	public void submitDetails(SubmitExpenseRequest request) {
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setDate(request.getDate());

		Map<String, String> details = request.getDetails();

		for (Map.Entry<String, String> entry : details.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = "
					+ entry.getValue());

			if ("clothes".equals(entry.getKey())) {
				expenseCategory.setClothes(Double.valueOf(entry.getValue()));
			}

			if ("eatingOut".equals(entry.getKey())) {
				expenseCategory.setEatingOut(Double.valueOf(entry.getValue()));
			}

			if ("entertainment".equals(entry.getKey())) {
				expenseCategory.setEntertainment(Double.valueOf(entry
						.getValue()));
			}

			if ("fuel".equals(entry.getKey())) {
				expenseCategory.setFuel(Double.valueOf(entry.getValue()));
			}

			if ("grocery".equals(entry.getKey())) {
				expenseCategory.setGrocery(Double.valueOf(entry.getValue()));
			}

			if ("householdItems".equals(entry.getKey())) {
				expenseCategory.setHouseholdItems(Double.valueOf(entry
						.getValue()));
			}

			if ("gifts".equals(entry.getKey())) {
				expenseCategory.setGifts(Double.valueOf(entry.getValue()));
			}

			if ("holidays".equals(entry.getKey())) {
				expenseCategory.setHolidays(Double.valueOf(entry.getValue()));
			}

			if ("kids".equals(entry.getKey())) {
				expenseCategory.setKids(Double.valueOf(entry.getValue()));
			}

			if ("shopping".equals(entry.getKey())) {
				expenseCategory.setShopping(Double.valueOf(entry.getValue()));
			}

			if ("sports".equals(entry.getKey())) {
				expenseCategory.setSports(Double.valueOf(entry.getValue()));
			}

			if ("travel".equals(entry.getKey())) {
				expenseCategory.setTravel(Double.valueOf(entry.getValue()));
			}

			if ("bills".equals(entry.getKey())) {
				expenseCategory.setBills(Double.valueOf(entry.getValue()));
			}

			if ("cash".equals(entry.getKey())) {
				expenseCategory.setCash(Double.valueOf(entry.getValue()));
			}

		}

		//expenseCategoryDao.save(expenseCategory);

	}


	@Override
	public void addNewCategory(CategoryListRequest categoryListRequest) {
		
		UserDetails userdetailsInDB = userDetailsDao.findByPhoneNumber(categoryListRequest.getPhoneNumber());
		CategoryList categoryList = categoryListDao.findByUserDetailsId(userdetailsInDB.getId());
		
		List<String> categoryFromDb = categoryList.getCategory();
		
		List<String> oldCategoryList = new ArrayList<>(categoryFromDb);
		oldCategoryList.add(categoryListRequest.getCategory());
		
		categoryList.setCategory(oldCategoryList);
		categoryListDao.save(categoryList);
	}


}
