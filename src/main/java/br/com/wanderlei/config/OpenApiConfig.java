package br.com.wanderlei.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI custonOpenApi() {
        return new OpenAPI()
                .info(new Info()
                    .title("REST API´s RestFull from 0 with java, Spring Boot, Kubernets and Docker")
                        .version("v1")
                        .description ("REST API´s RestFull from 0 with java, Spring Boot, Kubernets and Docker")
                        .termsOfService ("https://github.com/WanderleiBolzan")
                        .license (new License ()
                                .name("Apache 2")
                                .url("https://github.com/WanderleiBolzan")
                        )
                ) ;

    }

}
