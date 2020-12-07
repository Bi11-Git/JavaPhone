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
public class FixContract extends Contract{
    
    public static final int ADSL = 0;
    public static final int VDSL = 1;
    
    private int networkSpeed;
    private int networkType;

    public FixContract(int id, long phoneNumber) {
        super(id, phoneNumber);
    }
     
}