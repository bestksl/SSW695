package com.hobbymatcher.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.hobbymatcher.entity.Review;
import org.springframework.stereotype.Service;

import com.hobbymatcher.dao.ReviewDao;
import com.hobbymatcher.dao.UserDao;
import com.hobbymatcher.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao;
    private final UserDao userDao;

    public ReviewServiceImpl(ReviewDao reviewDao, UserDao userDao) {
        this.reviewDao = reviewDao;
        this.userDao = userDao;
    }

//    public List<Review> list(int blogId) {
//        List<Review> reviewList = new ArrayList<Review>();
//        try {
//            reviewList = reviewDao.listCommentByBlogId(blogId);
//            if (reviewList == null || reviewList.size() == 0) {
//                return null;
//            }
//            for (Review c : reviewList) {
//                c.setUser(userDao.findUserById(c.getUserId()));
//            }
//            return reviewList;
//        } catch (Exception e) {
//            return null;
//        }
//
//    }

    @Override
    public List<Review> listCommentsByTypeAndId(String type, int id) {
        List<Review> reviewList = new ArrayList<Review>();
        try {
            reviewList = reviewDao.listReviewByTypeAndId(type, id);
            if (reviewList == null || reviewList.size() == 0) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return reviewList;
    }

    @Override
    public Boolean deleteComment(int id) {
        try {
            return reviewDao.deleteReview(id) != 0;
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
            return reviewDao.addReview(review) != 0;
        }
    }
}
