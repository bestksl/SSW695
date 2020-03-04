package com.hobbymatcher.config.server;

//@RestController
//@Configuration
//@EnableWebSecurity
//
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
////    @Autowired
////    UserService userService;
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//
//    }
//
//}

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                //.loginProcessingUrl("/doLogin")
//                .successHandler(new MyAuthenticationSuccessHandler())
//                .failureHandler(new MyAuthenticationFailureHandler())
//                .and()
//                .authorizeRequests()
//                //.antMatchers("/**").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/user/*").hasAnyAuthority("DELETE_USER")
//                .antMatchers("/swagger-ui.html").permitAll()
//                .antMatchers("/webjars/**").permitAll()
//                .antMatchers("/v2/**").permitAll()
//                .antMatchers("/swagger-resources/**").permitAll()
//                .antMatchers("/**")
//                .fullyAuthenticated()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .csrf()
//                .disable()
//                .exceptionHandling()
//                .accessDeniedHandler(new MyAccessDeniedHandler())
//                .authenticationEntryPoint(new MyLoginUrlAuthenticationEntryPoint());
//        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//    }


//    @Bean
//    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
//        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
//        filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
//            @Override
//            public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
//                resp.setContentType("application/json;charset=utf-8");
//                PrintWriter out = resp.getWriter();
//                RespBean respBean = RespBean.ok("login success!");
//                respBean.setObj(userService.loadUserByUsername(authentication.getName()));
//                out.write(new ObjectMapper().writeValueAsString(respBean));
//                out.flush();
//                out.close();
//            }
//        });
//        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
//            @Override
//            public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
//                resp.setContentType("application/json;charset=utf-8");
//                PrintWriter out = resp.getWriter();
//                RespBean respBean = RespBean.error("login failed!");
//                respBean.setStatus(403);
//                resp.setStatus(403);
//                out.write(new ObjectMapper().writeValueAsString(respBean));
//                out.flush();
//                out.close();
//            }
//        });
//        filter.setAuthenticationManager(authenticationManagerBean());
//        return filter;
//    }


