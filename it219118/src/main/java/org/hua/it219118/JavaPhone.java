/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.it219118;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author bi11
 */
public class JavaPhone {
    private final ArrayList<Account> accounts;
    private int id = 2351;

    
    public JavaPhone() {
        accounts = new ArrayList<>();
        
    }
    
    
    public Account addAccount(int vatNumber, String identityNumber) {
        
        for(Account a : accounts){
            if((a.getIdentityNumber().contentEquals(identityNumber)) || (a.getVatNumber() == vatNumber)){
                return null;
            }
        }
        
        
        Account n = new Account(vatNumber, identityNumber);
        accounts.add(n);
        
        return n;
    }
    
    
    public boolean checkAllCOntractsPhoneNumbers(long phoneNumber) {
        
        for(Account a : accounts){
            if(a.getAllContractsPhoneNumber(phoneNumber)) {
                return true;
            }
        }
        
        return false;
    }
    
    public Account getAccount(String accSearch) {
        
        for(Account acc : accounts) {
            
            if( accSearch.contentEquals(acc.getIdentityNumber()) || accSearch.contentEquals(String.valueOf(acc.getVatNumber()))){
                return acc;
            }
        }
        
        return null;
    }
    
}