package com.brian.springboot.client.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brian.springboot.client.models.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class AppController {

    @GetMapping("/list")
    public List<Message> List(){
        return Collections.singletonList(new Message("Test List"));
    } 

    @PostMapping("/create")
    public Message create(@RequestBody Message message) {
        System.out.println("Mensaje guardado : " + message);
        
        return message;
    }

    @GetMapping("/authorized")
    public Map<String, String> authorized(@RequestParam String code){
            return Collections.singletonMap("code", code);
        
    }
    

}
