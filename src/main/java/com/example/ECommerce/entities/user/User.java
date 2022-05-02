package com.example.ECommerce.entities.user;


import com.example.ECommerce.aware.ApplicationContextHolder;
import com.example.ECommerce.models.usermodels.SellerModel;
import com.example.ECommerce.models.usermodels.UserModel;
import com.example.ECommerce.repo.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USER")
public class User {
    /*
    ID
    EMAIL
    FIRST_NAME
    MIDDLE_NAME
    LAST_NAME
    PASSWORD
    IS_DELETED
    IS_ACTIVE
    IS_EXPIRED
    IS_LOCKED
    INVALID_ATTEMPT_COUNT
    PASSWORD_UPDATE_DATE
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String email;
    String firstName;
    String middleName;
    String lastName;
    String password;
    private boolean isDeleted;
    boolean isActive = false;
    private boolean isExpired;
    private boolean isLocked;
    Integer invalidAttemptCount;
    Date passwordUpdateDate;

    @Transient
    public String resetPasswordToken;

    public User() {
    }


    public User(UserModel userModel) {
        if (userModel != null) {
            this.firstName = userModel.getFirstName();
            this.middleName = userModel.getMiddleName();
            this.lastName = userModel.getLastName();
            this.email = userModel.getEmail();
            this.password = userModel.getPassword();
            this.isActive = false;

        }
    }


    public User(SellerModel sellerModel) {
        this.email = sellerModel.getEmail();
        this.firstName = sellerModel.getFirstName();
        this.middleName = sellerModel.getMiddleName();
        this.lastName = sellerModel.getLastName();
    }


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    Seller seller;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Address address;

    @ManyToOne
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    List<Role> roles;


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getExpired() {
        return isExpired;
    }

    public void setExpired(Boolean expired) {
        isExpired = expired;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    public Integer getInvalidAttemptCount() {
        return invalidAttemptCount;
    }

    public List<Role> getRoles() {
        return roles;
    }


    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setInvalidAttemptCount(Integer invalidAttemptCount) {
        this.invalidAttemptCount = invalidAttemptCount;
    }

    public Date getPasswordUpdateDate() {
        return passwordUpdateDate;
    }

    public void setPasswordUpdateDate(Date passwordUpdateDate) {
        this.passwordUpdateDate = passwordUpdateDate;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", isDeleted=" + isDeleted +
                ", isActive=" + isActive +
                ", isExpired=" + isExpired +
                ", isLocked=" + isLocked +
                ", invalidAttemptCount=" + invalidAttemptCount +
                ", passwordUpdateDate=" + passwordUpdateDate +
                ", resetPasswordToken='" + resetPasswordToken + '\'' +
                ", customer=" + customer +
                ", seller=" + seller +
                ", address=" + address +
                ", roles=" + roles +
                '}';
    }

    private static UserRepo getUserRepository() {
        return ApplicationContextHolder.getBean(UserRepo.class);
    }

    public static User findByEmail(String email) {
        return getUserRepository().findByEmail(email);
    }

    public static User currentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
        return User.findByEmail(authentication.getName());
    }
    return null;
   }
   }