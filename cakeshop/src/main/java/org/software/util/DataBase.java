package org.software.util;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;


import javax.naming.InitialContext;

public class DataBase {
	public Connection getConnection(String profile) {
		Connection connection = null;
		
		/*
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost:5433/cakeshop";
		String user = "owner_shop";
		String password = "imtheboss";
		*/
	String JndiDataSourceName = "";
		
		if (profile.equals("admin")) {
			JndiDataSourceName = "cakeshopAdminDS";
			//user = "admin_shop";
			//password = "admin";
		}
		if (profile.equals("client")) {
			JndiDataSourceName = "cakeshopClientDS";
			//user = "client_shop";
			//password = "client";
		}
		if (profile.equals("guest")) {
			JndiDataSourceName = "cakeshopGuestDS";
			//user = "guest_shop";
			//password = "guest";
		}
		
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:jboss/" + JndiDataSourceName);
			connection = ds.getConnection();
			
			//Class.forName(driver);
			//connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}	
		
		return connection;
	}

	public void closeObject(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeObject(PreparedStatement preparedStatement) {
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeObject(Statement statement) {
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeObject(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}