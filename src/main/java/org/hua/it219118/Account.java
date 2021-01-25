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

    public Account(int vatNumber) {
        contractsList = new ArrayList<Contract>();
        input = new Scanner(System.in);
        this.vatNumber = vatNumber;
        this.identityNumber = String.valueOf(vatNumber);
        eAccount = 0;
        rand = new Random();

        configuringDefaultAccount();


    }
    public Account(int vatNumber, String identityNumber) {

        contractsList = new ArrayList<Contract>();
        input = new Scanner(System.in);
        this.vatNumber = vatNumber;
        this.identityNumber = identityNumber;
        eAccount = 0;
        rand = new Random();

    }

    public void configuringNewAccount() {

        System.out.print("what account do you want to have?\n" +
                "1 : individual account\n" +
                "2 : professional account\n" +
                "3 : student account\n" +
                "Choose an option :");

        boolean isNotOk;
        int userInput = 0;

        //do-while loop until the user give a correct answer
        do {
            isNotOk = false;

            // if the user's answer it is not a number then flag (isNotOk) is true and repeat the loop
            try {
                userInput = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Not a number!");
                isNotOk = true;
            }

            // switch for the user's choice
            switch (userInput) {
                case 1 :
                    category = INDIVIDUAL;
                    break;
                case 2 :
                    category = PROFESSIONAL;
                    discount = PROFESSIONAL;
                    break;
                case 3 :
                    category = STUDENT;
                    discount = STUDENT;
                    break;
                default:
                    isNotOk = true;
                    System.out.println( userInput + " it is not an option!");
                    break;
            }


        } while(isNotOk);

        System.out.println("Enter an e-mail :");
        email = input.nextLine();

        System.out.println("Enter an address :");
        address = input.nextLine();

        System.out.print("e-Accounts have +2% discount \nDo you want an e-Account (Y/N) :");
        String answer = input.nextLine();

        switch (answer) {
            case "YES" :
            case "Yes":
            case "yes":
            case "y":
            case "Y":
                eAccount = 2;
                System.out.println("You have an e-Account");
                break;
            default:
                System.out.println("you have not an e-Account");
                break;
        }

        addContract();

    }

    public void configuringDefaultAccount() {

        if(vatNumber == 111111111) {
            category = PROFESSIONAL;
            discount = PROFESSIONAL;
            email = "Cosmote@istrash.gr";
            address = "Ελ. Βενιζέλου 70, Μοσχάτο 176 71";
            eAccount = 2;
            contractsList.add(new FixContract(111111111));
            contractsList.add(new MobileContract(222222222));


        } else {
            category = INDIVIDUAL;
            discount = INDIVIDUAL;
            email = "Wind@istrash.gr";
            address = "Ομήρου 9, Ταύρος Αττικής 177 78";
            eAccount = 0;
            contractsList.add(new FixContract(333333333));
            contractsList.add(new MobileContract(444444444));


        }

        checkDiscount();
    }

    public void mainMenu(){

        System.out.println("1 : add new contract");
        System.out.println("2 : delete a contract");
        System.out.println("3 : show all contract's details");
        System.out.println("4 : Show account details");
        System.out.println("0 : sign out");
        System.out.print("Choose an option :");

        boolean isNotOk;
        int userInput = 0;

        //do-while loop until the user give a correct answer
        do {
            isNotOk = false;

            // if the user's answer it is not a number then flag (isNotOk) is true and repeat the loop
            try {
                userInput = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Not a number!");
                isNotOk = true;
            }


        } while(isNotOk);

        switch(userInput) {
            case 1:
                addContract();
                break;
            case 2:
                deleteContract();
                break;
            case 3:
                showContractDetails();
                mainMenu();
                break;
            case 4:
                showAccountDetails();
                mainMenu();
                break;
            case 0:
                return;
            default:
                System.out.println(userInput + "! Is not an option");
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
        System.out.print("choose an option :");


        boolean isNotOk;
        int userInput = 0;

        //do-while loop until the user give a correct answer
        do {
            isNotOk = false;

            // if the user's answer it is not a number then flag (isNotOk) is true and repeat the loop
            try {
                userInput = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Not a number!");
                isNotOk = true;
            }


        } while(isNotOk);

        switch (userInput) {
            case 1:
                createContract(FIX);
                break;
            case 2:
                createContract(MOBILE);
                break;
            default:
                System.out.println(userInput + "! Is not an option");
                addContract();
                break;

        }
    }

    // create a contract
    public void createContract( String prefix) {

        System.out.print("Enter next 9 digits for your fix phone number :" + prefix);
        boolean isNotOk;
        String userInput;

        do{
            isNotOk = false;
            userInput = prefix + input.nextLine();

            if(userInput.length() != 10) {
                System.out.println(userInput + "! Is not 10 digits!!!\n" + prefix);
                isNotOk = true;
            }

            for(String number : phoneNumbersList) {
                if( number.contentEquals(userInput)) {
                    System.out.println("This number already exist!");
                    System.out.print("Enter next 9 digits for your fix phone number :2");
                    isNotOk = true;
                }
            }

        } while (isNotOk);

        Contract n;

        // create a fix contract or mobile contract
        if(prefix.contentEquals(FIX)) {
            n = new FixContract(rand.nextInt(1000), userInput, vatNumber);
        } else {
            n = new MobileContract(rand.nextInt(1000), userInput, vatNumber);

        }

        //add the new contract to the list
        this.contractsList.add(n);
        //add the new phone number to the list
        Account.phoneNumbersList.add(userInput);

        checkDiscount();
        mainMenu();

    }


    public void showContractDetails() {

        System.out.printf("Identity Number :%s \nVat Number :%d\nTotal Discount :%s\n", identityNumber, vatNumber, discount + "%");
        System.out.printf("  %-3s %-12s %-13s %-15s %-17s %-14s %-11s %-8s %-10s %s \n", "id", "Phone number", "starting date", "expiration date", "minutes to mobile", "minutes to fix", "internet", "sms", "price", "price after discount");

        int count = 1;
        //for loop to iterate all contracts and print their details
        for(Contract c : contractsList) {
            Double d = (c.getPrice() * (100 - discount) ) / 100 ;
            String pr = (int) Math.round(d) + "€/Month";
            System.out.println(count + " " + c.toString() + pr );
            count++;
        }

        System.out.println("\n");

    }

    public void showAccountDetails() {

        System.out.println("\nVat Number :" + vatNumber);
        System.out.println("Identity Number :" + identityNumber);

        if(category == INDIVIDUAL) {
            System.out.println("Individual Account");
        } else if(category == PROFESSIONAL) {
            System.out.println("Professional Account");
        } else {
            System.out.println("Student Account");
        }

        System.out.println("Email :" + email);
        System.out.println("Address : " + address);
        System.out.println("Total discount for all our services " + discount + "%  \n");


    }

    
    public void deleteContract() {

        showContractDetails();

        System.out.print(" Which contract do you want to delete? :");

        boolean isNotOk;
        int userInput = 0;

        //do-while loop until the user give a correct answer
        do {
            isNotOk = false;

            // if the user's answer it is not a number then flag (isNotOk) is true and repeat the loop
            try {
                userInput = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Not a number!");
                isNotOk = true;
            }

            if(userInput < 0) {
                System.out.println("Only positive integers please");
                isNotOk = true;
            }


        } while(isNotOk);


        if ( contractsList.size() >= userInput ) {

            userInput--;

            //get and remove that contract
            Contract c = contractsList.get(userInput);
            contractsList.remove(userInput);

            System.out.println("Contract deleted!");

            //find that phone number on the list and remove it
            for ( int i = 0 ; i < phoneNumbersList.size() ; i++ ) {
                String num = phoneNumbersList.get(i);
                if( num.contentEquals(c.getPhoneNumber())) {
                    phoneNumbersList.remove(i);
                }
            }
        } else {
            System.out.println("This contact doesnt exist!");
        }



        checkDiscount();
        mainMenu();
    }

    //calculate the discount
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
