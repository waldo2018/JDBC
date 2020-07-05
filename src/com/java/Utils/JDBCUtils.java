package com.java.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class JDBCUtils {
	
	/**
	 * 獲取數據庫連接
	 * @return 數據庫連接
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
		
		// 加載配置文件
		InputStream input = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties info = new Properties();
		info.load(input);
		
		// 讀取配置文件信息
		String url = info.getProperty("url");
		String user = info.getProperty("user");
		String password = info.getProperty("password");
		String driverClass = info.getProperty("driverClass");
		
		// 加載驅動信息
		Class.forName(driverClass);
		
		// 獲取數據庫連接
		return DriverManager.getConnection(url, user, password);
	}
	
	/**
	 * 關閉數據庫資源
	 * @param conn 數據庫連接
	 * @param ps PreparedStatement連接
	 * @throws SQLException
	 */
	public static void closeResource(Connection conn ,  PreparedStatement ps) throws SQLException {
		
		if (conn != null) {
			conn.close();
		}
		if (ps != null) {
			ps.close();
		}
	}
	/**
	 * 關閉數據庫資源2
	 * @param conn 數據庫連接
	 * @param st Statement
	 * @param rs ResultSet
	 * @throws SQLException
	 */
	public static void closeResource(Connection conn , PreparedStatement pstmt , ResultSet rs) throws SQLException {
		
		if (conn != null) {			
			conn.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (rs != null) {
			rs.close();
		}
	}
	
	public static Connection getConnection2() throws SQLException {
		
		DataSource ds = new ComboPooledDataSource("c3p0-config");
		
		return ds.getConnection();
	}
}
