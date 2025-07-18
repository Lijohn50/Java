package com.example.labpractice;

public class Book {

    private int number;
    private String title;
    private double price;
    private int quantity;

    public Book(int number, String title, double price, int quantity) {
        this.number = number;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
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
