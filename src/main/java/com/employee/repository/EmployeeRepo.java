package com.employee.repository;

import org.springframework.data.repository.CrudRepository;

import com.employee.domain.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, String> {

	Employee getEmployeeByEmpId(String empId);
	
}
