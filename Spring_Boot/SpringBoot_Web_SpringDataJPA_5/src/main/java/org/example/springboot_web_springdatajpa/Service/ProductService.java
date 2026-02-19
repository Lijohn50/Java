package org.example.springboot_web_springdatajpa.Service;

import org.example.springboot_web_springdatajpa.model.Product;
import org.example.springboot_web_springdatajpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {


    @Autowired
    ProductRepository repository;
//     List<Product> products = new ArrayList<>(Arrays.asList(new Product("mobile", 201, 2500), new Product("headphone", 202, 400), new Product("laptop", 203, 45000)));

    public List<Product>getProducts(){

        return repository.findAll();
    }

    public Product getProductById(int productId) {

        return repository.getReferenceById(productId);
    }

    public void addProducts(Product newProduct){

        repository.save(newProduct);
    }


    public void updateProduct(Product product) {

        repository.save(product);
    }

    public void deleteProduct(int productId) {

        repository.deleteById(productId);
    }
}
