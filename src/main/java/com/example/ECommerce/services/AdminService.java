package com.example.ECommerce.services;


import com.example.ECommerce.entities.user.User;
import com.example.ECommerce.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AdminService {

    @Autowired
    UserRepo userRepo;

    @Autowired(required = false)
    private User user;

    @Autowired
    EmailService emailService;



    public User activate(User user) {
        user.setActive(true);
        userRepo.save(user);
        String message = "Congratulations!!\nYour account is being activated. We hope you enjoy our service";

        emailService.sendMail(user.getEmail(),"Account Activation!",message );
        return user;
    }

    public User deActivate(User user) {
        user.setActive(false);
        userRepo.save(user);
        String message = "Your account is De-Activated :( \nFeel free to connect if you want to activate you account.";
        emailService.sendMail(user.getEmail(), "Account De-activation" ,message);
        return user;

    }
    }




