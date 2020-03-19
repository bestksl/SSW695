package com.hobbymatcher.config.server;

import com.hobbymatcher.authentication.filter.JwtRequestFilter;
//import com.hobbymatcher.authentication.entrypoint.MyLoginUrlAuthenticationEntryPoint;
//import com.hobbymatcher.authentication.handler.MyAccessDeniedHandler;
import com.hobbymatcher.service.UserService;

import io.swagger.annotations.Authorization;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RestController;

@EnableWebSecurity
public class ResourceSeverConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtRequestFilter jwtReqFilter;
	
//  --- replace passwordEncoder with this method
//	@Bean
//	public BCryptPasswordEncoder bcryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
    
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable()
    		.authorizeRequests().antMatchers("/user/auth").permitAll()
    		.anyRequest().authenticated()
    		.and().sessionManagement()
    		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	http.addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class);
//        http.formLogin()
//            .and()
//            .authorizeRequests()
//            .antMatchers(POST, "/login").permitAll()
//            .antMatchers(DELETE, "/user/*").hasAnyAuthority("DELETE_USER")
//            .antMatchers("/swagger-ui.html").permitAll()
//            .antMatchers("/webjars/**").permitAll()
//            .antMatchers("/v2/**").permitAll()
//            .antMatchers("/swagger-resources/**").permitAll()
//            .antMatchers("/oauth/token").permitAll()
//            .antMatchers("/oauth/authorize").permitAll()
//            .antMatchers("/**")
//            .fullyAuthenticated()
//            .anyRequest()
//            .authenticated()
//            .and()
//            .csrf()
//            .disable()
//            .exceptionHandling()
//            .accessDeniedHandler(new MyAccessDeniedHandler())
//            .authenticationEntryPoint(new MyLoginUrlAuthenticationEntryPoint());
    }
}
