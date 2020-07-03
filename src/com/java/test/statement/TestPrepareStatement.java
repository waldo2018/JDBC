package com.java.test.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.java.Utils.JDBCUtils;

public class TestPrepareStatement {
	
	@Test
	public void testPreparedStatement() {
			
		String name = "CC";
		String password = "1234";
		
		String sql = "update user_table set password=? where user=?";
		
		update(sql,password,name);
		
	}
	/**
	 * 通用的增刪改操作
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
