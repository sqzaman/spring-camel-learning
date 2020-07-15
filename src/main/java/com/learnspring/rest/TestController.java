package com.learnspring.rest;

import java.util.Arrays;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.learnspring.model.Employee;
import com.learnspring.model.EmployeeGroup;

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
				exchange.getIn().setBody(new Employee("TEST", "Syed Rafael"));
			}

		});

		return exchange.getIn().getBody(Employee.class);

	}

	@GetMapping("/getEmployeesFromGroup/{groupName}")
	public Object getAllEmployeesFromGroup(@PathVariable("groupName") final String groupName) {

		EmployeeGroup group = new EmployeeGroup(groupName, Arrays.asList(new Employee("1001ED12", "Tom"),
				new Employee("1001ED12", "Benton"), new Employee("1001ED12", "Chirs")));

		Endpoint ep = template.getCamelContext().getEndpoint("{{routes.employeeGroup}}");
		Exchange ex = template.getCamelContext().getEndpoint("{{routes.employeeGroup}}").createExchange();

		ex.getIn().setBody(group);
		Exchange exchange = template.send(ep, ex);

		return exchange.getIn().getBody();

	}

}
