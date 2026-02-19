package org.example.springboot_web_springdatajpa.controller;

import org.example.springboot_web_springdatajpa.Service.ProductService;
import org.example.springboot_web_springdatajpa.model.Product;
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

    @PutMapping("/products")
    public void updateProduct(@RequestBody Product product){

        productService.updateProduct(product);
    }

    @DeleteMapping("/products/{productId}")
    public void deleteProduct(@PathVariable int productId){

        productService.deleteProduct(productId);
    }
}
