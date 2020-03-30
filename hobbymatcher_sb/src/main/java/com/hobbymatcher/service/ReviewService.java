package com.hobbymatcher.service;

import java.util.List;

import com.hobbymatcher.entity.Review;

public interface ReviewService {

    List<Review> listCommentsByTypeAndId(String type, int id);


    Boolean deleteComment(int id);

    Boolean addComment(Review review);

}
