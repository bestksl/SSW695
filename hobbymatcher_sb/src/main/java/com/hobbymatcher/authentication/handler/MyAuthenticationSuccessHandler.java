//package com.hobbymatcher.authentication.handler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.hobbymatcher.entity.RespBean;
//import com.hobbymatcher.service.UserService;
//import org.apache.commons.collections.MapUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
//import org.springframework.security.oauth2.provider.*;
//import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//
//@Component
//public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//
//	@Autowired
//	ClientDetailsService clientDetailsService;
//
//	@Autowired
//	AuthorizationServerTokenServices defaultAuthorizationServerTokenServices;
//	@Autowired
//	UserService userService;
//
//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication)
//			throws IOException, ServletException {
//		String header = req.getHeader("Authorization");
//		if (header == null || !header.toLowerCase().startsWith("basic ")) {
//			throw new UnapprovedClientAuthenticationException("no \"Basic\" header in the request");
//		}
//		String[] tokens = extractAndDecodeHeader(header, req);
//
//		assert tokens.length == 2;
//
//		String clientId = tokens[0];
//		String clientSecret = tokens[1];
//		ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
//
//		if (clientDetails == null) {
//			throw new UnapprovedClientAuthenticationException("no such client id");
//		} else if (!StringUtils.equals(clientDetailsService.loadClientByClientId(clientId).getClientSecret(),
//				clientSecret)) {
//			throw new UnapprovedClientAuthenticationException("client secret is wrong");
//		}
//		TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "custom");
//		OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
//		OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
//		OAuth2AccessToken oAuth2AccessToken = defaultAuthorizationServerTokenServices
//				.createAccessToken(oAuth2Authentication);
//
//		RespBean ok = RespBean.ok("login success！", authentication.getPrincipal());
//		ok.setObj(oAuth2AccessToken);
//		resp.setContentType("application/json;charset=utf-8");
//		PrintWriter out = resp.getWriter();
//		out.write(new ObjectMapper().writeValueAsString(ok));
//		out.flush();
//		out.close();
//	}
//
//	private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {
//		byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
//
//		byte[] decoded;
//		try {
//			decoded = Base64.getDecoder().decode(base64Token);
//		} catch (IllegalArgumentException var7) {
//			var7.printStackTrace();
//			throw new BadCredentialsException("Failed to decode basic authentication token");
//		}
//
//		String token = new String(decoded, StandardCharsets.UTF_8);
//		int delim = token.indexOf(":");
//		if (delim == -1) {
//			throw new BadCredentialsException("Invalid basic authentication token");
//		} else {
//			return new String[] { token.substring(0, delim), token.substring(delim + 1) };
//		}
//	}
//}
