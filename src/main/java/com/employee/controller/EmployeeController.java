package com.employee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.domain.Employee;
import com.employee.helper.EmployeeHelper;
import com.employee.input.EmployeeInput;
import com.employee.message.ResponseMessage;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeHelper employeeHelper;
	
	@PostMapping("/addemployee")
	public ResponseMessage<Employee> addEmployee(@Valid @RequestBody EmployeeInput empInput, BindingResult result,
			HttpServletRequest request,final HttpServletResponse res){
		return employeeHelper.addEmployee(empInput,result,res);
	}
	
	@PutMapping("/updateemployee")
	public ResponseMessage<Employee> updateEmployee(@Valid @RequestBody EmployeeInput empInput,HttpServletRequest request,
			final HttpServletResponse res){
		return employeeHelper.updateEmployee(empInput,res);
	}
	
	@GetMapping("/getemployee")
	public ResponseMessage<Employee> getEmployee(@RequestParam(name="empId",required = true) String empId, HttpServletRequest request,
			final HttpServletResponse res){
		return employeeHelper.getEmployee(empId,res);
	}
	
	@DeleteMapping("/deleteemployee")
	public ResponseMessage<Employee> deleteEmployee(@RequestParam(name="empId",required = true) String empId, HttpServletRequest request,
			final HttpServletResponse res){
		return employeeHelper.deleteEmployee(empId,res);
	}
	
}
