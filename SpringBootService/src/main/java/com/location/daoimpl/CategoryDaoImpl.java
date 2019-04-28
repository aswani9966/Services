package com.location.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.location.dao.CategoryDao;
import com.location.model.Category;
import com.location.repository.CategoryRepository;
@Repository
public class CategoryDaoImpl implements CategoryDao{
	@Autowired
    CategoryRepository categoryRepository;
	public Category createCategoryData(Category categoryData) {
		return categoryRepository.saveAndFlush(categoryData);
	}


	public void deleteCategoryData(Category categoryData) {
		categoryRepository.delete(categoryData);
		
	}

	public List<Category> getAllCategoryData() {
		return categoryRepository.findAll();
	}
	
	public Category getCategory(Integer categoryid) {
		return categoryRepository.findById(categoryid);
	}


	public List<Category> findAllCategorisByDeptId(Integer departmentId) {
		return categoryRepository.findAllCategorisByDeptId(departmentId);
	}
	public Category findCategoryByNameDepartmentId(String categoryName, Integer departmentId){
		return categoryRepository.findCategoryByNameDepartmentId(categoryName, departmentId);
	}


	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
}
