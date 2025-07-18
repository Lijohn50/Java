package com.example.ct4.model;

public class Book {
    private String number;
    private String title;
    private double price;
    private int quantity;


    public Book() {

    }

    public Book(String number, String title, double price, int quantity) {
        this.number = number;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
