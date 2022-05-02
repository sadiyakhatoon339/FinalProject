package com.example.ECommerce.models.usermodels;

import com.example.ECommerce.entities.user.User;

import java.util.Date;
import java.util.List;

public class UserModel {

    private Integer id;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String password;
    private List<Long> roles;

    public UserModel() {
    }

    public UserModel(User user) {
        this.email=user.getEmail();
        this.firstName=user.getEmail();
        this.middleName=user.getEmail();
        this.lastName=user.getEmail();

    }
//    Boolean isDeleted;
//    Boolean isActive;
//    Boolean isExpired;
//    Boolean isLocked;
//    Integer invalidAttemptCount;
//    Date passwordUpdateDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }




//    public Boolean getDeleted() {
//        return isDeleted;
//    }
//
//    public void setDeleted(Boolean deleted) {
//        isDeleted = deleted;
//    }
//
//    public Boolean getActive() {
//        return isActive;
//    }
//
//    public void setActive(Boolean active) {
//        isActive = active;
//    }
//
//    public Boolean getExpired() {
//        return isExpired;
//    }
//
//    public void setExpired(Boolean expired) {
//        isExpired = expired;
//    }
//
//    public Boolean getLocked() {
//        return isLocked;
//    }
//
//    public void setLocked(Boolean locked) {
//        isLocked = locked;
//    }
//
//    public Integer getInvalidAttemptCount() {
//        return invalidAttemptCount;
//    }
//
//    public void setInvalidAttemptCount(Integer invalidAttemptCount) {
//        this.invalidAttemptCount = invalidAttemptCount;
//    }
//
//    public Date getPasswordUpdateDate() {
//        return passwordUpdateDate;
//    }
//
//    public void setPasswordUpdateDate(Date passwordUpdateDate) {
//        this.passwordUpdateDate = passwordUpdateDate;
//    }
}
