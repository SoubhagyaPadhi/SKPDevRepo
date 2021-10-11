package com.example.demo.model;

import org.springframework.security.core.userdetails.User;

public class CustomerHelper extends User{

	private static final long serialVersionUID = 1L;
	   public CustomerHelper(Customer user) {
	      super(
	    		  user.getUsername(),
	    		  user.getPassword(),
	    		  user.getListOfgrantedAuthorities()
	    		);
	   }

}
