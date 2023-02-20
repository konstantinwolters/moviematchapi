package com.example.moviematchapi.config;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.example.moviematchapi.proxy")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class ProjectConfig {
}
