package com.example.ECommerce.repo;

import com.example.ECommerce.entities.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    List<Role> findAllByIdIn(List<Long> roleIds);

}
