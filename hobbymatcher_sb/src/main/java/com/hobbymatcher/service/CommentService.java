package com.hobbymatcher.service;

import java.util.List;

import com.hobbymatcher.entity.Comment;

public interface CommentService {

    List<Comment> listCommentsByBlogId(int blogId);

    Boolean deleteComment(int id);

    Boolean addComment(Comment comment);

}
