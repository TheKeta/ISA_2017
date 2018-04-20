package com.reservationapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reservationapp.model.CurrentUser;
import com.reservationapp.model.User;
import com.reservationapp.service.UserService;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userService.findOneByEmail(email);
		if(user==null)
			return null;
		return new CurrentUser(user);
	}
}
