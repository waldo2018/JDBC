package com.java.test.c3p0;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestC3p0 {
	
	/**
	 *  使用C3P0獲取數據連接的第一種方式，不推薦使用該方式
	 * @throws PropertyVetoException 
	 * @throws SQLException 
	 */
	@Test
	public void testC3p01() throws PropertyVetoException, SQLException {
		
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
		cpds.setJdbcUrl("jdbc:mysql://192.168.1.105:3306/test");
		cpds.setUser("root");
		cpds.setPassword("128680");
		
		cpds.setMaxPoolSize(20);
		
		Connection conn = cpds.getConnection();
		
		System.out.println(conn);
		
	}
	
	/**
	 * 使用C3P0獲取數據庫連接的第二種方式，加載配置文件。比較推薦該方式
	 * @throws SQLException 
	 */
	@Test
	public void testC3p02() throws SQLException {
		
		ComboPooledDataSource cpds = new ComboPooledDataSource("c3p0-config");
		
		Connection conn = cpds.getConnection();
		
		System.out.println(conn);
	}
}
