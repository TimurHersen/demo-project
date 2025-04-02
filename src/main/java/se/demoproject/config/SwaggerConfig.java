package se.demoproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI workOrderOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("WorkOrder Management API")
                        .description("API for creating, assigning and executing work orders")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Timur Hersen")
                                .email("timur.hersen@gmail.com")
                                .url("https://github.com/TimurHersen/demo-project")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local development server")
                ));
    }

}
