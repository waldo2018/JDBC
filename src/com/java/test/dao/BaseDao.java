package com.java.test.dao;

import org.apache.commons.dbutils.QueryRunner;

public abstract class BaseDao<T> {
	
	private QueryRunner queryRunner = new QueryRunner();
	
	// 定义一个变量来接受泛型的类型
	private Class<T> type;
 
}
