package com.hobbymatcher.controller.blog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbymatcher.entity.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hobbymatcher.entity.Blog;
import com.hobbymatcher.service.BlogService;
import com.hobbymatcher.service.ReviewService;
import com.hobbymatcher.service.impl.AuthUtilService;

//import com.sun.deploy.net.HttpResponse;

@Controller
@CrossOrigin
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private AuthUtilService authUtilService;

	// list Blog
	@RequestMapping(value = "/listblog", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listBlog(String id, HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			if (!"".equals(id) && id != null) {
				modelMap.put("list", blogService.listBlogByHobbyId(Integer.parseInt(id)));
				modelMap.put("msg", "list with hobby success");
				response.setStatus(200);
				return modelMap;
			} else {
				modelMap.put("list", blogService.listBlog());
				modelMap.put("msg", "list success");
				response.setStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("msg", e.getMessage());
			modelMap.put("status", false);
			response.setStatus(400);
		}
		return modelMap;
	}

	// getblog
	@RequestMapping(value = "/getblog", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getBlog(HttpServletRequest req, HttpServletResponse response, //
			@RequestParam("id") Integer id) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			modelMap.put("blog", blogService.findBlogById(id));
			response.setStatus(200);
			return modelMap;
		} catch (Exception exp) {
			exp.printStackTrace();
			modelMap.put("status", false);
			response.setStatus(400);
		}
		return modelMap;
	}

	// deleteBlog
	@RequestMapping(value = "/deleteblog", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteBlog(HttpServletRequest req, HttpServletResponse response, //
			@RequestParam("id") Integer id) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			Boolean result = blogService.deleteBlog(id);
			modelMap.put("status", result);
			modelMap.put("msg", result ? "delete success!" : "there is no blog object with id =" + id);
			response.setStatus(result ? 200 : 400);
		} catch (Exception e) {
			modelMap.put("msg", e.getStackTrace());
			modelMap.put("status", false);
			response.setStatus(400);
		}
		return modelMap;
	}

	// postblog
	@RequestMapping(value = "/postblog", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> postBlog(HttpServletRequest req, HttpServletResponse response, //
			@RequestBody Blog blog) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		blog.setByUserId(authUtilService.getUserId(req));
		Boolean result = blogService.addBlog(blog);
		modelMap.put("status", result);
		modelMap.put("msg", result ? "create success!" : "create failed maybe hobbyId or userId not exist");
		response.setStatus(result ? 200 : 400);
		return modelMap;
	}

	// addBlog
	@RequestMapping(value = "/addcomment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addcomment(@RequestBody Review review, HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (review != null) {
			Boolean result;
			try {
				result = reviewService.addReview(review);
			} catch (Exception e) {
				modelMap.put("status", false);
				modelMap.put("msg", "the foreign key not exist || the userId and blogId must can be parsed to int");
				return modelMap;
			}
			modelMap.put("status", result);
			modelMap.put("msg", result ? "add comment success!" : "create failed ");
			response.setStatus(result ? 200 : 400);
			return modelMap;
		} else {
			modelMap.put("status", false);
			response.setStatus(400);
			return modelMap;
		}
	}

//    //findBlogById
//    //update blog
//    @RequestMapping(value = "/findblogandcommentsbyid", method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String, Object> findBlogAndCommentsById(String blogId, HttpServletResponse response) {
//        Map<String, Object> modelMap = new HashMap<String, Object>();
//        try {
//            int id1 = Integer.parseInt(blogId);
//            Blog blog = blogService.findBlogById(id1);
//            if (blog == null) {
//                modelMap.put("status", false);
//                modelMap.put("msg", "no target blog");
//                response.setStatus(400);
//                return modelMap;
//            }
//            modelMap.put("blog", blog);
//            List<Review> reviews = reviewService.listCommentsByBlogId(Integer.parseInt(blog.getBlogId()));
//            if (reviews != null) {
//                modelMap.put("comments", reviews);
//            } else {
//                modelMap.put("msg", "no comments with blogid: " + blog.getBlogId());
//            }
//            response.setStatus(200);
//            return modelMap;
//        } catch (Exception e) {
//            modelMap.put("msg", e.getMessage());
//            e.printStackTrace();
//            modelMap.put("status", false);
//            response.setStatus(400);
//            return modelMap;
//        }
//    }

	@RequestMapping(value = "/updateblog", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateBlog(@RequestBody Blog blog, HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (blog != null) {
			Boolean result = blogService.updateBlog(blog);
			modelMap.put("status", result);
			modelMap.put("msg", result ? "update blog success!" : "update failed");
			response.setStatus(result ? 200 : 400);
			return modelMap;
		} else {
			modelMap.put("status", false);
			modelMap.put("msg", "blog object is null, please check request body");
			response.setStatus(400);
			return modelMap;
		}

	}

	@RequestMapping(value = "/like", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> like(HttpServletResponse response, Integer id) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Blog blog = blogService.findBlogById(id);
		blog.setClapCount(blog.getClapCount() + 1);
		blogService.updateBlog(blog);
		modelMap.put("status", true);
		response.setStatus(200);
		return modelMap;
	}

	@ResponseBody
	@RequestMapping(value = "/deletecomment", method = RequestMethod.GET)
	public Map<String, Object> deleteComment(String id, HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			int idInt = Integer.parseInt(id);
			Boolean result = reviewService.deleteReview(idInt);
			modelMap.put("msg", result ? "delete comment success!" : "delete failed");
			response.setStatus(result ? 200 : 400);
		} catch (Exception e) {
			modelMap.put("msg", e.getMessage());
			modelMap.put("status", false);
			response.setStatus(400);
		}
		return modelMap;
	}
}