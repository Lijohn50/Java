package com.example.demo.adminModel;

public class Customer {
    private String username;
    private String email;
    private String address;
    private String mobile;
    private String gender;

    public Customer(String username, String email, String address, String mobile, String gender) {
        this.username = username;
        this.email = email;
        this.address = address;
        this.mobile = mobile;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
