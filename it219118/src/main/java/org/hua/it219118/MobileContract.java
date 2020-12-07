/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.it219118;

/**
 *
 * @author bi11
 */
public class MobileContract extends Contract{
    private int freeData;
    private int freeMessages;
    

    public MobileContract(int id, int phoneNumber) {
        super(id, phoneNumber);
    }

    
}