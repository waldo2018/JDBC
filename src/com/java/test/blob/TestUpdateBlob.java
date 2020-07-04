package com.java.test.blob;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.java.Utils.JDBCUtils;

public class TestUpdateBlob {
	
	/**
	 * 測試blob類型數據的修改操作
	 * 
	 */
	@Test
	public void update() {
		
		// 聲明連接
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 獲取數據庫連接
			conn = JDBCUtils.getConnection();
			
			String name = "汪峰";
			String sql = "update customers set photo = ? where name = ?";
			
			// 預編譯Sql，生成PreparedStatement對象
			pstmt = conn.prepareStatement(sql);
			
			// 填充佔位符
			FileInputStream fis = new FileInputStream("WebContent\\WEB-INF\\images\\IMG_0926.JPG");
			pstmt.setBlob(1, fis);
			pstmt.setString(2, name);
			
			// 執行操作
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtils.closeResource(conn, pstmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
	}
}
