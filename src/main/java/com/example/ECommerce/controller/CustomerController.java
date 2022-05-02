package com.example.ECommerce.controller;

import com.example.ECommerce.entities.user.Address;
import com.example.ECommerce.entities.user.Customer;
import com.example.ECommerce.entities.user.User;
import com.example.ECommerce.exception.PasswordMismatch;
import com.example.ECommerce.models.usermodels.AddressModel;
import com.example.ECommerce.models.usermodels.CustomerModel;
import com.example.ECommerce.models.usermodels.PasswordModel;
import com.example.ECommerce.models.usermodels.UserModel;
import com.example.ECommerce.repo.AddressRepository;
import com.example.ECommerce.repo.CustomerRepository;
import com.example.ECommerce.repo.UserRepo;
import com.example.ECommerce.services.CustomerService;
import com.example.ECommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/register-customer")
    public Customer registercustomer(@RequestBody CustomerModel customermodel) {
        return customerService.customerRegister(customermodel);
    }

    @GetMapping("/get-customers")
    public List<Customer> customer() {
        return customerService.getList();
    }

    @GetMapping("/customer-profile/{email}")
    public User viewProfile(@PathVariable String email) {
        return customerService.viewProfile(email);
    }

    @PostMapping("/address")
    public User addAddress(@RequestBody AddressModel addressModel) {
        return (customerService.addAddress(addressModel));
    }

    @GetMapping("/address/view")
    public ResponseEntity<Address> viewAddress1() {
        return customerService.findAddress();
    }

    @PatchMapping("/update/password")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordModel passwordModel) throws PasswordMismatch {
        return userService.updatePassword(passwordModel);
    }

    @PatchMapping("/profile/update")
    public User editProfile(@RequestBody UserModel usermodel) {
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
        Customer customer = customerRepository.findById(user.getId());
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(user.getId());
        customerModel.setEmail(user.getEmail());
        customerModel.setFirstName(user.getFirstName());
        customerModel.setMiddleName(user.getMiddleName());
        customerModel.setLastName(user.getLastName());
        customerModel.setContact(customer.getContact());
        return user;
    }



    @DeleteMapping("/address/delete/{id}")
    public String addressDelete(@PathVariable long id) {
        addressRepository.deleteById(id);
        return "Address is Deleted";
    }

    @PatchMapping("/address/update/{id}")
    public AddressModel updateAddress(@PathVariable long id, @RequestBody Map<Object, Object> map) {
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

}

