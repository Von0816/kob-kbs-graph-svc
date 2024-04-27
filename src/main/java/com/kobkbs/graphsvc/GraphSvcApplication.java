package com.kobkbs.graphsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.kobkbs.*")
public class GraphSvcApplication {
  
  public static void main(String[] args) throws Exception {
    SpringApplication.run(GraphSvcApplication.class, args);
  }

}
