package com.joystickjudgement.msgame.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfiguration {

    private final static String LICENSE = "https://github.com/Joystick-Judgement/joystick-judgement-backend/blob/main/LICENSE";

    private final static String API_DESCRIPTION = """
                    API that allows the management of electronic games and also that have reviews attached to them.
                   
                    Main features:
                        * Games management by admin
                            * Game registration with image upload in bucket
                            * Game update, deletion and search by custom filters and custom sort
                        * Game reviews by anonymous users
                        * Email delivery of spam/malicious games to the admins by webhook.
                        
                    Backend contributors:
                        * Github
                            * https://github.com/Duduxs
                        * Linkedin
                            * https://www.linkedin.com/in/eduarddojose/
            """;

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Microservice Game API")
                                .version("1.0")
                                .summary("API that allows the management of electronic games and also that have reviews attached to them.")
                                .description(API_DESCRIPTION)
                                .license(
                                        new License()
                                                .name("MIT LICENSE")
                                                .url(LICENSE)
                                                .identifier("MIT")
                                )
                                .termsOfService(LICENSE)
                                .contact(
                                        new io.swagger.v3.oas.models.info.Contact()
                                                .name("JoystickJudgement")
                                                .url("https://github.com/Joystick-Judgement")
                                )
                );
    }

}
