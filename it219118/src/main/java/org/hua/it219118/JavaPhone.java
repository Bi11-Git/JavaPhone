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



        for(Account ac : accountsList) {
            for( Contract con : ac.getContractsList() ) {


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