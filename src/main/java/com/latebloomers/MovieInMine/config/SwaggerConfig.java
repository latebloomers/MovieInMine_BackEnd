package com.latebloomers.MovieInMine.config;//package com.example.sideproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
//
//import java.util.ArrayList;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket apiV1() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("groupName1")
//                .select()
//                .apis(RequestHandlerSelectors.
//                        basePackage("com.example.sideproject"))
//                .paths(PathSelectors.ant("/v1/api/**"))
//                .build()
//                .apiInfo(apiInfo());
//    }
//
//    @Bean
//    public Docket apiV2() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .useDefaultResponseMessages(false)
//                .groupName("groupName2")
//                .select()
//                .apis(RequestHandlerSelectors.
//                        basePackage("com.example.sideproject"))
//                .paths(PathSelectors.ant("/v2/api/**"))
//                .build()
//                .apiInfo(apiInfo());
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfo(
//                "Title",
//                "Description",
//                "version 1.0",
//                "https://naver.com",
//                new Contact("Contact Me", "https://daum.net", "colt@colt.com"),
//                "Edit Licenses",
//                "https://naver.com",
//                new ArrayList<>()
//        );
//    }
//}


@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

}