import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class DirWalker {
	
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
			Logger.getLogger("01").log(Level.SEVERE, e.getMessage());
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
        	Logger.getLogger("01").log(Level.SEVERE, e.getMessage());
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
        				}
        			}        
                } catch(IOException e) {
                	Logger.getLogger("02").log(Level.SEVERE, e.getMessage());
                	e.printStackTrace();
                }
            }
        }    	    
    }	

	
    public static void main(String[] args) {		
    	final long startTime = System.currentTimeMillis();		
	      System.setProperty("java.util.logging.config.file",
	              "./logging.properties");		
    	DirWalker fw = new DirWalker();    	
    	
    	fw.outputFile( "C:\\Users\\10901\\Documents\\Github\\MCDA5510_Assignments\\Assignment1\\Assignment1\\output\\output.csv");
        fw.walk( "C:\\Users\\10901\\Documents\\GitHub\\MCDA5510_Assignments\\Sample Data\\" ); 
    	try {    		
			DirWalker.fileWriter.close();
		} catch (IOException e) {
			Logger.getLogger("01").log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} 
    	
	    final long endTime = System.currentTimeMillis();	
		Logger.getLogger("02").log(Level.INFO,"Total Execution Time: " + (endTime - startTime) +" ms" + "\n" + "Number Of Valid Rows :" + validRows + "\n" + "Number Of Skipped Rows :" + skippedRows);
    }

}