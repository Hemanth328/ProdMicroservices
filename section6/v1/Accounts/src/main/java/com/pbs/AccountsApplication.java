package com.pbs;

import com.pbs.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(value = AccountsContactInfoDto.class)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST API Documentation",
                description = "Hemanth Accounts microservice RESTAPI Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Hemanth Kumar",
                        email = "crazymail@gmail.com",
                        url = "https://www.hemanthkumar.com"
                ),
                license = @License(
                        name = "Apache2.4",
                        url = "https://www.apache.org"
                )
        ),

        externalDocs = @ExternalDocumentation(
                description = "Hemanth Accounts microservice REST API Documentation",
                url = "http://localhost:8080/swagger-ui/index.html"
        )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
