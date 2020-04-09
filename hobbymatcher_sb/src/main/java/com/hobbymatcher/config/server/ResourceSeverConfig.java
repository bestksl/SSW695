package com.hobbymatcher.config.server;

import static org.springframework.http.HttpMethod.DELETE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import com.hobbymatcher.authentication.filter.JwtRequestFilter;
//import com.hobbymatcher.authentication.entrypoint.MyLoginUrlAuthenticationEntryPoint;
//import com.hobbymatcher.authentication.handler.MyAccessDeniedHandler;
import com.hobbymatcher.service.UserService;

@EnableWebSecurity
public class ResourceSeverConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtRequestFilter jwtReqFilter;

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/oauth/authorize").permitAll()
                .antMatchers(DELETE, "/user/*").hasAnyAuthority("super_user")
                // -- allowed apis --
                // -- auth
                .antMatchers("/register").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/handshake").permitAll()
                // -- files
                .antMatchers("/files/**").permitAll()
                // -- hobbies
                .antMatchers("/hobby/listhobby").permitAll()
                .antMatchers("/hobby/gethobby").permitAll()
                // -- events
                .antMatchers("/event/listevent").permitAll()
                .antMatchers("/event/getevent").permitAll()
                .antMatchers("/event/listRecentEvents").permitAll()

                .antMatchers("/review/**").permitAll()

                // ------------------
                .antMatchers("/**")
                .authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.logout()
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.ACCEPTED));

        http.addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
