package com.example.ECommerce.entities.user;


import com.example.ECommerce.models.usermodels.AddressModel;

import javax.persistence.*;

@Entity
@Table(name="Address")
public class Address {

    /*
    ID
    CITY
    STATE
    COUNTRY
    ADDRESS_LINE
    ZIP_CODE
    LABEL (Ex. office/home)
    USER_ID
     */

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String state;
    private String country;
    private String addressLine;
    private String zipCode;
    private String label;




    public Address() {
    }

    public Address(AddressModel addressModel){
        this.city = addressModel.getCity();
        this.state = addressModel.getState();
        this.country = addressModel.getCountry();
        this.addressLine = addressModel.getAddressLine();
        this.zipCode = addressModel.getZipCode();
        this.label = addressModel.getLabel();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


}