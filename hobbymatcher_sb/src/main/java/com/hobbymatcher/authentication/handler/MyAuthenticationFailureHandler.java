//package com.hobbymatcher.authentication.handler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.hobbymatcher.entity.RespBean;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@Component
//public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
//
//	@Override
//	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, //
//			AuthenticationException exp) throws IOException, ServletException {
//		RespBean error = RespBean.error("login failed!");
//		error.setStatus(403);
//		resp.setContentType("application/json;charset=utf-8");
//		resp.setStatus(403);
//		PrintWriter out = resp.getWriter();
//		out.write(new ObjectMapper().writeValueAsString(error));
//		out.flush();
//		out.close();
//	}
//}
