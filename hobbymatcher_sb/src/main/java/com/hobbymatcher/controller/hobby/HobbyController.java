package com.hobbymatcher.controller.hobby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.hobbymatcher.authentication.service.JwtUtilService;
import com.hobbymatcher.entity.Followship;
import com.hobbymatcher.entity.Hobby;
import com.hobbymatcher.entity.Participation;
import com.hobbymatcher.service.HobbyService;
import com.hobbymatcher.service.impl.AuthUtilService;
import com.hobbymatcher.util.FileUtil;

@CrossOrigin
@SessionAttributes()
@RestController
@RequestMapping("/hobby")
public class HobbyController {

	@Autowired
	private HobbyService hobbyService;

	@Autowired
	private AuthUtilService authUtilService;

	@GetMapping("/listhobby")
	private Map<String, Object> listHobby(HttpServletResponse response, //
			@RequestParam(name = "offset", required = false, defaultValue = "0") Integer offset,
			@RequestParam(name = "perpage", required = false, defaultValue = "10") Integer perpage,
			@RequestParam(name = "categories", required = false, defaultValue = "") String categories) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			List<String> tmp = new ArrayList<>();
			for (String categoryId : categories.split(",")) {
				if (!categoryId.trim().isEmpty()) {
					tmp.add(categoryId);
				}
			}
			String[] categoryIds = tmp.toArray(new String[] {});

			resp.put("count", hobbyService.listHobby(true, 0, Integer.MAX_VALUE, categoryIds).get(0).getId());
			resp.put("list", hobbyService.listHobby(false, offset, perpage, categoryIds));
			resp.put("success", true);
			response.setStatus(200);
		} catch (Exception exp) {
			exp.printStackTrace();
			resp.put("success", false);
			response.setStatus(400);
		}
		return resp;
	}

	// addhobby
	@RequestMapping(value = "/addhobby", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addHobby(HttpServletRequest req, HttpServletResponse response, //
			@ModelAttribute Hobby hobby, @RequestPart(name = "file", required = true) MultipartFile imageFile) {
		Map<String, Object> resp = new HashMap<String, Object>();
		if (hobby != null) {
			hobby.setPhotoId(FileUtil.transferFile(imageFile));
			hobby.setCreatedById(authUtilService.getUserId(req));
			Boolean inserted = hobbyService.insertHobby(hobby);
			resp.put("success", inserted);
			response.setStatus(inserted ? 200 : 400);
			return resp;
		} else {
			resp.put("success", false);
			response.setStatus(400);
			return resp;
		}
	}

	// gethobby
	@GetMapping("/gethobby")
	public Map<String, Object> getHobby(String id, HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			Hobby hobby = hobbyService.findHobbyById(Integer.parseInt(id));

			if (hobby == null) {
				response.setStatus(404);
			} else {
				resp.put("hobby", hobby);
				resp.put("success", true);
				response.setStatus(200);
			}
		} catch (Exception exp) {
			exp.printStackTrace();
			resp.put("success", false);
			response.setStatus(400);
		}
		return resp;
	}

	// edithobby
	@RequestMapping(value = "/edithobby", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editHobby(HttpServletResponse response, //
			@ModelAttribute Hobby hobby, @RequestPart(name = "file", required = false) MultipartFile imageFile) {
		Map<String, Object> resp = new HashMap<String, Object>();
		if (hobby != null) {
			if (imageFile != null && !imageFile.isEmpty()) {
				hobby.setPhotoId(FileUtil.transferFile(imageFile));
			}
			Boolean inserted = hobbyService.updateHobby(hobby);
			resp.put("success", inserted);
			response.setStatus(inserted ? 200 : 400);
			return resp;
		} else {
			resp.put("success", false);
			response.setStatus(400);
			return resp;
		}
	}

	@GetMapping("/getfollowship")
	public Map<String, Object> getFollowship(HttpServletRequest request, HttpServletResponse response, //
			@RequestParam("id") Integer hobbyId) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			int userId = authUtilService.getUserId(request);
			Followship follow = hobbyService.getFollowship(userId, hobbyId);
			if (follow == null) {
				resp.put("status", "");
			} else {
				resp.put("status", "followed");
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

	@PostMapping("/followship")
	public Map<String, Object> manageFollowship(HttpServletRequest request, HttpServletResponse response, //
			@RequestParam("id") Integer hobbyId, @RequestParam("action") String action) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			int userId = authUtilService.getUserId(request);
			Followship follow = hobbyService.getFollowship(userId, hobbyId);

			if ("follow".equals(action)) {
				if (follow == null) {
					hobbyService.followHobby(userId, hobbyId);
				}
				resp.put("status", "followed");
			} else if ("cancel".equals(action)) {
				if (follow != null) {
					hobbyService.unfollowHobby(userId, hobbyId);
				}
				resp.put("status", "");
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
}