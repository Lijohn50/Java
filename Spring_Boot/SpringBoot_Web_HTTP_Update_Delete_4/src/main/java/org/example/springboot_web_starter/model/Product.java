package org.example.springboot_web_starter.model;



public class Product {

    private String productName;
    private int productId;
    private double productPrice;

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Product(String productName, int productId, double productPrice) {
        this.productName = productName;
        this.productId = productId;
        this.productPrice = productPrice;
    }


}
