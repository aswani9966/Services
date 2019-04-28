package com.location.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.location.model.Category;
import com.location.model.SubCategory;
@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>{
	@Query(value = "SELECT * FROM subcategory s WHERE s.category_id = ?1", nativeQuery = true)
	List<SubCategory> findAllSubCategorisByCategoryId(Integer categoryId);
	@Query(value = "SELECT * FROM subcategory s WHERE s.id = ?1", nativeQuery = true)
	SubCategory findBySubCategoryId(Integer subCategoryId);
	@Query(value = "SELECT * FROM subcategory s WHERE s.name = ?1 and s.category_id=?2", nativeQuery = true)
	SubCategory findSubCategoryByNameCategoryId(String subCategoryName, Integer categoryId);
	@Query(value = "SELECT * FROM subcategory s WHERE s.id = ?1", nativeQuery = true)
	SubCategory findById(Integer subCategoryId);
	@Query(value = "SELECT * FROM subcategory s WHERE s.category_id = ?1", nativeQuery = true)
	Long findCategoryMappedCount(Integer categoryId);
}
