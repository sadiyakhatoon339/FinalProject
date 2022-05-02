package com.example.ECommerce.controller;

import com.example.ECommerce.entities.user.User;
import com.example.ECommerce.models.usermodels.ConfirmationToken;
import com.example.ECommerce.repo.TokenRepository;
import com.example.ECommerce.repo.UserRepo;
import com.example.ECommerce.services.Forgotpasswordservice;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ForgotPasswordController {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    Forgotpasswordservice forgotpasswordservice;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(value = {"/forgot-password"})
    public ResponseEntity<User> forgotPassword(@RequestBody ObjectNode objectNode) {
        String email = objectNode.get("email").asText();
        System.out.println(email);

        User user = forgotpasswordservice.forgotPassword(email);
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        System.out.println("User  found");
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PatchMapping(value = {"/reset-password"})
    public void resetPassword(@RequestParam("token") String confirmationToken, @RequestBody ObjectNode objectNode) {
        ConfirmationToken token = tokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            User user = userRepository.findByEmail(token.getUserEntity().getEmail());
            String newPassword = objectNode.get("newPassword").asText();
            String confirmPassword = objectNode.get("confirmPassword").asText();
            if (newPassword.equals(confirmPassword)) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
                System.out.println("Password Changed");
            } else {
                System.out.println("New Password and Confirm Password do not match");
            }

        } else {
            System.out.println("token invalid");
        }

    }


}