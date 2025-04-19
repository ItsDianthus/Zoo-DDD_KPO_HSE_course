package com.proj.KPO_DZ_2.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI zooManagementOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Zoo Management API")
                        .version("v1.0")
                        .description("Зоопарк: животные, вольеры, расписание кормлений")
                        .contact(new Contact()
                                .name("Zoo Team")
                                .email("support@zoo-example.com")
                        )
                );
    }
}
