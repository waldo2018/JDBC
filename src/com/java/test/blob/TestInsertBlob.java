package com.java.test.blob;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import org.junit.Test;

import com.java.Utils.JDBCUtils;

public class TestInsertBlob {
	
	/**
	 * 向數據表中插入blob類型文件
	 * Blob簡介
	 * 		在MySQL中，blob是一個二進制的大型對象，是一個可以存儲大量數據的容器，它能夠容納不同的大小
	 * 		插入Blob類型對象，必須使用PreparedStatement對象，因為Blob類型的數據是無法使用字符串拼接的。
	 * 		MySQL中有四種不同的Blob對象，分別是：TinyBlob（255字節），Blob（65K），MediumBlob（16M），LongBlob（4g）
	 */
	@Test
	public void testInsertBlob() {
		
		// 聲明對象
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// 設置插入語句
		String sql = "insert into customers (name, email, birth, photo) values (?,?,?,?)";
		
		try {
			
			// 創建數據連接
			conn = JDBCUtils.getConnection();
			
			// Sql語句預編譯，獲取PreparedStatement對象
			pstmt = conn.prepareStatement(sql);
			
			// 填充佔位符
			pstmt.setString(1, "zhangsan");
			pstmt.setString(2, "zhangsan@gmial.com");
			pstmt.setDate(3, new Date(new java.util.Date().getTime()));
			
			// 操作Blob類型的變量
			FileInputStream fs = new FileInputStream("WebContent\\WEB-INF\\images");
			pstmt.setBlob(4, fs);
			
			// 執行操作
			pstmt.execute();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtils.closeResource(conn, pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
