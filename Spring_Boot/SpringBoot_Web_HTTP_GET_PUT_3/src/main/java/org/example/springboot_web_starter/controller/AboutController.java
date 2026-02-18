package org.example.springboot_web_starter.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AboutController {

    @RequestMapping("/about")
    public String aboutUs(){

        return "explore about us!!";
    }
}
