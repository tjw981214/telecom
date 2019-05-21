package com.telecom.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3P0的数据库连接池
 * @author 唐佳威
 *
 */
public class DButil {
	private static ComboPooledDataSource cpds = null;
	
	static {
		cpds = new ComboPooledDataSource("mysql");
	}
	/**
	 * 通过数据库连接池获得数据库连接的方法
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return cpds.getConnection();
	}
	/**
	 * 释放当前连接
	 * @param conn 数据库连接
	 */
	public static void free(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	/**
	 * 释放数据库连接
	 * @param rs ResultSet结果集
	 * @param pstm PreparedStatement 预编译句柄
	 * @param conn Connection 数据库连接
	 */
	public static void free(ResultSet rs,PreparedStatement pstm,Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
