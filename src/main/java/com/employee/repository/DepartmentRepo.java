package com.employee.repository;

import org.springframework.data.repository.CrudRepository;

import com.employee.domain.Department;

public interface DepartmentRepo extends CrudRepository<Department, Long> {

	Department getDepartmentByDeptId(Long deptId);
	
	Department getDepartmentByDeptNameIgnoreCase(String deptName);
}
