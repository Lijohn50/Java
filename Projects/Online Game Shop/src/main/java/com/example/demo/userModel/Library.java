package com.example.demo.userModel;

public class Library {
    private String title;
    private String genre;
    private String purchaseDate;
    private String platform;
    private String image;

    public Library(String title, String genre, String platform, String purchaseDate, String image) {
        this.title = title;
        this.genre = genre;
        this.purchaseDate = purchaseDate;
        this.platform = platform;
        this.image = image;
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

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String type) {
        this.purchaseDate = type;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
