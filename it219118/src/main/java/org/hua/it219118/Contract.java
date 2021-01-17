/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.it219118;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author bi11
 */
public abstract class Contract {

    public static final int CREDIT_CART = 0;
    public static final int CASH = 1;
    public static final int STANDING_ORDER = 2;

    private final int vatNumber;
    private final int id;
    private final String phoneNumber;

    private int mobileMin;
    private int fixMin;

    private LocalDate startingDate;
    private LocalDate endingDate;
    private int payMethod;
    private double price;
    Scanner input;
    
    public Contract(int id, String phoneNumber, int vatNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.vatNumber = vatNumber;
        input = new Scanner(System.in);

    }

    public void  configuringContract() {

        System.out.println("How match call minutes to mobile phones do you want ?\n" +
                " Price is 10 cent per minute \n " +
                " 0. I dont want call minutes to mobile phones \n" +
                " 1. 120 min \n" +
                " 2. 480 min \n" +
                " 3. 1000 min \n" +
                " 4. 1500 min ");

        System.out.printf("Choose an option :");

        int userInput = Integer.parseInt(input.nextLine());

        switch (userInput) {
            case 1:
                System.out.println("You have 120 min/month");
                mobileMin = 120;
                break;
            case 2:
                System.out.println("You have 480 min/month");
                mobileMin = 480;
                break;
            case 3:
                System.out.println("You have 1000 min/month");
                mobileMin = 1000;
                break;
            case 4:
                System.out.println("You have 1500 min/month");
                mobileMin = 1500;
                break;
            default:
                System.out.println("You dont have call minutes to mobile phones in your contract");
                break;


        }

        System.out.println("How match call minutes to fix phones do you want ? \n" +
                " Price is 5 cent per minute \n " +
                " 1. 120 min \n" +
                " 2. 480 min \n" +
                " 3. 1000 min \n" +
                " 4. 1500 min ");

        System.out.printf("Choose an option :");

        userInput = Integer.parseInt(input.nextLine());

        switch (userInput) {
            case 1:
                System.out.println("You have 120 min/month");
                fixMin = 120;
                break;
            case 2:
                System.out.println("You have 480 min/month");
                fixMin = 480;
                break;
            case 3:
                System.out.println("You have 1000 min/month");
                fixMin = 1000;
                break;
            case 4:
                System.out.println("You have 1500 min/month");
                fixMin = 1500;
                break;
            default:
                System.out.println("You dont have call minutes to fix phones in your contract");
                break;


        }

        startingDate = LocalDate.now();

        System.out.printf("In how match days from now do you want to start the contract? : ");
        userInput = Integer.parseInt(input.nextLine());

        startingDate.plusDays((long)userInput);
        endingDate = startingDate;

        System.out.printf("Give the duration of this contract (1 - 24 months) :");
        userInput = Integer.parseInt(input.nextLine());

        endingDate.plusMonths((long)userInput);

        System.out.println("please enter your payment method \n" +
                " (if you choose credit cart or Standing order as a payment method you get 5% discount\n" +
                " 1. Credit card \n" +
                " 2. Cash \n" +
                " 3. Standing order ");

        userInput = Integer.parseInt(input.nextLine());

        switch (userInput) {
            case 1:

                break;
            case 2:
                System.out.println("You have 480 min/month");
                fixMin = 480;
                break;
            case 3:
                System.out.println("You have 1000 min/month");
                fixMin = 1000;
                break;
            case 4:
                System.out.println("You have 1500 min/month");
                fixMin = 1500;
                break;
            default:
                System.out.println("You dont have call minutes to fix phones in your contract");
                break;


        }

    }

    public double getPrice() {

        price = ((double)mobileMin) * 0.1 ;
        price += ((double)fixMin) * 0.05 ;

        return price;
    }

    public boolean isActive() {
        return ((startingDate.compareTo(LocalDate.now()) >= 0 ) && (endingDate.compareTo(LocalDate.now()) <= 0));
    }

    public int hasMinutesDiscount() {
        if(mobileMin + fixMin >= 1000) {
            if(phoneNumber.charAt(0) == '2') {
                return 8;
            }  else {
                return 11;
            }
        }

        return 0;
    }

    public int hasPaymentMethodDiscount() {
        if((payMethod == CREDIT_CART) || payMethod == STANDING_ORDER) {
            return 2;
        }
        return 0;
    }

    @Override
    public String toString() {
        return String.format("\t" + id + "\t" +startingDate + "\t" + endingDate + "\t" + mobileMin + "\t" + fixMin + "\t");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getFixMin() {
        return fixMin;
    }

    public int getMobileMin() {
        return mobileMin;
    }

    public String getInternet() {

        return null;
    }

    public int getMessages() {
        return 0;
    }
}
