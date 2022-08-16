package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

	public static Connection getConnection(Connection conn) {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				e.getSQLState();
			}
		}
		return conn;
	}

	private static Properties loadProperties() {

		try {
			FileReader fs = new FileReader("projeto-JDBC/db.properties");
			Properties props = new Properties();
			props.load(fs);
			fs.close();
			return props;

		} catch (IOException e) {
			e.getCause();
		}
		return null;
	}

}
