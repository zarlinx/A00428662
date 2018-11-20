
import java.sql.Connection;
import java.util.Collection;

import org.apache.log4j.Logger;

public class Test {
	final static Logger logger = Logger.getLogger(Assignment2.class);
	static Connection connection = null;

	public static void main(String[] args) {

        
        try {
            MySQLAccess dao = new MySQLAccess();
        	connection = dao.setupConnection();
			dao.manageWizard();
			
/*			Collection<Transaction> trxns = dao.getAllTransactions(connection);
			for (Transaction trxn:trxns ){
				System.out.println(trxn.toString());
			}
*/			
			if (connection != null) {
				connection.close();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	Boolean createTransaction(Transaction trxn) {
		return null;		
	}
	
	Boolean updateTransaction(Transaction trxn) {
		return null;		
	}
	
	Boolean getTransaction(int trxnID) {
		return null;		
	}
	
	Boolean removeTransaction(int trxnID) {
		return null;		
	}
	
	
	
}