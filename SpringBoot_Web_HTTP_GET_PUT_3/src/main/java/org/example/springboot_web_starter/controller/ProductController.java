package org.example.springboot_web_starter.controller;

import org.example.springboot_web_starter.Service.ProductService;
import org.example.springboot_web_starter.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping ("/products")
    public List<Product> getProduct(){

        return productService.getProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id){

        return productService.getProductById(id);
    }

    @PostMapping("/products")
    public void addNewProduct(@RequestBody Product product){

        productService.addProducts(product);
    }
}
