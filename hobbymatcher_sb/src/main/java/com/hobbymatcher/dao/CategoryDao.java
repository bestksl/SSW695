package com.hobbymatcher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hobbymatcher.entity.Category;

@Repository
public interface CategoryDao {

	List<Category> queryCategory();

	Category findCategoryByName(@Param("name") String name);

	Category findCategoryById(@Param("id") int id);
}
