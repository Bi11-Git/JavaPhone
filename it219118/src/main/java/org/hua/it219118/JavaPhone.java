/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.it219118;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author bi11
 */
public class JavaPhone {

    private final ArrayList<Account> accountsList;
    Scanner input ;
    int userInput;

    //Constructor
    public JavaPhone() {

        System.out.println("Default Accounts vat Numbers : 111111111 and 222222222");
        accountsList = new ArrayList<>();
        this.input = new Scanner(System.in);

        accountsList.add(new Account(111111111));
        accountsList.add(new Account(222222222));

        welcomeScreen();

    }

    //is the main screen
    private void welcomeScreen() {

        boolean isNotOk = true;

        //while loop to check the input from user
        while(isNotOk) {

            isNotOk = false;

            System.out.print(" Welcome to JavaPhone \n" +
                    " 1 : Log In\n" +
                    " 2 : Sign Up\n" +
                    " 3 : Print Statistics\n" +
                    " 0 Exit\n" +
                    "Choose an option :");

            try {
                userInput = Integer.parseInt(input.nextLine()); //input from user becomes from String to Integer
            } catch (NumberFormatException e) {
                System.out.println("not a number");
                isNotOk = true; //if parseInt throw exception then isNotOk becomes true and repeat the loop
            }

        }

        //a switch for choices
        switch (userInput) {
            case 0:
                break;
            case 1:
                logIn();
                break;
            case 2:
                signUp();
                break;
            case 3:
                printStatistics();
                break;
            default:
                System.out.println("Wrong input : " + userInput);
                welcomeScreen();
                break;
        }

    }


    private  void logIn() {

        System.out.print("Give the vat number or identification number :");
        String userInput = input.nextLine();

        Account currentAccount = null;

        //find the right account
        for(Account acc : accountsList ) {

            if( userInput.contentEquals(acc.getIdentityNumber()) || userInput.contentEquals(String.valueOf(acc.getVatNumber()))){
                currentAccount = acc;
            }
        }

        // if this vat number or identification number doesn't exist
        if(currentAccount == null) {

            System.out.println("This account does not exist!");

        } else {

            currentAccount.mainMenu();

        }

        welcomeScreen();

    }

    private void signUp() {

        int vatNumber = 0;

        boolean isNotOk ;


        //do-while loop to get the vat number
        do {
            isNotOk = false;

            System.out.print("Enter a vat number :");

            //convert the input to integer
            try {
                vatNumber = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("not a number");
                isNotOk = true;
            }

            //check the right length
            if(vatNumber < 100000000 || vatNumber > 999999999 ) {
                System.out.println("Is not 9 digits!");
                isNotOk = true;
            }

            //check if vat number exist
            for(Account a : accountsList){
                if( a.getVatNumber() == vatNumber ){

                    System.out.println("This vat number already exist!");
                    welcomeScreen();

                    return;

                }
            }

        } while(isNotOk);


        String identityNumber;

        //get the identity number from user
        System.out.print("Enter a identity number :");
        identityNumber = input.nextLine();



        //check if identity number exist
        for(Account a : accountsList){
            if( a.getIdentityNumber().contentEquals(identityNumber) ){

                System.out.println("This vat number already exist!");
                welcomeScreen();

                return;
            }

        }


        //create the new account
        Account currentAccount = new Account(vatNumber, identityNumber);

        accountsList.add(currentAccount);


        currentAccount.configuringNewAccount();

        currentAccount.mainMenu();

        welcomeScreen();

    }

    private void printStatistics() {

        //Some variables to save the stats
        int counterFix = 0 , counterMobile = 0;
        int mobileMin = 0, sumMobileMin = 0, minMobileMin = 1500, maxMobileMin = 0;
        int fixMin = 0, sumFixMin = 0 , minFixMin = 1500 , maxFixMin = 0;
        String internet = null ;
        int internet24 = 0 , internet30 = 0 , internet50 = 0 ;

        int mobileMinutes = 0, sumMobileMinutes = 0, minMobileMinutes = 1500, maxMobileMinutes = 0;
        int fixMinutes = 0, sumFixMinutes = 0 , minFixMinutes = 1500 , maxFixMinutes = 0;
        int data = 0 , sumData = 0 , minData = 0 , maxData = 0 ;
        int sms = 0 , sumSms = 0 , minSms = 0, maxSms = 0;



        // get the stats from contracts
        for(Account ac : accountsList) {
            for( Contract con : ac.getContractsList() ) {

                // check if this contract is a FixContract
                if(con instanceof FixContract) {

                    mobileMin = con.getMobileMin();
                    sumMobileMin += mobileMin;

                    if (mobileMin > maxMobileMin)
                        maxMobileMin = mobileMin;

                    if (mobileMin < minMobileMin)
                        minMobileMin = mobileMin;

                    fixMin = con.getFixMin();
                    sumFixMin += fixMin;

                    if (fixMin > maxFixMin)
                        maxFixMin = fixMin;

                    if (fixMin < minFixMin)
                        minFixMin = fixMin;

                    internet = con.getInternet();

                    switch (internet) {
                        case "24Mbps":
                            internet24++;
                            break;
                        case "30Mbps" :
                            internet30++;
                            break;
                        case "50Mbps" :
                            internet50++;
                            break;
                    }

                    counterFix++;

                    // Check if is a MobileContract
                } else if(con instanceof MobileContract) {

                    mobileMinutes = con.getMobileMin();
                    sumMobileMinutes += mobileMinutes;

                    if (mobileMinutes > maxMobileMinutes)
                        maxMobileMinutes = mobileMinutes;

                    if (mobileMinutes < minMobileMinutes)
                        minMobileMinutes = mobileMinutes;

                    fixMinutes = con.getFixMin();
                    sumFixMinutes += fixMinutes;

                    if (fixMinutes > maxFixMinutes)
                        maxFixMinutes = fixMinutes;

                    if (fixMinutes < minFixMinutes)
                        minFixMinutes = fixMinutes;


                    data = con.getData();
                    sumData += data;

                    if (data > maxData)
                        maxData = data;

                    if (data < minData)
                        minData = data;

                    sms = con.getMessages();
                    sumSms += sms;

                    if (sms > maxSms)
                        maxSms = sms;

                    if (sms < minSms)
                        minSms = sms;

                    counterMobile++;



                }

            }
        }

        //find the most preferred  internet speed
        if( (internet24 >= internet30) && (internet24 >= internet50) ) {
            internet = "24Mbps";
        }

        if((internet30 >= internet24) && (internet30 >= internet50)){
            internet = "30Mbps";
        }

        if((internet50 >= internet24) && ((internet50 >= internet30))) {
            internet = "50Mbps";
        }

        // finding the mean
        if( counterFix != 0 ) {
            if( sumMobileMin != 0 ) {
                sumMobileMin /= counterFix;
            }

            if(sumFixMin != 0 ) {
                sumFixMin /= counterFix;
            }
        }

        if(counterMobile != 0 ) {

            if( sumMobileMinutes != 0 ) {
                sumMobileMinutes /= counterMobile;
            }

            if(sumFixMinutes != 0 ) {
                sumFixMinutes /= counterMobile;
            }

            if( sumData != 0) {
                sumData /= counterMobile;
            }

            if( sumSms != 0) {
                sumSms /= counterMobile;
            }
        }


        System.out.println("Fix contracts statistics");
        System.out.printf("      %-20s %-20s\n","Mobile Minutes", "Fix minutes");
        System.out.printf("Min   %-20d %-20d\n", minMobileMin, minFixMin);
        System.out.printf("Mean  %-20d %-20d\n", sumMobileMin, sumFixMin );
        System.out.printf("Max   %-20d %-20d\n", maxMobileMin, maxFixMin);
        System.out.println(" Most preferred network speed is " + internet + "\n");

        System.out.println("Mobile contracts statistics");
        System.out.printf("      %-20s %-20s %-20s %-20s\n", "Mobile Minutes", "Fix minutes", "Data", "Messages");
        System.out.printf("Min   %-20s %-20s %-20s %-20s\n", minMobileMinutes, minFixMinutes, minData, minSms);
        System.out.printf("Mean  %-20s %-20s %-20s %-20s\n", sumMobileMinutes, sumFixMinutes, sumData, sumSms );
        System.out.printf("Min   %-20s %-20s %-20s %-20s\n\n", maxMobileMinutes, maxFixMinutes, maxData, maxSms);



        welcomeScreen();
    }

    
}