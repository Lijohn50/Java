package com.example.demo.adminModel;

public class AddGame {
    private String title;
    private String genre;
    private String type;
    private String platform;
    private String releaseDate;
    private double price;
    private String description;
    private String image;
    private String cpu;
    private String windows;
    private String ram;
    private String gpu;
    private String storage;

    public AddGame(String title, String genre, String type, String platform, String releaseDate, double price, String description, String image, String cpu, String windows, String ram, String gpu, String storage) {
        this.title = title;
        this.genre = genre;
        this.type = type;
        this.platform = platform;
        this.releaseDate = releaseDate;
        this.price = price;
        this.description = description;
        this.image = image;
        this.cpu = cpu;
        this.windows = windows;
        this.ram = ram;
        this.gpu = gpu;
        this.storage = storage;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getWindows() {
        return windows;
    }

    public void setWindows(String windows) {
        this.windows = windows;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }
}
