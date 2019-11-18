package com.expense.service;

import com.expense.request.LoginForm;

public interface LoginService {

	
	boolean userIsPresent(LoginForm loginRequest);
	void saveNewUser(LoginForm loginForm);
}
