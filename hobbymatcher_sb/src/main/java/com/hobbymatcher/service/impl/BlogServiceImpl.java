package com.hobbymatcher.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hobbymatcher.dao.BlogDao;
import com.hobbymatcher.dao.UserDao;
import com.hobbymatcher.entity.Blog;
import com.hobbymatcher.service.BlogService;

import javax.validation.Valid;

@Valid
@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogDao blogDao;

	@Override
	public List<Blog> listBlog() {
		return blogDao.listBlog();
	}

	@Override
	public Boolean deleteBlog(int id) {
		try {
			return blogDao.deleteBlog(id) == 1;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean addBlog(Blog blog) {
		return blogDao.addBlog(blog) == 1;
	}

	@Override
	public Blog findBlogById(int id) {
		return blogDao.findBlogById(id);
	}

	@Override
	public Boolean updateBlog(Blog blog) {
		return blogDao.updateBlog(blog) == 1;
	}

	@Override
	public List<Blog> listBlogByHobbyId(int id) {
		return blogDao.listBlogByHobbyId(id);
	}

	@Override
	public List<Blog> paginateListBlogByHobbyId(Boolean justCount, Integer hobbyId, Integer offset, Integer perpage) {
		return blogDao.paginateListBlogByHobbyId(justCount, hobbyId, offset, perpage);
	}

	@Override
	public List<Blog> paginateListBlog(Boolean justCount, Integer offset, Integer perpage) {
		return blogDao.paginateListBlog(justCount, offset, perpage);
	}
}
