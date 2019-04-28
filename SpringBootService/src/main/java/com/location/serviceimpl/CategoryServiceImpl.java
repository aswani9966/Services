package com.location.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.location.dao.CategoryDao;
import com.location.dao.DepartmentDao;
import com.location.dao.SubCategoryDao;
import com.location.dto.ResponseObject;
import com.location.model.Category;
import com.location.model.Department;
import com.location.model.SubCategory;
import com.location.service.CategoryService;
@Component
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	SubCategoryDao subCategoryDao;
	@Autowired
	DepartmentDao departmentDao;
	
	@Override
	public Category updateCategory(Category cate) {
		Category category=null;
		category=categoryDao.findCategoryByNameDepartmentId(cate.getName(), cate.getDepartment_id());
		if(category == null){
			category=categoryDao.createCategoryData(cate);
		}
		return category;
	}

	@Override
	public void deleteCategory(Integer id) {
		Category category= new Category();
		category.setId(id);
		categoryDao.deleteCategoryData(category);
	}
	@Override
	public List<ResponseObject> getCategories() {
		List<ResponseObject> response=new ArrayList<ResponseObject>(); 
		ResponseObject responseObj=null;
		
		List<Department> departmentsList=departmentDao.getAllDepartmentData();
		Map<Integer,String> departments=new HashMap<Integer,String>();
		for (Department department : departmentsList) {
			departments.put(department.getId(), department.getName());
		}
		
		List<Category> responseDatas= categoryDao.findAll();
		for (Category category : responseDatas) {
			responseObj = new ResponseObject();
			responseObj.setId(category.getId().toString());
			responseObj.setName(category.getName());
			responseObj.setParent(departments.get(category.getDepartment_id()));
			response.add(responseObj);
		}
		return response;
	}

	@Override
	public SubCategory updateSubCategory(SubCategory subcate) {
		SubCategory subCategory=null;
		subCategory=subCategoryDao.findSubCategoryByNameCategoryId(subcate.getName(), subcate.getCategory_id());
		if(subCategory == null){
			subCategory=subCategoryDao.createSubCategoryData(subcate);
		}
		return subCategory;
	}

	@Override
	public void deleteSubCategory(Integer id) {
		SubCategory subCategory= new SubCategory();
		subCategory.setId(id);
		subCategoryDao.deleteSubCategoryData(subCategory);
		
	}

	@Override
	public List<ResponseObject> getSubCategories() {
		List<ResponseObject> response=new ArrayList<ResponseObject>(); 
		ResponseObject responseObj=null;
		List<Category> categoriesList=categoryDao.getAllCategoryData();
		Map<Integer,String> categories=new HashMap<Integer,String>();
		for (Category category : categoriesList) {
			categories.put(category.getId(), category.getName());
		}
		List<SubCategory> responseDatas= subCategoryDao.findAll();
		for (SubCategory subCategory : responseDatas) {
			responseObj = new ResponseObject();
			responseObj.setId(subCategory.getId().toString());
			responseObj.setName(subCategory.getName());
			responseObj.setParent(categories.get(subCategory.getCategory_id()));
			response.add(responseObj);
		}
		return response;
	}

	@Override
	public Long findCountByCategory(Integer categoryId) {
		return subCategoryDao.findCountByCategory(categoryId);
	}
}
