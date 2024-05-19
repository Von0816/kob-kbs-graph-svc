package com.kobkbs.graphsvc.controller.v1;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "https://kob-kbs-react-5e24760e9974.herokuapp.com")
@RestController
@RequestMapping("/api/v1/hello-world")
@RequiredArgsConstructor
public class HelloWorld {

  @GetMapping
  public String helloWorld() {
    return "Hello World";
  }
}
