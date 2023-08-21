package com.example.restfullwebservice.config;

//**
//https://velog.io/@kjgi73k/Springboot3에-Swagger3적용하기


import io.swagger.v3.core.model.ApiDescription;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring boot Swagger 3.0 적용하기
 *
 * https://dev-youngjun.tistory.com/258
 *
 */
//@OpenAPIDefinition(
//        info=@Info(title="API 명세서",
//                description = "Spring boot API",
//                version = "v1"
//        )
//)
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        Contact contact=new Contact();
        contact.setName("Hong Gil Dong");
        contact.setUrl("https://macaronics.net");
        contact.setUrl("honggil@gmail.com");

        return new Info()
                .title("Springdoc 테스트")
                .description("Springdoc을 사용한 Swagger UI 테스트")
                .contact(contact)
                .version("1.0.0");
    }


}