package auth.user;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector
{
    public Connection connectDB(String dbName)
    {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String dbURL = "jdbc:mysql://localhost/";
			String username = "root";
			String password = "";
            try {
                // Load driver
                Class.forName(driver);
            }
            
            catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            
			System.out.println("Driver Loaded.");
			
			// Establish connection
			Connection con = DriverManager.getConnection(dbURL + dbName, username, password);
			System.out.println("Connection Established.");
			
			return con;
		}
		
		catch (SQLException ex) {
			ex.printStackTrace();
            return null;
		}
    }
}
