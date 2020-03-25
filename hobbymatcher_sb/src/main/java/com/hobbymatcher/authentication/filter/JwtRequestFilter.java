package com.hobbymatcher.authentication.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hobbymatcher.authentication.service.JwtUtilService;
import com.hobbymatcher.service.UserService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtilService jwtUtilService;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws ServletException, IOException {
		String authHeader = req.getHeader("Authorization");

		String username = null;
		String jwtToken = null;

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			jwtToken = authHeader.substring(7); // remove the 'Bearer '
			username = jwtUtilService.extractUsername(jwtToken);
		}

		SecurityContext context = SecurityContextHolder.getContext();
		if (username != null && context.getAuthentication() == null) {
			UserDetails details = userService.loadUserByUsername(username);

			if (jwtUtilService.validateToken(jwtToken, details)) {
				UsernamePasswordAuthenticationToken userpassAuthToken = //
						new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
				userpassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				context.setAuthentication(userpassAuthToken);
			}
		}

		chain.doFilter(req, resp);
	}
}
