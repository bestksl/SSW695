package com.hobbymatcher.controller.hobby;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.hobbymatcher.authentication.service.JwtUtilService;
import com.hobbymatcher.entity.Hobby;
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
    private Map<String, Object> listHobby(HttpServletResponse response) {
        Map<String, Object> resp = new HashMap<String, Object>();
        try {
            resp.put("list", hobbyService.listHobby());
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
			@ModelAttribute Hobby hobby, @RequestPart("file") MultipartFile imageFile) {
		Map<String, Object> resp = new HashMap<String, Object>();
		if (hobby != null) {
			if (!imageFile.isEmpty()) {
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

//	// deletehobby
//	@RequestMapping(value = "/deletehobby", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> deleteHobby(String id, HttpServletResponse response) {
//		Map<String, Object> resp = new HashMap<String, Object>();
//		try {
//			Boolean deleted = hobbyService.deleteHobby(Integer.parseInt(id));
//			if (deleted) {
//				resp.put("success", true);
//				response.setStatus(200);
//			} else {
//				resp.put("success", false);
//				response.setStatus(400);
//			}
//		} catch (Exception exp) {
//			exp.printStackTrace();
//			resp.put("success", false);
//			response.setStatus(400);
//		}
//		return resp;
//	}
}