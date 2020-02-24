package com.hobbymatcher.authentication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hobbymatcher.entity.RespBean;

public class MyLoginUrlAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse resp, AuthenticationException authException)
			throws IOException, ServletException {
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		RespBean respBean = RespBean.error("Please login!");
		if (authException instanceof InsufficientAuthenticationException) {
			respBean.setMsg("Please login!");
		}
		resp.setStatus(403);
		respBean.setStatus(403);
		out.write(new ObjectMapper().writeValueAsString(respBean));
		out.flush();
		out.close();
	}

}
