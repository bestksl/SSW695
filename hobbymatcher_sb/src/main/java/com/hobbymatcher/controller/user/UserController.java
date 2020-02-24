package com.hobbymatcher.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

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

	// list user
	@RequestMapping(value = "/", method = RequestMethod.GET)
	private Map<String, Object> listUser(HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<User> list;
		try {
			list = userService.getUserList();
			modelMap.put("list", list);
			modelMap.put("msg", "return list success");
			response.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("msg", e.toString());
			response.setStatus(422);
		}
		return modelMap;
	}

	// login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> login(@RequestBody User user, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (user == null) {
			modelMap.put("status", false);
			modelMap.put("msg", "user object is null, login failed");
			return modelMap;
		}
		user = userService.loadUserByUsername(user.getEmail());
		request.getSession().setAttribute("user", user);
		Boolean result = userService.login(user.getEmail(), user.getPassword());
		modelMap.put("status", result);
		if (result) {
			user.setPassword(null);
			modelMap.put("user", user);
		}
		modelMap.put("msg", result ? "login success" : "login failed");
		response.setStatus(result ? 200 : 401);
		return modelMap;
	}

	// logout
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> loginOut(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		request.getSession().invalidate();
		modelMap.put("status", true);
		modelMap.put("msg", "logout success");
		response.setStatus(200);
		return modelMap;
	}

	// register
	@PostMapping("/")
	public Map<String, Object> add(@RequestBody User user, HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (user != null) {
			Boolean result = userService.register(user);
			modelMap.put("status", result);
			modelMap.put("msg", result ? "add success" : "add failed");
			response.setStatus(result ? 200 : 400);
		} else {
			modelMap.put("status", false);
			modelMap.put("msg", "user object is null, add failed");
			response.setStatus(400);
		}
		return modelMap;
	}

	// delete user
	@DeleteMapping("/{id}")
	public Map<String, Object> deleteUser(@PathVariable String id, HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		User user1 = userService.findUserById(id);
		if (user1 != null) {
			Boolean result = userService.deleteUser(user1.getId());
			modelMap.put("status", result);
			modelMap.put("msg", result ? "delete success" : "delete failed");
			response.setStatus(result ? 200 : 400);
			return modelMap;
		}
		modelMap.put("status", false);
		modelMap.put("msg", "user object is null, delete failed");
		response.setStatus(400);
		return modelMap;
	}

	// update user
	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUser(@RequestBody User user, HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (user != null) {
			Boolean result = userService.updateUser(user);
			modelMap.put("status", result);
			modelMap.put("msg", result ? "update success" : "update failed");
			response.setStatus(result ? 200 : 400);
			return modelMap;
		} else {
			modelMap.put("status", false);
			modelMap.put("msg", "user object is null, update failed");
			response.setStatus(400);
			return modelMap;
		}

	}

	@GetMapping("/{id}")
	public Map<String, Object> getUser(@PathVariable String id, HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		User user = userService.findUserById(id);
		if (user != null) {
			modelMap.put("status", true);
			modelMap.put("msg", "success");
			modelMap.put("user", user);
			response.setStatus(200);
			return modelMap;
		} else {
			modelMap.put("status", false);
			modelMap.put("msg", "no such user");
			response.setStatus(400);
			return modelMap;
		}
	}
}
