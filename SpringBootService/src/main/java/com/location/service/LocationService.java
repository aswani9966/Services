package com.location.service;

import java.util.List;

import com.location.dto.ResponseObject;
import com.location.model.Department;
import com.location.model.Location;

public interface LocationService {
	public List<ResponseObject> getAllLocationData();
	public List<ResponseObject> getAllDepartmentsData();
	public List<ResponseObject> getAllDepartmentsByLocationId(Integer locationId);
	public List<ResponseObject> getAllCategoriesByDepartmentId(Integer departmentId);
	public List<ResponseObject> getAllSubCategoriesByCategoryId(Integer categoryId);
	public ResponseObject getSubCategory(Integer subCategoryId);
	public ResponseObject processCSVData(String filePath,String type);
	public void deleteLocation(Integer id);
	public Location updateLocation(Integer id, String name);
	
	public Department updateDepartment(Department dept);
	public void deleteDepartment(Integer id);
	public List<ResponseObject> getAllLocations();
	public List<ResponseObject> getAllDepartments();
	public List<ResponseObject> getAllCategories();
	public List<ResponseObject> getAllSubCategories();
	public List<ResponseObject> getAllSkus();
	
}
