package com.example.demo.model;

public class RecentActivity {

    private String name;
    private String genre;
    private String date;
    private double price;
    private String image;

    public RecentActivity(String name, String genre, String date, double price, String image) {
        this.name = name;
        this.genre = genre;
        this.date = date;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
