package ru.hexlet.lections.trees.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hexlet.lections.trees.repository.PersonRepository;
import ru.hexlet.lections.trees.service.PersonService;
import ru.hexlet.lections.trees.service.impl.PersonServiceImpl;

import java.util.List;

@Configuration
public class TreesAppConfig {
    @Bean
    public PersonService personService(PersonRepository personRepository) {
        return new PersonServiceImpl(personRepository);
    }

    @Bean
    public GroupedOpenApi publicUserApi() {
        return GroupedOpenApi.builder()
                .group("People")
                .pathsToMatch("/person/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info().title("Application API")
                        .version("v1.0")
                        .description("example")
                        .license(new License().name("Apache 2.0")
                                .url("http://springdoc.org"))
                        .contact(new Contact().name("username")
                                .email("test@gmail.com")))
                .servers(List.of(new Server().url("http://localhost:8080")
                                .description("Dev service")));
    }
}
