package com.employee.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tbl_employee")
public class Employee {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "emp_id", nullable = false, unique = true)
	private String empId;
	
	@Column(name="emp_name")
	private String empName;

	@OneToOne (targetEntity = Department.class, cascade = CascadeType.PERSIST)
	@JoinColumn (name = "department")
	private Department department;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee(String empName, Department department) {
		super();
		this.empName = empName;
		this.department = department;
	}

	public Employee() {
		super();
	}
	
	
}
