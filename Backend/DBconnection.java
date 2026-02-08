
import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {

     private static final String URL= "jdbc:sqlite:products.db";
     public static Connection getConnection(){
                try{
                    return DriverManager.getConnection(URL);

                }
                catch(Exception e){
                    e.printStackTrace();
                    return null;

                }
            
            }}
    