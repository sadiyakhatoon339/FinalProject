package com.example.ECommerce.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailService{

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.mailSender = javaMailSender;
    }

    @Async

        public void sendMail(String toEmail,String subject,String body)
        {
            SimpleMailMessage message=new SimpleMailMessage();
            message.setFrom("sadiyakhatoon339@gmail.com");
            message.setTo(toEmail);
            message.setText(body);
            message.setSubject(subject);
            message.setSentDate(new Date());

            mailSender.send(message);

            System.out.println("mail sent successfully");
        }
    }



