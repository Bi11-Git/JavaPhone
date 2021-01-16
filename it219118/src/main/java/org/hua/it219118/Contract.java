/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.it219118;

import java.time.LocalDate;

/**
 *
 * @author bi11
 */
public abstract class Contract {

    public static final int CREDIT_CART = 0;
    public static final int CASH = 1;
    public static final int STANDING_ORDER = 2;
    
    private final int id;
    private final String phoneNumber;
    private int mobileMin;
    private int fixMin;
    private LocalDate startingDate;
    private int duration;
    private int payMethod;
    private boolean internet;
    
    public Contract(int id, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;

        configuringContract();
    }

    public void  configuringContract() {
    }

    public int getId() {
        return id;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }
      
}
