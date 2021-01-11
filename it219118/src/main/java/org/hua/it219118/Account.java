/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.it219118;


import java.util.ArrayList;

/**
 *
 * @author bi11
 */
public class Account {
    
        
    public static final int INDIVIDUAL = 1;
    public static final int PROFESSIONAL = 2;
    public static final int STUDENT = 3;
    public static int id = 5684;
    
    private final int vatNumber;
    private final String identityNumber;
    
    private int category;
    private String address;
    private String email;
    private boolean eAccount;
    
    private int discount; // auto prepei na ftia3w
    
    private ArrayList<Contract> contracts;

    public Account(int vatNumber, String identityNumber) {
        contracts = new ArrayList<Contract>();
        this.vatNumber = vatNumber;
        this.identityNumber = identityNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
                
        this.email = email;
    }

    public void setCategory(int anCategory) {
        this.category = anCategory;
    }

    public void seteAccount(boolean eAccount) {
        this.eAccount = eAccount;
    }

    public int getVatNumber() {
        return vatNumber;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }
    
    public Contract createNewFixContact(String fixPhoneNumber) {

        if(fixPhoneNumber.length() != 10) {
            return null;
        }

        FixContract n = new FixContract(id++ , fixPhoneNumber);
        this.addContract(n);
    
        return n;

    }
    
    public void addContract(Contract anContract) {
        contracts.add(anContract);
    }
    
    public boolean getAllContractsPhoneNumber(String phoneNumber){
        if(contracts.isEmpty()) {
            return false;
        }
        String phone;
        for(Contract contract : contracts){
            phone = contract.getPhoneNumber();
            if(phone.contentEquals(phoneNumber)) {
                return true;
            }
        }
        
        return false;
    }
    
    public void deleteContract() {
        
        
    }
    
    public void printContract() {
        
    }
}
