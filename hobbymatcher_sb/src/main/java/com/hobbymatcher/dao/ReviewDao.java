package com.hobbymatcher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hobbymatcher.entity.Review;

@Repository
public interface ReviewDao {

    int addReview(Review review);

    int deleteReview(@Param("id") int id);

    List<Review> listReviewByTypeAndId(@Param("ownerType") String ownerType, @Param("id") int id);

}
