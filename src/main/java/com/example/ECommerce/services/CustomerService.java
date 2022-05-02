package com.example.ECommerce.services;

import com.example.ECommerce.entities.user.Address;
import com.example.ECommerce.entities.user.Customer;
import com.example.ECommerce.entities.user.User;
import com.example.ECommerce.exception.UserNotFoundException;
import com.example.ECommerce.models.usermodels.*;
import com.example.ECommerce.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    UserRepo userRepo;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private  EmailService emailService;

    public Customer customerRegister(CustomerModel customerModel){
        Customer customer=new Customer(customerModel);
        User user =new User();
        user.setEmail(customerModel.getEmail());
        user.setFirstName(customerModel.getFirstName());
        user.setMiddleName(customerModel.getMiddleName());
        user.setRoles(roleRepository.findAllByIdIn(Arrays.asList(3L)));
        //Encoding password
        user.setPassword(bCryptPasswordEncoder.encode(customerModel.getPassword()));

        user.setLastName(customerModel.getLastName());

        //setting customer into user
        customer.setUser(user);
        customerRepository.save(customer);

        return customer;
    }

    public List<Customer> getList(){
        List<Customer> customers= customerRepository.findAll();
        return customers;
    }

    public User viewProfile(String email){
        User user=userRepo.findByEmail(email);
        return user;
    }

    public ResponseEntity<UserModel> resend(String email) throws UserNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User Not found");
        }
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        tokenRepository.save(confirmationToken);
        UserModel userModel = new UserModel(user);

        emailService.sendMail(user.getEmail(),"Complete registration","To confirm your account, please click on the given link : "
                + "http://localhost:8080/users/confirm?token=" + confirmationToken.getConfirmationToken());
        return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
    }


    public ResponseEntity<Address> findAddress() {
        User currentUser = User.currentUser();
        User user = userRepo.findByEmail(currentUser.getEmail());
        Address address = user.getAddress();

        return new ResponseEntity<Address>(address, HttpStatus.FOUND);
    }

    public User addAddress(AddressModel addressModel) {
        User user = User.currentUser();
        System.out.println("hello");
        Address address = new Address(addressModel);
        addressRepository.save(address);
        user.setAddress(address);
        userRepo.save(user);
        return user;
    }
}



