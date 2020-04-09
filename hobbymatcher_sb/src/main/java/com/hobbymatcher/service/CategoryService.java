package com.hobbymatcher.service;

import java.util.List;

import com.hobbymatcher.entity.Category;

public interface CategoryService {

	List<Category> listCategory();

	Category findCategoryByName(String name);

	Category findCategoryById(int id);
}
