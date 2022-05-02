package com.example.ECommerce.entities.user;

import com.example.ECommerce.models.usermodels.SellerModel;

import javax.persistence.*;

@Entity
@Table(name="Seller")
public class Seller{
    /*
    USER_ID
    GST
    COMPANY_CONTACT
    COMPANY_NAME
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    String gst;
    String companyContact;
    String companyName;

    //*

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    User user;

    public Seller() {
    }

    public Seller(SellerModel sellerModel) {
        this.id=sellerModel.getId();
        this.gst=sellerModel.getGst();
        this.companyContact=sellerModel.getCompanyContact();
        this.companyName=sellerModel.getCompanyName();
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

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}