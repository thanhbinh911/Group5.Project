package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class JDBCUtil {
	public static Connection openConnection() {
		Connection c  = null;
		try {
			// Register MySQL Driver with DriverManager
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// Parameters for connecting
			String port = "3306";
			String database = "pickleball";
			String url = "jdbc:mySQL://localhost:"+port+"/"+database;
			String username = "root";
			String password = "";
			//Connecting
			c = DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
	
	public static void closeConnection(Connection c) {
		try {
			if (c != null) {
				c.close();
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void printInfo(Connection c) {
		try {
			if (c != null) {
				DatabaseMetaData mtdt = c.getMetaData();
				System.out.println(mtdt.getDatabaseProductName());
				System.out.println(mtdt.getDatabaseProductVersion());
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	
}
