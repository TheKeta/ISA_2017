package com.reservationapp.model;

import org.springframework.security.core.authority.AuthorityUtils;


public class CurrentUser extends org.springframework.security.core.userdetails.User {
	private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList(user.getUserType().getName()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }
}

