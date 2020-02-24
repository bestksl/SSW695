package com.hobbymatcher.authentication.Handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hobbymatcher.entity.RespBean;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication)
			throws IOException, ServletException {
		RespBean ok = RespBean.ok("login success！", authentication.getPrincipal());
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write(new ObjectMapper().writeValueAsString(ok));
		out.flush();
		out.close();
	}
}
