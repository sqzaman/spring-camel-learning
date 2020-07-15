package com.learnspring.route.splitter;

import java.util.List;

import com.learnspring.model.Employee;
import com.learnspring.model.EmployeeGroup;

public class EmployeeSplitter {
	
	public List<Employee> splitEmployeeFromGroup(EmployeeGroup body) {
		
		return body.getEmployees();
		
	}

}
