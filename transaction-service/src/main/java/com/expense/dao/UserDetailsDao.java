package com.expense.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.entity.UserDetails;

@Repository
public interface UserDetailsDao extends JpaRepository<UserDetails, Long>{
	
	//boolean checkIsUserPresent(LoginForm loginForm);
	
	public UserDetails findByFirstName(String firstName);
	
	
	public UserDetails findByPhoneNumber(String phoneNumber);
	
	

}
