package com.hobbymatcher.service;

import java.util.List;

import com.hobbymatcher.entity.Review;

public interface ReviewService {

	List<Review> listReviewByTypeAndId(String ownerType, int ownerId);

	Boolean deleteReview(int id);

	Boolean addReview(Review review);

	boolean checkReview(Review review);
}
