package org.practice.ecom_project.services;


import org.practice.ecom_project.model.Product;
import org.practice.ecom_project.repository.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository){

        this.repository = repository;
    }

    public List<Product> getProduct() {

        return repository.findAll();
    }

    public Product getProductById(int id) {

        return repository.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {

        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repository.save(product);
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {

        product.setImageData(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());

        return repository.save(product);
    }

    public void deleteProduct(int id) {

        repository.deleteById(id);
    }

    public List<Product> searchProducts(String keyWord) {

        return repository.searchProducts(keyWord);
    }
}
