package com.hobbymatcher.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hobbymatcher.entity.RespBean;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {


    public MyLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse resp, AuthenticationException authException) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        RespBean respBean = RespBean.error("你无此权限 访问失败!");
        if (authException instanceof InsufficientAuthenticationException) {
            respBean.setMsg("请求失败，请联系管理员!");
        }
        out.write(new ObjectMapper().writeValueAsString(respBean));
        out.flush();
        out.close();
    }


}
