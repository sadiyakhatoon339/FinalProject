package com.example.ECommerce.controller;


import com.example.ECommerce.entities.user.Customer;
import com.example.ECommerce.entities.user.Seller;
import com.example.ECommerce.entities.user.User;
import com.example.ECommerce.models.usermodels.CustomerModel;
import com.example.ECommerce.repo.UserRepo;
import com.example.ECommerce.services.AdminService;
import com.example.ECommerce.services.CustomerService;
import com.example.ECommerce.services.SellerService;
import com.example.ECommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private AdminService adminService;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/all-seller")
    public List<Seller> registerseller() {
        return sellerService.getList();
    }

    @PatchMapping(value = "/activateCustomer/{id}")
    public ResponseEntity<Object> activateCustomer(@PathVariable("id") long id) {
        System.out.println("ID: " + id);
        User customer = userRepo.findById(id).get();
        if (customer == null) {
            System.out.println("Not found");
            return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
        } else if (customer.getActive()) {
            System.out.println("Found");
            return new ResponseEntity<>("Already in activated state", HttpStatus.OK);
        } else {
            System.out.println("found");
            adminService.activate(customer);
            return new ResponseEntity<>("Account is activated", HttpStatus.CREATED);

        }
    }

    //@Transactional
    @PatchMapping(value = "/deactivateCustomer/{id}")
    public ResponseEntity<Object> deactivateCustomer(@PathVariable("id") long id) {
        System.out.println("ID: " + id);
        User customer = userRepo.findById(id).get();
        if (customer == null) {
            System.out.println("Not found");
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } else if (customer.getActive()) {
            adminService.deActivate(customer);

            return new ResponseEntity<>("Account is de-activated", HttpStatus.CREATED);

        } else {

            System.out.println(" Your account is already de-activated");
            return new ResponseEntity<>(customer, HttpStatus.OK);

        }
    }
}




