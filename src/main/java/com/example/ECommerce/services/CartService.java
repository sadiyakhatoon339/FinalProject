package com.example.ECommerce.services;

import com.example.ECommerce.entities.order.Cart;
import com.example.ECommerce.entities.user.Customer;
import com.example.ECommerce.repo.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getList(){
        List<Cart> cart= cartRepository.findAll();
        return cart;
    }
}
