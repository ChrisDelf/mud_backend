package com.dev.mud_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.data.domain.Pageable;

@Configuration
@EnableSwagger2
public class Swagger2Config
{
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2).select()
                //                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.dev.mud_backend"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false) // Allows only my exception responses
                .ignoredParameterTypes(Pageable.class) // allows only my paging parameter list
                .apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo()
    {
        return new ApiInfoBuilder().title("Mud Back End Starting Project")
                .description("A starting application for developing Java Spring Back End Projects")
                .contact(new Contact("Chris Delfaus", "http://www.lambdaschool.com", "ChrisDel@lambdaschool.com"))
                .license("MIT")
                .licenseUrl("https://github.com/ChrisDelf/mud_backend")
                .version("1.0.0")
                .build();
    }
}
