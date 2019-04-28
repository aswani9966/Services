package com.location.dao;

import java.util.List;

import com.location.model.Category;
import com.location.model.SKU;

public interface SkuDao {
	public SKU findSKUByName(String skuName, Integer locationId, Integer departmentId, Integer categoryId, Integer subCategoryId);
	public SKU createSKU(SKU sku);
	public List<SKU> getAllSKUData();
	public void deleteSkuData(SKU sku);
}

