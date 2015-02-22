/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.budgetsocket.pojos.ui;

import com.mac.budgetsocket.pojos.ui.AddressUIModel;

/**
 *
 * @author Mac
 */
public class UserUIModel {
    private String fName;
    private String lName;
    private String phone;
    private String email;
    private String preference;
    private AddressUIModel address;

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public AddressUIModel getAddress() {
        return address;
    }

    public void setAddress(AddressUIModel address) {
        this.address = address;
    }
    
    
}
