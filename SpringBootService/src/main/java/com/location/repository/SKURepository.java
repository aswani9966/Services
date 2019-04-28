package com.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.location.model.SKU;
@Repository
public interface SKURepository extends JpaRepository<SKU, Long>{
	@Query(value = "SELECT * FROM sku_data sd WHERE sd.name=?1 and sd.location_id=?2 and sd.department_id = ?3 and sd.category_id=?4 and sd.subcategory_id=?5", nativeQuery = true)
	SKU findSKUByName(String skuName, Integer locationId, Integer departmentId,
			Integer categoryId, Integer subCategoryId);

}
