package com.location.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.location.model.Department;
import com.location.model.Location;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	@Query(value = "SELECT * FROM department d WHERE d.location_id = ?1", nativeQuery = true)
	List<Department> findAllDepartmentsByLocationId(Integer locationId);
	@Query(value = "SELECT * FROM department d WHERE d.name = ?1 and d.location_id=?2", nativeQuery = true)
	Department findDepartmentByNameLocationId(String departmentName, Integer locationId);
	@Query(value = "SELECT * FROM department d WHERE d.id = ?1", nativeQuery = true)
	Department findById(Integer departmentId);
}
