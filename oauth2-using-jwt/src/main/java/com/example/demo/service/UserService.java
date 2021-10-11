package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.model.Customer;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	CustomerDAO customerDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Customer customer = null;
		try {
			customer = customerDAO.getUserDetails(username);
			return buildUserForAuthentication(customer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

	}
	
	 private org.springframework.security.core.userdetails.User buildUserForAuthentication(Customer user) {

	        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ADMIN");
	        List<GrantedAuthority> authorities = Arrays.asList(grantedAuthority);

	        return new org.springframework.security.core.userdetails.User(
	                user.getUsername(),
	                user.getPassword(),
	                true,
	                true,
	                true,
	                true,
	                authorities);
	    }

}
