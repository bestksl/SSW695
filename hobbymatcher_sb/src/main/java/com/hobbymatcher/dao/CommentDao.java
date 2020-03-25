package com.hobbymatcher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hobbymatcher.entity.Comment;

@Repository
public interface CommentDao {

    int addComment(Comment comment);

    int deleteComment(@Param("id") int id);

    List<Comment> listCommentByBlogId(@Param("blogId") int blogId);

}
