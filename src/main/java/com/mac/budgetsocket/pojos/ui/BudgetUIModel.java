/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.budgetsocket.pojos.ui;

import java.util.List;

/**
 *
 * @author Mac
 */
public class BudgetUIModel {
    private UserUIModel user;
    private List<BillUIModel> bills;

    public UserUIModel getUser() {
        return user;
    }

    public void setUser(UserUIModel user) {
        this.user = user;
    }

    public List<BillUIModel> getBills() {
        return bills;
    }

    public void setBills(List<BillUIModel> bills) {
        this.bills = bills;
    }
    
    
}
