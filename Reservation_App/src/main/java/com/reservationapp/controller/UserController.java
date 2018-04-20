package com.reservationapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservationapp.model.CurrentUser;
import com.reservationapp.model.User;
import com.reservationapp.model.UserForm;
import com.reservationapp.service.impl.CurrentUserDetailsService;
import com.reservationapp.service.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private CurrentUserDetailsService currentUserDetailsService;
	
	@RequestMapping(value="/getUsers", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getUserRole", method = RequestMethod.GET)
	public int getUserRole() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		if(user!=null) {
			if(user.getUserType().getName().equals("ADMINFZ")) {
				return 3; //admin fun zone
			}
			else if(user.getUserType().getName().equals("ADMIN"))
				return 1; //admin
			else
				return 2; //obican
		}
		return 0; //nije ulogovan
	}
	
	@RequestMapping(value="/getCurrentUser", method = RequestMethod.GET)
	public  ResponseEntity<User> getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		User user = userService.findOneById(id);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
//	
//	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
//	public ResponseEntity<User> addUser(@RequestBody User user){
//		User newUser = userService.save(user);
//		return new ResponseEntity<>(newUser, HttpStatus.OK);
//	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<UserForm> updateUser(@RequestBody UserForm user){
		
		User userExists = userService.findOneByEmail(user.getEmail());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedUser = userService.findOneByEmail(auth.getName());
		
		if(userExists==null || loggedUser == null || !user.getEmail().equals(loggedUser.getEmail())){
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		if(!user.getCity().equals(""))
			userExists.setCity(user.getCity());
		
		if(!user.getFirstName().equals(""))
			userExists.setFirstName(user.getFirstName());
		
		if(!user.getLastName().equals(""))
			userExists.setLastName(user.getLastName());
		
		User newUser = userService.save(userExists);
		
		CurrentUser userDetails = currentUserDetailsService.loadUserByUsername(userExists.getEmail());
		
		auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		UserForm res = new UserForm(newUser);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/changePassword", method=RequestMethod.PUT, consumes="application/json")
	public boolean changePassword(@RequestBody UserForm user){
		
		User userExists = userService.findOneByEmail(user.getEmail());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedUser = userService.findOneByEmail(auth.getName());
		
		if(userExists==null || loggedUser == null || !user.getEmail().equals(loggedUser.getEmail())){
			return false;
		}
		
		if(!user.getOldPassword().equals(loggedUser.getPassword()) 
				|| !user.getPassword().equals(user.getRepeatedPassword())){
			return false;
		}
		
		
		userExists.setPassword(user.getPassword());
		
		User newUser = userService.save(userExists);
		CurrentUser userDetails = currentUserDetailsService.loadUserByUsername(newUser.getEmail());
		
		auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		return true;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> delete(@PathVariable Long id) {
		User deleted = userService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}
