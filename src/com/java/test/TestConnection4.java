package com.java.test;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class TestConnection4 {
	
	/**
	 * 连接的第四种方式
	 * DriverManager的类中已经可以自动注册Driver驱动，所以可以不用生成Driver实例类。
	 */
	@Test
	public void testConnection() {
		
		try {
			
			// 创建连接的四种参数
			String url = "jdbc:mysql://192.168.1.105/mysql";
			String user = "root";
			String password = "128680";
			String driverName = "com.mysql.cj.jdbc.Driver";
			
			// 获取Driver的实例化
			Class.forName(driverName);
			//Driver driver = (Driver)clazz.newInstance();
			
			// 获取数据连接
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
