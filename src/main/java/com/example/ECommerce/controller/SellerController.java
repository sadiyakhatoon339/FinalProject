package com.example.ECommerce.controller;


import com.example.ECommerce.entities.user.Address;
import com.example.ECommerce.entities.user.Customer;
import com.example.ECommerce.entities.user.Seller;
import com.example.ECommerce.entities.user.User;
import com.example.ECommerce.exception.PasswordMismatch;
import com.example.ECommerce.models.usermodels.*;
import com.example.ECommerce.repo.AddressRepository;
import com.example.ECommerce.repo.SellerRepository;
import com.example.ECommerce.repo.UserRepo;
import com.example.ECommerce.services.SellerService;
import com.example.ECommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
public class SellerController {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressRepository addressRepository;

    @PostMapping("/register-seller")
    public Seller register(@RequestBody SellerModel sellermodel) {
        return sellerService.sellerRegister(sellermodel);
    }

    @GetMapping("/get-sellers")
    public List<Seller> seller() {

        return sellerService.getList();
    }

    @GetMapping("/seller-profile/{email}")
    public User viewProfile(@PathVariable String email) {
        return sellerService.viewProfile(email);
    }


    @PostMapping("/address")
    public ResponseEntity<User> addAddress(@RequestBody AddressModel addressModel) {

        return ResponseEntity.accepted().body(sellerService.addAddress(addressModel));
    }

    @GetMapping("/address/view")
    public ResponseEntity<Address> viewAddresss() {
        return sellerService.findAddress();
    }

    @PatchMapping("/update/password")
    public ResponseEntity<String> updatePasswords(@RequestBody PasswordModel passwordModel) throws PasswordMismatch {
        return userService.updatePassword(passwordModel);
    }

    @PatchMapping("/address/update/{id}")
    public AddressModel updateAddresss(@PathVariable long id, @RequestBody Map<Object, Object> map) {
        Address address = addressRepository.findById(id);
        map.forEach((k, v) -> {
            Field field = org.springframework.util.ReflectionUtils.findField(Address.class, (String) k);
            if (field.getName() == "id" || field.getName() == "user_id" || field.getName() == "label") {
                return;
            }
            field.setAccessible(true);
            System.out.println(">>>>>>" + field);
            ReflectionUtils.setField(field, address, v);
        });
        addressRepository.save(address);
        Address updatedAddress = addressRepository.findById(id);
        AddressModel addressModel = new AddressModel(updatedAddress);
        return addressModel;
    }

    @PatchMapping("/profile/update")
    public User editProfiles(@RequestBody UserModel usermodel) {
        User user = User.currentUser();
        if(usermodel.getFirstName()!=null) {
            user.setFirstName(usermodel.getFirstName());
        }

        if(usermodel.getMiddleName()!=null) {
            user.setMiddleName(usermodel.getMiddleName());
        }

        if(usermodel.getLastName()!=null) {
            user.setLastName(usermodel.getLastName());
        }
        userRepo.save(user);
        Seller seller = sellerRepository.findById(user.getId());
        SellerModel sellerModel = new SellerModel(user);
        sellerModel.setGst(seller.getGst());
        sellerModel.setCompanyName(seller.getCompanyName());
        sellerModel.setCompanyContact(seller.getCompanyContact());
        return user;
    }



}
