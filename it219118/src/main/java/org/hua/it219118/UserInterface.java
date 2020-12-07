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
public class UserInterface {
    
    JavaPhone db;
    Account currentAccount;
    Contract currentContract;
    
    Scanner input ;

    public UserInterface(JavaPhone db) {
        this.db = db;
        this.input = new Scanner(System.in);
        
        welcomeScreen();
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
                addContract();
                break;                
            case 2:
                
                break;
            case 3:
                
                break;
            case 4:
                
                break;
            case 0:
                currentAccount = null;
                welcomeScreen();
                break;
        }
        
    }
    
    public final void welcomeScreen(){
        System.out.println(" Welcome to JavaPhone!!! "); 
        System.out.println(" 1 : Log In");
        System.out.println(" 2 : Sign Up");
        System.out.println(" 0 Exit");        
        System.out.println("Choose n option :");
        
        int userInput = Integer.parseInt(input.nextLine());
        
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
        
        currentAccount = db.getAccount(userInput);
        
        if(currentAccount == null) {
            
            System.out.println("This account dosent exist!");  

            welcomeScreen();
            
        } else {
            
            mainMenu();
        }
        
    }
    
    public void signUp() {
        
        do{
            System.out.println(" Enter a vat number :");
            int vatNumber = Integer.parseInt(input.nextLine());
        
            System.out.println(" Enter a identity number :");
            String identityNumber = input.nextLine();
        
            currentAccount = db.addAccount(vatNumber, identityNumber);
        
        } while ( currentAccount == null); 
        
        updateAccCategory();
        updateAccEmail();
        updateAccAddress();
        updateAccType();
        mainMenu();
        
    }
    
    public void updateAccCategory(){
        System.out.println("what account do you want to have?");
        System.out.println( Account.INDIVIDUAL + " : individual account");
        System.out.println( Account.PROFESSIONAL + " : proffesional account");
        System.out.println( Account.STUDENT + " : student account");
        System.out.println("Choose n option :");
        
        int userInput = Integer.parseInt(input.nextLine());
        
        switch (userInput) {
            case Account.INDIVIDUAL:
                currentAccount.setCategory(Account.INDIVIDUAL);
                break;
            case Account.PROFESSIONAL:
                currentAccount.setCategory(Account.PROFESSIONAL);
                break;
            case Account.STUDENT:
                currentAccount.setCategory(Account.STUDENT);
                break;
            default:
                break;
        }
        
    }
    
    public void updateAccEmail(){
        
        System.out.println("Enter an e-mail :");
        String userInput = input.nextLine();
            
        currentAccount.setEmail(userInput);       
        
    }
    
    public void updateAccAddress(){
       
        System.out.println("Enter an address :");
        String userInput = input.nextLine();
            
        currentAccount.setAddress(userInput);
        
    }
    
    public void updateAccType(){
        
        System.out.println("Do you want an e-Account (Y/N) :");
        String answer = input.nextLine();
        
        currentAccount.seteAccount(false);
        
        switch (answer) {
            case "YES":
                currentAccount.seteAccount(true);
                break;
            case "Yes":
                currentAccount.seteAccount(true);
                break;
            case "yes":
                currentAccount.seteAccount(true);
                break;
            case "y":
                currentAccount.seteAccount(true);
                break;
            case "Y":
                currentAccount.seteAccount(true);
                break;
        }
    }
    
    public void updateAccDetails(){
              
    }
    
    public void addContract() {
        System.out.println("What contract do you want?");
        System.out.println("1 : fix contract ");
        System.out.println("2 : mobile contract");
        System.out.println("0 : exit");
        System.out.printf("coose an option :");
        
        int userInput = Integer.parseInt(input.nextLine()); // try catch
        
        switch (userInput) {
                case 0:
                    mainMenu();
                    break;
                case 1:
                    createFixContract();
                    break;
                case 2:
                    //createMobileContract();
                    break;
                    
        }
    }
    
    public void createFixContract() {
        
        System.out.println("Enter an fix phone number :");
        
        do{
            long userInput = Long.parseLong(input.nextLine());
            
            boolean notExist = db.checkAllCOntractsPhoneNumbers(userInput);
        
            currentContract = currentAccount.createNewFixContact(userInput);
        
            if(currentContract == null) {
            
                System.out.println("Enter an fix phone number :");
            }
            
        } while (currentContract == null);

        
    }
}
