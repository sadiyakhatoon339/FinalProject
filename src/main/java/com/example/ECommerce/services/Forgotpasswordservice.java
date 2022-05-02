package com.example.ECommerce.services;


import com.example.ECommerce.entities.user.User;
import com.example.ECommerce.models.usermodels.ConfirmationToken;
import com.example.ECommerce.repo.TokenRepository;
import com.example.ECommerce.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Forgotpasswordservice {


    @Autowired
    UserRepo userRepo;

   @Autowired
   TokenRepository tokenRepository;

   @Autowired
   EmailService senderService;

    Boolean userExist(String email){
        return userRepo.findByEmail(email) !=null ? true : false;
    }


    public User forgotPassword(String email) {
        System.out.println(email);
        boolean ifExist=userExist(email);
        if(ifExist){
            User user=userRepo.findByEmail(email);
            System.out.println(user.getFirstName());
            System.out.println(user.getEmail());
            System.out.println(user.getRoles());
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            tokenRepository.save(confirmationToken);
            user.setResetPasswordToken(confirmationToken.getConfirmationToken());
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Forgot Password!");
            mailMessage.setText("To reset your password, please click here : "
                    +"http://localhost:8080/reset-password?token="+confirmationToken.getConfirmationToken());


            senderService.sendMail(user.getEmail(),"Registration !!!","welcome to the My Backend Application Service . This is your username "
                    +user.getEmail()+"To reset your password, please click here : "
                    +"http://localhost:8080/reset-password?token="+confirmationToken.getConfirmationToken());
            return user;
        }
        else{
            System.out.println("User is null");
            return null;

        }



    }
}
