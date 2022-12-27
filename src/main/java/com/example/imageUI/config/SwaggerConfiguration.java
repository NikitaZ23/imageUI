//package com.example.imageUI.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger.web.*;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.List;
//
//@Configuration
//public class SwaggerConfiguration {
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//
////    @Bean
////    public Docket notesApi() {
////        return new Docket(DocumentationType.SWAGGER_2)
////                .select()
////                .apis(RequestHandlerSelectors.any())
////                .paths(PathSelectors.any())
////                .build()
////                .securitySchemes(List.of(apiKey()))
////                .securityContexts(List.of(securityContext()))
////                .pathMapping("/");
////    }
////
////    @Bean
////    UiConfiguration uiConfig() {
////        return UiConfigurationBuilder.builder()
////                .deepLinking(true)
////                .displayOperationId(true)
////                .defaultModelsExpandDepth(1)
////                .defaultModelExpandDepth(1)
////                .defaultModelRendering(ModelRendering.EXAMPLE)
////                .displayRequestDuration(true)
////                .docExpansion(DocExpansion.NONE)
////                .filter(false)
////                .maxDisplayedTags(null)
////                .operationsSorter(OperationsSorter.ALPHA)
////                .showExtensions(false)
////                .showCommonExtensions(false)
////                .tagsSorter(TagsSorter.ALPHA)
////                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
////                .validatorUrl(null)
////                .build();
////    }
////
////    private ApiKey apiKey() {
////        return new ApiKey("JWT ACCESS TOKEN", "Authorization", "header");
////    }
////
////    private SecurityContext securityContext() {
////        return SecurityContext.builder().securityReferences(defaultAuth()).build();
////    }
////
////    private List<SecurityReference> defaultAuth() {
////        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
////        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
////        authorizationScopes[0] = authorizationScope;
////        return List.of(new SecurityReference("JWT ACCESS TOKEN", authorizationScopes));
////    }
//}
