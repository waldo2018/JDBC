package com.java.test.connect;

import java.sql.Connection;
import java.util.Properties;

import org.junit.Test;

import com.mysql.cj.jdbc.Driver;

public class TestConnection2 {
	
	/**
	 * 数据库连接的第二种方式
	 * 与第一种方式相比，该方式通过反射的模式实例化，没有体现第三方驱动的API
	 */
	@Test
	public void testConnection(){
		
		try {
			
		// 创建Driver的实例化对象
		String className = "com.mysql.cj.jdbc.Driver";
		Class<?> clazz = Class.forName(className);
		Driver driver = (Driver)clazz.newInstance();
		
		// 提供连接数据的url
		String url = "jdbc:mysql://192.168.1.105/mysql";
		
		// 提供数据库连接所需要的用户名和密码
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "128680");
		
		// 创建数据库连接
		Connection conn = driver.connect(url, info);
		System.out.println(conn);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		
	}}
}
