/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.budgetsocket.pojos.ui;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Mac
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillUIModel {
    
    private String billName;
    private String source;
    private String type;
    private int dueDate;
    private int remainingPmt;
    private double lateFee;
    private double interestRate;
    private int gracePeriod;
    private double amount;
    private String website;
    private boolean isRevolving;
    private AddressUIModel address;

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public int getRemainingPmt() {
        return remainingPmt;
    }

    public void setRemainingPmt(int remainingPmt) {
        this.remainingPmt = remainingPmt;
    }

    public double getLateFee() {
        return lateFee;
    }

    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isIsRevolving() {
        return isRevolving;
    }

    public void setIsRevolving(boolean isRevolving) {
        this.isRevolving = isRevolving;
    }

    public AddressUIModel getAddress() {
        return address;
    }

    public void setAddress(AddressUIModel address) {
        this.address = address;
    }    
}
