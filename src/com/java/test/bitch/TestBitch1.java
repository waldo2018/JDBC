package com.java.test.bitch;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Test;

import com.java.Utils.JDBCUtils;

public class TestBitch1 {
	
	/**
	 * 測試多條數據插入(第一種方式：通過不斷的循環執行sql語句)
	 */
	@Test
	public void testInsert() throws Exception {
		
		// 創建數據庫連接
		Connection conn = JDBCUtils.getConnection();
		
		Statement st = conn.createStatement();
		
		for (int i = 0; i < 20000; i++) {
			String sql = "insert into goods(NAME) values("+ i +")";
			st.execute(sql);
		}
		
		JDBCUtils.closeResource(conn, null);
		
		if (st != null) {
			st.close();
		}
	}
}
