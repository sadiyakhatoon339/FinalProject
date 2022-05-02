package com.example.ECommerce.controller;

import com.example.ECommerce.entities.order.Cart;
import com.example.ECommerce.entities.product.ProductVariation;
import com.example.ECommerce.entities.user.AuthToken;
import com.example.ECommerce.entities.user.Customer;
import com.example.ECommerce.entities.user.User;
import com.example.ECommerce.exception.UserNotFoundException;
import com.example.ECommerce.models.usermodels.ConfirmationToken;
import com.example.ECommerce.models.usermodels.LoginModel;
import com.example.ECommerce.models.usermodels.UserModel;
import com.example.ECommerce.repo.AuthTokenRepository;
import com.example.ECommerce.repo.CartRepository;
import com.example.ECommerce.repo.TokenRepository;
import com.example.ECommerce.repo.UserRepo;
import com.example.ECommerce.services.UserService;
import com.example.ECommerce.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userServices;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthTokenRepository authTokenRepository;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/register")
    public User register(@RequestBody UserModel user) {
        return userServices.save(user);
    }


    HashMap<String, Object> response = new HashMap<>();

//    @PostMapping("/login")
//    ResponseEntity<Map> login(@RequestBody LoginModel model) {
//
//        try {
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword());
//            Authentication authentication = authenticationManager.authenticate(authenticationToken);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            response.put("token", JwtUtils.create(authentication));
//            return ResponseEntity.accepted().body(response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//    }

    @PostMapping("/login")
    public ResponseEntity<Map> login(@RequestBody LoginModel model) {

        HashMap<String, Object> response = new HashMap<>();

        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword());
            System.out.println(authenticationToken);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            response.put("token", JwtUtils.create(authentication));
            AuthToken authToken = new AuthToken();
            authToken.setToken(JwtUtils.create(authentication));
            authToken.setUsername(model.getUsername());
            authTokenRepository.save(authToken);


            return ResponseEntity.accepted().body(response);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }



    @PostMapping("/logout")
    public ResponseEntity<String> logout() throws UserNotFoundException {
        User user = User.currentUser();
        AuthToken authToken = authTokenRepository.findByUsername(user.getEmail());
        if(authToken != null){
            authTokenRepository.delete(authToken);
            return new ResponseEntity<>("logged out successfully",HttpStatus.OK);
        }
        throw new UserNotFoundException("Please login first");
    }


    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public void confirmAccount(@RequestParam("token") String confirmationToken) {
        ConfirmationToken token = tokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            User user = userRepo.findByEmail(token.getUserEntity().getEmail());
            user.setActive(true);
            userRepo.save(user);
        } else {
            System.out.println("token invalid");
        }
    }


}
