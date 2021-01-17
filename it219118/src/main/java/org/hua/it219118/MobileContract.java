/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.it219118;

import java.util.Scanner;

/**
 *
 * @author bi11
 */

public class MobileContract extends Contract{
    private int freeData;
    private int freeMessages;
    Scanner input;
    

    public MobileContract(int id, String phoneNumber, int vatNumber) {
        super(id, phoneNumber, vatNumber);
        input = new Scanner(System.in);

        configuringContract();
    }

    @Override
    public void configuringContract(){

        super.configuringContract();

        System.out.println("How match data do you want ? (Price: 1.35€/GB)  \n" +
                " 0. I dont want internet \n" +
                " 1. 1GB \n" +
                " 2. 2GB \n" +
                " 3. 3GB ");
        System.out.printf("Choose an option :");

        int userInput = Integer.parseInt(input.nextLine());

        switch (userInput) {
            case 1:
                System.out.println("You have 1GB/month");
                freeData = 1;
                break;
            case 2:
                System.out.println("You have 2GB/month");
                freeData = 2;
                break;
            case 3:
                System.out.println("You have 3GB/month");
                freeData = 3;
                break;
            case 4:
                System.out.println("You have 3GB/month");
                freeData = 5;
                break;
            default:
                System.out.println("You dont have data in your contract");
                freeData = 0;
                break;


        }

        System.out.println("How many sms do you want ? (Price: 0.25€/sms) \n" +
                " 0. I dont want sms \n" +
                " 1. 100 sms \n" +
                " 2. 250 sms \n" +
                " 3. 500 sms \n" +
                " 4. 750 sms");
        System.out.printf("Choose an option :");


        userInput = Integer.parseInt(input.nextLine());

        switch (userInput) {
            case 1:
                System.out.println("You have 100 sms/month");
                freeMessages = 100 ;
                break;
            case 2:
                System.out.println("You have 200 sms/month");
                freeMessages = 250 ;
                break;
            case 3:
                System.out.println("You have 300 sms/month");
                freeMessages = 500;
                break;
            case 4:
                System.out.println("You have 300 sms/month");
                freeMessages = 750;
                break;
            default:
                System.out.println("You dont have sms in your contract");
                freeMessages = 0;
                break;


        }

    }

    @Override
    public double getPrice() {
        double price = super.getPrice();

        price += freeData * 1.35;
        price += freeMessages * 0.25;

        return price;
    }

    @Override
    public String toString(){
        return String.format(super.toString() +  freeData + "GB" + "\t" + freeMessages + " sms\t" + this.getPrice() + "€/Month"  );
    }

    @Override
    public String getInternet() {
        return String.valueOf(freeData);
    }

    @Override
    public int getMessages() {
        return freeMessages;
    }
    
}