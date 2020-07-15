package com.learnspring.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.learnspring.route.Aggregator.EmployeeAggregatorStrategy;
import com.learnspring.route.processor.EmployeeGroupProcessor;
import com.learnspring.route.splitter.EmployeeSplitter;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class EmployeeGroupRoute extends RouteBuilder {
	
	private final EmployeeGroupProcessor processor;
	private final EmployeeAggregatorStrategy aggregator;

	@Override
	public void configure() throws Exception {
		from("{{routes.employeeGroup}}")
		.log(LoggingLevel.INFO, "plain body before split: " + "${in.body}")
		.split().method(new EmployeeSplitter(), "splitEmployeeFromGroup")
		.log(LoggingLevel.INFO, "body after split: " + "${in.body}")
		.process(processor)
		.log(LoggingLevel.INFO,  "body after process: " + "${in.body}")
		.aggregate(constant(true), aggregator).completionTimeout(1000)
		.log(LoggingLevel.INFO, "body after aggregation: "+"${in.body}")
		.end();
		
	}

}
