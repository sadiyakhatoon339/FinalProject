package com.example.ECommerce.repo;

import com.example.ECommerce.entities.order.Cart;
import com.example.ECommerce.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAll();
    @Modifying
    @Query(value = "delete from cart where product_variation_id=:id", nativeQuery = true)
    void deleteByProductVariationId(@Param("id") long id);

    @Query(value = "select * from cart where customer_user_id=:id", nativeQuery = true)
    Cart findByUserId(@Param("id") long id);
}
