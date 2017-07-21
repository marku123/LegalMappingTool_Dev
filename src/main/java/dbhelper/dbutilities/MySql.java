package dbhelper.dbutilities;

import java.net.InetAddress;
import java.sql.*;

public class MySql {

	public static Connection connect() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn;

			if (InetAddress.getLocalHost().getHostName().matches(".*jpcloudusa008.*")) {
				// Connection for JavaPipe host.
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ukram123_unhcr_legaltool_dev", "ukram123_1", "%%bac0n2013");
			} else if (InetAddress.getLocalHost().getHostName().matches(".*xjdz3.*")) {
				// Connection for Daily Razor host.
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ukramcom_unhcr_legaltool_dev", "ukramcom_1", "%%bac0n2013");
			} else {

				// Connection for my local machine.
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ukram123_unhcr_legaltool", "ukram123", "%%bac0n2013");
			}

			return conn;
		} catch (Exception e) {

			return null;
		}
	}

	public static boolean close(Connection c) {
		try {
			c.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
