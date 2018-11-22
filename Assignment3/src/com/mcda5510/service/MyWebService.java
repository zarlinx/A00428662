package com.mcda5510.service;

import java.sql.Connection;

import com.mcda5510.connect.ConnectionFactory;
import com.mcda5510.dao.MySQLAccess;
import com.mcda5510.entity.Transaction;

public class MyWebService {
	
	ConnectionFactory factory=new ConnectionFactory();
	Connection connection=factory.getConnection("mySQLJDBC");
	MySQLAccess dao=new MySQLAccess();
	Transaction trxn = new Transaction();
	String out = null;
	
	public String createTransaction(int ID, String nameOnCard, String cardNumber, double unitPrice, int quantity, String expDate) {
		trxn.setID(ID);
		trxn.setNameOnCard(nameOnCard);
		trxn.setCardNumber(cardNumber);
		trxn.setUnitPrice(unitPrice);
		trxn.setQuantity(quantity);
		trxn.setExpDate(expDate);
		out = dao.createTransaction(trxn,connection);	
		return out;
//		} else {
//			out = "Invalid input!    All fields required but ';:!@#$%^*+?<>'.\n";
//			return out;
//		}
	}
	
	public String updateTransaction(int ID, String nameOnCard, String cardNumber, double unitPrice, int quantity, String expDate) {
		trxn.setID(ID);
		trxn.setNameOnCard(nameOnCard);
		trxn.setCardNumber(cardNumber);
		trxn.setUnitPrice(unitPrice);
		trxn.setQuantity(quantity);
		trxn.setExpDate(expDate);
		out=dao.updateTransaction(trxn,connection);		
		
		return out;
	}
	
	public String getTransaction (int ID) {
		out = dao.getTransaction(ID, connection);

		return out;
	}
	
	public String removeTransaction(int ID) {
		String out=dao.removeTransaction(ID,connection);
		
		return out;
	}

}
