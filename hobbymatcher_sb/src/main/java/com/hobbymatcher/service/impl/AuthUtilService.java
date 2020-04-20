package com.hobbymatcher.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hobbymatcher.authentication.service.JwtUtilService;
import com.hobbymatcher.entity.User;
import com.hobbymatcher.service.UserService;

@Service
public class AuthUtilService {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtilService jwtUtilService;

	public User getUser(HttpServletRequest req) {
		return userService.findUserByEmail(getEmail(req));
	}

	public String getEmail(HttpServletRequest req) {
		String authHeader = req.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			jwtToken = authHeader.substring(7); // remove the 'Bearer '
			username = jwtUtilService.extractUsername(jwtToken);
		}
		return username;
	}

	public Integer getUserId(HttpServletRequest req) {
		return getUser(req).getId();
	}
}
