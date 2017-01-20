package com.jack;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * Hello world!
 *
 */
@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class App 
{
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
