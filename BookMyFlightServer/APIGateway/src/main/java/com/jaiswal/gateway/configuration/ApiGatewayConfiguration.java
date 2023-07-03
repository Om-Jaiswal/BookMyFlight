package com.jaiswal.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f.addRequestHeader("MyHeader", "MyURI").addRequestHeader("Param", "MyValue"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/flight-management-system/**")
						.uri("lb://flight-management-system"))
				.route(p -> p.path("/search-flights/**")
						.uri("lb://search-flights"))
				.route(p -> p.path("/user-profile/**")
						.uri("lb://user-profile"))
				.route(p -> p.path("/book-flights/**")
						.uri("lb://book-flights"))
				.build();
	}
}