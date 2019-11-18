package com.expense.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.entity.CategoryList;
import com.expense.entity.UserDetails;

@Repository
public interface CategoryListDao extends JpaRepository<CategoryList, Long>{
	
	public CategoryList findByUserDetailsId(Long id);

}
