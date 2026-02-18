package org.example;

public class Desktop implements Computer{

    public Desktop(){

        System.out.println("using the desktop constructor!!");
    }

    public void compile(){

        System.out.println("compiling from desktop class using setter injection!!");
    }
}
