package org.example.springboot_web_springdatajpa.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String greetings(){

        return "welcome to springboot practice!!";
    }

    @RequestMapping("/login")
    public String login(){

        return "login successful";
    }
}
