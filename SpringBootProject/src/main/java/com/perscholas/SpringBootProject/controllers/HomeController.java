package com.perscholas.SpringBootProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.perscholas.SpringBootProject.dao.IUserRepo;
import com.perscholas.SpringBootProject.models.User;
import com.perscholas.SpringBootProject.security.SecurityUtils;
import com.perscholas.SpringBootProject.services.ProductServices;
import com.perscholas.SpringBootProject.services.UserServices;

@Controller
public class HomeController {
	
	@Autowired
	IUserRepo userrepo;
	
	@Autowired
	ProductServices productService;
	
	@Autowired
	UserServices userservice;
	
	@RequestMapping("/index")
	public String getIndex() {
		return "index";
	}
	
	@RequestMapping("/HomemadeIceCream")
	public String getHomemade() {
		return "HomemadeIceCream";
	}
	
	@RequestMapping("/catering")
	public String getCatering() {
		return "catering";
	}
	
	@RequestMapping("/about")
	public String getAbout() {
		return "about";
	}

	@RequestMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@RequestMapping("/accessdenied")
	public String getAccessDenied() {
		return "accessdenied";
	}

	
	@RequestMapping("/register")
	public String register(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}
	
    @RequestMapping("/shop")
    public String getShop(Model model) {
    	model.addAttribute("products", productService.findAll());
    	return "shop";
    }
	
	@RequestMapping("/registerprocessing")
	public String registerProcessing(@Valid @ModelAttribute("user") User user,final BindingResult bindingresult) {
		System.out.println(bindingresult.getErrorCount() + "Errors");
//		String regex = "^(.+)@(.+)$";
//		 
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(user.getEmail());
		
		if(userrepo.findByUsername(user.getUsername()).isPresent() || bindingresult.hasErrors()) {
			if(bindingresult.hasErrors()) {
				return "register";
			} else {
				FieldError field = new FieldError("Duplicate Username", "username", "Username already Exists");
				bindingresult.addError(field);
				System.out.println(bindingresult.getErrorCount() + "Errors");
				return "register";
			}

		}
//		else if(user.getUsername().length() < 6 || user.getUsername().length() > 14) {
//			FieldError field = new FieldError("Username wrong char size", "username", "");
//			bindingresult.addError(field);
//			return "register";
//		}
////		!matcher.matches() || 
//		else if(user.getEmail().length() < 8) {
//			FieldError field = new FieldError("Email too short", "email", "");
//			bindingresult.addError(field);
//			return "register";
//		}
////		else if(!matcher.matches()) {
////			FieldError field = new FieldError("Pattern Issue", "email", "");
////			bindingresult.addError(field);
////			return "register";
////		}
//		else if(user.getPassword().length() < 6) {
//			FieldError field = new FieldError("Password wrong char size", "username", "");
//			bindingresult.addError(field);
//			return "register";
//		}
		else if(!bindingresult.hasErrors()){
			
			userservice.save(user);
			
			return "redirect:/index";
			
		} else {
			return "register";
		}
		
	}
	
//	@RequestMapping(value="/register", method=RequestMethod.POST)
//	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
//		ModelAndView modelAndView = new ModelAndView();
//		// Check for the validations
//		if(bindingResult.hasErrors()) {
//			modelAndView.addObject("successMessage", "Please correct the errors in form!");
//			modelMap.addAttribute("bindingResult", bindingResult);
//		}
//		else if(userservice.UserExist(user)){
//			modelAndView.addObject("successMessage", "user already exists!");			
//		}
//		// we will save the user if, no binding errors
//		else {
//			userservice.save(user);
//			modelAndView.addObject("successMessage", "User is registered successfully!");
//		}
//		modelAndView.addObject("user", new User());
//		return modelAndView;
//	}
}

