package com.java.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import com.mysql.cj.jdbc.Driver;

public class TestConnection1 {
	
	/**
	 * 测试数据库连接方式1
	 * 1.注册驱动程序
	 * 2.提供连接数据库的详细信息 url user passwd
	 * 3.创建驱动连接。 driver.connet方法
	 */
	@Test
	public void testConnection() {
		
		try {
			
		// 提供一个java.sql.Driver的实现类的对象
		Driver driver = new com.mysql.cj.jdbc.Driver();
		
		// 提供一个url指明操作的数据库
		String url = "jdbc:mysql://192.168.1.105:3306/mysql";
		
		// 提供properties对象，提供用户名和密码
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "128680");
		
		// 调用Driver获取连接
		Connection conn = driver.connect(url, info);
		System.out.println(conn);
		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
