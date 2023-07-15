package com.example.tutoring.config;

import java.util.HashSet;
import java.util.Set;
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


@Configuration
@EnableSwagger2
public class SwaggerConfig  {

  private String version;
  private String title;


  @Bean
  public Docket api() {
    version = "0.0.1";
    title = "TUTORING Technical Task " + version;
    return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.example.tutoring")) // 컨트롤러가 있는 패키지 지정
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo(title, version));
  }

  private Set<String> getConsumeContentTypes() {
    Set<String> consumes = new HashSet<>();
    consumes.add("application/json;charset=UTF-8");
    consumes.add("application/x-www-form-urlencoded");
    return consumes;
  }

  private Set<String> getProduceContentTypes() {
    Set<String> produces = new HashSet<>();
    produces.add("application/json;charset=UTF-8");
    return produces;
  }


  private ApiInfo apiInfo(String title, String version) {

    return new ApiInfoBuilder()
        .title(title)
        .description("튜토링 백엔드 기술과제의 api 명세입니다")
        .version(version)
        .contact(new Contact("황순욱",null,"bravadosw@naver.com"))
        .build();
  }
}
