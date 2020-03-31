package com.hobbymatcher.controller.Review;

import com.hobbymatcher.entity.Review;
import com.hobbymatcher.service.ReviewService;
import com.hobbymatcher.service.impl.AuthUtilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@SessionAttributes()
@RequestMapping("/review")
@RestController
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private AuthUtilService authUtilService;

	@GetMapping
	public Map<String, Object> getReviewByTypeAndId(HttpServletRequest req, HttpServletResponse response, //
			@RequestParam("type") String ownerType, @RequestParam("oid") Integer ownerId) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			List<Review> reviews = reviewService.listReviewByTypeAndId(ownerType, ownerId);
			for (Review review : reviews) {
				review.setComments(reviewService.listCommentsByParentId(review.getId()));
			}
			resp.put("list", reviews);
			resp.put("success", true);
			response.setStatus(200);
		} catch (Exception exp) {
			exp.printStackTrace();
			resp.put("success", false);
			response.setStatus(400);
		}
		return resp;

	}

	@DeleteMapping("/{id}")
	public Map<String, Object> deleteReview(@PathVariable int id, HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			reviewService.deleteCommentsOfReview(id);
			boolean res = reviewService.deleteReview(id);
			resp.put("success", res);
			response.setStatus(200);
		} catch (Exception exp) {
			exp.printStackTrace();
			resp.put("success", false);
			response.setStatus(400);
		}
		return resp;

	}

	@PostMapping
	public Map<String, Object> addReview(HttpServletRequest req, HttpServletResponse response, //
			@RequestBody Review review) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			review.setOnDateTime(new Date());
			review.setByUserId(authUtilService.getUserId(req));
			reviewService.addReview(review);
			resp.put("success", true);
			response.setStatus(200);
		} catch (Exception exp) {
			exp.printStackTrace();
			resp.put("error", false);
			response.setStatus(400);
		}
		return resp;
	}
}
