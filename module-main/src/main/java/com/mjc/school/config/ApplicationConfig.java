package com.mjc.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@ComponentScan(basePackages = {"com.mjc.school"})
public class ApplicationConfig {
    @Bean("keyboardScanner")
    public Scanner keyboardScanner() {
        return new Scanner(System.in);
    }
}
