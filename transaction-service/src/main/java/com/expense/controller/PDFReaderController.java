package com.expense.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cm.nci.pdf.PdfDetails;

import com.expense.request.CategoryListRequest;
import com.expense.request.DisplayDetailsRequest;
import com.expense.request.ExpenseCategory;
import com.expense.request.LoginForm;
import com.expense.request.SubmitExpenseRequest;
import com.expense.service.LoginService;
import com.expense.service.TransactionService;

@Controller
public class PDFReaderController {

	@Autowired
	TransactionService transactionService;

	@Autowired
	LoginService loginService;

	@RequestMapping("/details")
	public String readPdf(
			Model model,
			@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "phoneNumber", required = true) String phoneNumber) {
		List<PdfDetails> displayDetailsRequest = new ArrayList<PdfDetails>();
		List<String> categoryList = transactionService
				.getCategories(phoneNumber);
		try {
			displayDetailsRequest = transactionService.readPdfFile(file);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("displayDetailsRequest", displayDetailsRequest);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("phoneNumber", phoneNumber);
		return "details";
	}

	@RequestMapping(value = "/checkUser", method = RequestMethod.POST)
	public String confirm(
			@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model) {

		boolean userIsPresent = loginService.userIsPresent(loginForm);

		if (userIsPresent) {
			model.addAttribute("phoneNumber", loginForm.getPhoneNumber());
			return "fileUpload";
		} else {
			model.addAttribute("firstName", loginForm.getFirstName());
			model.addAttribute("lastName", loginForm.getLastName());
			model.addAttribute("phoneNumber", loginForm.getPhoneNumber());
			return "signup";
		}

	}

	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public String newUser(
			@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model) {

		// loginService.saveNewUser(loginForm);

		model.addAttribute("firstName", loginForm.getFirstName());
		model.addAttribute("lastName", loginForm.getLastName());
		model.addAttribute("phoneNumber", loginForm.getPhoneNumber());
		model.addAttribute("password", loginForm.getPassword());
		model.addAttribute("dateOfBirth", loginForm.getDateOfBirth());

		return "accountDetails";

	}

	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public String addAccount(
			@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model) {

		loginService.saveNewUser(loginForm);
		model.addAttribute("phoneNumber", loginForm.getPhoneNumber());

		return "fileUpload";

	}

	@RequestMapping(value = "/addOther", method = RequestMethod.POST)
	public String addOther(
			@RequestParam(value = "phoneNumber", required = true) String phoneNumber,
			Model model) {

		model.addAttribute("phoneNumber", phoneNumber);

		return "addCategory";

	}

	@RequestMapping(value = "/addNewCategory", method = RequestMethod.POST)
	public String addNewCategory(
			@ModelAttribute(name = "categoryListRequest") CategoryListRequest categoryListRequest ,
			Model model) {
		
		transactionService.addNewCategory(categoryListRequest);

		//model.addAttribute("phoneNumber", phoneNumber);

		return "addCategory";

	}

	@GetMapping("/submit")
	public String submit(HttpServletRequest request,
			SubmitExpenseRequest submitExpenseRequest) {
		submitExpenseRequest.setDate("2019.11.05");
		Map<String, String> details = new HashMap<String, String>();
		details.put("clothes", "2.00");
		submitExpenseRequest.setDetails(details);

		System.out.println(submitExpenseRequest);
		transactionService.submitDetails(submitExpenseRequest);
		return "submit";

	}

	// Login form
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	// Login form with error
	@GetMapping("/addOther")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "details";
	}

	/*
	 * @GetMapping("/submit")
	 * 
	 * @ResponseBody public void submit() {
	 * 
	 * SubmitExpenseRequest submitExpenseRequest = new SubmitExpenseRequest();
	 * submitExpenseRequest.setDate("2019.11.05"); Map<String, String> details =
	 * new HashMap<String, String>(); details.put("clothes", "2.00");
	 * submitExpenseRequest.setDetails(details);
	 * 
	 * System.out.println(submitExpenseRequest);
	 * transactionService.submitDetails(submitExpenseRequest);
	 * 
	 * }
	 */

}
