package org.practice.ecom_project.controller;


import org.practice.ecom_project.model.Product;
import org.practice.ecom_project.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service){

        this.service = service;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){

        return new ResponseEntity<>(service.getProduct(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){

        Product product = service.getProductById(id);
        if(product != null){

            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?>addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){

        try{

            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }catch (Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]>getImageById(@PathVariable int productId){

        Product product = service.getProductById(productId);
        byte[] imageFile = product.getImageData();

        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String>updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile){

        Product product1;
        try{

            product1 = service.updateProduct(id, product, imageFile);
        }catch (IOException e){

            return new ResponseEntity<>("update failed!", HttpStatus.BAD_REQUEST);
        }

        if(product1 != null){

            return new ResponseEntity<>("updated", HttpStatus.OK);
        }else{

            return new ResponseEntity<>("update failed!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String>deleteProduct(@PathVariable int id){

        Product product = service.getProductById(id);
        if(product != null){

            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }else{

            return new ResponseEntity<>("Product does not exist!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>>searchProducts(@RequestParam String keyWord){

        List<Product> products = service.searchProducts(keyWord);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
