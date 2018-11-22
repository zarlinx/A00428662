package com.mcda5510.connect;
import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLJDBCConnection implements DBConnection{

	public Connection setupConnection()  {

		Connection connection = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			// Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Setup the connection with the DB

//			connection = DriverManager.getConnection("jdbc:mysql://localhost/transactions?" 				+ "user=root&password=root123" // Creds
			connection = DriverManager.getConnection("jdbc:mysql://dev.cs.smu.ca:3306/q_cai?" 				+ "user=q_cai&password=A00428662" // Creds		
			+ "&useSSL=false" // b/c localhost
			+"&allowPublicKeyRetrieval=true"
					+ "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"); // timezone

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return connection;
	}		
	
	
}
