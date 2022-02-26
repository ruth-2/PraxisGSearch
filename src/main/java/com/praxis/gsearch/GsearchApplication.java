package com.praxis.gsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class GsearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(GsearchApplication.class, args);
    }
}
