package com.devil.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MySQL {
	Connection connection;
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://120.77.171.124:3306/travel";
	String user = "ptx";
	String passWord = "qaz382820748!";
	public List<Map<String, String>> getData(String sql, String[] values) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			Statement statement;
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, passWord);
			if(!connection.isClosed()) {
				statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);
				
				while(resultSet.next()) {
					Map<String, String> hm = new HashMap<String, String>();
					for(int i = 0; i < values.length; i++) {
						hm.put(values[i], resultSet.getString(values[i]) == null ? "":resultSet.getString(values[i]));
					}
					list.add(hm);
				}
					statement.close();
			}
			connection.close();
		}catch (Exception e) {
			System.err.println("连接数据库失败");
			e.printStackTrace();

		}
		return list;
	}
}
