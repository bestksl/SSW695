package com.hobbymatcher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hobbymatcher.entity.Blog;

public interface BlogDao {
    int deleteBlog(@Param("blogId") int id);

    int updateBlog(Blog blog);

    int addBlog(Blog blog);

    Blog findBlogById(@Param("blogId") int id);

    List<Blog> listBlog();

    List<Blog> listBlogByHobbyId(@Param("hobbyId") int id);


}