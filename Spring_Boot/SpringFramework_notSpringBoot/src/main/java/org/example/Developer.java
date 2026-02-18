package org.example;


import org.springframework.stereotype.Component;

@Component
public class Developer {
    public Computer getComp() {
        return comp;
    }

    public void setComp(Computer comp) {
        this.comp = comp;
    }

    private int age;
    private Desktop desk;

    private Computer comp;

    public Desktop getDesk() {
        return desk;
    }

    public void setDesk(Desktop desk) {
        this.desk = desk;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Developer(){

        System.out.println("using the dev constructor!!");
    }

    public Developer(int age){

        this.age = age;
    }

    public void create(){

        System.out.println("Creating a Project!!");
        comp.compile();
    }
}
