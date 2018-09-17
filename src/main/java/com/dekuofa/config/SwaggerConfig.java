package com.dekuofa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author dekuofa <br>
 * @date 2018-08-23 <br>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.host}")
    private String host;
    @Value("${swagger.path}")
    private String path;

    @Bean
    public Docket api(ApiInfo apiInfo) {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .host(host)
                .pathMapping(path)
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(securityContexts())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    // swagger2 全局使用 jwt 配置 参照 http://fish119.site/2018/01/04/Swagger2-%E9%9D%9E%E5%85%A8%E5%B1%80Header%E5%8F%82%E6%95%B0%EF%BC%88Token%EF%BC%89%E9%85%8D%E7%BD%AE/
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!/?login).*$"))
                        .build()
        );
    }

    private List<SecurityReference> defaultAuth() {
        List<AuthorizationScope> authorizationScopes = new ArrayList<>();
        authorizationScopes.add(new AuthorizationScope("global", "accessEverything"));
        return Collections.singletonList(
                new SecurityReference("Authorization",
                         authorizationScopes.toArray(new AuthorizationScope[0])));
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    @Bean
    public ApiInfo apiInfo(Contact contact) {

        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful API")
                .description("rest api 文档构建利器")
                .termsOfServiceUrl("http://blog.csdn.net/itguangit")
                .contact(contact)
                .version("1.0")
                .build();
    }

    @Bean
    public Contact contact() {
        return new Contact("dekuofa", null, "xiwenandlejian@gmail.com");
    }
}
