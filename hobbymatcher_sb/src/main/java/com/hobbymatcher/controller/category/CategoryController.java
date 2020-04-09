package com.hobbymatcher.controller.category;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hobbymatcher.entity.Category;
import com.hobbymatcher.service.CategoryService;

@CrossOrigin
@SessionAttributes()
@Controller
@RequestMapping("/category")
public class CategoryController {

	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@RequestMapping(value = "/listcategory", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listCategory(HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			resp.put("list", categoryService.listCategory());
			response.setStatus(200);
		} catch (Exception exp) {
			exp.printStackTrace();
			resp.put("success", false);
			response.setStatus(400);
		}
		return resp;
	}

	// getcategory
	@RequestMapping(value = "/getcategory", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getCategory(String id, HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			Category category = categoryService.findCategoryById(Integer.parseInt(id));

			if (category == null) {
				response.setStatus(404);
			} else {
				resp.put("category", category);
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

}