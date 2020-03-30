package com.hobbymatcher.controller.Review;


import com.hobbymatcher.entity.Review;
import com.hobbymatcher.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@SessionAttributes()
@RequestMapping("/review")
@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/")
    public Map<String, Object> getReviewByTypeAndId(String type, int id, HttpServletResponse response) {
        Map<String, Object> resp = new HashMap<String, Object>();
        try {
            resp.put("list", reviewService.listReviewByTypeAndId(type, id));
            resp.put("success", true);
            response.setStatus(200);
        } catch (Exception exp) {
            exp.printStackTrace();
            resp.put("success", false);
            response.setStatus(400);
        }
        return resp;

    }

    @DeleteMapping("/")
    public Map<String, Object> deleteReview(int id, HttpServletResponse response) {
        Map<String, Object> resp = new HashMap<String, Object>();
        try {
            boolean res = reviewService.deleteReview(id);
            resp.put(res ? "delete success" : "no such review", res);
            response.setStatus(200);
        } catch (Exception exp) {
            exp.printStackTrace();
            resp.put("error", false);
            response.setStatus(400);
        }
        return resp;

    }

    @PostMapping("/")
    public Map<String, Object> addReview(Review review, HttpServletResponse response) {
        Map<String, Object> resp = new HashMap<String, Object>();
        if (reviewService.checkReview(review)) {
            review.setOnDateTime(new Date());
            resp.put("add status", reviewService.addReview(review));
            response.setStatus(200);
        } else {
            resp.put("Review lack some important attr, please check again ", false);
            response.setStatus(200);
        }
        return resp;
    }
}
