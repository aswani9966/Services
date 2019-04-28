package com.location.service;

import java.util.List;

import com.location.dto.ResponseObject;
import com.location.model.Category;
import com.location.model.SubCategory;

public interface CategoryService {
	public Category updateCategory(Category dept);
	public void deleteCategory(Integer id);
	public List<ResponseObject> getCategories();
	
	public SubCategory updateSubCategory(SubCategory dept);
	public void deleteSubCategory(Integer id);
	public List<ResponseObject> getSubCategories();
	public Long findCountByCategory(Integer categoryId);
}
