package com.employee.helper;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.employee.domain.Department;
import com.employee.domain.Employee;
import com.employee.input.EmployeeInput;
import com.employee.message.ResponseMessage;
import com.employee.message.ResponseStatus;
import com.employee.message.ResponseStatusCode;
import com.employee.repository.DepartmentRepo;
import com.employee.repository.EmployeeRepo;

@Service
public class EmployeeHelper {


	public static final Logger logger = LogManager.getLogger(EmployeeHelper.class);

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	DepartmentRepo departmentRepo;


	public ResponseStatus validation(BindingResult result) {
		if(result != null && result.hasErrors()){
			String errorMessage = null;
			for (int i = 0; i < result.getAllErrors().size(); i++) {
				if(!result.getAllErrors().get(i).getDefaultMessage().isEmpty()) {
					errorMessage = result.getAllErrors().get(i).getDefaultMessage();
					break;
				}
			}	
			return new ResponseStatus(ResponseStatusCode.STATUS_REQUIRED,errorMessage);
		}
		return null;
	}

	public ResponseMessage<Employee> addEmployee(@Valid EmployeeInput empInput, BindingResult result, HttpServletResponse res) {
		ResponseStatus status = null;
		Employee empEntity = null;
		try {
			status = validation(result);
			if (status == null) {
				Department department = departmentRepo.getDepartmentByDeptNameIgnoreCase(empInput.getDepartment().getDepartName());
				empEntity = employeeRepo.save(new Employee(empInput.getEmpName(),(department != null)?department:new Department(empInput.getDepartment().getDepartName())));
				res.setStatus(ResponseStatusCode.STATUS_OK);
				status = new ResponseStatus(ResponseStatusCode.STATUS_OK,"Employee added");
			} else {
				res.setStatus(ResponseStatusCode.STATUS_REQUIRED);
			}
		} catch (Exception e) {
			logger.error(e);
			status = new ResponseStatus(ResponseStatusCode.STATUS_INTERNAL_ERROR,"Internal Error");
		}
		return new ResponseMessage<>(status,empEntity);
	}

	public ResponseMessage<Employee> getEmployee(String empId, HttpServletResponse res) {
		ResponseStatus status = null;
		Employee empEntity = null;
		try {
			empEntity = employeeRepo.getEmployeeByEmpId(empId);
			if(empEntity == null) {
				res.setStatus(ResponseStatusCode.STATUS_NORECORD);
				status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD,"Employee not found");
			} else {
				res.setStatus(ResponseStatusCode.STATUS_OK);
				status = new ResponseStatus(ResponseStatusCode.STATUS_OK,"Success");
			}
		}
		catch (Exception e) {
			logger.error(e);
			status = new ResponseStatus(ResponseStatusCode.STATUS_INTERNAL_ERROR,"Internal Error");
		}
		return new ResponseMessage<>(status,empEntity);
	}

	public ResponseMessage<Employee> updateEmployee(@Valid EmployeeInput empInput,HttpServletResponse res) {
		ResponseStatus status = null;
		Employee empEntity = null;
		try {
			if(empInput.getId() != null && !empInput.getId().isEmpty()) {
				empEntity = employeeRepo.getEmployeeByEmpId(empInput.getId());
				if(empEntity != null) {
					Department department = departmentRepo.getDepartmentByDeptNameIgnoreCase(empInput.getDepartment().getDepartName());
					empEntity.setEmpName(empInput.getEmpName());
					empEntity.setDepartment((department != null)?department:new Department(empInput.getDepartment().getDepartName()));
					employeeRepo.save(empEntity);
					status = new ResponseStatus(ResponseStatusCode.STATUS_OK,"Employee deatils updated");
				} else {
					res.setStatus(ResponseStatusCode.STATUS_NORECORD);
					status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD,"Employee not found");
				}
			} else {
				res.setStatus(ResponseStatusCode.STATUS_REQUIRED);
				status = new ResponseStatus(ResponseStatusCode.STATUS_REQUIRED,"Employee id required");
			}
		} catch (Exception e) {
			logger.info(e);
		}
		return new ResponseMessage<>(status,empEntity);
	}

	public ResponseMessage<Employee> deleteEmployee(String empId, HttpServletResponse res) {
		ResponseStatus status = null;
		try {
			Employee empEntity = employeeRepo.getEmployeeByEmpId(empId);
			if(empEntity == null) {
				res.setStatus(ResponseStatusCode.STATUS_NORECORD);
				status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD,"Employee not found");
			} else {
				employeeRepo.delete(empEntity);
				res.setStatus(ResponseStatusCode.STATUS_OK);
				status = new ResponseStatus(ResponseStatusCode.STATUS_OK,"Success");
			}
		}
		catch (Exception e) {
			logger.error(e);
			status = new ResponseStatus(ResponseStatusCode.STATUS_INTERNAL_ERROR,"Internal Error");
		}
		return new ResponseMessage<>(status,null);
	}
}
