package com.shilymily.cloudconfigservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableConfigServer
@PropertySource("/service-config/application.yml")
public class CloudConfigServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigServiceApplication.class, args);
    }

}
