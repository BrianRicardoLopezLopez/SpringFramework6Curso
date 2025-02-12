package com.brian.cursos.springboot.error.springboot_error;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.brian.cursos.springboot.error.springboot_error.models.domain.User;

@Configuration
public class AppConfig {

    @Bean
    public List<User> user() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Brian", "Lopez"));
        users.add(new User(2L, "Andres", "Mena"));
        users.add(new User(3L, "Maria", "Perez"));
        users.add(new User(4L, "Josefa", "Ramirez"));
        users.add(new User(5L, "Ale", "Gutierrez"));
        return users;
    }

}
