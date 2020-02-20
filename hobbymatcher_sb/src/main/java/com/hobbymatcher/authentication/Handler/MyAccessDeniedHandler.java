package com.hobbymatcher.authentication.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hobbymatcher.entity.RespBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyAccessDeniedHandler extends AccessDeniedHandlerImpl {


    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
        RespBean error = RespBean.error("Sorry you don't have this authentication，access denied");
        resp.setStatus(403);
        error.setStatus(403);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(new ObjectMapper().writeValueAsString(error));
        out.flush();
        out.close();
    }
}
