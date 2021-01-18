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
    
    public JavaPhone() {

        accountsList = new ArrayList<>();
        this.input = new Scanner(System.in);

        welcomeScreen();

    }

    public void welcomeScreen() {
        System.out.print(" Welcome to JavaPhone \n" +
                " 1 : Log In\n" +
                " 2 : Sign Up\n" +
                " 3 : Print Statistics\n" +
                " 0 Exit\n" +
                "Choose an option :");

        int userInput = Integer.parseInt(input.nextLine());

        System.out.println(userInput);

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

    public  void logIn() {

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

    public void signUp() {

        Account currentAccount = null;

        do{
            System.out.println(" Enter a vat number :");
            int vatNumber = Integer.parseInt(input.nextLine());

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

    public void printStatistics() {

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
                    sumFixMin += fixMinutes;

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

        System.out.println("Show fix contracts statistics");
        System.out.println("\t");
        System.out.println("Min\t");
        System.out.println("Mean\t");
        System.out.println("Max\t");


        welcomeScreen();
    }

    
}