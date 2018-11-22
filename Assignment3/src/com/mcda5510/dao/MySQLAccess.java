package com.mcda5510.dao;

/**
 * Original source code from 
 * http://www.vogella.com/tutorials/MySQLJava/article.html
 * 
**/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.mcda5510.entity.Transaction;
import com.mcda5510.service.MyWebService;


public class MySQLAccess {
	final static Logger logger = Logger.getLogger(MyWebService.class);
	public Statement statement = null;
	private ResultSet resultSet = null;
		

	public boolean validateInputs(Transaction trxn) {
		String nameOnCard = trxn.getNameOnCard();
		String cardNumber = trxn.getCardNumber();
		String expDate = trxn.getExpDate();
		if(nameOnCard.contains(";")||nameOnCard.contains(":")||nameOnCard.contains("!")||nameOnCard.contains("@")
        		||nameOnCard.contains("#")||nameOnCard.contains("$")||nameOnCard.contains("%")||nameOnCard.contains("^")
        		||nameOnCard.contains("*")||nameOnCard.contains("+")||nameOnCard.contains("?")||nameOnCard.contains("<")
        		||nameOnCard.contains(">")||cardNumber.contains(";")||cardNumber.contains(":")||cardNumber.contains("!")
        		||cardNumber.contains("@")||cardNumber.contains("#")||cardNumber.contains("$")||cardNumber.contains("%")
        		||cardNumber.contains("^")||cardNumber.contains("*")||cardNumber.contains("+")||cardNumber.contains("?")
        		||cardNumber.contains("<")||cardNumber.contains(">")||expDate.contains(";")||expDate.contains(":")
        		||expDate.contains("!")||expDate.contains("@")||expDate.contains("#")||expDate.contains("$")
        		||expDate.contains("%")||expDate.contains("^")||expDate.contains("*")||expDate.contains("+")
        		||expDate.contains("?")||expDate.contains("<")||expDate.contains(">")) {
			logger.info("Invalid input!    \n';:!@#$%^*+?<>'shall not be contained.    \n");
			return false;
		}
		logger.info("Validation approved!    \n");
		return true;
	}
	
	
	public String createTransaction(Transaction trxn, Connection connection) {
		String output = "";
    	try {
     		String query = "SELECT ID FROM Transaction";
		    Statement st = connection.createStatement();
    		ResultSet rs = st.executeQuery(query);
    		while (rs.next()){
    		    int id = rs.getInt("ID");
    		    if(id == trxn.getID()) { 
    				output = "Create rejected!    ID(Primary Key) already exists."; 	
    	 		    logger.info("Create rejected!    \n"+"ID(Primary Key) already exists.");
                    return output;	           
    		    } 
    		}
    		st.close();
    		rs.close();
        } catch (Exception e) {

        }
    	
    	if(validateInputs(trxn)) {
    	
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into Transaction (ID, NameOnCard, CardNumber, UnitPrice, Quantity, "
							+ "TotalPrice, ExpDate, CreatedOn, CreatedBy, CreditCardType) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			preparedStatement.setInt(1, trxn.getID());
			preparedStatement.setString(2, trxn.getNameOnCard());
			preparedStatement.setString(3, trxn.getCardNumber());
			preparedStatement.setDouble(4, trxn.getUnitPrice());
			preparedStatement.setInt(5, trxn.getQuantity());
			preparedStatement.setDouble(6, trxn.getTotalPrice());
			preparedStatement.setString(7, trxn.getExpDate());
			preparedStatement.setString(8, trxn.getCreatedOn());
			preparedStatement.setString(9, trxn.getCreatedBy());
			preparedStatement.setString(10, trxn.getCreditCardType());
			preparedStatement.executeUpdate();
			output = "Create success!    \nThe transaction record created is : \n"+trxn.toString();
			logger.info("Create success!    \nThe transaction record created is : \n"+trxn.toString());
		} catch (Exception e) {
			e.printStackTrace();
    	}
    	}
		return output;
    	
	}

	public String updateTransaction(Transaction trxn, Connection connection){
		String output = "";
    	int notDuplicate=1;
    	try {
		      String query = "SELECT ID FROM Transaction";
		      Statement st = connection.createStatement();
		      ResultSet rs = st.executeQuery(query);		          		      
		      while (rs.next()){
		          int id = rs.getInt("ID");		        
		          if(id==trxn.getID()) {
		        	  notDuplicate=0;
		        	  notDuplicate=notDuplicate*notDuplicate;			  
				  
		          } else {
		        	notDuplicate=notDuplicate*notDuplicate;
  		          }
		      }
		        st.close();
	    		rs.close(); 
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
		      
		  if (notDuplicate==1) {
			  output = "Update failed!    ID(Primary Key) does Not exist.";
	 		  logger.info("Update failed!    \n"+"ID(Primary Key) does not exists.");
		      return output;
		  }else if(validateInputs(trxn)){     		       
    	
		try { 
			PreparedStatement preparedStatement = connection
					.prepareStatement("update Transaction set NameOnCard=?, CardNumber=?, UnitPrice=?, Quantity=?, "
							+ "TotalPrice=?, ExpDate=?, CreatedOn=?, CreatedBy=?, CreditCardType=? where (ID=?) ");
			// Parameters start with 1
			preparedStatement.setString(1, trxn.getNameOnCard());
			preparedStatement.setString(2, trxn.getCardNumber());
			preparedStatement.setDouble(3, trxn.getUnitPrice());
			preparedStatement.setInt(4, trxn.getQuantity());
			preparedStatement.setDouble(5, trxn.getTotalPrice());
			preparedStatement.setString(6, trxn.getExpDate());
			preparedStatement.setString(7, trxn.getCreatedOn());
			preparedStatement.setString(8, trxn.getCreatedBy());
			preparedStatement.setString(9, trxn.getCreditCardType());
			preparedStatement.setInt(10, trxn.getID());
			preparedStatement.executeUpdate();
      		} catch (Exception e) {
			    e.printStackTrace();
			}
		output = "Succeed!    The transaction record updated is : \n"+trxn.toString();	
		logger.info("Update success!    \nThe transaction record updated is : \n"+trxn.toString());	
		  }
			return output;
	}
	
	public String getTransaction(int trxnID, Connection connection) {
		String output = null; 
		
		int notDuplicate=1;
		try {
  		    String query = "SELECT ID FROM Transaction";
 		    Statement st = connection.createStatement();
 		    ResultSet rs = st.executeQuery(query);
 		    while (rs.next()){
 		        int id = rs.getInt("ID"); 		        
 		        if(id==trxnID) {
 		        	notDuplicate=0;
 		        	notDuplicate=notDuplicate*notDuplicate; 				  
 		        } else {
 		        	notDuplicate=notDuplicate*notDuplicate;
     		    }
		    } 				
	    }catch(Exception e){
 	        e.printStackTrace();
// 	        output = "Invalid input!    All fields required but ';:!@#$%^*+?<>'.\n";
 	        return output;
        }
		
 		if (notDuplicate==1){
 		    output = "Get rejected!    \n"+"ID(Primary Key) does NOT exists.";
 		    logger.info("Get rejected!    \n"+"ID(Primary Key) does NOT exists.");
 		    return output;
 		}else {
		    try {
			    statement =connection.createStatement();
			    resultSet = statement.executeQuery("select * from Transaction where ID = "+trxnID);
			    if(resultSet.next()) {
				    int ID = resultSet.getInt("ID");
				    String nameOnCard = resultSet.getString("NameOnCard");
				    String cardNumber = resultSet.getString("CardNumber");
				    double unitPrice = resultSet.getDouble("UnitPrice");
				    int quantity = resultSet.getInt("Quantity");
				    double totalPrice = resultSet.getDouble("TotalPrice");
				    String expDate = resultSet.getString("ExpDate");
				    String createdOn = resultSet.getString("CreatedOn");
				    String createdBy = resultSet.getString("CreatedBy");

				    output=("Get approved!    \n"+
				    "ID: "+ID+"\n Name On Card: "+nameOnCard+"\n Card Number: "+cardNumber+"\n Unit Price: "+unitPrice
				    +"\n Quantity: "+quantity+"\n Total Price: "+totalPrice+"\n Expire Date: "+expDate+"\n Created On: "
				    + createdOn+"\n Created By: "+createdBy);
				    logger.info("Get approved!    \n"+
						    " ID: "+ID+"\n Name On Card: "+nameOnCard+"\n Card Number: "+cardNumber+"\n Unit Price: "+unitPrice
						    +"\n Quantity: "+quantity+"\n Total Price: "+totalPrice+"\n Expire Date: "+expDate+"\n Created On: "
						    + createdOn+"\n Created By: "+createdBy);
			    }
			
		    } catch (Exception e) {
			    e.printStackTrace();
		    }
		    return output;
 		}

	}
	
	public Transaction getTransactionObject(int trxnID, Connection connection) {
		Transaction trxn = new Transaction();
		    try {
			    statement =connection.createStatement();
			    resultSet = statement.executeQuery("select * from Transaction where ID = "+trxnID);
			    if(resultSet.next()) {
				    trxn.setID(resultSet.getInt("ID"));
				    trxn.setNameOnCard(resultSet.getString("NameOnCard"));
				    trxn.setCardNumber(resultSet.getString("CardNumber"));
				    trxn.setUnitPrice(resultSet.getDouble("UnitPrice"));
				    trxn.setQuantity(resultSet.getInt("Quantity"));
				    trxn.setExpDate(resultSet.getString("ExpDate"));
			    }			
		    } catch (Exception e) {
			    e.printStackTrace();
		    }
		return trxn;
 		
	}
	
	public String removeTransaction(int trxnID, Connection connection) {
		String output = null; 
		
		int notDuplicate=1;
		try {
  		    String query = "SELECT ID FROM Transaction";
 		    Statement st = connection.createStatement();
 		    ResultSet rs = st.executeQuery(query);
 		    while (rs.next()){
 		        int id = rs.getInt("ID"); 		        
 		        if(id==trxnID) {
 		        	notDuplicate=0;
 		        	notDuplicate=notDuplicate*notDuplicate; 				  
 		        } else {
 		        	notDuplicate=notDuplicate*notDuplicate;
     		    }
 		    } 				
	    }catch(Exception e){
 	        e.printStackTrace();
        }
		
 		if (notDuplicate==1){
 		    output = "Delete rejected!    \n"+"ID(Primary Key) does NOT exists.";
 		    logger.info("Delete rejected!    \n"+"ID(Primary Key) does NOT exists.");
 		    return output;
 		}else {
 			try {
 		        PreparedStatement preparedStatement = connection
 					.prepareStatement("delete from Transaction where ID = ?");
 				preparedStatement.setInt(1, trxnID);
 				preparedStatement.executeUpdate();
 		   	}catch (Exception e) {
 				e.printStackTrace();
 			}
			output="Delete approved!    \n";
 		    logger.info("Delete approved!    \n");
 		    return output;
 		}

	}


}
