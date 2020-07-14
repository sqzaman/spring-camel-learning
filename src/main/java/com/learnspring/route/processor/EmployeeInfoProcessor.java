package com.learnspring.route.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.learnspring.model.Employee;

@Component
public class EmployeeInfoProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String employeeId = exchange.getIn().getHeader("employeeId", String.class);
		exchange.getIn().setBody(new Employee(employeeId, "Syed Quamruzzaman"));		
	}

}
