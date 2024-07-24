package gr.aueb.cf.schoolapp.service.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBUtil {
	
	private static BasicDataSource ds = new BasicDataSource();
	private static Connection connection;
	
	static {
		ds.setUrl("jdbc:mysql://localhost:3306/school6db?serverTimeZone=UTC");
		ds.setUsername("userdb6");
		ds.setPassword(System.getenv("PASS_DB6"));
		//ds.setPassword("12345");
		ds.setInitialSize(10);
		ds.setMaxTotal(50);
		ds.setMinIdle(8);
		ds.setMaxIdle(10);
	}
	
	/**
	 * No instances of this class should be available
	 */
	private DBUtil() {
		
	}
	
	public static Connection getConnection() throws SQLException {
		connection = ds.getConnection();
		System.out.println("Connection given");
		return connection;
	}
	
	public static void closeConnection() {
		try {
			if (connection != null) connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
