package com.location.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.location.dto.ResponseObject;
import com.location.dto.SkuObject;
import com.location.model.Category;
import com.location.model.Department;
import com.location.model.Location;
import com.location.model.SKU;
import com.location.model.SubCategory;
import com.location.service.CategoryService;
import com.location.service.LocationService;
import com.location.service.SkuService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/api/v1"})
public class LocationsController {
	private static final Gson gson = new Gson();
	@Autowired
	LocationService locationService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	SkuService skuService;
	
	@GetMapping(path={"/locations"}, produces = "application/json")
	public List<ResponseObject> getAllLocationData() {
		List<ResponseObject> responseDatas=null;
		try{
			responseDatas= locationService.getAllLocationData();
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseDatas;
	}
	@GetMapping(path={"/departments"}, produces = "application/json")
	public List<ResponseObject> getAllDepartmentsData() {
		List<ResponseObject> responseDatas=null;
		try{
			responseDatas= locationService.getAllDepartmentsData();
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseDatas;
	}
	@GetMapping(path={"/location/{location_id}/department"}, produces = "application/json")
	public List<ResponseObject> getAllDepartmentsByLocationId(@PathVariable(value = "location_id") Integer locationId) {
		List<ResponseObject> responseDatas=null;
		try{
			System.out.println("locationid="+locationId);
			responseDatas= locationService.getAllDepartmentsByLocationId(locationId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseDatas;
	}
	@GetMapping(path={"/location/{location_id}/department/{department_id}/category"}, produces = "application/json")
	public List<ResponseObject> getAllCategoriesByDepartmentId(@PathVariable(value = "location_id") Integer locationId,
			@PathVariable(value = "department_id") Integer departmentId) {
		List<ResponseObject> responseDatas=null;
		try{
			System.out.println("departmentId="+departmentId);
			responseDatas= locationService.getAllCategoriesByDepartmentId(departmentId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseDatas;
	}
	@GetMapping(path={"/location/{location_id}/department/{department_id}/category/{category_id}/subcategory"}, produces = "application/json")
	public List<ResponseObject> getAllSubCategoriesByCategoryId(@PathVariable(value = "location_id") Integer locationId,
			@PathVariable(value = "department_id") Integer departmentId,@PathVariable(value = "category_id") Integer categoryId) {
		List<ResponseObject> responseDatas=null;
		try{
			System.out.println("categoryId="+categoryId);
			responseDatas= locationService.getAllSubCategoriesByCategoryId(categoryId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseDatas;
	}
	@GetMapping(path={"/location/{location_id}/department/{department_id}/category/{category_id}/subcategory/{subcategory_id}"}, produces = "application/json")
	public ResponseObject getSubCategory(@PathVariable(value = "location_id") Integer locationId,
			@PathVariable(value = "department_id") Integer departmentId,@PathVariable(value = "category_id") Integer categoryId,
			@PathVariable(value = "subcategory_id") Integer subcategoryId) {
		ResponseObject responseData=null;
		try{
			System.out.println("subcategoryId="+subcategoryId);
			responseData= locationService.getSubCategory(subcategoryId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseData;
	}
	@GetMapping(path={"/processMetaData"}, produces = "application/json")
	public ResponseObject processMetaData(){
		ResponseObject responseData=null;
		try (InputStream input = LocationsController.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }
            prop.load(input);
            String filePath=prop.getProperty("metaDataFilePath");
            System.out.println(filePath);
            responseData=locationService.processCSVData(filePath,"meta");
        } catch (IOException ex) {
        	
            ex.printStackTrace();
        }
		return responseData;
	}
	@GetMapping(path={"/processSKU"}, produces = "application/json")
	public ResponseObject processSKU(){
		ResponseObject responseData=null;
		try (InputStream input = LocationsController.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }
            prop.load(input);
            String filePath=prop.getProperty("skuDataFilePath");
            System.out.println(filePath);
            responseData=locationService.processCSVData(filePath,"sku");
        } catch (IOException ex) {
        	
            ex.printStackTrace();
        }
		return responseData;
	}
	@GetMapping(path={"/metaData"}, produces = "application/json")
	public List<ResponseObject> getAllSubCategoriesByCategoryId() {
		List<ResponseObject> responseDatas=new ArrayList<ResponseObject>();
		try{
			ResponseObject root=new ResponseObject();
			root.setId("R");
			root.setName("Meta_Data");
			root.setParent("");
			responseDatas.add(root);
			responseDatas.addAll(locationService.getAllLocations());
			responseDatas.addAll(locationService.getAllDepartments());
			responseDatas.addAll(locationService.getAllCategories());
			responseDatas.addAll(locationService.getAllSubCategories());
			
			//responseDatas= locationService.getAllSubCategoriesByCategoryId(categoryId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseDatas;
	}
	@GetMapping(path={"/skuData"}, produces = "application/json")
	public List<ResponseObject> getAllSkus() {
		List<ResponseObject> responseDatas=new ArrayList<ResponseObject>();
		try{
			responseDatas = locationService.getAllSkus();
			
			//responseDatas= locationService.getAllSubCategoriesByCategoryId(categoryId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseDatas;
	}
	@DeleteMapping(path={"/deleteLocation/{id}"})
	public void deleteLocation(@PathVariable Integer id){
		locationService.deleteLocation(id);
	}
	@PutMapping(path={"/updateLocation/{id}/{name}"})
	public Location updateLocation(@PathVariable Integer id, @PathVariable String name){
		return locationService.updateLocation(id, name);
	}
	
	@PostMapping(path = {"/saveDepartment"}, produces = "application/json")
    public ResponseEntity <String> saveDepartment(@RequestBody Department department) {
		Department dept=locationService.updateDepartment(department);
        System.out.println(department.toString());
        if(department.getId()==null && dept.getId()!=null){
        	return ResponseEntity.ok(gson.toJson("Department Inserted"));
        }else{
        	return ResponseEntity.ok(gson.toJson("Department Updated"));
        }
    }
	@DeleteMapping(path={"/deleteDepartment/{id}"})
	public void deleteDepartment(@PathVariable Integer id){
		locationService.deleteDepartment(id);
	}
	
	@GetMapping(path={"/getcategories"}, produces = "application/json")
	public List<ResponseObject> getAllCategories() {
		List<ResponseObject> responseDatas=null;
		try{
			responseDatas= categoryService.getCategories();
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseDatas;
	}
	
	@PostMapping(path = {"/saveCategory"}, produces = "application/json")
    public ResponseEntity <String> saveCategory(@RequestBody Category category) {
		Category category1=categoryService.updateCategory(category);
        System.out.println(category1.toString());
        if(category1.getId()==null && category.getId()!=null){
        	return ResponseEntity.ok(gson.toJson("Category Inserted"));
        }else{
        	return ResponseEntity.ok(gson.toJson("Category Updated"));
        }
    }
	@DeleteMapping(path={"/deleteCategory/{id}"})
	public void deleteCategory(@PathVariable Integer id){
		categoryService.deleteCategory(id);
	}
	
	@GetMapping(path={"/getSubCategories"}, produces = "application/json")
	public List<ResponseObject> getAllSubCategories() {
		List<ResponseObject> responseDatas=null;
		try{
			responseDatas= categoryService.getSubCategories();
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseDatas;
	}
	
	@PostMapping(path = {"/saveSubCategory"}, produces = "application/json")
    public ResponseEntity <String> saveSubCategory(@RequestBody SubCategory subCategory) {
		SubCategory subCategory1=categoryService.updateSubCategory(subCategory);
        System.out.println(subCategory1.toString());
        if(subCategory1.getId()==null && subCategory.getId()!=null){
        	return ResponseEntity.ok(gson.toJson("Category Inserted"));
        }else{
        	return ResponseEntity.ok(gson.toJson("Category Updated"));
        }
    }
	@DeleteMapping(path={"/deleteSubCategory/{id}"})
	public void deleteSubCategory(@PathVariable Integer id){
		categoryService.deleteSubCategory(id);
	}
	
	@GetMapping(path={"/getSkus"}, produces = "application/json")
	public List<SkuObject> getSkus() {
		List<SkuObject> responseDatas=null;
		try{
			responseDatas= skuService.getSkus();
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseDatas;
	}
	
	@PostMapping(path = {"/saveSku"}, produces = "application/json")
    public ResponseEntity <String> saveSubCategory(@RequestBody SKU sku) {
		SKU sku1=skuService.updateSku(sku);
        System.out.println(sku1.toString());
        if(sku1.getId()==null && sku.getId()!=null){
        	return ResponseEntity.ok(gson.toJson("SKU Inserted"));
        }else{
        	return ResponseEntity.ok(gson.toJson("SKU Updated"));
        }
    }
	@DeleteMapping(path={"/deleteSku/{id}"})
	public void deleteSku(@PathVariable Integer id){
		skuService.deleteSku(id);
	}
	
}
