package com.springapp.productspring.utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class DbUtility {

	private static Properties dbProperties;

	static {
		dbProperties = new Properties();
		FileReader reader = null;
		try {
			reader = new FileReader("src/main/resources/application.properties");
			dbProperties.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getDbUrl() {
		return dbProperties.getProperty("spring.datasource.url");
	}

	private static String getDbDriver() {
		return dbProperties.getProperty("spring.datasource.driver-class-name");
	}

	private static String getDbUserName() {
		return dbProperties.getProperty("spring.datasource.username");
	}

	private static String getDbPassword() {
		return dbProperties.getProperty("spring.datasource.password");
	}

	public static Connection createConnection() throws ClassNotFoundException, SQLException, Exception{
		Connection connection = null;
		try {
			Class.forName(getDbDriver());

			connection = DriverManager.getConnection(
					getDbUrl(), 
					getDbUserName(),
					getDbPassword()
					);
			return connection;

		} catch (ClassNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}
}
