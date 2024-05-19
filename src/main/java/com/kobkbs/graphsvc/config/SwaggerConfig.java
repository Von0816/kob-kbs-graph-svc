package com.kobkbs.graphsvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"!prod && dev"})
public class SwaggerConfig {

}
