package com.location.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.location.dao.SubCategoryDao;
import com.location.model.SubCategory;
import com.location.repository.SubCategoryRepository;
@Repository
public class SubCategoryDaoImpl implements SubCategoryDao{
	@Autowired
    SubCategoryRepository subCategoryRepository;
	public SubCategory createSubCategoryData(SubCategory subCategoryData) {
		return subCategoryRepository.saveAndFlush(subCategoryData);
	}

	public void deleteSubCategoryData(SubCategory subCategoryData) {
		subCategoryRepository.delete(subCategoryData);
		
	}

	public List<SubCategory> getAllSubCategoryData() {
		return subCategoryRepository.findAll();
	}

	public List<SubCategory> findAllSubCategorisByCategoryId(Integer categoryId) {
		return subCategoryRepository.findAllSubCategorisByCategoryId(categoryId);
	}
	public SubCategory getSubCategory(Integer subCategoryId) {
		return subCategoryRepository.findById(subCategoryId);
	}
	
	public SubCategory findSubCategoryByNameCategoryId(String subCategoryName, Integer categoryId){
		return subCategoryRepository.findSubCategoryByNameCategoryId(subCategoryName, categoryId);
	}

	@Override
	public List<SubCategory> findAll() {
		return subCategoryRepository.findAll();
	}

	@Override
	public Long findCountByCategory(Integer categoryId) {
		return subCategoryRepository.findCategoryMappedCount(categoryId);
	}

}
