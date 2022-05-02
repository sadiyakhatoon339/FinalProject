package com.example.ECommerce.repo;

import com.example.ECommerce.entities.user.Customer;
import com.example.ECommerce.entities.user.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    List<Seller> findAll();
    Seller findById(long id);
}


