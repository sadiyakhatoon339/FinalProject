package com.example.ECommerce.repo;


import com.example.ECommerce.entities.product.Product;
import com.example.ECommerce.entities.product.ProductVariation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariationRepository extends JpaRepository<ProductVariation,Long> {

    ProductVariation findById(long id);
    List<ProductVariation> findAllByProduct(Product product);

}