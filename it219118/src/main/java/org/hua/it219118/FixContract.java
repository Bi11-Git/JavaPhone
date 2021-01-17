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
public class FixContract extends Contract {
    
    private String networkSpeed;
    private String networkType;
    Scanner input;

    public FixContract(int id, String phoneNumber, int vatNumber) {

        super(id, phoneNumber, vatNumber);
        input = new Scanner(System.in);

        configuringContract();
    }

    @Override
    public void configuringContract(){

        super.configuringContract();


        System.out.println("Choose your internet connection \n" +
                "0. I dont want internet\n" +
                " 1. ADSL-24mbps Price: 10€ \n" +
                " 2. VDSL-30mbps Price: 15€\n" +
                " 3. VDSL-50Mbps Price: 20€");
        System.out.printf("Choose an option :");

        int userInput = Integer.parseInt(input.nextLine());


        switch (userInput) {
            case 1:
                networkType = "ADSL";
                System.out.println("Your connection type is ADSL and your connection speed is 24 Mbps.");
                networkSpeed = "24Mbps";
                break;
            case 2:
                System.out.println("Your connection type is VDSL and your connection speed is 30 Mbps.");
                networkType = "VDSL";
                networkSpeed = "30Mbps";
                break;
            case 3:
                System.out.println("Your connection type is VDSL and your connection speed is 50 Mbps.");
                networkType = "VDSL";
                networkSpeed = "50Mbps";
                break;
            default:
                networkType = "No";
                networkSpeed = "0Mbps";
                break;
        }

    }

    @Override
    public double getPrice() {
        double price = super.getPrice();

        switch (networkSpeed) {
            case "24Mbps":
                price += 10;
                break;
            case "30Mbps":
                price += 15;
                break;
            case "50Mbps":
                price +=20;
                break;
        }

        return price;
    }

    @Override
    public String toString(){
        return String.format(super.toString() + networkType + "-" + networkSpeed + "\tNo sms" + "\t" + this.getPrice() + "€/Month"  );
    }

    @Override
    public String getInternet() {
        return networkSpeed;
    }

}