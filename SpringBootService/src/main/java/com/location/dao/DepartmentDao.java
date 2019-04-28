package com.location.dao;

import java.util.List;

import com.location.model.Department;

public interface DepartmentDao {
	public Department createDepartmentData(Department departmentData);
	public void deleteDepartmentData(Department departmentData);
	public List<Department> getAllDepartmentData();
	public List<Department> findAllDepartmentsByLocationId(Integer locationId);
	public Department findDepartmentByNameLocationId(String departmentName, Integer locationId);
	public List<Department> findAll();
	public Department getDepartment(Integer departmentId);
}
