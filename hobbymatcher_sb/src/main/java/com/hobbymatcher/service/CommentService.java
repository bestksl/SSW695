package com.hobbymatcher.service;

import java.util.List;

import com.hobbymatcher.entity.Review;

public interface CommentService {

    List<Review> listCommentsByBlogId(int blogId);

    Boolean deleteComment(int id);

    Boolean addComment(Review review);

}
