package com.hobbymatcher.service;

import java.util.List;

import com.hobbymatcher.entity.Comment;
import com.hobbymatcher.entity.Review;

public interface ReviewService {

	List<Review> listReviewByTypeAndId(String ownerType, int ownerId);

	List<Comment> listCommentsByParentId(int parentId);

	Boolean deleteReview(int id);

	void deleteCommentsOfReview(int id);

	Boolean addReview(Review review);

	boolean checkReview(Review review);

	Review findById(int id);

	void updateReview(Review original);
}
