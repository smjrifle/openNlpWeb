package auth.user;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginService {
	public boolean login(User user)
	{
		DatabaseConnector dConnector = new DatabaseConnector();
		Connection con = dConnector.connectDB("openNlpWeb");
		boolean isUserExists=false;
		// Login
				try {
					Statement stmt = con.createStatement();
					
					String query = "select * from userdb where username='"+user.getUsername()+"' and password='"+user.getPassword()+"'";
					System.out.println(query);
					ResultSet rs = stmt.executeQuery(query);
					if(rs.next())
					{
						System.out.println("exists");
						isUserExists= true;
					}
					else
					{
						System.out.println("not exists");
						isUserExists= false;
						
					}
					 con.close();
					 
		           
				}
				
				catch (Exception e) {
					e.printStackTrace();
				}
				return isUserExists;
	}
}
