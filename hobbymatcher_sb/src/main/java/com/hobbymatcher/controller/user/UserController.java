package com.hobbymatcher.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hobbymatcher.authentication.service.JwtUtilService;
import com.hobbymatcher.entity.User;
import com.hobbymatcher.service.UserService;

@CrossOrigin
@SessionAttributes()
@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// register
	@PostMapping("/")
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

	// list user
	@GetMapping("/")
	private Map<String, Object> listUser(HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			resp.put("list", userService.listUser());
			resp.put("success", true);
			response.setStatus(200);
		} catch (Exception exp) {
			exp.printStackTrace();
			resp.put("success", false);
			response.setStatus(400);
		}
		return resp;
	}

	// login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> login(@RequestBody User user, //
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		if (user == null) {
			resp.put("status", false);
		} else {
			user = userService.findUserByEmail(user.getEmailAddr());

			if (userService.login(user.getEmailAddr(), user.getPassword())) {
				user.setPassword(null);

				request.getSession().setAttribute("user", user);

				resp.put("user", user);
				resp.put("status", true);
				response.setStatus(200);
			} else {
				resp.put("status", false);
				response.setStatus(401);
			}
		}
		return resp;
	}

	// logout
	@PostMapping("/logout")
	@ResponseBody
	private Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		request.getSession().invalidate();
		resp.put("status", true);
		response.setStatus(200);
		return resp;
	}

	@GetMapping("/{id}")
	public Map<String, Object> getUser(@PathVariable String id, HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		User user = userService.findUserById(id);
		if (user != null) {
			resp.put("status", true);
			resp.put("user", user);
			response.setStatus(200);
		} else {
			resp.put("status", false);
			response.setStatus(400);
		}
		return resp;
	}

	// update user
	@PostMapping("/updateuser")
	@ResponseBody
	public Map<String, Object> updateUser(@RequestBody User user, HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		if (user != null) {
			Boolean updated = userService.updateUser(user);
			resp.put("status", updated);
			response.setStatus(updated ? 200 : 400);
		} else {
			resp.put("status", false);
			response.setStatus(400);
		}
		return resp;
	}

	// delete user
	@DeleteMapping("/{id}")
	public Map<String, Object> deleteUser(@PathVariable String id, HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		User user = userService.findUserById(id);
		if (user != null) {
			Boolean result = userService.deleteUser(user.getId());
			resp.put("status", result);
			response.setStatus(result ? 200 : 400);
		} else {
			resp.put("status", false);
			response.setStatus(400);
		}
		return resp;
	}

	// -----------------------
	// ----- jwt example -----
	// -----------------------

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtUtilService jwtUtilService;

	@PostMapping("/auth")
	public ResponseEntity<?> auth( //
			@RequestParam("username") String username, @RequestParam("password") String password) {
		Map<String, String> resp = new HashMap<>();
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			resp.put("jwt", jwtUtilService.generateToken(userService.findUserByEmail(username)));
			return ResponseEntity.ok(resp);
		} catch (BadCredentialsException exp) {
			resp.put("message", "Incorrect username or password.");
			return ResponseEntity.status(401).body(resp);
		}
	}
}
