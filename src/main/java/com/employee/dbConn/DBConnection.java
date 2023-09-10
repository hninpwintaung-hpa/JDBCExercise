package com.employee.dbConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnection {

	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=JDBCEXAMPLE";
	private static final String user = "sa";
	private static final String pass = "123@ace";

	private DBConnection() {
	};

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, user, pass);
	}

	public static HikariDataSource getDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(URL);
		config.setUsername(user);
		config.setPassword(pass);
		config.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		return new HikariDataSource(config);
	}
}
