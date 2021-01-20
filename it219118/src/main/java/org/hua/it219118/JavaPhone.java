/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.it219118;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author bi11
 */
public class JavaPhone {

    private final static int MAX_ATTEMPTS = 3;

    private final ArrayList<Account> accountsList;
    Scanner input ;
    int userInput;
    
    public JavaPhone() throws IOException {

        accountsList = new ArrayList<>();
        this.input = new Scanner(System.in);

        welcomeScreen();

    }

    public void welcomeScreen() throws IOException {

        boolean notNumber = true;

        while(notNumber) {

            notNumber = false;

            System.out.print(" Welcome to JavaPhone \n" +
                    " 1 : Log In\n" +
                    " 2 : Sign Up\n" +
                    " 3 : Print Statistics\n" +
                    " 0 Exit\n" +
                    "Choose an option :");

            try {
                userInput = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("not a number");
                notNumber = true;
            }
        }

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

    public  void logIn() throws IOException {

        System.out.println("Give the vat number or identification number :");

        String userInput = input.nextLine();

        Account currentAccount = null;

        for(Account acc : accountsList ) {

            if( userInput.contentEquals(acc.getIdentityNumber()) || userInput.contentEquals(String.valueOf(acc.getVatNumber()))){
                currentAccount = acc;
            }
        }

        if(currentAccount == null) {

            System.out.println("This account does not exist!");

        } else {

            currentAccount.mainMenu();

        }

        welcomeScreen();

    }

    public void signUp() throws IOException {

        Account currentAccount = null;

        do{
            int vatNumber = 0; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

            for( int i = 0; i < MAX_ATTEMPTS ; i++) {

                System.out.println(" Enter a vat number :");

                try {
                    vatNumber = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    Runtime.getRuntime().exec("cls");
                    System.out.println("not a number");
                    if(i == MAX_ATTEMPTS)
                        System.exit(1);
                }
            }

            System.out.println(" Enter a identity number :");
            String identityNumber = input.nextLine();

            for(Account a : accountsList){
                if( !(a.getIdentityNumber().contentEquals(identityNumber)) || (a.getVatNumber() == vatNumber) ){
                    currentAccount = new Account(vatNumber, identityNumber);

                    accountsList.add(currentAccount);
                }
            }

        } while ( currentAccount == null);


        currentAccount.configuringNewAccount();

        currentAccount.mainMenu();

        welcomeScreen();

    }

    public void printStatistics() throws IOException {

        int counterFix = 0 , counterMobile = 0;
        int mobileMin = 0, sumMobileMin = 0, minMobileMin = 0, maxMobileMin = 0;
        int fixMin = 0, sumFixMin = 0 , minFixMin = 0 , maxFixMin = 0;
        String internet = null ;
        int internet24 = 0 , internet30 = 0 , internet50 = 0 ;

        int mobileMinutes = 0, sumMobileMinutes = 0, minMobileMinutes = 0, maxMobileMinutes = 0;
        int fixMinutes = 0, sumFixMinutes = 0 , minFixMinutes = 0 , maxFixMinutes = 0;
        int data = 0 , sumData = 0 , minData = 0 , maxData = 0 ;
        int sms = 0 , sumSms = 0 , minSms = 0, maxSms = 0;



        for(Account ac : accountsList) {
            for( Contract con : ac.getContractsList() ) {

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

                } else if(con instanceof MobileContract) {

                    mobileMinutes = con.getMobileMin();
                    sumFixMinutes += mobileMinutes;

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

        if( (internet24 >= internet30) && (internet24 >= internet50) ) {
            internet = "24Mbps";
        }

        if((internet30 >= internet24) && (internet30 >= internet50)){
            internet = "30Mbps";
        }

        if((internet50 >= internet24) && ((internet50 >= internet30))) {
            internet = "50Mbps";
        }

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
        System.out.println(" \tMobile Minutes\tFix minutes");
        System.out.println("Min\t" + minMobileMin + "\t" + minFixMin);
        System.out.println("Mean\t" + sumMobileMin + "\t" + sumFixMin );
        System.out.println("Max\t"  + maxMobileMin + "\t" + maxFixMin);
        System.out.println(" Most preferred network speed is " + internet );

        System.out.println("Mobile contracts statistics");
        System.out.println(" \tMobile Minutes\tFix minutes\tData\tMessages");
        System.out.println("Min\t" + minMobileMinutes + "\t" + minFixMinutes + "\t" + minData + "\t" + minSms);
        System.out.println("Mean\t" + sumMobileMinutes  + "\t" + sumFixMinutes + "\t" + sumData + "\t" + sumSms );
        System.out.println("Min\t" + maxMobileMinutes + "\t" + maxFixMinutes + "\t" + maxData + "\t" + maxSms);



        welcomeScreen();
    }

    
}