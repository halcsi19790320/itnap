package hu.tigra.itnap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FileDao {

	
	public Connection openConnection() throws SQLException {
		/*String url = "jdbc:postgresql://localhost/test";
		Properties props = new Properties();
		props.setProperty("user", "fred");
		props.setProperty("password", "secret");
		props.setProperty("ssl", "true");
		Connection conn = DriverManager.getConnection(url, props);*/
		String url = "jdbc:postgresql://localhost/ftr6?user=ftr6&password=ftr6";
		return DriverManager.getConnection(url);
	}
	
	public boolean checkConnection() {
		Connection connection;
		try {
			connection = openConnection();
			return connection.prepareStatement("select 1").execute();
		} catch (SQLException e) {
						e.printStackTrace();
			return false;
		}
	}
}
