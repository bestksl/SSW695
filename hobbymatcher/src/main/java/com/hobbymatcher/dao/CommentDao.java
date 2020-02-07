package com.hobbymatcher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hobbymatcher.entity.Comment;

public interface CommentDao {

    int addComment(Comment comment);

    int deleteComment(@Param("id") int id);

    List<Comment> listCommentByBlogId(@Param("blogId") int blogId);

}
