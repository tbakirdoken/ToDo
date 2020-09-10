package com.springboot.todo.ToDoList.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BcryptConfig {

    // BCrypt will internally generate a random salt.
    // This is important to understand because it means that each call will have a different result,
    // and so we need to only encode the password once.
    // BCrypt algorithm generates a String of length 60
    @Bean
    public BCryptPasswordEncoder by() {
        return new BCryptPasswordEncoder();
    }
}
