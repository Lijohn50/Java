package org.practice.ecom_project.repository;

import org.practice.ecom_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE " + "LOWER(p.name) LIKE LOWER(CONCAT('%' ,:keyWord ,'%')) OR " + "LOWER(p.description) LIKE LOWER(CONCAT('%' ,:keyWord ,'%')) OR " + "LOWER(p.brand) LIKE LOWER(CONCAT('%' ,:keyWord ,'%')) OR " + "LOWER(p.category) LIKE LOWER(CONCAT('%' ,:keyWord ,'%'))")
    List<Product>searchProducts(String keyWord);
}
