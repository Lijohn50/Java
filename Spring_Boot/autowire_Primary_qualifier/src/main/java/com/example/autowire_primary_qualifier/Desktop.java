package com.example.autowire_primary_qualifier;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class Desktop implements Computer {

    public void compile(){

        System.out.println("compile on a Desktop machine!!");
    }
}
