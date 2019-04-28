package com.location.serviceimpl;

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
import com.location.dto.SkuObject;
import com.location.model.Category;
import com.location.model.Department;
import com.location.model.Location;
import com.location.model.SKU;
import com.location.model.SubCategory;
import com.location.service.SkuService;
@Component
public class SkuServiceImpl implements SkuService{
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	LocationDao locationDao;
	@Autowired
	SubCategoryDao subCategoryDao;
	@Autowired
	DepartmentDao departmentDao;
	@Autowired
	SkuDao skuDao;
	
	@Override
	public SKU updateSku(SKU sku) {
		SKU sku1=null;
		sku1=skuDao.findSKUByName(sku.getName(), sku.getLocation_id(), sku.getDepartment_id(), sku.getCategory_id(), sku.getSubcategory_id());
		if(sku1 == null){
			sku1=skuDao.createSKU(sku);
		}
		return sku1;
	}

	@Override
	public void deleteSku(Integer id) {
		SKU sku= new SKU();
		sku.setId(id);
		skuDao.deleteSkuData(sku);		
	}

	@Override
	public List<SkuObject> getSkus() {
		List<SkuObject> response=new ArrayList<SkuObject>(); 
		SkuObject responseObj=null;
		
		List<Location> locationList=locationDao.getAllLocationData();
		Map<Integer,String> locations=new HashMap<Integer,String>();
		for (Location location : locationList) {
			locations.put(location.getId(), location.getName());
		}
		
		List<Department> departmentsList=departmentDao.getAllDepartmentData();
		Map<Integer,String> departments=new HashMap<Integer,String>();
		for (Department department : departmentsList) {
			departments.put(department.getId(), department.getName());
		}
		
		List<Category> categoriesList=categoryDao.getAllCategoryData();
		Map<Integer,String> categories=new HashMap<Integer,String>();
		for (Category category : categoriesList) {
			categories.put(category.getId(), category.getName());
		}
		List<SubCategory> subCategoriesList=subCategoryDao.getAllSubCategoryData();
		Map<Integer,String> subCategories=new HashMap<Integer,String>();
		for (SubCategory subCategory : subCategoriesList) {
			subCategories.put(subCategory.getId(), subCategory.getName());
		}
		
		List<SKU> responseDatas= skuDao.getAllSKUData();
		for (SKU sku : responseDatas) {
			responseObj = new SkuObject();
			responseObj.setId(sku.getId());
			responseObj.setName(sku.getName());
			responseObj.setLocationName(locations.get(sku.getLocation_id()));
			responseObj.setDepartmentName(departments.get(sku.getDepartment_id()));
			responseObj.setCategoryName(categories.get(sku.getCategory_id()));
			responseObj.setSubcategoryName(subCategories.get(sku.getSubcategory_id()));
			response.add(responseObj);
		}
		return response;
	}
	
}
