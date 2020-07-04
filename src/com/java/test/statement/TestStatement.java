package com.java.test.statement;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import org.junit.Test;


public class TestStatement {
	/**
	 * 使用statement來獲取數據庫中的相關數據,Connection ,Statement,ResultSet,ResultSetMetaData 都是接口類，是一種規範。具體的實現，是由各家廠商來實現的。
	 * 一組實現的集合就是驅動。
	 */
	@SuppressWarnings("resource")
	@Test
	public void testLogin() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("用户名：");
		String userName = sc.nextLine();
		System.out.println("密码");
		String password = sc.nextLine();
		
		String sql = "SELECT user,password FROM user_table WHERE USER = '" + userName + "' AND PASSWORD = '" + password
				+ "'";
		
		User user = get(sql,User.class);
		
		if (user != null) {
			
			System.out.println("用户登录成功");
			
		} else {
			
			System.out.println("用户登录失败");
		}
		
		
	}

	private <T> T get(String sql, Class<T> clazz) {
		
		T t = null;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			// 1.加载配置文件
			InputStream input = TestStatement.class.getClassLoader().getResourceAsStream("jdbc.properties");
			Properties info =  new Properties();
			info.load(input);
			
			// 2.读取配置文件
			String url = info.getProperty("url");
			String user = info.getProperty("user");
			String password = info.getProperty("password");
			String driverClass = info.getProperty("driverClass");
			
			// 3.加载驱动
			Class.forName(driverClass);
			
			// 4.获取数据连接
			conn = DriverManager.getConnection(url, user, password);
			st = conn.createStatement();
			 rs = st.executeQuery(sql);
			
			// 5.获取结果集的元数据
			ResultSetMetaData rsmd = rs.getMetaData();
			
			// 6.获取结果集的列数
			int columnCount = rsmd.getColumnCount();
			
			if(rs.next()) {
				
				t = clazz.newInstance();
				
				for (int i=0;i < columnCount;i++) {
					
					// 獲取列的別名
					String columnName = rsmd.getColumnLabel(i + 1);
					
					// 根據列名獲取對應表中的數據
					Object columnVal = rs.getObject(columnName);
					
					// 将数据表中获取到的信息封装进对象	
					Field field = clazz.getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(t,columnVal);
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return t;
	}
}
