// package com.kobkbs.graphsvc.config;
//
// import org.springframework.context.annotation.Configuration;
//
// import io.swagger.v3.oas.annotations.OpenAPIDefinition;
// import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
// import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
// import io.swagger.v3.oas.annotations.info.Info;
// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import io.swagger.v3.oas.annotations.security.SecurityScheme;
// import io.swagger.v3.oas.annotations.security.SecuritySchemes;
// import io.swagger.v3.oas.annotations.info.Contact;
//
// @Configuration
// @OpenAPIDefinition(info = @Info(title = "Kuching Old Bazaar Knowledge-based System API", version = "1.0", description = "API documentation for Kuching Old Bazaar Knowledge-based System", contact = @Contact(name = "Boonlert Randy", email = "boonlertrandy@gmail.com")), security = {@SecurityRequirement(name = "bearerToken"), @SecurityRequirement(name = "cookie")})
// @SecuritySchemes({
//   @SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT"),
//   @SecurityScheme(name = "cookie", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER, paramName = "cookie")
// })
// public class SwaggerConfig {
//
// }
