package banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
	
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/bank";
		String user = "root";
		String pass = "Mohiddin@Sri1319";
		Connection con = null;
		try { 
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass); 
		}
		catch(Exception e) {
			System.out.println("Exception occured");
			e.printStackTrace();
		}
		return con;
		}
}

