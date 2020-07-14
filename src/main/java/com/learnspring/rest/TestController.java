package com.learnspring.rest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.learnspring.model.Employee;


@RestController
@RequestMapping("/api/v1")
public class TestController {
	
	@Autowired
	ProducerTemplate template;
	
	@GetMapping("/getEmployeeInfo/{employeeId}")
	public Employee getEmployee(@PathVariable("employeeId") final String id) {

		Exchange exchange = template.send("{{routes.employeeInfo}}", new Processor() {
            public void process(Exchange exchange) throws Exception {
                exchange.getIn().setHeader("employeeId", id);
            }

        });
		
		return exchange.getIn().getBody(Employee.class);
		
	}

}
