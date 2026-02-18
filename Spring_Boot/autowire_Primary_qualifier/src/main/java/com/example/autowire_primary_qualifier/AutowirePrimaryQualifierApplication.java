package com.example.autowire_primary_qualifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AutowirePrimaryQualifierApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AutowirePrimaryQualifierApplication.class, args);

        Programmer programmer = context.getBean(Programmer.class);
        programmer.create();
    }
}
