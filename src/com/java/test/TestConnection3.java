package com.java.test;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import com.mysql.cj.jdbc.Driver;

public class TestConnection3 {
	
	/**
	 * 数据库连接的第三种方式 使用DriverManager类
	 * 	使用DriverManager来获取连接
	 */
	@Test
	public void testConnection() {
		
		try {
			// 创建连接所需要的四种变量
			String url = "jdbc:mysql://192.168.1.105/mysql";
			String user = "root";
			String password = "128680";
			String dirverName = "com.mysql.cj.jdbc.Driver";
			
			// 实例化Driver
			Class<?> clazz = Class.forName(dirverName);
			Driver driver = (Driver)clazz.newInstance();
			
			// 注册驱动
			DriverManager.registerDriver(driver);
			
			// 获取连接
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
