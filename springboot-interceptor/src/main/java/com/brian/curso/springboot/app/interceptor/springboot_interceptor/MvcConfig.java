package com.brian.curso.springboot.app.interceptor.springboot_interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

    @Autowired
    @Qualifier("timeInterceptor")
    private HandlerInterceptor timeInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // esta es general donde incluye a todas las url 
        //registry.addInterceptor(timeInterceptor);
        //decidir en ruta vamos aplicar el interceptor
        registry.addInterceptor(timeInterceptor).addPathPatterns("/app/bar", "/app/foo");
        // aqui es donde podemos decir donde vamos a excluir es lo contrario del otro
        //registry.addInterceptor(timeInterceptor).excludePathPatterns("/app/bar", "/app/foo");
    }

    

}
