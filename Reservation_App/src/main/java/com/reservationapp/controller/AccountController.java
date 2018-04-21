package com.reservationapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.reservationapp.model.User;
import com.reservationapp.model.UserCreateForm;
import com.reservationapp.model.UserForm;
import com.reservationapp.service.UserService;
import com.reservationapp.service.UserTypeService;
import com.reservationapp.service.impl.CurrentUserDetailsService;

@Controller
public class AccountController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserTypeService userTypeService;
	
	@Autowired
	private CurrentUserDetailsService currentUserDetailsService;
	
	
	@RequestMapping(value="/userProfile", method = RequestMethod.GET)
	public ModelAndView userProfile(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		UserForm userForm = new UserForm(user);
		
		modelAndView.addObject("user", userForm);
		modelAndView.addObject("welcomeMessage", "Welcome " + userForm.getFirstName());
		modelAndView.addObject("message", "");
		
		modelAndView.setViewName("userProfile");
		return modelAndView;
		}
	
//	@RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
//	public ModelAndView updateUser(@Valid UserForm user, BindingResult bindingResult, HttpServletRequest request){
//		ModelAndView modelAndView = new ModelAndView();
//		User userExists = userService.findOneByEmail(user.getEmail());
//		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		User loggedUser = userService.findOneByEmail(auth.getName());
//		
//		if(userExists==null || loggedUser == null || !user.getEmail().equals(loggedUser.getEmail())){
//			modelAndView.setViewName("redirect:login");
//		}else{
//			userExists.setCity(user.getCity());
//			userExists.setFirstName(user.getFirstName());
//			userExists.setLastName(user.getLastName());
//			userService.save(userExists);
//			modelAndView.setViewName("redirect:userProfile");
//		}
//		
//		return modelAndView;
//	}
}
