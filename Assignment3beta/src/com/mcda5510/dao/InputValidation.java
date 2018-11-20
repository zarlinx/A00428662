package com.mcda5510.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.mcda5510.entity.Transaction;

public class InputValidation {

	public int validateID(Scanner scanner) {
      	System.out.println("Please enter the ID :");
      	int trxnID = 0;
    	try {
            trxnID = scanner.nextInt();
        } catch (Exception e) {
        	System.out.println("Invalid input! ID should be an integer.");
        	trxnID = validateID(scanner);
        }
    	return trxnID;
    }
    	
    public String validateNameOnCard(Scanner scanner) {
      	System.out.println("Please enter the name on card :");
    	String nameOnCard = "";
    	try {		
    		nameOnCard = scanner.nextLine();
            if(nameOnCard.contains(";")||nameOnCard.contains(":")||nameOnCard.contains("!")||nameOnCard.contains("@")
        		||nameOnCard.contains("#")||nameOnCard.contains("$")||nameOnCard.contains("%")||nameOnCard.contains("^")
        		||nameOnCard.contains("*")||nameOnCard.contains("+")||nameOnCard.contains("?")||nameOnCard.contains("<")
        		||nameOnCard.contains(">")) {
            	throw new Exception();
            }
            }catch(Exception e) {
            System.out.println("Invalid input! ';:!@#$%^*+?<>'shall not be contained.");
           	nameOnCard = validateNameOnCard(scanner);
        }
        return nameOnCard;
    }

    public String validateCardNumber(Scanner scanner) {
      	System.out.println("Please enter the card number :");
    	String cardNumber = "";
    	try {
    		cardNumber = scanner.nextLine();
    		if(!(cardNumber.matches("5[1-5][0-9]{14}")||cardNumber.matches("4[0-9]{15}")||cardNumber.matches("3[4,7][0-9]{13}"))){
    			throw new Exception();
    			}
    		}catch(Exception e) {
        System.out.println("Invalid input! Card number should specify.");
        cardNumber = validateCardNumber(scanner); 
        }
    	return cardNumber;
    }
    
    public double validateUnitPrice(Scanner scanner) {
      	System.out.println("Please enter the unit price :");
      	double unitPrice =0.00;
    	try {
			unitPrice = scanner.nextDouble();
		} catch (Exception e) {
			System.out.println("Invalid input! unit price should be an double.");
			unitPrice = validateUnitPrice(scanner);
		}   
    	return unitPrice;
    }
    
    public int validateQuantity(Scanner scanner) {
      	System.out.println("Please enter the quantity :");
      	int quantity = 0;
    	try {
			quantity = scanner.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input! Quantity should be an integer.");
			quantity = validateQuantity(scanner);
		}
    	return quantity;
    }
    
    public String validateExpDate(Scanner scanner) {
      	System.out.println("Please enter the date of expiry :");
    	String expDate = "";
    	try {
    		expDate = scanner.nextLine();
            if(!(expDate.matches("0[1-9]/201[6-9]")||expDate.matches("0[1-9]/201[6-9]")||expDate.matches("0[1-9]/202[0-9]")
        		||expDate.matches("0[1-9]/203[0-1]")||expDate.matches("1[0-2]/201[6-9]")||expDate.matches("1[0-2]/201[6-9]")
        		||expDate.matches("1[0-2]/202[0-9]")||expDate.matches("1[0-2]/203[0-1]"))){
        	throw new Exception();
            }
    	}catch(Exception e) {
        	System.out.println("Invalid input! Expire date should be between 01/2016 to 12/2031.");
            expDate = validateExpDate(scanner); 
        }
    	return expDate;
    }
}
    

    
    
