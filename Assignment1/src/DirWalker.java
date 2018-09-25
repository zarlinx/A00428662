import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.FileWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;

public class DirWalker {
	final static Logger logger = Logger.getLogger(DirWalker.class);	
	static long validRows= 0;
	static int skippedRows= 0;	
	static FileWriter fileWriter = null;
	
	
	public void outputFile( String csvFile ) {
		
        String CSVFile= csvFile;
        String[] title = {"First Name","Last Name","Street Number","Street","City","Province","Postal Code","Country","Phone Number","email Address"};
		
		try {			
			fileWriter= new FileWriter(CSVFile);
			
		    for( int i=0; i < title.length; i++ ) {
			    fileWriter.append( title[ i ] );    
			    fileWriter.append( "," );
		    }    
		    fileWriter.append( "Date" );
		    fileWriter.append( "\n" );
		} catch ( IOException e) {
			logger.error("IOException",e);
    		e.printStackTrace();		
	    }
	}
	
	
    public void walk( String path) {  
    	
    	File root = new File( path );
        File[] list = root.listFiles(); 
        
        try {
        	if (list == null) {
        		throw new IOException("File not found");}
        }catch(IOException e) {
        	logger.error("IOException",e);
    		e.printStackTrace();		
        	return;
        }        
        
        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath());
            }
            else {
            	String pathString= f.getParent();
            	int index= pathString.lastIndexOf("Sample Data")+"Sample Data\\".length();
            	Reader in;            	
        		try {
        			in = new FileReader(f.getAbsolutePath());
        			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        			
        			for( CSVRecord record : records) {
        				break;
        			}
        			for( CSVRecord record : records ) {        			
        				String tuple= "";
        				int j= 1;
        				
        				for( int i= 0; i < record.size(); i++ ) {	
        					tuple+= record.get(i)+ ","; 
        					if( record.get(i).length() == 0 ) {	        											
        						j=0;        						
        					}        					
        					}
        				fileWriter.append(tuple);
        				fileWriter.append(pathString.substring(index));
        				fileWriter.append("\n");
        				if( j==1 ) {
        					validRows++;
        				}
        				else{
        					skippedRows++;
                                                logger.info("A Row Has been skipped!");        					
        				}
        			}        
                } catch(IOException e) {
                	logger.error("IOException:"+ e);
                	e.printStackTrace();
                }
            }
        }    	    
    }	

	
    public static void main(String[] args) {	
    	
    	final long startTime = System.currentTimeMillis();	
    	DirWalker fw = new DirWalker();    	
    	
    	fw.outputFile( "/home/student_2018_fall/q_cai/A00428662-MCDA5510/Assignment1/output/result.csv");
        fw.walk( "/home/student_2018_fall/q_cai/A00428662-MCDA5510/Sample Data/" ); 
    	try {    		
			DirWalker.fileWriter.close();
		} catch (IOException e) {
			logger.error("IOException",e);    	
			e.printStackTrace();
		} 
    	
	    final long endTime = System.currentTimeMillis();	
	    logger.info("\nTotal Execution Time: " + (endTime - startTime) +" ms" + "\n" + "Number Of Valid Rows :" + validRows + "\n" + "Number Of Skipped Rows :" + skippedRows);
    }

}
