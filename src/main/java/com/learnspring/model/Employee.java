package com.learnspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	private String employeeId;
	private String employeeName;
	
	@Override
	public String toString() {

		return String.format("Employee Id: %s, Emaplyee Name: %s", this.getEmployeeId(), this.getEmployeeName());
	}

	
	
	
	
	
	
	
	

}
