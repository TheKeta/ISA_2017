package com.reservationapp.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.reservationapp.model.CurrentUser;
import com.reservationapp.model.User;
import com.reservationapp.model.UserCreateForm;
import com.reservationapp.model.UserForm;
import com.reservationapp.model.UserType;
import com.reservationapp.service.UserService;
import com.reservationapp.service.UserTypeService;
import com.reservationapp.service.impl.CurrentUserDetailsService;
import com.reservationapp.service.impl.EmailService;
import com.reservationapp.service.impl.MailSender;


@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserTypeService userTypeService;
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private CurrentUserDetailsService currentUserDetailsService;
	

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView loginUser(@Valid User user, BindingResult bindingResult, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findOneByEmail(user.getEmail());
		//System.out.println("VALJA: " + bCryptPasswordEncoder.matches(user.getPassword(), userExists.getPassword()));
		if (userExists == null || !user.getPassword().equals(userExists.getPassword())
				|| !userExists.isActive()) {
//			bindingResult
//					.rejectValue("email", "error.user",
//							"There is already a user registered with the email provided");
			modelAndView.addObject("error", "Wrong username or password");
			modelAndView.setViewName("login");
			return modelAndView;
		}
//		if (bindingResult.hasErrors()) {
//			modelAndView.setViewName("login");
//			return modelAndView;
//		}
		CurrentUser userDetails = currentUserDetailsService.loadUserByUsername(userExists.getEmail());
		
		Authentication auth = 
				  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		UserForm userForm = new UserForm(userDetails.getUser());
		
		modelAndView.addObject("user", userForm);
		modelAndView.addObject("welcomeMessage", "Welcome " + userForm.getFirstName());
		modelAndView.addObject("message", "");
		
		modelAndView.setViewName("userProfile");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		UserCreateForm user = new UserCreateForm();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid UserCreateForm user, BindingResult bindingResult, HttpServletRequest request) throws MailException, InterruptedException {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findOneByEmail(user.getEmail());
		if (userExists != null) {
			modelAndView.addObject("message", "Email already taken.");
			modelAndView.addObject("user", user);
			modelAndView.setViewName("registration");
			return modelAndView;
		}
		if(!user.getPassword().equals(user.getRepeatedPassword())){
			modelAndView.addObject("message", "Repeated password incorrect.");
			modelAndView.addObject("user", user);
			modelAndView.setViewName("registration");
			return modelAndView;
		}
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			
			UserType userType = userTypeService.findOneByName("VISITOR");
			User newUser = new User(user.getEmail(), user.getCity(), user.getPassword(),
					user.getFirstName(), user.getLastName(), userType);
			

			newUser.setUserType(userType);
			newUser.setToken(UUID.randomUUID().toString());
			newUser.setActive(false);
			//user.setUsername("stf");
			userService.save(newUser);
			
			String appUrl = request.getScheme() + "://" + request.getServerName();
			if(request.getServerName().equals("localhost"))
				appUrl+=":8080";
			String message = "To confirm your e-mail address, please click the link below:\n" + appUrl + "/confirm?token=" + newUser.getToken();
			mailSender.sendMail(newUser.getEmail(), message);
			modelAndView.addObject("successMessage", "Please confirm your registration via email.");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("login");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/confirm", method = RequestMethod.GET)
	public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {
			
		User user = userService.findOneByToken(token);
			
		if (user == null) { // No token found in DB
			modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
			modelAndView.setViewName("registration");
		} else { // Token found
			modelAndView.addObject("confirmationToken", user.getToken());
			user.setActive(true);
			userService.save(user);
			
//			UserDetails userDetails = userService.loadUser(user.getEmail());
//			Authentication auth = 
//					  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//			SecurityContextHolder.getContext().setAuthentication(auth);
			
			modelAndView.addObject("successMessage", "Registration successful.");
			modelAndView.setViewName("redirect:login");
		}
		return modelAndView;		
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());

		modelAndView.addObject("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
	

}

