package com.java.test.bitch;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.java.Utils.JDBCUtils;

public class TestBitch4 {
	
	/**
	 * 實現批處理的第三種方式：使用addBitch（），executeBitch（），clearBitch（）來執行。
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testBitch3() throws ClassNotFoundException, IOException, SQLException {
		
		String sql = "insert into goods (name) values (?)";
		
		long start = System.currentTimeMillis();
		
		// 創建數據連接
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		
		// 设定不自动提交
		conn.setAutoCommit(false);
		
		for (int i=0; i< 20000; i++) {
			ps.setString(1, "name_" + i);
		
		// 積攢sql執行語句
		ps.addBatch();
		
		// 執行
		if (i % 500 == 0) {
			ps.executeBatch();
			ps.clearBatch();
		}
		
		// 提交数据
		conn.commit();
			
	  }
		JDBCUtils.closeResource(conn, ps);
		
		long end = System.currentTimeMillis();
		
		System.out.println(end-start);
	}
}
