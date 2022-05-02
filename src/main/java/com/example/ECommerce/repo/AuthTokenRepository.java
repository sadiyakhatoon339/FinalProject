package com.example.ECommerce.repo;

import com.example.ECommerce.entities.user.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken,Long> {
    AuthToken findByToken(String token);
    int countByToken(String token);
    AuthToken findByUsername(String username);

}
