package com.hobbymatcher.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.hobbymatcher.entity.Review;
import org.springframework.stereotype.Service;

import com.hobbymatcher.dao.CommentDao;
import com.hobbymatcher.dao.UserDao;
import com.hobbymatcher.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentDao commentDao;
	private final UserDao userDao;

	public CommentServiceImpl(CommentDao commentDao, UserDao userDao) {
		this.commentDao = commentDao;
		this.userDao = userDao;
	}

	@Override
	public List<Review> listCommentsByBlogId(int blogId) {
		List<Review> reviewList = new ArrayList<Review>();
		try {
			reviewList = commentDao.listCommentByBlogId(blogId);
			if (reviewList == null || reviewList.size() == 0) {
				return null;
			}
			for (Review c : reviewList) {
				c.setUser(userDao.findUserById(c.getUserId()));
			}
			return reviewList;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	@Override
	public Boolean deleteComment(int id) {
		try {
			if (commentDao.deleteComment(id) == 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public Boolean addComment(Review review) {
		if (review == null) {
			return false;
		} else {
			return commentDao.addComment(review) != 0;
		}
	}
}
