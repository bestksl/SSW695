package com.hobbymatcher.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.hobbymatcher.authentication.service.JwtUtilService;
import com.hobbymatcher.entity.PasswordUpdate;
import com.hobbymatcher.entity.User;
import com.hobbymatcher.service.BlogService;
import com.hobbymatcher.service.EventService;
import com.hobbymatcher.service.HobbyService;
import com.hobbymatcher.service.UserService;
import com.hobbymatcher.service.impl.AuthUtilService;
import com.hobbymatcher.util.FileUtil;

@CrossOrigin
@SessionAttributes()
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthUtilService authUtilService;
	@Autowired
	private JwtUtilService jwtUtilService;

	@Autowired
	private HobbyService hobbyService;
	@Autowired
	private EventService eventService;
	@Autowired
	private BlogService blogService;

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
		User user = userService.findUserById(Integer.valueOf(id));
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
		User user = userService.findUserById(Integer.valueOf(id));
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

	@RequestMapping(value = "/general-info", method = RequestMethod.GET)
	public Map<String, Object> getGeneralInformation(HttpServletRequest req, HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			User user = authUtilService.getUser(req);
			resp.put("username", user.getUsername());
			resp.put("firstName", user.getFirstName());
			resp.put("lastName", user.getLastName());
			resp.put("nickName", user.getNickName());
			resp.put("dateOfBirth", user.getDateOfBirth());
			resp.put("gender", user.getGender());
			resp.put("photoId", user.getPhotoId());

			resp.put("success", true);
			response.setStatus(200);
		} catch (Exception exp) {
			exp.printStackTrace();
			resp.put("success", false);
			response.setStatus(400);
		}
		return resp;
	}

	// update user
	@ResponseBody
	@PutMapping("/general-info")
	public Map<String, Object> updateGeneralInformation(HttpServletRequest req, HttpServletResponse response, //
			@ModelAttribute User user, @RequestPart(value = "file", required = false) MultipartFile imageFile) {
		Map<String, Object> resp = new HashMap<String, Object>();
		if (user != null) {
			user.setId(authUtilService.getUserId(req));
			if (imageFile != null && !imageFile.isEmpty()) {
				user.setPhotoId(FileUtil.transferFile(imageFile));
			}
			Boolean updated = userService.updateUser(user);
			resp.put("status", updated);
			response.setStatus(updated ? 200 : 400);
		} else {
			resp.put("status", false);
			response.setStatus(400);
		}
		return resp;
	}

	@RequestMapping(value = "/hobbies", method = RequestMethod.GET)
	public Map<String, Object> getHobbies(HttpServletRequest req, HttpServletResponse response, //
			@PathParam("load") String load) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			int userId = authUtilService.getUserId(req);
			if ("created".equals(load)) {
				resp.put("list", hobbyService.listHobbyByCreatedById(userId));
			} else if ("followed".equals(load)) {
				resp.put("list", hobbyService.listHobbyByFollowingUserId(userId));
			}
			resp.put("success", true);
			response.setStatus(200);
		} catch (Exception exp) {
			exp.printStackTrace();
			resp.put("success", false);
			response.setStatus(400);
		}
		return resp;
	}

	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public Map<String, Object> getEvents(HttpServletRequest req, HttpServletResponse response, //
			@PathParam("load") String load) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			int userId = authUtilService.getUserId(req);
			if ("past".equals(load)) {
				// past joined events
				resp.put("list", eventService.listPastJoinEvents(userId));
			} else if ("coming".equals(load)) {
				// joined and happening in future
				resp.put("list", eventService.listJoinedFutureEvents(userId));
			} else if ("hold".equals(load)) {
				// past created events
				resp.put("list", eventService.listPastHoldEvents(userId));
			}
			resp.put("success", true);
			response.setStatus(200);
		} catch (Exception exp) {
			exp.printStackTrace();
			resp.put("success", false);
			response.setStatus(400);
		}
		return resp;
	}

	@RequestMapping(value = "/blogs", method = RequestMethod.GET)
	public Map<String, Object> getBlogs(HttpServletRequest req, HttpServletResponse response, //
			@PathParam("load") String load) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			// TODO filter by load => 'mine', 'commented'
			int userId = authUtilService.getUserId(req);
			resp.put("list", blogService.listBlogByUserId(userId));
			resp.put("success", true);
			response.setStatus(200);
		} catch (Exception exp) {
			exp.printStackTrace();
			resp.put("success", false);
			response.setStatus(400);
		}
		return resp;
	}

	@RequestMapping(value = "/email/{newEmail}", method = RequestMethod.PUT)
	public Map<String, Object> updateEmail(HttpServletRequest req, HttpServletResponse response, //
			@PathVariable("newEmail") String newEmail) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			User user = authUtilService.getUser(req);

			user.setEmail(newEmail);
			userService.updateEmail(user.getId(), newEmail);

			// refresh jwt token since email is used for authentication
			resp.put("jwtToken", jwtUtilService.generateToken(user));

			// update spring auth with new user email
			UsernamePasswordAuthenticationToken userpassAuthToken = //
					new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			userpassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
			SecurityContextHolder.getContext().setAuthentication(userpassAuthToken);

			resp.put("success", true);
			response.setStatus(200);
		} catch (Exception exp) {
			exp.printStackTrace();
			resp.put("success", false);
			response.setStatus(400);
		}
		return resp;
	}

	@RequestMapping(value = "/password", method = RequestMethod.PUT)
	public Map<String, Object> updatePassword(HttpServletRequest req, HttpServletResponse response, //
			@RequestBody PasswordUpdate password) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			int userId = authUtilService.getUserId(req);

			// TODO - validate password.getCurrent()
			// TODO - validate password.getNewone() == password.getConfirmed()

			userService.updatePassword(userId, password.getNewPassword());

			resp.put("success", true);
			response.setStatus(200);
		} catch (Exception exp) {
			exp.printStackTrace();
			resp.put("success", false);
			response.setStatus(400);
		}
		return resp;
	}
}
