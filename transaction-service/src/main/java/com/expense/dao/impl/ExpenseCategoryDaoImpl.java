package com.expense.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.expense.dao.ExpenseCategoryDao;
import com.expense.entity.ExpenseCategory;

@Repository
public class ExpenseCategoryDaoImpl implements ExpenseCategoryDao<ExpenseCategory>{

	@Autowired
	private EntityManager entityManager;

	
	@Override
	@Transactional
	public void save(ExpenseCategory expenseCategory) {
		entityManager.persist(expenseCategory);
		
	}


	

}
