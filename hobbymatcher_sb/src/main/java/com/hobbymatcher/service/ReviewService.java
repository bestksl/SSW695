package com.hobbymatcher.service;

import java.util.List;

import com.hobbymatcher.entity.Review;

public interface ReviewService {

    List<Review> listReviewByTypeAndId(String type, int id);


    Boolean deleteReview(int id);

    Boolean addReview(Review review);

    boolean checkReview(Review review);
}
