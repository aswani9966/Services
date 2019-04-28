package com.location.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.location.dao.DepartmentDao;
import com.location.model.Department;
import com.location.repository.CategoryRepository;
import com.location.repository.DepartmentRepository;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{
	@Autowired
    DepartmentRepository departmentRepository;
	public Department createDepartmentData(Department departmentData) {
		return departmentRepository.saveAndFlush(departmentData);
	}

	public void deleteDepartmentData(Department departmentData) {
		departmentRepository.delete(departmentData);
		
	}

	public List<Department> getAllDepartmentData() {
		return departmentRepository.findAll();
	}
	public Department getDepartment(Integer departmentId) {
		return departmentRepository.findById(departmentId);
	}

	public List<Department> findAllDepartmentsByLocationId(Integer locationId) {
		return departmentRepository.findAllDepartmentsByLocationId(locationId);
	}
	
	public Department findDepartmentByNameLocationId(String departmentName, Integer locationId){
		return departmentRepository.findDepartmentByNameLocationId(departmentName, locationId);
	}

	@Override
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}


}
