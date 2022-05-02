package com.example.ECommerce.controller;

import com.example.ECommerce.entities.order.Cart;
import com.example.ECommerce.entities.product.ProductVariation;
import com.example.ECommerce.entities.user.User;
import com.example.ECommerce.models.usermodels.CartModel;
import com.example.ECommerce.repo.CartRepository;
import com.example.ECommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @PostMapping("/addproduct")
    public ResponseEntity<String> addProduct(@RequestBody ProductVariation productVariation) {
        if (productVariation.getQuantityAvailable() > 0) {
            User user = User.currentUser();
            Cart cart = new Cart(productVariation);
            cart.setUser(user);
            cartRepository.save(cart);
            return new ResponseEntity<>("Product saved", HttpStatus.ACCEPTED);
        } else
            return new ResponseEntity<>("Product Not saved", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/view-cart")
    public List<Cart> viewCart() {
        return cartService.getList();
    }

    @Transactional
    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity deleteProduct(@PathVariable long id) {
        User user = User.currentUser();
        if (user != null) {
            cartRepository.deleteByProductVariationId(id);
            return new ResponseEntity<>("product deleted from the cart", HttpStatus.ACCEPTED);
        } else return new ResponseEntity<>("Product not available", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update-product")
    public ResponseEntity<String> updateProduct(@RequestBody CartModel cartModel) {
        User user = User.currentUser();
        Cart cart = cartRepository.findByUserId(user.getId());
        System.out.println(cart);
        if (user != null && cartModel.getQuantity() < 1) {
            cart.setQuantity(cartModel.getQuantity());
            if (cart.isWishlistItem() != cartModel.isWishlistItem()) {
                cart.setWishlistItem(cartModel.isWishlistItem());
            }
            cartRepository.save(cart);
            return new ResponseEntity<>("Product quantity updated ", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Product quantity Cannot be updated", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete-cart")
    public ResponseEntity addressDelete() {
        Cart cart = new Cart();
        User user = new User();
        if (user != null) {
            cartRepository.deleteAll();
            return new ResponseEntity<>("Cart deleted", HttpStatus.ACCEPTED);
        } else
            return new ResponseEntity<>("Cart cannot be deleted", HttpStatus.BAD_REQUEST);

    }
}
