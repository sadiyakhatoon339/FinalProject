package com.example.ECommerce.repo;

import com.example.ECommerce.entities.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findById(long id);
    List<Customer> findAll();
}
