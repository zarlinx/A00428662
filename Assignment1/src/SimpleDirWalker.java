import java.io.File;

public class SimpleDirWalker {

    public void walk( String path ) {

        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
                System.out.println( "Dir:" + f.getAbsoluteFile() );
            }
            else {
                System.out.println( "File:" + f.getAbsoluteFile() );
            }
        }
    }

    public static void main(String[] args) {
    	SimpleDirWalker fw = new SimpleDirWalker();
        fw.walk("C:\\Users\\10901\\Documents\\GitHub\\MCDA5510_Assignments\\Sample Data\\" );
    }

}