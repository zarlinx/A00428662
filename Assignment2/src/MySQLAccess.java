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
import java.util.Scanner;


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
    
    public void manageWizard() {

  	    Scanner scanner = new Scanner(System.in);
	    String operation = "";

	    System.out.println("Please choose 1, 2, 3, 4 to manage the transaction :\n"
	    +"1 = Create a new record\n"
	    +"2 = Update an existing record\n"
	    +"3 = Get a record\n"
	    +"4 = Remove a record\n");

	    operation = scanner.nextLine();
		if(operation.equals("1")) {
        	validateIDCreate();
            validateNameOnCard();
            validateCardNumber();
            validateUnitPrice();
            validateQuantity();
            validateExpDate();
            validateCreditCardType();
            createTransaction(trxn);
        }
        else if(operation.equals("2")) {
        	validateIDUpdate();
        	validateNameOnCard();
            validateCardNumber();
            validateUnitPrice();
            validateQuantity();
            validateExpDate();
            validateCreditCardType();
        	updateTransaction(trxn);
        }
        else if(operation.equals("3")) {
        	validateIDGet();
        	getTransaction(trxnID);
        }
        else if(operation.equals("4")) {
        	validateIDRemove();
            removeTransaction(trxnID);
    	}
        else {
        	Assignment2.logger.error("Invalid input! Choose operation from 1 to 4.");
        }
        manageWizard();
        scanner.close();		 
	}

	private void validateCreditCardType() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose 1, 2, 3 to decide the credit card type :\n"
	    +"1 = MasterCard\n"
	    +"2 = Visa\n"
	    +"3 = American Express\n");
        String type = scanner.nextLine();
		if(type.equals("1")) {
			if(trxn.getCardNumber().matches("5[1-5][0-9]{14}")){
			trxn.setCreditCardType("MasterCard");
			}else {
				Assignment2.logger.error("Invalid input! Please specify MasterCard type.");
				validateCreditCardType();
			}
        
        }else if(type.equals("2")) {
        	if(trxn.getCardNumber().matches("4[0-9]{15}")){
    			trxn.setCreditCardType("Visa");
    			}else {
    				Assignment2.logger.error("Invalid input! Please specify Visa type.");
    				validateCreditCardType();
    			}
        	
        }else if(type.equals("3")) {
        	if(trxn.getCardNumber().matches("3[4,7][0-9]{13}")){
    			trxn.setCreditCardType("American Express");
    			}else {
    				Assignment2.logger.error("Invalid input! Please specify American Express type.");
    				validateCreditCardType();
    			}
        	
        }else {
        	Assignment2.logger.error("Invalid input! Choose credit card type from 1 to 3.");
            validateCreditCardType();
        }

		
	}

	public int validateIDCreate() {
    	Scanner scanner = new Scanner(System.in);
      	System.out.println("Please enter the ID :");
    	try {
            trxnID = scanner.nextInt();
     		String query = "SELECT ID FROM Transaction";
    		Statement st = Assignment2.connection.createStatement();
    		ResultSet rs = st.executeQuery(query);
    		while (rs.next()){
    		    int id = rs.getInt("id");
    		    if(id == trxnID) { 
    		        Assignment2.logger.error("Creating transaction record failed! ID(Primary Key) already exists.");
    		        st.close();
    	    		rs.close();   	    		  
                    manageWizard();   		           
    		    } 
    		}
    		st.close();
    		rs.close();
        } catch (Exception e) {
        	Assignment2.logger.error("Invalid input! ID should be an integer.");
        	trxnID=validateIDCreate();
        }
    	trxn.setID(trxnID);
//    	scanner.close();
    	return trxnID;
    }
    	
    public String validateNameOnCard() {
    	Scanner scanner = new Scanner(System.in);
      	System.out.println("Please enter the name on card :");
    	nameOnCard = scanner.nextLine();

        if(nameOnCard.contains(";")||nameOnCard.contains(":")||nameOnCard.contains("!")||nameOnCard.contains("@")
        		||nameOnCard.contains("#")||nameOnCard.contains("$")||nameOnCard.contains("%")||nameOnCard.contains("^")
        		||nameOnCard.contains("*")||nameOnCard.contains("+")||nameOnCard.contains("?")||nameOnCard.contains("<")
        		||nameOnCard.contains(">")) {
        	Assignment2.logger.error("Invalid input! ';:!@#$%^*+?<>'shall not be contained.");
           	nameOnCard=validateNameOnCard();
        }
        trxn.setNameOnCard(nameOnCard);
//        scanner.close();
        return nameOnCard;
    }

    public void validateCardNumber() {
    	Scanner scanner = new Scanner(System.in);
      	System.out.println("Please enter the card number :");
    	cardNumber = scanner.nextLine();
        if(cardNumber.matches("5[1-5][0-9]{14}")||cardNumber.matches("4[0-9]{15}")||cardNumber.matches("3[4,7][0-9]{13}")){
        	trxn.setCardNumber(cardNumber);
        }else {
        Assignment2.logger.error("Invalid input! Card number should specify.");
        validateCardNumber(); 
        }
    }
    
    public void validateUnitPrice() {
    	Scanner scanner = new Scanner(System.in);
      	System.out.println("Please enter the unit price :");
      	
    	try {
			unitPrice = scanner.nextDouble();
		} catch (Exception e) {
			Assignment2.logger.error("Invalid input! unit price should be an double.");
			validateUnitPrice();
		}   
    	trxn.setUnitPrice(unitPrice);
    }
    
    public void validateQuantity() {
    	Scanner scanner = new Scanner(System.in);
      	System.out.println("Please enter the quantity :");
      	
    	try {
			quantity = scanner.nextInt();
		} catch (Exception e) {
			Assignment2.logger.error("Invalid input! Quantity should be an integer.");
			validateQuantity();
		}
    	trxn.setQuantity(quantity);
    }
    
    public void validateExpDate() {
    	Scanner scanner = new Scanner(System.in);
      	System.out.println("Please enter the date of expiry :");
    	expDate = scanner.nextLine();
        if(expDate.matches("0[1-9]/201[6-9]")||expDate.matches("0[1-9]/201[6-9]")||expDate.matches("0[1-9]/202[0-9]")
        		||expDate.matches("0[1-9]/203[0-1]")||expDate.matches("1[0-2]/201[6-9]")||expDate.matches("1[0-2]/201[6-9]")
        		||expDate.matches("1[0-2]/202[0-9]")||expDate.matches("1[0-2]/203[0-1]")){
        	trxn.setExpDate(expDate);
        }else {
        Assignment2.logger.error("Invalid input! Expire date should be between 01/2016 to 12/2031.");
        validateExpDate(); 
        }
    }
    
    public void validateIDUpdate() {
    	Scanner scanner = new Scanner(System.in);
    	int notDuplicate=1;
    	System.out.println("Please enter the ID :");
    	try {
            trxnID = scanner.nextInt();

            try {
     		      String query = "SELECT ID FROM Transaction";
    		      Statement st = Assignment2.connection.createStatement();
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
    		        	Assignment2.logger.error("Update failed! ID(Primary Key) does Not exist.");
    		        	st.close();
    		        	manageWizard();
    		        }     		       

    	    }catch(Exception e)
    	    {
    	    	e.printStackTrace();
            }

        }catch(Exception e)
            {
        	Assignment2.logger.error("Invalid input! ID should be an integer.");
            }
    }
    
    public void validateIDGet() {
    	Scanner scanner = new Scanner(System.in);
    	int notDuplicate=1;
    	System.out.println("Please enter the ID :");
    	try {
            trxnID = scanner.nextInt();

            try {
     		      String query = "SELECT ID FROM Transaction";
    		      Statement st = Assignment2.connection.createStatement();
    		      ResultSet rs = st.executeQuery(query);
    		          	
   		          		      
    		      while (rs.next())
    		      {
    		        int id = rs.getInt("ID");
    		        
    		        if(id==trxnID) {
    		        	notDuplicate=0;
    		        	notDuplicate=notDuplicate*notDuplicate;
		
    		          } else {
    		        	  notDuplicate=notDuplicate*notDuplicate;
        		      }
    		      }
    		          if (notDuplicate==1)
    		          {
    		        	  Assignment2.logger.error("Retrieve failed! ID(Primary Key) does NOT exists.");
    		        	  st.close();
    		        	  manageWizard();
    		          }     		       
    	    }catch(Exception e)
    	    {
    	    	e.printStackTrace();
            }

        }catch(Exception e)
            {
        	Assignment2.logger.error("Invalid input! ID should be an integer.");
            }
    }
    
	public boolean createTransaction(Transaction trxn) {
		try {
			PreparedStatement preparedStatement = Assignment2.connection
					.prepareStatement("insert into Transaction (ID, NameOnCard, CardNumber, UnitPrice, Quantity, "
							+ "TotalPrice, ExpDate, CreatedOn, CreatedBy, CreditCardType) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			// Parameters start with 1
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
			Assignment2.logger.info("Succeed! The transaction record created is : \n"+trxn.toString());	
		} catch (Exception e) {
			e.printStackTrace();
    	}
		return true;
	}

	public boolean updateTransaction(Transaction trxn){
		try { 
			PreparedStatement preparedStatement = Assignment2.connection
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
			Assignment2.logger.info("Succeed! The transaction record updated is : \n"+trxn.toString());	
		} catch (Exception e) {
			e.printStackTrace();
				}
		return true;
	}
	
	public boolean getTransaction(int trxnID) {
	      
		try {
			statement = Assignment2.connection.createStatement();
			resultSet = statement.executeQuery("select * from Transaction where ID = "+trxnID);
			Collection<Transaction> trxns = createTrxns(resultSet);
			
			for (Transaction trxn:trxns ){
				System.out.println(trxn.toString());
			}
			Assignment2.logger.info("Success! The transaction record got is : \n"+trxn.toString());	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void validateIDRemove() {
		Scanner scanner = new Scanner(System.in);
    	int notDuplicate=1;
    	System.out.println("Please enter the ID :");
    	try {
            trxnID = scanner.nextInt();

            try {
     		    String query = "SELECT ID FROM Transaction";
    		    Statement st = Assignment2.connection.createStatement();
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
    		        if (notDuplicate==1){
    		        	Assignment2.logger.error("Delete failed! ID(Primary Key) does NOT exists.");
    		        	st.close();
    		        	manageWizard();
    		        }     		       

    	    }catch(Exception e){
    	    	e.printStackTrace();
            }

        }catch(Exception e){
        	Assignment2.logger.error("Invalid input! ID should be an integer.");
        }
    }
	
	public boolean removeTransaction(int trxnID) {
		
		try {
			PreparedStatement preparedStatement = Assignment2.connection
					.prepareStatement("delete from Transaction where ID = ?");
			// Parameters start with 1
			preparedStatement.setInt(1, trxnID);
			preparedStatement.executeUpdate();
			
			//Collection<Transaction> trxns = getAllTransactions(Assignment2.connection);
			Assignment2.logger.info("Remove succeed!");
			//for(Transaction trxn:trxns) {
			//	System.out.println(trxn.toString());
		//	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}


}
