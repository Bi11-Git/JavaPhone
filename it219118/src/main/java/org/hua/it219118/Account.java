/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.it219118;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author bi11
 */
public class Account {

    private static final ArrayList<String> phoneNumbersList = new ArrayList<>();
        
    public static final int INDIVIDUAL = 0;
    public static final int PROFESSIONAL = 10;
    public static final int STUDENT = 15;

    public static final String FIX = "2";
    public static final String MOBILE = "6";
    
    private final int vatNumber;
    private final String identityNumber;
    
    private int category;
    private String email;
    private String address;
    private int eAccount;
    
    private int discount;
    
    private ArrayList<Contract> contractsList;
    private Scanner input;
    private Random rand;

    public Account(int vatNumber, String identityNumber) {

        contractsList = new ArrayList<Contract>();
        input = new Scanner(System.in);
        this.vatNumber = vatNumber;
        this.identityNumber = identityNumber;
        eAccount = 0;
        rand = new Random();

    }

    public void configuringNewAccount() {

        System.out.println("what account do you want to have?");
        System.out.println( INDIVIDUAL + " : individual account");
        System.out.println( PROFESSIONAL + " : professional account");
        System.out.println( STUDENT + " : student account");
        System.out.println("Choose an option :");

        int userInput = Integer.parseInt(input.nextLine());

        switch (userInput) {
            case Account.INDIVIDUAL:
                category = INDIVIDUAL;
                break;
            case Account.PROFESSIONAL:
                category = PROFESSIONAL;
                discount = PROFESSIONAL;
                break;
            case Account.STUDENT:
                category = STUDENT;
                discount = STUDENT;
                break;
            default:
                break;
        }

        System.out.println("Enter an e-mail :");
        email = input.nextLine();

        System.out.println("Enter an address :");
        address = input.nextLine();

        System.out.println("e-Accounts have +2% discount \n Do you want an e-Account (Y/N) :");
        String answer = input.nextLine();

        switch (answer) {
            case "YES" :
            case "Yes":
            case "yes":
            case "y":
            case "Y":
                eAccount = 2;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + answer);
        }

        addContract();

    }

    public void mainMenu(){

        System.out.println("1 : add new contract");
        System.out.println("2 : delete a contract");
        System.out.println("3 : show all contract's details");
        System.out.println("0 : sign out");
        System.out.printf("Choose an option :");

        int userInput = Integer.parseInt(input.nextLine());

        switch(userInput) {
            case 1:
                addContract();
                break;
            case 2:
                deleteContract();
                break;
            case 3:
                showContractDetails();
                break;
            case 0:
                return;
            default:
                mainMenu();
                break;
        }

    }

    public int getVatNumber() {
        return vatNumber;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void addContract() {
        System.out.println("What contract do you want?");
        System.out.println("1 : fix contract ");
        System.out.println("2 : mobile contract");
        System.out.println("0 : exit");
        System.out.printf("choose an option :");


        int userInput = Integer.parseInt(input.nextLine());


        switch (userInput) {
            case 1:
                createContract(FIX);
                break;
            case 2:
                createContract(MOBILE);
                break;
            default:
                mainMenu();
                break;

        }
    }

    public void createContract( String prefix) {


        System.out.printf("Enter next 9 digits for your fix phone number :" + prefix);
        FixContract currentContract = null;
        boolean exist;
        String userInput;

        do{
            exist = false;
            userInput = prefix + input.nextLine();

            if(userInput.length() != 10) {
                System.out.println(userInput + "! Is not 10 digits!!!");
                exist = true;
            }

            for(String number : phoneNumbersList) {
                if( number.contentEquals(userInput)) {
                    System.out.println("This number already exist!");
                    System.out.printf("Enter next 9 digits for your fix phone number :2");
                    exist = true;
                }
            }


        } while (exist);

        Contract n;

        if(prefix.contentEquals("2")) {
            n = new FixContract(rand.nextInt(10000), userInput, vatNumber);
        } else {
            n = new MobileContract(rand.nextInt(10000), userInput, vatNumber);

        }

        this.contractsList.add(n);
        this.phoneNumbersList.add(userInput);

        checkDiscount();
        mainMenu();

    }


    public void showContractDetails() {

        System.out.println(" \tid\tstarting date\texpiration date\tminutes to cell phones\tminutes to fix phones\tinternet\tsms\tprice\tprice after discount ");

        int count = 1;
        for(Contract c : contractsList) {
            Double d = (c.getPrice() * (100 - discount) ) / 100 ;
            System.out.println(count + c.toString() + d );
            count++;
        }


        mainMenu();
    }

    
    public void deleteContract() {

        showContractDetails();

        System.out.print(" Which contract do you want to delete? :");
        int userInput = Integer.parseInt(input.nextLine());

        if ( contractsList.size() <= userInput) {
            Contract c = contractsList.get(userInput - 1);
            contractsList.remove(userInput - 1);

            for ( int i = 0 ; i < phoneNumbersList.size() ; i++ ) {
                String num = phoneNumbersList.get(i);
                if( num.contentEquals(c.getPhoneNumber())) {
                    phoneNumbersList.remove(i);
                }
            }
        }

        checkDiscount();
        mainMenu();
    }

    public void checkDiscount() {

        discount = eAccount + category;
        int count = 3;
        for(Contract c : contractsList) {
            if(c.isActive()) {
                if(count > 0) {
                    discount += 5;
                    count--;
                }

                discount += c.hasMinutesDiscount();
                discount += c.hasPaymentMethodDiscount();

            }
        }

        if(discount > 45) {
            discount = 45;
        }

    }

    public ArrayList<Contract> getContractsList() {
        return contractsList;
    }
}
