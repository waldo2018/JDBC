package com.java.test.blob;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.junit.Test;

import com.java.Utils.JDBCUtils;

public class TestSelectBlob {
	
	/**
	 * 測試從數據庫中讀取Blob類型的數據
	 */
	@Test
	public void get() {
		
		String name = "朱茵";
		String email = "zhuyin@126.com";
		String sql = "select id, name, email, birth, photo from customers where name = ? and email = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			// 獲取數據連接
			conn = JDBCUtils.getConnection();
			
			// 預編譯Sql文，獲得PreparedStatement對象
			pstmt = conn.prepareStatement(sql);
			
			// 填充佔位符
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			
			// 執行操作，獲得ResultSet對象
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				int id = rs.getInt(1);
				name = rs.getString(2);
				email = rs.getString(3);
				Date birth = rs.getDate(4);
				
				Customer cust = new Customer(id, name, email, birth);
				
				System.out.println(cust.toString());
				
				// 讀取Blob數據
				Blob blob = rs.getBlob(5);
				
				// 獲取blob的輸出流
				InputStream ips = blob.getBinaryStream();
				OutputStream ops = new FileOutputStream("WebContent\\WEB-INF\\images\\IMG_1000.JPG");
				
				byte[] buffer =  new byte[1024];
				
				int len = 0;
				
				while ((len=ips.read(buffer)) != -1) {
					ops.write(buffer, 0, len);
				}
				if (ips != null) {
					ips.close();
				}
				if (ops != null) {
					ops.close();
				}
			}
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
