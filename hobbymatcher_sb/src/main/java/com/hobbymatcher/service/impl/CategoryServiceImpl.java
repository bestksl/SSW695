package com.hobbymatcher.service.impl;

import com.hobbymatcher.dao.CategoryDao;
import com.hobbymatcher.entity.Category;
import com.hobbymatcher.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao;

	@Autowired
	public CategoryServiceImpl(CategoryDao hobbyDao) {
		this.categoryDao = hobbyDao;
	}

	@Override
	public List<Category> listCategory() {
		return categoryDao.queryCategory();
	}

	@Override
	public Category findCategoryByName(String name) {
		return categoryDao.findCategoryByName(name);
	}

	@Override
	public Category findCategoryById(int id) {
		return categoryDao.findCategoryById(id);
	}
}