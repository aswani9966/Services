package com.location.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.location.dao.SkuDao;
import com.location.model.SKU;
import com.location.repository.SKURepository;
@Repository
public class SkuDaoImpl implements SkuDao{
	@Autowired
    SKURepository skuRepository;
	@Override
	public SKU findSKUByName(String skuName, Integer locationId,
			Integer departmentId, Integer categoryId, Integer subCategoryId) {
		return skuRepository.findSKUByName(skuName,locationId,departmentId,categoryId,subCategoryId);
	}
	@Override
	public SKU createSKU(SKU sku) {
		return skuRepository.saveAndFlush(sku);
	}
	@Override
	public List<SKU> getAllSKUData() {
		return skuRepository.findAll();
	}
	@Override
	public void deleteSkuData(SKU sku) {
		skuRepository.delete(sku);		
	}
}
