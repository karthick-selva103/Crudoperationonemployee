package com.employee.input;

import javax.validation.constraints.NotNull;

public class EmployeeInput {
	
	private String id;
	
	@NotNull(message="empName required")
	private String empName;
	
	private DepartmentInput department;
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public DepartmentInput getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentInput department) {
		this.department = department;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
