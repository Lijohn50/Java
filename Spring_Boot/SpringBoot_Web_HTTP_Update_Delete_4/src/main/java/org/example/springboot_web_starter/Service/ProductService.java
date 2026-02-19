package org.example.springboot_web_starter.Service;

import org.example.springboot_web_starter.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

     List<Product> products = new ArrayList<>(Arrays.asList(new Product("mobile", 201, 2500), new Product("headphone", 202, 400), new Product("laptop", 203, 45000)));

    public List<Product>getProducts(){

        return products;
    }

    public Product getProductById(int productId) {

        int productIndex = 0;
        for(Product i : products){

            if(i.getProductId() == productId){

                break;
            }
            productIndex++;
        }
        return products.get(productIndex);
    }

    public void addProducts(Product newProduct){

        products.add(newProduct);
    }


    public void updateProduct(Product product) {

        int index = 0;
        for(int i = 0; i < products.size(); i++){

            if(products.get(i).getProductId() == product.getProductId()){

                index = i;
            }
        }
        products.set(index, product);
    }

    public void deleteProduct(int productId) {

        int productIndex = 0;
        for(Product i : products){

            if(i.getProductId() == productId){

                break;
            }
            productIndex++;
        }
        products.remove(productIndex);
    }
}
