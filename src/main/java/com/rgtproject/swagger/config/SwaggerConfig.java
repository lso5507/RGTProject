package com.rgtproject.swagger.config;


import java.util.Arrays;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@OpenAPIDefinition(
	info = @Info(title = "알지티 API",
		description = "알맞는 명세서를 선택하세요.",
		version = "v1"))
@Configuration
public class SwaggerConfig {
	@Value("${domain.name}")
	private String domainName;

	@Bean
	public GroupedOpenApi MemberGroup() {
		return GroupedOpenApi.builder()
			.group("사용자API")
			.packagesToScan("com.rgtproject.member.controller.MemberController")
			.build();
	}
	@Bean
	public GroupedOpenApi OrderGroup() {
		return GroupedOpenApi.builder()
			.group("주문API")
			.packagesToScan("com.rgtproject.order.controller.OrderController")
			.build();
	}

	@Bean
	public OpenAPI JwtOpenAPI() {
		return new OpenAPI()
			.servers(Arrays.asList(
				new Server().url(domainName).description("HTTPS Server"))
			)
			.components(new Components()
				.addSecuritySchemes("JWT",
					new SecurityScheme()
						.type(SecurityScheme.Type.HTTP)
						.scheme("bearer")
						.bearerFormat("JWT"))
				);
	}


}