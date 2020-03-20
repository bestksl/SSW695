package com.hobbymatcher.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@Autowired
	private UserService userService;

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
	@ResponseBody
	@PostMapping("/updateuser")
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
}
