/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.budgetsocket;

import com.mac.budgetsocket.endpoints.BillSocket;

/**
 *
 * @author Mac
 */
public class Application {
    
    public static void main(String[] args){
        BillSocket billSocket = new BillSocket();
        billSocket.start();
    }
}
