package com.sahansha.cards;

import com.sahansha.cards.dto.CardsContactInfoDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.sahansha.cards.controller") })
@EnableJpaRepositories("com.sahansha.cards.repository")
@EntityScan("com.sahansha.cards.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(CardsContactInfoDTO.class)
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "Bank Loan Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Sahansha khan",
						email = "sahansha@gmail.com",
						url = "https://www.sahansa.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.sahansha.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Bank Loan Cards microservice REST API Documentation",
				url = "https://www.sahansha.com"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}
}
