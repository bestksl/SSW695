package com.hobbymatcher.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.hobbymatcher.entity.Comment;
import com.hobbymatcher.entity.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hobbymatcher.dao.ReviewDao;
import com.hobbymatcher.dao.UserDao;
import com.hobbymatcher.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;

	@Override
	public List<Review> listReviewByTypeAndId(String ownerType, int ownerId) {
		return reviewDao.listReviewByTypeAndId(ownerType, ownerId);
	}

	@Override
	public List<Comment> listCommentsByParentId(int parentId) {
		return reviewDao.listCommentsByParentId(parentId);
	}

	@Override
	public Boolean deleteReview(int id) {
		try {
			return reviewDao.deleteReview(id) != 0;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public Boolean addReview(Review review) {
		if (review == null) {
			return false;
		} else {
			return reviewDao.addReview(review) != 0;
		}
	}

	@Override
	public boolean checkReview(Review review) {
		if (review == null)
			return false;

		// check review important attr
		if (review.getId() < 0)
			return false;
		else if (review.getOwnerId() < 0)
			return false;
		else if (review.getByUserId() < 0)
			return false;
		else
			return review.getContent() != null && !"".equals(review.getContent());
	}
}
