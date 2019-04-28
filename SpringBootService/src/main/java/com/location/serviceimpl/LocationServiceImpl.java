package com.location.serviceimpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.location.dao.CategoryDao;
import com.location.dao.DepartmentDao;
import com.location.dao.LocationDao;
import com.location.dao.SkuDao;
import com.location.dao.SubCategoryDao;
import com.location.dto.ResponseObject;
import com.location.model.Category;
import com.location.model.Department;
import com.location.model.Location;
import com.location.model.SKU;
import com.location.model.SubCategory;
import com.location.service.LocationService;
@Component
public class LocationServiceImpl implements LocationService{
	@Autowired
	LocationDao locationDao;
	@Autowired
	DepartmentDao departmentDao;
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	SubCategoryDao subCategoryDao;
	@Autowired
	SkuDao skuDao;
	public List<ResponseObject> getAllLocationData(){
		List<ResponseObject> response=new ArrayList<ResponseObject>(); 
		ResponseObject responseObj=null;
		List<Location> responseDatas= locationDao.getAllLocationData();
		for (Location location : responseDatas) {
			responseObj = new ResponseObject();
			responseObj.setId(location.getId().toString());
			responseObj.setName(location.getName());
			//responseObj.setParent("R");
			response.add(responseObj);
		}
		return response;
	}
	public List<ResponseObject> getAllLocations(){
		List<ResponseObject> response=new ArrayList<ResponseObject>(); 
		ResponseObject responseObj=null;
		List<Location> responseDatas= locationDao.getAllLocationData();
		for (Location location : responseDatas) {
			responseObj = new ResponseObject();
			responseObj.setId("L"+location.getId());
			responseObj.setName(location.getName());
			responseObj.setParent("R");
			response.add(responseObj);
		}
		return response;
	}
	public List<ResponseObject> getAllDepartmentsByLocationId(Integer locationId) {
		List<ResponseObject> response=new ArrayList<ResponseObject>(); 
		ResponseObject responseObj=null;
		List<Department> responseDatas= departmentDao.findAllDepartmentsByLocationId(locationId);
		for (Department department : responseDatas) {
			responseObj = new ResponseObject();
			responseObj.setId(department.getId().toString());
			responseObj.setName(department.getName());
			responseObj.setParent(department.getLocation_id().toString());
			response.add(responseObj);
		}
		return response;
	}
	public List<ResponseObject> getAllDepartments() {
		List<ResponseObject> response=new ArrayList<ResponseObject>(); 
		ResponseObject responseObj=null;
		List<Department> responseDatas= departmentDao.findAll();
		for (Department department : responseDatas) {
			responseObj = new ResponseObject();
			responseObj.setId("D"+department.getId());
			responseObj.setName(department.getName());
			responseObj.setParent("L"+department.getLocation_id());
			response.add(responseObj);
		}
		return response;
	}
	public List<ResponseObject> getAllCategoriesByDepartmentId(
			Integer departmentId) {
		List<ResponseObject> response=new ArrayList<ResponseObject>(); 
		ResponseObject responseObj=null;
		List<Category> responseDatas= categoryDao.findAllCategorisByDeptId(departmentId);
		for (Category category : responseDatas) {
			responseObj = new ResponseObject();
			responseObj.setId(category.getId().toString());
			responseObj.setName(category.getName());
			responseObj.setParent(category.getDepartment_id().toString());
			response.add(responseObj);
		}
		return response;
	}
	public List<ResponseObject> getAllCategories() {
		List<ResponseObject> response=new ArrayList<ResponseObject>(); 
		ResponseObject responseObj=null;
		List<Category> responseDatas= categoryDao.findAll();
		for (Category category : responseDatas) {
			responseObj = new ResponseObject();
			responseObj.setId("C"+category.getId());
			responseObj.setName(category.getName());
			responseObj.setParent("D"+category.getDepartment_id());
			response.add(responseObj);
		}
		return response;
	}
	public List<ResponseObject> getAllSubCategoriesByCategoryId(
			Integer categoryId) {
		List<ResponseObject> response=new ArrayList<ResponseObject>(); 
		ResponseObject responseObj=null;
		List<SubCategory> responseDatas= subCategoryDao.findAllSubCategorisByCategoryId(categoryId);
		for (SubCategory subCategory : responseDatas) {
			responseObj = new ResponseObject();
			responseObj.setId(subCategory.getId().toString());
			responseObj.setName(subCategory.getName());
			responseObj.setParent(subCategory.getCategory_id().toString());
			response.add(responseObj);
		}
		return response;
	}
	public List<ResponseObject> getAllSubCategories() {
		List<ResponseObject> response=new ArrayList<ResponseObject>(); 
		ResponseObject responseObj=null;
		List<SubCategory> responseDatas= subCategoryDao.findAll();
		for (SubCategory subCategory : responseDatas) {
			responseObj = new ResponseObject();
			responseObj.setId("S"+subCategory.getId());
			responseObj.setName(subCategory.getName());
			responseObj.setParent("C"+subCategory.getCategory_id());
			response.add(responseObj);
		}
		return response;
	}
	public ResponseObject getSubCategory(Integer subCategoryId) {
		//List<ResponseObject> response=new ArrayList<ResponseObject>(); 
		ResponseObject response=null;
		SubCategory responseDatas= subCategoryDao.getSubCategory(subCategoryId);
		//for (SubCategory subCategory : responseDatas) {
		response = new ResponseObject();
		response.setId(""+responseDatas.getId());
		response.setName(responseDatas.getName());
		//}
		return response;
	}
	
	public ResponseObject processCSVData(String fileName,String type){
		ResponseObject response=null;
		List<String> failedList=new ArrayList<String>();
		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			while (line != null) {
				String[] attributes = line.split(",");
				//System.out.println(attributes);
				if(attributes.length==4 && "meta".equalsIgnoreCase(type)){
					processData(attributes);
				}else if(attributes.length==6 && "sku".equalsIgnoreCase(type)){
					processSkuData(attributes);
				}else{
					failedList.add(attributes.toString());
				}
				line = br.readLine();
			}
		}catch (IOException ioe) {
            ioe.printStackTrace();
        }
		return response;
	}
	public void processData(String[] attributes){
		Location location= null;
		location= locationDao.getLocationByLocationName(attributes[0]);
		if( location == null){
			location=new Location();
			location.setName(attributes[0]);
			location = locationDao.createLocationData(location);
		}
		System.out.println(location.toString());
		Department department=null;
		department=departmentDao.findDepartmentByNameLocationId(attributes[1], location.getId());
		if(department == null){
			department= new Department();
			department.setName(attributes[1]);
			department.setLocation_id(location.getId());
			department = departmentDao.createDepartmentData(department);
		}
		System.out.println(department.toString());
		Category category = null;
		category = categoryDao.findCategoryByNameDepartmentId(attributes[2], department.getId());
		if(category == null){
			category= new Category();
			category.setName(attributes[2]);
			category.setDepartment_id(department.getId());
			category = categoryDao.createCategoryData(category);
		}
		System.out.println(category.toString());
		SubCategory subCategory = null;
		subCategory = subCategoryDao.findSubCategoryByNameCategoryId(attributes[3], category.getId());
		if(subCategory == null){
			subCategory = new SubCategory();
			subCategory.setName(attributes[3]);
			subCategory.setCategory_id(category.getId());
			subCategory = subCategoryDao.createSubCategoryData(subCategory);
		}
		System.out.println(subCategory.toString());
	}
	
	public void processSkuData(String[] attributes){
		Location location= null;
		location= locationDao.getLocationByLocationName(attributes[2]);
		if( location == null){
			location=new Location();
			location.setName(attributes[2]);
			location = locationDao.createLocationData(location);
		}
		System.out.println(location.toString());
		Department department=null;
		department=departmentDao.findDepartmentByNameLocationId(attributes[3], location.getId());
		if(department == null){
			department= new Department();
			department.setName(attributes[3]);
			department.setLocation_id(location.getId());
			department = departmentDao.createDepartmentData(department);
		}
		System.out.println(department.toString());
		Category category = null;
		category = categoryDao.findCategoryByNameDepartmentId(attributes[4], department.getId());
		if(category == null){
			category= new Category();
			category.setName(attributes[4]);
			category.setDepartment_id(department.getId());
			category = categoryDao.createCategoryData(category);
		}
		System.out.println(category.toString());
		SubCategory subCategory = null;
		subCategory = subCategoryDao.findSubCategoryByNameCategoryId(attributes[5], category.getId());
		if(subCategory == null){
			subCategory = new SubCategory();
			subCategory.setName(attributes[5]);
			subCategory.setCategory_id(category.getId());
			subCategory = subCategoryDao.createSubCategoryData(subCategory);
		}
		System.out.println(subCategory.toString());
		SKU sku=null;
		sku=skuDao.findSKUByName(attributes[1], location.getId(), department.getId(), category.getId(), subCategory.getId());
		if(sku == null){
			sku = new SKU();
			sku.setName(attributes[1]);
			sku.setLocation_id(location.getId());
			sku.setDepartment_id(department.getId());
			sku.setCategory_id(category.getId());
			sku.setSubcategory_id(subCategory.getId());
			sku=skuDao.createSKU(sku);					
		}
		System.out.println(sku.toString());
	}
	
	public List<ResponseObject> getAllSkus() {
		List<ResponseObject> response=new ArrayList<ResponseObject>(); 
		ResponseObject responseObj=null;
		List<SKU> responseDatas= skuDao.getAllSKUData();
		responseObj = new ResponseObject();
		responseObj.setId("R");
		responseObj.setName("SKU_Data");
		responseObj.setParent("");
		response.add(responseObj);
		Location location=null;
		Department department=null;
		Category category=null;
		SubCategory subCategory=null;
		for (SKU sku : responseDatas) {
			responseObj = new ResponseObject();
			responseObj.setId("SKU"+sku.getId());
			responseObj.setName(sku.getName());
			responseObj.setParent("R");
			response.add(responseObj);
			location = locationDao.getLocation(sku.getLocation_id());
			responseObj = new ResponseObject();
			responseObj.setId(location.getId().toString());
			responseObj.setName(location.getName());
			responseObj.setParent("SKU"+sku.getId());
			response.add(responseObj);
			department = departmentDao.getDepartment(sku.getDepartment_id());
			responseObj = new ResponseObject();
			responseObj.setId(department.getId().toString());
			responseObj.setName(department.getName());
			responseObj.setParent("SKU"+sku.getId());
			response.add(responseObj);
			category = categoryDao.getCategory(sku.getCategory_id());
			responseObj = new ResponseObject();
			responseObj.setId(category.getId().toString());
			responseObj.setName(category.getName());
			responseObj.setParent("SKU"+sku.getId());
			response.add(responseObj);
			subCategory = subCategoryDao.getSubCategory(sku.getSubcategory_id());
			responseObj = new ResponseObject();
			responseObj.setId(subCategory.getId().toString());
			responseObj.setName(subCategory.getName());
			responseObj.setParent("SKU"+sku.getId());
			response.add(responseObj);
		}
		return response;
	}
	@Override
	public void deleteLocation(Integer id) {
		Location loc= new Location();
		loc.setId(id);
		locationDao.deleteLocation(loc);
		
	}
	@Override
	public Location updateLocation(Integer id, String name) {
		Location loc= new Location();
		loc.setId(id);
		loc.setName(name);
		return locationDao.updateLocation(loc);
		
	}
	public List<ResponseObject> getAllDepartmentsData() {
		List<ResponseObject> response=new ArrayList<ResponseObject>(); 
		ResponseObject responseObj=null;
		
		List<Location> locationsList=locationDao.getAllLocationData();
		Map<Integer,String> locations=new HashMap<Integer,String>();
		for (Location location : locationsList) {
			locations.put(location.getId(), location.getName());
		}
		
		List<Department> responseDatas= departmentDao.getAllDepartmentData();
		for (Department department : responseDatas) {
			responseObj = new ResponseObject();
			responseObj.setId(department.getId().toString());
			responseObj.setName(department.getName());
			responseObj.setParent(locations.get(department.getLocation_id()));
			response.add(responseObj);
		}
		return response;
	}
	@Override
	public Department updateDepartment(Department dept) {
		Department department=null;
		department=departmentDao.findDepartmentByNameLocationId(dept.getName(), dept.getLocation_id());
		if(department == null){
			department=departmentDao.createDepartmentData(dept);
		}
		return department;
	}
	@Override
	public void deleteDepartment(Integer id) {
		Department dept= new Department();
		dept.setId(id);
		departmentDao.deleteDepartmentData(dept);
		
	}
	
	
}
