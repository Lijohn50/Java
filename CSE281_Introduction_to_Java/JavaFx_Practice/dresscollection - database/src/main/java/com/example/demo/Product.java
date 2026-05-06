package com.example.demo;

public class Product {

    private String name;
    private String type;
    private String color;
    private double price;
    private String lastPurchase;
    private int quantity;
    private boolean boosted;
    private String image;

    public Product(String name, String type, String color, double price, String lastPurchase, int quantity, boolean boosted, String image)
    {
        this.name = name;
        this.type = type;
        this.color = color;
        this.price = price;
        this.lastPurchase = lastPurchase;
        this.quantity = quantity;
        this.boosted = boosted;
        this.image = "file:///" + image.replace("\\", "/").replace(" ", "%20");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLastPurchase() {
        return lastPurchase;
    }

    public void setLastPurchase(String lastPurchase) {
        this.lastPurchase = lastPurchase;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getBoosted() {
        return boosted;
    }

    public void setBoosted(boolean boosted) {
        this.boosted = boosted;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = "file:///" + image.replace("\\", "/").replace(" ", "%20");
    }
}
