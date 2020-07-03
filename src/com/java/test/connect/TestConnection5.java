package com.java.test.connect;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.junit.Test;

public class TestConnection5 {
	
	/**
	 * 连接方式5（最终版）
	 * 实现了代码和数据的分离，如果需要修改配置文件信息，不需要进入的代码部分
	 * 如果修改配置文件，省去了重新编译的过程
	 */
	@Test
	public void testConnection() {
		
		try {
			
			// 使用配置文件的方式加载连接所需要的信息
			InputStream inStream = TestConnection5.class.getClassLoader().getResourceAsStream("jdbc.properties");
			Properties info = new Properties();
			info.load(inStream);
			
			// 读取配置文件
			String url = info.getProperty("url");
			String user = info.getProperty("user");
			String password = info.getProperty("password");
			String driverClass = info.getProperty("driverName");
			
			// 声明驱动名称
			Class.forName(driverClass);
			
			// 创建连接方式
			Connection conn = DriverManager.getConnection(url, user, password);
			
			System.out.println(conn);		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
