package com.brian.springboot.di.app.springboot_di;

import com.brian.springboot.di.app.springboot_di.repositories.ProductRepository;
import com.brian.springboot.di.app.springboot_di.repositories.ProductRepositoryJson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Bean("productJson")
    ProductRepository productRepositoryJson(){
        return new ProductRepositoryJson();
    }
}
