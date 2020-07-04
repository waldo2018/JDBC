package com.java.test.statement;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.java.Utils.JDBCUtils;

public class TestPreparedStatement2 {
	
	/**
	 * 这个是用来测试对数据库的查询操作(一條數據)
	 */
	@Test
	public void testPreparedStatement() {
		
		String user = "BB";
		String password = "654321";
		
		String sql = "select user, password from user_table where user=? and password=?";
		
		User usr = get(User.class,sql,user,password);
		
		System.out.println(usr.toString());
		
	}
	/**
	 * 这个是用来测试对数据库的查询操作(多條數據)
	 */
	@Test
	public void testPreparedStatement1() {
		
		String user = "AA";
		
		String sql = "select user, password from user_table where user=?";
		
		List<User> userList = getList(User.class,sql,user);
		
		System.out.println(userList.toString());
		
	}
	
	
	/**
	 * 獲取一條數據
	 * @param <T> 對象類
	 * @param clazz 實例類
	 * @param sql 執行語句
	 * @param args 執行參數
	 * @return 獲取一個對象
	 */
	private <T> T get(Class<T> clazz , String sql , Object... args) {
		
		// 聲明連接
		T t = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			// 獲取數據庫連接
			conn = JDBCUtils.getConnection();
			
			// 預編譯sql獲取preparedStatement對象
			ps = conn.prepareStatement(sql);
			
			// 填補佔位符
			for (int i = 0 ; i < args.length ; i++) {
				ps.setObject(i + 1, args[i]);
			}
			
			// 執行Sql語句，取得resultset對象
			rs = ps.executeQuery();
			
			// 獲取結果集的元數據
			ResultSetMetaData rsmd = ps.getMetaData();
			
			int columnCount = rsmd.getColumnCount();
			
			if(rs.next()) {
				
				t = clazz.newInstance();
				
				for (int i = 0 ; i < columnCount ; i++) {
					
					// 获取数据表名
					String columnLabel = rsmd.getColumnLabel(i + 1);
					
					// 获取数据表值
					Object columnVal = rs.getObject(i + 1);
					
					Field field = clazz.getDeclaredField(columnLabel);
					
					field.setAccessible(true);
					
					field.set(t,columnVal);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				JDBCUtils.closeResource(conn, ps, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return t;
	}
	
	/**
	 * 獲取一組數據對象 (原始方式)
	 * @param <T> 
	 * @param clazz
	 * @param sql
	 * @param args
	 * @return
	 */
	private <T> List<T> getList(Class<T> clazz, String sql, Object...args) {
		
		// 聲明數據連接
		T t = null;
		List<T> tlist = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			// 獲取數據庫連接
			conn = JDBCUtils.getConnection();
			
			// 預編譯Sql語句，獲取PreparedStatement對象
			pstmt = conn.prepareStatement(sql);
			
			// 填充佔位符
			for (int i = 0 ; i < args.length ; i++) {
				pstmt.setObject(i + 1, args[i]);
			}
			
			// 執行Sql語句，獲取ResultSet對象
			rs = pstmt.executeQuery();
			
			// 獲取元數據
			ResultSetMetaData rsmd = pstmt.getMetaData();
			
			// 獲取元數據列數
			int columnCount = rsmd.getColumnCount();
			
			// 將元數據匹配到對象中
			while(rs.next()) {
				
				// 創建對象實例
				t = clazz.newInstance();
				
				// 元數據中的一組數據匹配到對象實例中
				for (int i = 0; i < columnCount; i++) {
					
					// 獲取數據表的表名
					String columnLabel = rsmd.getColumnLabel(i + 1);
					
					// 獲取對應數據
					Object columnVal = rs.getObject(i + 1);
					
					// 數據匹配
					Field field = clazz.getDeclaredField(columnLabel);
					field.setAccessible(true);
					field.set(t, columnVal);
					
				}
				// 將對象添加到List中
				tlist.add(t);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtils.closeResource(conn, pstmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tlist;
	}
	
	/**
	 * 獲取一組數據對象 (原始方式)
	 * @param <T> 
	 * @param clazz
	 * @param sql
	 * @param args
	 * @return
	 */
	/*
	 * private <T> List<T> getList2(Class<T> clazz, String sql, Object...args) {
	 * 
	 * // 聲明數據連接 T t = null; List<T> tlist = new ArrayList<>(); Connection conn =
	 * null; PreparedStatement pstmt = null; ResultSet rs = null;
	 * 
	 * try {
	 * 
	 * // 獲取數據庫連接 conn = JDBCUtils.getConnection();
	 * 
	 * // 預編譯Sql語句，獲取PreparedStatement對象 pstmt = conn.prepareStatement(sql);
	 * 
	 * // 填充佔位符 for (int i = 0 ; i < args.length ; i++) { pstmt.setObject(i + 1,
	 * args[i]); }
	 * 
	 * // 執行Sql語句，獲取ResultSet對象 rs = pstmt.executeQuery();
	 * 
	 * // 獲取元數據 ResultSetMetaData rsmd = pstmt.getMetaData();
	 * 
	 * // 獲取元數據列數 int columnCount = rsmd.getColumnCount();
	 * 
	 * // 設置一個Map Map<String, Object> tempMap = null; List<Map<String, Object>>
	 * mapList = new ArrayList<>();
	 * 
	 * // 將元數據匹配到對象中 while(rs.next()) {
	 * 
	 * tempMap = new HashMap<>();
	 * 
	 * // 元數據中的一組數據匹配到對象實例中 for (int i = 0; i < columnCount; i++) {
	 * 
	 * // 獲取數據表的表名 String columnLabel = rsmd.getColumnLabel(i + 1);
	 * 
	 * // 獲取對應數據 Object columnVal = rs.getObject(i + 1);
	 * 
	 * tempMap.put(columnLabel, columnVal); } mapList.add(tempMap); }
	 * 
	 * // 對MapList進行遍歷，將數據放入對象中 for (Map<String, Object> map : mapList) {
	 * 
	 * t = clazz.newInstance();
	 * 
	 * for (Map.Entry<String, Object> entry : map.entrySet()) { String
	 * propertityName = entry.getKey(); Object propertityVal = entry.getValue();
	 * //ReflectionUtils.setFieldValue(t, propertyName, propertityVal); }
	 * 
	 * }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } finally { try {
	 * JDBCUtils.closeResource(conn, pstmt, rs); } catch (SQLException e) {
	 * e.printStackTrace(); } } return tlist; }
	 */
} 
