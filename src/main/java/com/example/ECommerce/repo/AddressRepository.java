package com.example.ECommerce.repo;


import com.example.ECommerce.entities.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Address findById(long id);
}