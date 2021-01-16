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

    private ArrayList<Account> accountsList;
    private int id = 2351;
    Scanner input ;
    
    public JavaPhone() {

        accountsList = new ArrayList<Account>();
        this.input = new Scanner(System.in);

        welcomeScreen();

    }

    public void welcomeScreen() {
        System.out.println(" Welcome to JavaPhone ");
        System.out.println(" 1 : Log In");
        System.out.println(" 2 : Sign Up");
        System.out.println(" 0 Exit");
        System.out.printf("Choose an option :");



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

            welcomeScreen();

        } else {

            currentAccount.mainMenu();
            welcomeScreen();

        }

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

    
}