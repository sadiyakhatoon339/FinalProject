package com.example.ECommerce.repo;

import com.example.ECommerce.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    @Modifying
    @Query(value = "update user set is_active=true where email = email ",nativeQuery = true)
    void updateUser(@Param("email") String email);


}
