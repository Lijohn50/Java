package com.example.demo.adminModel;

public class Product {
    private String title;
    private String genre;
    private String type;
    private String platform;
    private String releaseDate;
    private double price;
    private String imagePath;

    public Product(String title, String genre, String type, String platform, String releaseDate, double price, String imagePath)
    {
        this.title = title;
        this.genre = genre;
        this.type = type;
        this.platform = platform;
        this.releaseDate = releaseDate;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
