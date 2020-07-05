package com.java.test.dbcp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

public class TestDbcp {

	/**
	 * 使用dbcp创建获取数据连接第一种方式
	 * @throws SQLException 
	 * 
	 */
	@Test
	public void testDbcp1() throws SQLException {
		
		BasicDataSource bds = new BasicDataSource();
		
		bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		bds.setUrl("jdbc:mysql://192.168.1.105:3306/test");
		bds.setUsername("root");
		bds.setPassword("128680");
		
		Connection conn = bds.getConnection();
		
		System.out.println(conn);
	}
	
	/**
	 * 使用DBCP配置文件来操作链接，第二种方式比较推荐
	 * @throws Exception 
	 */
	@Test
	public void testDbcp2() throws Exception {
		
		Properties info = new Properties();
		
		InputStream  is = TestDbcp.class.getClassLoader().getResourceAsStream("dbcp.properties");
		
		info.load(is);
		
		BasicDataSource bds = BasicDataSourceFactory.createDataSource(info);
		
		Connection conn = bds.getConnection();
		
		System.out.println(conn);
		
	}
}
