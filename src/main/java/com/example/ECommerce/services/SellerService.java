package com.example.ECommerce.services;

import com.example.ECommerce.entities.user.Address;
import com.example.ECommerce.entities.user.Customer;
import com.example.ECommerce.entities.user.Seller;
import com.example.ECommerce.entities.user.User;
import com.example.ECommerce.models.usermodels.AddressModel;
import com.example.ECommerce.models.usermodels.CustomerModel;
import com.example.ECommerce.models.usermodels.SellerModel;
import com.example.ECommerce.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SellerService {


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    RoleRepository roleRepository;

    public Seller sellerRegister(SellerModel sellerModel){
        Seller seller=new Seller(sellerModel);

        User user =new User();
        user.setEmail(sellerModel.getEmail());
        user.setFirstName(sellerModel.getFirstName());
        user.setMiddleName(sellerModel.getMiddleName());
        user.setRoles(roleRepository.findAllByIdIn(Arrays.asList(2L)));

        //Encoding password
        user.setPassword(bCryptPasswordEncoder.encode(sellerModel.getPassword()));

        user.setLastName(sellerModel.getLastName());

        //setting customer into user
        seller.setUser(user);
        sellerRepository.save(seller);

        return seller;


    }

    public List<Seller> getList(){
        List<Seller> sellers= sellerRepository.findAll();
        return sellers;
    }


    public User viewProfile(String email){
        User user=userRepo.findByEmail(email);
        return user;
    }

    public User addAddress(AddressModel addressModel) {
        User user = User.currentUser();

        Address address=new Address(addressModel);
        addressRepository.save(address);
        user.setAddress(address);
        userRepo.save(user);
        return user;
    }

    public ResponseEntity<Address> findAddress() {
        User currentUser = User.currentUser();
//        User user = userRepo.findByEmail(currentUser.getEmail());
        Address address = currentUser.getAddress();

        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }


}
