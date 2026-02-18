package com.example.autowire_primary_qualifier;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component

//@Primary --Chooses a class from a common interface

public class Laptop implements Computer {

    public void compile(){

        System.out.println("compile on a Laptop machine!!");
    }
}
