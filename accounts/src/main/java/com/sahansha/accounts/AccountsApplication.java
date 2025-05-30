package com.sahansha.accounts;

import com.sahansha.accounts.dto.AccountsContactInfoDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(AccountsContactInfoDTO.class)
@OpenAPIDefinition(
		info = @Info(
				title = "Account Microservice",
				description = "Account Microservice is part of Bank Loan application",
				version = "v1",
				contact = @Contact(
						name = "Sahansha Khan",
						email = "sahanshakhan@gmail.com",
						url = "http://sahansha.com"
				),
				license = @License(
						name = "Bank Loan Licencing",
						url = "http://sahansha.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Refer to following link",
				url = "http://sahansha.com"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
