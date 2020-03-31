package com.hobbymatcher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hobbymatcher.entity.Comment;
import com.hobbymatcher.entity.Review;

@Repository
public interface ReviewDao {

	int addReview(Review review);

	int deleteReview(@Param("id") int id);

	List<Review> listReviewByTypeAndId(@Param("ownerType") String ownerType, @Param("ownerId") int ownerId);

	List<Comment> listCommentsByParentId(@Param("parentId") int parentId);

	Review findById(@Param("id") int id);

	void deleteCommentsOfReview(@Param("id") int id);

	void updateReview(@Param("id") int id, @Param("content") String content, @Param("rate") Integer rate);
}
