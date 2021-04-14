package com.employee.input;

import javax.validation.constraints.NotNull;

public class DepartmentInput {

	@NotNull(message="departName required")
	private String departName;

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}
	
}
