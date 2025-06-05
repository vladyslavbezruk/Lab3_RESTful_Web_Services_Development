package com.lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Анотація для позначення головного класу Spring Boot додатку
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        // Запуск додатку Spring Boot
        SpringApplication.run(Main.class, args);
    }
}