package com.kobkbs.graphsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.kobkbs.*")
@EnableCaching
public class GraphSvcApplication {
  
  public static void main(String[] args) throws Exception {
    SpringApplication.run(GraphSvcApplication.class, args);
  }

}
