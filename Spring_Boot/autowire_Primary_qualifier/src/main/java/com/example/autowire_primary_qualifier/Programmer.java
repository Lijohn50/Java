package com.example.autowire_primary_qualifier;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Programmer {

    @Autowired //@Autowired is Field Injection --Which is not recommended!!

    @Qualifier("laptop") // Chooses the class that is mentioned!!
    private Computer computer;

//    public Programmer(Laptop laptop){     This is Constructor Injection
//
//        this.laptop = laptop;
//    }

      //@Autowired
//    public void setLaptop(Laptop laptop){   This is setter Injection
//
//        this.laptop = laptop;
//    }

    public void create(){

        computer.compile();
        System.out.println("creating a masterpiece!!");
    }
}
