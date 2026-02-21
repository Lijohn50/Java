package org.example.spring_security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @RequestMapping("/")
    public String welcome(HttpServletRequest session){

        return "Welcome to backend!" + session.getSession().getId();
    }
}
