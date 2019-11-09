package hu.tigra.itnap.dao;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			return connection.prepareStatement("select 1 from FILESTORE").execute();
		} catch (SQLException e) {
						e.printStackTrace();
			return false;
		}
	}
	
	public Long storeFile(String name, int lenght, InputStream is) {
		Connection connection;
		try {
			connection = openConnection();
			PreparedStatement pstmt = connection.prepareStatement("insert into FILESTORE(nev,tartalom) values (?,?);",
					Statement.RETURN_GENERATED_KEYS);

			// set parameters
			pstmt.setString(1, name);
			pstmt.setBinaryStream(2, is);

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
			}

			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getLong(1);
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*public void downloadFile(Long id, OutputStream is) {
		Connection connection;
		try {
			connection = openConnection();
			PreparedStatement pstmt = connection.prepareStatement("insert into FILESTORE(nev,tartalom) values (?,?);",
					Statement.RETURN_GENERATED_KEYS);

			// set parameters
			pstmt.setString(1, name);
			pstmt.setBinaryStream(2, is);

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
			}

			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getLong(1);
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}*/
}
