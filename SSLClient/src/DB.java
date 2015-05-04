import java.sql.*;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class DB {
	static Connection conn = null;
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/sslusers?"
			+ "?verifyServerCertificate=false&amp;requireSSL=true&amp;useSSL=true";
	String USER = "root";
	String PASSWORD = "root";

	public boolean connect() {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return false;
	}

	public boolean executeScalar(String query) {

		Statement stmt = null;
		// STEP 4: Execute a query
		// System.out.println("Creating statement...");
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int count = 0;
			if (rs.last()) {
				count = rs.getInt(1);
				rs.beforeFirst();
			}
			return count > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public int executeScalar(String query, int a) {

		Statement stmt = null;
		// STEP 4: Execute a query
		System.out.println("Creating statement...");
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int count = 0;
			if (rs.last()) {
				count = rs.getInt(1);
				rs.beforeFirst();
			}
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
