package com.mcda5510.dao;

/**
 * Original source code from 
 * http://www.vogella.com/tutorials/MySQLJava/article.html
 * 
**/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.mcda5510.entity.Transaction;


public class MySQLAccess {
	
	public Statement statement = null;
	private ResultSet resultSet = null;
		
	int trxnID,quantity;
	String nameOnCard,cardNumber,expDate,createdBy,creditCardType;
	double unitPrice,totalPrice;
	Transaction trxn = new Transaction();
	
		
	public Connection setupConnection() throws Exception {

		Connection connection = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			// Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Setup the connection with the DB

			connection = DriverManager.getConnection("jdbc:mysql://localhost/Transactions?" // DTP
																							// I
																							// spelled
																							// transactoins
																							// wrong
																							// oops
					+ "user=root&password=root123" // Creds
					+ "&useSSL=false" // b/c localhost
					+ "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"); // timezone

		} catch (Exception e) {
			throw e;
		} finally {

		}
		return connection;
	}

	public Collection<Transaction> getAllTransactions(Connection connection) {
		Statement statement = null;
		ResultSet resultSet = null;
		Collection<Transaction> results = null;
		// Result set get the result of the SQL query
		try {
			// Statements allow to issue SQL queries to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from q_cai.transaction");
			results = createTrxns(resultSet);

			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			statement = null;
			resultSet = null;
		}
		return results;

	}

	private Collection<Transaction> createTrxns(ResultSet resultSet) throws SQLException {
		Collection<Transaction> results = new ArrayList<Transaction>();

		while (resultSet.next()) {
			Transaction trxn = new Transaction();
			trxn.setID(resultSet.getInt("ID"));
			trxn.setNameOnCard(resultSet.getString("NameOnCard"));
			trxn.setCardNumber(resultSet.getString("CardNumber"));
			trxn.setUnitPrice(resultSet.getDouble("UnitPrice"));
			trxn.setQuantity(resultSet.getInt("Quantity"));
			trxn.setTotalPrice(resultSet.getDouble("TotalPrice"));
			trxn.setExpDate(resultSet.getString("ExpDate"));
			trxn.setCreditCardType(resultSet.getString("CreditCardType"));
			results.add(trxn);
		}
		return results;

	}
    
/*    public void manageWizard() {

  	    Scanner scanner = new Scanner(System.in);
	    String operation = "";

	    System.out.println("Please choose 1, 2, 3, 4 to manage the transaction :\n"
	    +"1 = Create a new record\n"
	    +"2 = Update an existing record\n"
	    +"3 = Get a record\n"
	    +"4 = Remove a record\n");

	    operation = scanner.nextLine();
		if(operation.equals("1")) {
        	validateIDCreate(scanner);
            validateNameOnCard(scanner);
            validateCardNumber(scanner);
            validateUnitPrice(scanner);
            validateQuantity(scanner);
            validateExpDate(scanner);
            validateCreditCardType(scanner);
            createTransaction(trxn);
        }
        else if(operation.equals("2")) {
        	validateIDUpdate(scanner);
        	validateNameOnCard(scanner);
            validateCardNumber(scanner);
            validateUnitPrice(scanner);
            validateQuantity(scanner);
            validateExpDate(scanner);
            validateCreditCardType(scanner);
        	updateTransaction(trxn);
        }
        else if(operation.equals("3")) {
        	validateIDGet(scanner);
        	getTransaction(trxnID);
        }
        else if(operation.equals("4")) {
        	validateIDRemove(scanner);
            removeTransaction(trxnID);
    	}
        else {
        }
        manageWizard();
        scanner.close();		 
	}

	private void validateCreditCardType(Scanner scanner) {

        System.out.println("Please choose 1, 2, 3 to decide the credit card type :\n"
	    +"1 = MasterCard\n"
	    +"2 = Visa\n"
	    +"3 = American Express\n");
        String type = scanner.nextLine();
		if(type.equals("1")) {
			if(trxn.getCardNumber().matches("5[1-5][0-9]{14}")){
			trxn.setCreditCardType("MasterCard");
			}else {
				validateCreditCardType(scanner);
			}
        
        }else if(type.equals("2")) {
        	if(trxn.getCardNumber().matches("4[0-9]{15}")){
    			trxn.setCreditCardType("Visa");
    			}else {
    				validateCreditCardType(scanner);
    			}
        	
        }else if(type.equals("3")) {
        	if(trxn.getCardNumber().matches("3[4,7][0-9]{13}")){
    			trxn.setCreditCardType("American Express");
    			}else {
    				validateCreditCardType(scanner);
    			}
        	
        }else {
            validateCreditCardType(scanner);
        }

		
	}

	public int validateIDCreate(Scanner scanner) {

      	System.out.println("Please enter the ID :");
    	try {
            trxnID = scanner.nextInt();
     		String query = "SELECT ID FROM Transaction";
    		ResultSet rs = st.executeQuery(query);
    		while (rs.next()){
    		    int id = rs.getInt("id");
    		    if(id == trxnID) { 
    		        Assignment3.logger.error("Creating transaction record failed! ID(Primary Key) already exists.");
    		        st.close();
    	    		rs.close();   	    		  
                    manageWizard();   		           
    		    } 
    		}
    		st.close();
    		rs.close();
        } catch (Exception e) {
        	Assignment3.logger.error("Invalid input! ID should be an integer.");
        	trxnID=validateIDCreate(scanner);
        }
    	trxn.setID(trxnID);
    	return trxnID;
    }
    	
    public String validateNameOnCard(Scanner scanner) {

      	System.out.println("Please enter the name on card :");
    	nameOnCard = scanner.nextLine();

        if(nameOnCard.contains(";")||nameOnCard.contains(":")||nameOnCard.contains("!")||nameOnCard.contains("@")
        		||nameOnCard.contains("#")||nameOnCard.contains("$")||nameOnCard.contains("%")||nameOnCard.contains("^")
        		||nameOnCard.contains("*")||nameOnCard.contains("+")||nameOnCard.contains("?")||nameOnCard.contains("<")
        		||nameOnCard.contains(">")) {
        	Assignment3.logger.error("Invalid input! ';:!@#$%^*+?<>'shall not be contained.");
           	nameOnCard=validateNameOnCard(scanner);
        }
        trxn.setNameOnCard(nameOnCard);
        return nameOnCard;
    }

    public void validateCardNumber(Scanner scanner) {

      	System.out.println("Please enter the card number :");
    	cardNumber = scanner.nextLine();
        if(cardNumber.matches("5[1-5][0-9]{14}")||cardNumber.matches("4[0-9]{15}")||cardNumber.matches("3[4,7][0-9]{13}")){
        	trxn.setCardNumber(cardNumber);
        }else {
        Assignment3.logger.error("Invalid input! Card number should specify.");
        validateCardNumber(scanner); 
        }
    }
    
    public void validateUnitPrice(Scanner scanner) {

      	System.out.println("Please enter the unit price :");
      	
    	try {
			unitPrice = scanner.nextDouble();
		} catch (Exception e) {
			Assignment3.logger.error("Invalid input! unit price should be an double.");
			validateUnitPrice(scanner);
		}   
    	trxn.setUnitPrice(unitPrice);
    }
    
    public void validateQuantity(Scanner scanner) {

      	System.out.println("Please enter the quantity :");
      	
    	try {
			quantity = scanner.nextInt();
		} catch (Exception e) {
			Assignment3.logger.error("Invalid input! Quantity should be an integer.");
			validateQuantity(scanner);
		}
    	trxn.setQuantity(quantity);
    }
    
    public void validateExpDate(Scanner scanner) {

      	System.out.println("Please enter the date of expiry :");
    	expDate = scanner.nextLine();
        if(expDate.matches("0[1-9]/201[6-9]")||expDate.matches("0[1-9]/201[6-9]")||expDate.matches("0[1-9]/202[0-9]")
        		||expDate.matches("0[1-9]/203[0-1]")||expDate.matches("1[0-2]/201[6-9]")||expDate.matches("1[0-2]/201[6-9]")
        		||expDate.matches("1[0-2]/202[0-9]")||expDate.matches("1[0-2]/203[0-1]")){
        	trxn.setExpDate(expDate);
        }else {
        Assignment3.logger.error("Invalid input! Expire date should be between 01/2016 to 12/2031.");
        validateExpDate(scanner); 
        }
    }
    
    public void validateIDUpdate(Scanner scanner) {

    	int notDuplicate=1;
    	System.out.println("Please enter the ID :");
    	try {
            trxnID = scanner.nextInt();

            try {
     		      String query = "SELECT ID FROM Transaction";
    		      Statement st = Assignment3.connection.createStatement();
    		      ResultSet rs = st.executeQuery(query);
    		          	
   		          		      
    		      while (rs.next())
    		      {
    		        int id = rs.getInt("ID");
    		        
    		        if(id==trxnID) {
    		        	notDuplicate=0;
    		        	notDuplicate=notDuplicate*notDuplicate;			  
    				  
    		        } else 
    		        {
    		        	notDuplicate=notDuplicate*notDuplicate;
        		    }
    		      }
    		        if (notDuplicate==1)
    		        {
    		        	Assignment3.logger.error("Update failed! ID(Primary Key) does Not exist.");
    		        	st.close();
    		        	manageWizard();
    		        }     		       

    	    }catch(Exception e)
    	    {
    	    	e.printStackTrace();
            }

        }catch(Exception e)
            {
        	Assignment3.logger.error("Invalid input! ID should be an integer.");
            }
    }

*/	
    
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
                    return output;	           
    		    } 
    		}
    		st.close();
    		rs.close();
        } catch (Exception e) {

        }
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
			output = "Succeed!    The transaction record created is : \n"+trxn.toString();	
		} catch (Exception e) {
			e.printStackTrace();
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
		      return output;
		  }else {     		       
    	
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
		return output;
		  }
    	
	}
	
	public String getTransaction(int trxnID,Connection connection) {
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
 		    output = "Get rejected!    \n"+"ID(Primary Key) does NOT exists.";
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

				    output="Get approved!    "+
				    "ID: "+ID+", Name On Card: "+nameOnCard+", Card Number: "+cardNumber+", Unit Price: "+unitPrice
				    +", Quantity: "+quantity+", Total Price: "+totalPrice+", Expire Date: "+expDate+", Created On: "
				    + createdOn+", Created By: "+createdBy;
			    }
			
		    } catch (Exception e) {
			    e.printStackTrace();
		    }
		    return output;
 		}
	}
	
	public Transaction getTransactionObject(int trxnID,Connection connection) {
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
 		    return output;
 		}

	}


}
