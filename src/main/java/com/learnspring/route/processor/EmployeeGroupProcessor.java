package com.learnspring.route.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.learnspring.model.Employee;

@Component
public class EmployeeGroupProcessor  implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		Employee employee = exchange.getIn().getBody(Employee.class);
		
		exchange.getIn().setBody(String.format("%s: %s", employee.getEmployeeId(), employee.getEmployeeName()));		
		
	}

}
