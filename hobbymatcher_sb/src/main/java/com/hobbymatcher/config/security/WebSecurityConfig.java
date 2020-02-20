package com.hobbymatcher.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hobbymatcher.authentication.Filter.CustomAuthenticationFilter;
import com.hobbymatcher.authentication.Handler.MyAccessDeniedHandler;
import com.hobbymatcher.authentication.Handler.MyAuthenticationFailureHandler;
import com.hobbymatcher.authentication.Handler.MyAuthenticationSuccessHandler;
import com.hobbymatcher.entity.RespBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder bPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginProcessingUrl("/doLogin")
                .successHandler(new MyAuthenticationSuccessHandler())
                .failureHandler(new MyAuthenticationFailureHandler())
                .and()
                .authorizeRequests()
                //.antMatchers("/**").permitAll()
                // .antMatchers(HttpMethod.DELETE,"/user/*").hasAnyAuthority("DELETE_USER")
                .antMatchers("/aaa").hasAnyAuthority("USER_DDD")
                .antMatchers("/**")
                .fullyAuthenticated()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler(new MyAccessDeniedHandler());

    }

    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                RespBean respBean = RespBean.ok("登录成功!");
                out.write(new ObjectMapper().writeValueAsString(respBean));
                out.flush();
                out.close();
            }
        });
        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                RespBean respBean = RespBean.error("登录失败!");
                out.write(new ObjectMapper().writeValueAsString(respBean));
                out.flush();
                out.close();
            }
        });
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

}
