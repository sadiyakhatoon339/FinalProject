package com.example.ECommerce.controller;

import com.example.ECommerce.entities.order.Cart;
import com.example.ECommerce.entities.order.Order;
import com.example.ECommerce.entities.order.OrderProduct;
import com.example.ECommerce.entities.product.ProductVariation;
import com.example.ECommerce.repo.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderProductRepository orderProductRepository;

    @PostMapping("/order-products-in-the-cart")
    public ResponseEntity  orderProduct(ProductVariation productVariation){
      Cart cart=new Cart();
      OrderProduct orderProduct=new OrderProduct();
      //if product is there in the cart
      if(cart.getProductVariation()==productVariation && productVariation.getActive()){
          orderProduct.setProductVariation(productVariation);
          orderProduct.setQuantity(productVariation.getQuantityAvailable());
          orderProduct.setPrice(productVariation.getPrice());
          orderProductRepository.save(orderProduct);
          return new ResponseEntity<>(orderProduct.getId(), HttpStatus.ACCEPTED);
      }
      return new ResponseEntity<>("Order cannot be placed",HttpStatus.BAD_REQUEST);
    }





}
