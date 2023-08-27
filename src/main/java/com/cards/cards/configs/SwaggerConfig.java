package com.cards.cards.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	/**
	 * 
	 * @return
	 */
	@Bean
	public OpenAPI customOpenAPI() {
		final String securitySchemeName = "bearerAuth";
		final String apiTitle = String.format("%s API", StringUtils.capitalize("Cards Assigment"));
		String apiVersion = "1.0";
		return new OpenAPI().addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
				.components(
						new Components().addSecuritySchemes(securitySchemeName,
								new SecurityScheme().name(securitySchemeName).type(SecurityScheme.Type.HTTP)
										.scheme("bearer").bearerFormat("JWT")))
				.info(new Info().title(apiTitle).version(apiVersion));
	}
//	private SecurityScheme createAPIKeyScheme() {
//		return new SecurityScheme().type(SecurityScheme.Type.HTTP).bearerFormat("JWT").scheme("bearer");
//	}
//
//	@Bean
//	public OpenAPI openAPI() {
//		return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
//				.components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
//				.info(new Info().title("My REST API").description("Some custom description of API.").version("1.0")
//						.contact(new Contact().name("Sallo Szrajbman").email("www.baeldung.com")
//								.url("salloszraj@gmail.com"))
//						.license(new License().name("License of API").url("API license URL")));
//	}
}