package com.java.test.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.java.Utils.JDBCUtils;

public class TestPrepareStatement {
	
	/**
	 * 用来测试对数据库的更新操作
	 */
	@Test
	public void testUpdate() {
			
		String user = "CC";
		String password = "1234";
		
		String sql = "update user_table set password=? where user=?";
		
		update(sql,password,user);
		
	}
	/**
	 * 用来测试对数据库的插入操作
	 */
	@Test
	public void testInsert() {
		
		String user = "whc";
		String password = "128680";
		String balance = "5000";
		
		String sql = "INSERT INTO user_table (user,password,balance) VALUES(?,?,?)";
		
		update(sql,user,password,balance);
		
	}
	/**
	 * 用来测试数据库的删除操作
	 */
	@Test
	public void testDel() {
		
		String user = "whc";
		String password = "128680";
		
		String sql = "delete from user_table where user=? and password=?";
		
		update(sql,user,password);
	}
	/**
	 * 通用的增，刪，改操作
	 * @param sql 帶有佔位符的執行語句
	 * @param args 參數
	 */
	public void update(String sql , Object... args) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
			// 獲取數據庫連接
			conn = JDBCUtils.getConnection();
			
			// 獲取PreparedStatement的實例
			ps = conn.prepareStatement(sql);
			
			// 填充佔位符
			for(int i=0;i<args.length;i++) {
				ps.setObject(i + 1, args[i]);
			}
			
			// 執行sql語句
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				JDBCUtils.closeResource(conn, ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
