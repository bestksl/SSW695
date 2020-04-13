package com.hobbymatcher.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hobbymatcher.entity.Blog;

@Repository
public interface BlogDao {

	int deleteBlog(@Param("blogId") int id);

	int updateBlog(Blog blog);

	int addBlog(Blog blog);

	Blog findBlogById(@Param("blogId") int id);

	List<Blog> listBlog();

	List<Blog> listBlogByHobbyId(@Param("hobbyId") int id);

	List<Blog> paginateListBlogByHobbyId(@Param("justCount") Boolean justCount, //
			@Param("hobbyId") Integer hobbyId, @Param("offset") Integer offset, @Param("perpage") Integer perpage);

	List<Blog> paginateListBlog(Boolean justCount, Integer offset, Integer perpage);
}