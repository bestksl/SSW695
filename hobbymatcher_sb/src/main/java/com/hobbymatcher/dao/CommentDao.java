package com.hobbymatcher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hobbymatcher.entity.Review;

@Repository
public interface CommentDao {

    int addComment(Review review);

    int deleteComment(@Param("id") int id);

    List<Review> listCommentByBlogId(@Param("blogId") int blogId);

}
