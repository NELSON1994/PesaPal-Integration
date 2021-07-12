package com.pesapal.integration.pesapal.config;

import com.pesapal.integration.pesapal.model.User;
import com.pesapal.integration.pesapal.repository.UserRepository;
import com.pesapal.integration.pesapal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Configuration
public class AppConfigs {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    // LINK---> http://localhost:9099/swagger-ui.html#
//    @Bean
//    public Docket setSwagger(){
//        return new Docket(DocumentationType.SWAGGER_2).select()
//                .apis(RequestHandlerSelectors.basePackage("com.cma.cmaproject")).build();
//    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "PesaPal Integration",
                "Integration To PesaPal Application",
                "1.0",
                "@PesaPal Integration 2021 ",
                new Contact("Nelson Moses Otieno' ", "", "nelson62moses@gmail.com"),
                "",
                "",
                Collections.emptyList());
    }

    @Bean
    public Docket setSwagger(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*")
                        .allowedOrigins("*")
                        .maxAge(3600);
            }
        };
    }

    //added now ===to enable passing JWT on swagger
   private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");

    }
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }




    //Create default Admin to log in test
    @PostConstruct
    private void createDefaultUser(){
        String username = "PESAPALADMIN";
        String password = "pesapaladmin";
        String encr=userService.encryptPassword(password);
        User user=userRepository.findByUsername(username);
        if(user==null){
            User user1 = new User();
            user1.setUsername(username.toUpperCase());
            user1.setPassword(encr);
            userRepository.save(user1);

            System.out.println("======== DEFAULT ADMIN CREATED ================");
        }
        else{
            System.out.println("=============== DEFAULT ADMIN ALREADY CREATED ============");
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>    USERNAME : "+username);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>    PASSWORD  : "+password);

    }



}
