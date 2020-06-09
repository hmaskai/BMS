package com.shilymily.apigatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiGatewayServiceApplication.class, args);
  }

  @PostConstruct
  void init() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

}
