package com.hobbymatcher.authentication.Handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hobbymatcher.entity.RespBean;

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e)
			throws IOException, ServletException {
		RespBean error = RespBean.error("login failed!");
		resp.setContentType("application/json;charset=utf-8");
		resp.setStatus(403);
		PrintWriter out = resp.getWriter();
		out.write(new ObjectMapper().writeValueAsString(error));
		out.flush();
		out.close();
	}
}
