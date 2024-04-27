package com.kobkbs.graphsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = "com.kobkbs.*")
public class GraphSvcApplication {
  
  public static void main(String[] args) throws Exception {
    SpringApplication.run(GraphSvcApplication.class, args);
  }

  public WebMvcConfigurer corsConfigurer(){
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/").allowedOrigins("https://kob-kbs-react-5e24760e9974.herokuapp.com");
      }
    };
  }
}
