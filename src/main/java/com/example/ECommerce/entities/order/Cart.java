package com.example.ECommerce.entities.order;

import com.example.ECommerce.entities.product.ProductVariation;
import com.example.ECommerce.entities.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long quantity;
    private boolean isWishlistItem;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "productVariation_id")
    private ProductVariation productVariation;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private List<ProductVariation> productVariations;


    @OneToOne
    @MapsId
    @JoinColumn(name = "Customer_user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart() {
    }

    public Cart( ProductVariation productVariation) {

        this.quantity = productVariation.getQuantityAvailable();
        this.isWishlistItem =true;
        this.setProductVariations(Arrays.asList(productVariation));
        this.setProductVariation(productVariation);

    }

    public ProductVariation getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariation productVariation) {
        this.productVariation = productVariation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public boolean isWishlistItem() {
        return isWishlistItem;
    }

    public void setWishlistItem(boolean wishlistItem) {
        isWishlistItem = wishlistItem;
    }

    public List<ProductVariation> getProductVariations() {
        return productVariations;
    }

    public void setProductVariations(List<ProductVariation> productVariations) {
        this.productVariations = productVariations;
    }

}
