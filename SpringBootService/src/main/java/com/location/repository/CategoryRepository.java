package com.location.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.location.model.Category;
import com.location.model.Department;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query(value = "SELECT * FROM category d WHERE d.department_id = ?1", nativeQuery = true)
	List<Category> findAllCategorisByDeptId(Integer departmentId);
	@Query(value = "SELECT * FROM category c WHERE c.name = ?1 and c.department_id=?2", nativeQuery = true)
	Category findCategoryByNameDepartmentId(String categoryName, Integer departmentId);
	@Query(value = "SELECT * FROM category c WHERE c.id = ?1", nativeQuery = true)
	Category findById(Integer categoryid);
}
