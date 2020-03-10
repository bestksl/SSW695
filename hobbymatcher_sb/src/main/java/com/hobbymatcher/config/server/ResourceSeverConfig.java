package com.hobbymatcher.config.server;

import com.hobbymatcher.authentication.entrypoint.MyLoginUrlAuthenticationEntryPoint;
import com.hobbymatcher.authentication.handler.MyAccessDeniedHandler;
import com.hobbymatcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
@EnableResourceServer
public class ResourceSeverConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Bean
    public BCryptPasswordEncoder bPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //.loginProcessingUrl("/doLogin")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/user/*").hasAnyAuthority("DELETE_USER")
//                .antMatchers("/swagger-ui.html").permitAll()
//                .antMatchers("/webjars/**").permitAll()
//                .antMatchers("/v2/**").permitAll()
//                .antMatchers("/swagger-resources/**").permitAll()
//                .antMatchers("/oauth/token").permitAll()
//                .antMatchers("/oauth/authorize").permitAll()
//                .antMatchers("/**")
//                .fullyAuthenticated()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .csrf()
//                .disable()
//                .exceptionHandling()
//                .accessDeniedHandler(new MyAccessDeniedHandler())
//                .authenticationEntryPoint(new MyLoginUrlAuthenticationEntryPoint())
                ;
        //http.addFilterAt(new CustomAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

//    @Bean
//    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
//        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
//        filter.setAuthenticationSuccessHandler((req, resp, authentication) -> {
//            resp.setContentType("application/json;charset=utf-8");
//            PrintWriter out = resp.getWriter();
//            RespBean respBean = RespBean.ok("login success!");
//            respBean.setObj(userService.loadUserByUsername(authentication.getName()));
//            out.write(new ObjectMapper().writeValueAsString(respBean));
//            out.flush();
//            out.close();
//        });
//        filter.setAuthenticationFailureHandler((req, resp, e) -> {
//            resp.setContentType("application/json;charset=utf-8");
//            PrintWriter out = resp.getWriter();
//            RespBean respBean = RespBean.error("login failed!");
//            respBean.setStatus(403);
//            resp.setStatus(403);
//            out.write(new ObjectMapper().writeValueAsString(respBean));
//            out.flush();
//            out.close();
//        });
//       // filter.setAuthenticationManager(authenticationManagerBean());
//        return filter;
//    }
}
