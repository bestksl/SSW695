package com.hobbymatcher.controller.auth;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hobbymatcher.authentication.service.JwtUtilService;
import com.hobbymatcher.entity.LoginUser;
import com.hobbymatcher.entity.User;
import com.hobbymatcher.service.UserService;

@CrossOrigin
@SessionAttributes()
@RestController
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtUtilService jwtUtilService;

	@GetMapping("/handshake")
	public Map<String, Object> handshake(HttpServletRequest request) {
		Map<String, Object> resp = new HashMap<>();

		Authentication auth = getContext().getAuthentication();

		if (auth instanceof AnonymousAuthenticationToken) {
			resp.put("isLogin", false);
		} else {
			resp.put("isLogin", true);

			User user = (User) auth.getPrincipal();

			// refreshed jwt token
			resp.put("jwtToken", jwtUtilService.generateToken(user));

			resp.put("userId", user.getId());
			resp.put("firstName", user.getFirstName());
			resp.put("lastName", user.getLastName());
			resp.put("nickName", user.getNickName());
			resp.put("email", user.getEmail());
			resp.put("photoId", user.getPhotoId());
		}

		return resp;
	}

	// register
	@PostMapping("/register")
	public Map<String, Object> register(@RequestBody User user, HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		if (user != null) {
			Boolean registered = userService.register(user);
			resp.put("status", registered);
			response.setStatus(registered ? 200 : 400);
		} else {
			resp.put("status", false);
			response.setStatus(400);
		}
		return resp;
	}

	// login
	@ResponseBody
	@PostMapping("/login")
	private ResponseEntity<?> login(@RequestBody LoginUser user) {
		Map<String, String> resp = new HashMap<>();
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
			resp.put("jwt", jwtUtilService.generateToken(userService.findUserByEmail(user.getEmail())));
			return ResponseEntity.ok(resp);
		} catch (BadCredentialsException exp) {
			resp.put("message", "Incorrect username or password.");
			return ResponseEntity.status(401).body(resp);
		}
	}

	// logout
	@ResponseBody
	@PostMapping("/logout")
	private Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		request.getSession().invalidate();
		resp.put("status", true);
		response.setStatus(200);
		return resp;
	}
}
