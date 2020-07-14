package com.learnspring.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import com.learnspring.route.processor.EmployeeInfoProcessor;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmployeeInfoRoute extends RouteBuilder {
	
	private final EmployeeInfoProcessor processor;

	@Override
	public void configure() throws Exception {
		from("{{routes.employeeInfo}}")
		.log(LoggingLevel.INFO, "${in.headers}")
		.process(processor)
		.log(LoggingLevel.INFO, "${in.body}")
		.end();
		
	}

}
