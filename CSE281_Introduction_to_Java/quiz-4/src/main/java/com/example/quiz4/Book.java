package com.example.quiz4;

public class Book {

    private String number;
    private String title;
    private String price;
    private String quantity;

    public Book(String number, String title, String price, String quantity) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
