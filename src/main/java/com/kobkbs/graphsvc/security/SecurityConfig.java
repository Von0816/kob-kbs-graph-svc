// package com.kobkbs.graphsvc.security;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;
//
// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {
//
//   @Bean
//   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//     http.oauth2Client().and().oauth2Login().tokenEndpoint().and().userInfoEndpoint();
//
//     http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
//
//     http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.GET, "/*").permitAll().anyRequest().hasRole("ADMIN")).formLogin(form -> form.loginPage("/login").permitAll()).logout(logout -> logout.permitAll());
//
//     return http.build();
//   }
// }
