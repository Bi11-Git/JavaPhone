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

    private static ArrayList<String> phoneNumbersList = new ArrayList<String>();
        
    public static final int INDIVIDUAL = 1;
    public static final int PROFESSIONAL = 2;
    public static final int STUDENT = 3;

    public static final String FIX = "2";
    public static final String MOBILE = "6";
    
    private final int vatNumber;
    private final String identityNumber;
    
    private int category;
    private String email;
    private String address;
    private boolean eAccount;
    
    private int discount; // auto prepei na ftia3w !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    private ArrayList<Contract> contractsList;
    private Scanner input;
    private Random rand;

    public Account(int vatNumber, String identityNumber) {

        contractsList = new ArrayList<Contract>();
        input = new Scanner(System.in);
        this.vatNumber = vatNumber;
        this.identityNumber = identityNumber;
        eAccount = false;
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
                break;
            case Account.STUDENT:
                category = STUDENT;
                break;
            default:
                break;
        }

        System.out.println("Enter an e-mail :");
        email = input.nextLine();

        System.out.println("Enter an address :");
        address = input.nextLine();

        System.out.println("Do you want an e-Account (Y/N) :");
        String answer = input.nextLine();


        switch (answer) {
            case "YES" :
            case "Yes":
            case "yes":
            case "y":
            case "Y":
                eAccount = true;
                break;
        }

        addContract();

    }

    public void mainMenu(){

        System.out.println("1 : add new contract");
        System.out.println("2 : delete a contract");
        System.out.println("3 : print all contract's details");
        System.out.println("4 : update account details");
        System.out.println("0 : sign out");
        System.out.println("Choose an option :");

        int userInput = Integer.parseInt(input.nextLine());

        switch(userInput) {
            case 1:
                //addContract();
                break;
            case 2:
                //deleteContract();
                break;
            case 3:
                //printAllContracts();
                break;
            case 4:
                //updateDetails();
                break;
            case 0:
                return;
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

        int userInput = Integer.parseInt(input.nextLine()); // try catch

        switch (userInput) {
            case 0:
                mainMenu();
                break;
            case 1:
                createContract(FIX);
                break;
            case 2:
                createContract(MOBILE);
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
            n = new FixContract(rand.nextInt(10000), userInput);
        } else {
            n = new MobileContract(rand.nextInt(10000), userInput);

        }

        this.contractsList.add(n);
        this.phoneNumbersList.add(userInput);

        mainMenu();

    }
    
    public void deleteContract() {
        
        
    }
    
    public void printContract() {
        
    }
}
