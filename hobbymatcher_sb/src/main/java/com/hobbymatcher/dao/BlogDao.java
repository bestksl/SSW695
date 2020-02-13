package com.hobbymatcher.dao;

import com.hobbymatcher.entity.Blog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {
    int deleteBlog(@Param("blogId") int id);

    int updateBlog(Blog blog);

    int addBlog(Blog blog);

    Blog findBlogById(@Param("blogId") int id);

    List<Blog> listBlog();

    List<Blog> listBlogByHobbyId(@Param("hobbyId") int id);


}