package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:mysql://localhost:3308/JDBCDemo1";
    private static final String USER = "root";
    private static final String PASSWORD = "potato";

    public static Connection getConnection(){
    	Connection conn = null;
    	try {
    		conn = DriverManager.getConnection(URL, USER, PASSWORD);
    		if(conn != null) {
    			System.out.println("Connection Successfully");
    		}else {
    			System.out.println("Connection Failed");
    		}
    	}catch (SQLException e) {
			// TODO: handle exception
    		System.out.println("Exception: " + e.getMessage());
		}
		return conn;
    }
}
