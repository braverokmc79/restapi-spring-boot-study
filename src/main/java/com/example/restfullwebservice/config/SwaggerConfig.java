package com.example.restfullwebservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//**
//https://velog.io/@kjgi73k/Springboot3에-Swagger3적용하기


/**
 * Spring boot Swagger 3.0 적용하기
 *
 * https://dev-youngjun.tistory.com/258
 *
 */
@OpenAPIDefinition(
        info=@Info(title="API 명세서",
                description = "Spring boot API",
                version = "v1"
        )
)
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

        @Bean
        public GroupedOpenApi chatOpenApi() {
                String[] paths = {"/v1/**"};

                return GroupedOpenApi.builder()
                        .group("채팅서비스 API v1")
                        .pathsToMatch(paths)
                        .build();
        }



}
