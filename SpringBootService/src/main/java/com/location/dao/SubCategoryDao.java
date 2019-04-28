package com.location.dao;

import java.util.List;

import com.location.model.SubCategory;

public interface SubCategoryDao {
	public SubCategory createSubCategoryData(SubCategory subCategoryData);
	public void deleteSubCategoryData(SubCategory subCategoryData);
	public List<SubCategory> getAllSubCategoryData();
	public List<SubCategory> findAllSubCategorisByCategoryId(Integer categoryId);
	public SubCategory getSubCategory(Integer subCategoryId);
	public SubCategory findSubCategoryByNameCategoryId(String subCategoryName, Integer categoryId);
	public List<SubCategory> findAll();
	public Long findCountByCategory(Integer categoryId);
}
