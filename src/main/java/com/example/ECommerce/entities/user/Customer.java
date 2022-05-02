package com.example.ECommerce.entities.user;


import com.example.ECommerce.models.usermodels.CustomerModel;

import javax.persistence.*;
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String contact;


    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;


    public Customer() {
    }

    public Customer(CustomerModel customerModel) {
//        this.id = customerModel.getId();
        this.contact = customerModel.getContact();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}