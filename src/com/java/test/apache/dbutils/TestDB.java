package com.java.test.apache.dbutils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.java.Utils.JDBCUtils;


public class TestDB {
	
	/**
	 * 使用Apache的DButils来实现增删改的操作
	 * @throws SQLException 
	 */
	@Test
	public void testInsert() throws SQLException {
		
		QueryRunner queryRunner = new QueryRunner();
		
		Connection conn = JDBCUtils.getConnection2();
		
		String user = "lisi";
		String password = "15468";
		String sql = "insert into user_table (user, password) values (?,?)";
		
		try {
			int count = queryRunner.update(conn, sql, user, password);
			
			System.out.println(count);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}
	
	/**
	 * 测试删除
	 * @throws SQLException 
	 */
	@Test
	public void testDelete() throws SQLException {
		
		String sql = "delete from user_table where user = ? and password = ?";
		
		Connection conn = JDBCUtils.getConnection2();
		
		QueryRunner queryRunner = new QueryRunner();
		
		int count = queryRunner.update(conn, sql, "DD", "abcder");
		
		System.out.println(count);
	}
	
	/**
	 * 測試修改操作
	 * @throws SQLException
	 */
	@Test
	public void testChange() throws SQLException {
		
		String sql = "update user_table set balance = ? where user = ? and password = ?";
		
		Connection conn = JDBCUtils.getConnection2();
		
		QueryRunner queryRunner = new QueryRunner();
		
		int count = queryRunner.update(conn, sql, "2000", "AA", "1245");
		
		System.out.println(count);
	}
	
	/**
	 * 測試select操作，返回一個User對象
	 * @throws SQLException 
	 */
	@Test
	public void testGet() throws SQLException {
		
		
		String sql = "select id, name, password,address from user where name = ? and password = ?";
		
		Connection conn = JDBCUtils.getConnection2();
		
		QueryRunner queryRunner = new QueryRunner();
		
		User user = queryRunner.query(conn, sql, new BeanHandler<User>(User.class), "章子怡", "qwerty");
		
		System.out.println(user);
	}
	
	/**
	 * 獲取多條數據
	 * @throws SQLException 
	 */
	@Test
	public void testGetList() throws SQLException {
		
		String sql = "select id, name, password,address from user where id between ? and ?";
		
		Connection conn = JDBCUtils.getConnection2();
		
		QueryRunner queryRunner = new QueryRunner();
		
		List<User> userList = queryRunner.query(conn, sql, new BeanListHandler<User>(User.class), 1, 3);
		
		System.out.println(userList);
	}
	
	/**
	 * 自定義一個Handler
	 * @throws SQLException 
	 */
	@Test
	public void testHandler() throws SQLException {
		
		String sql = "select id, name, password,address from user where id between ? and ?";
		
		Connection conn = JDBCUtils.getConnection2();
		
		QueryRunner queryRunner = new QueryRunner();
		
		ResultSetHandler<User> handler = new ResultSetHandler<User>() {

			@Override
			public User handle(ResultSet rs) throws SQLException {
				
				User user = null;
				
				if (rs.next()) {
					
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String password = rs.getString(3);
					String address = rs.getString(4);
					
					user = new User(id, name, password, address);
				}
				
				return user;
			}
			
		};
		
		User user = queryRunner.query(conn, sql, handler, 1, 1);
		
		System.out.println(user);
	}
	
	/**
	 * 獲取數據表中的一個值
	 * @throws SQLException 
	 */
	@Test
	public void getValue() throws SQLException {
		
		String sql = "select count(*) from user";
		
		Connection conn = JDBCUtils.getConnection2();
		
		QueryRunner queryRunner = new QueryRunner();
		
		Object count = queryRunner.query(conn, sql, new ScalarHandler<Integer>());
		
		System.out.println(count);
	}
}
