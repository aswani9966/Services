package com.location.dao;

import java.util.List;

import com.location.model.Category;

public interface CategoryDao {
	public Category createCategoryData(Category categoryData);
	public void deleteCategoryData(Category categoryData);
	public List<Category> getAllCategoryData();
	public List<Category> findAllCategorisByDeptId(Integer departmentId);
	public Category findCategoryByNameDepartmentId(String categoryName, Integer departmentId);
	public List<Category> findAll();
	public Category getCategory(Integer categoryid);
}
