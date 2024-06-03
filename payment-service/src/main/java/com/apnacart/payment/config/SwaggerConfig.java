package com.apnacart.payment.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(servers = { @Server(url = "https://localhost:8282/")},
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Payment Service APIs", description = "This lists all the Order Service API Calls. The Calls are OAuth2 secured, "
                + "so please use your client ID and Secret to test them out.",
                version = "v1.0"))
@SecurityScheme(
        name = "security_auth",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(authorizationCode = @OAuthFlow(
                authorizationUrl = "http://localhost:8282/realms/master/protocol/openid-connect/auth",
                tokenUrl = "http://localhost:8282/realms/master/protocol/openid-connect/token",
                scopes = {
                        @OAuthScope(name = "openid", description = "openid scope")
                }
        ))


)
public class SwaggerConfig {


    @Bean
    public OpenAPI myOpenAPI() {
//        Contact contact = new Contact();
//        contact.setEmail("ExpertAlerts-ATeam@JohnDeere.com");
//        contact.setName("Machine Insights Team!!");

//        License license = new License();
//        license.setName("Apache License Version 2.0");
//        license.setUrl("https://www.apache.org/licenses/LICENSE-2.0");
        return new OpenAPI().info(new Info().title("Payment Api")
                .description("Payment place api...")
//                .contact(contact)
//                .license(license)
                .version("0.0.1"));
    }
}
