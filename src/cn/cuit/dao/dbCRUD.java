package cn.cuit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

import cn.cuit.utils.dbConnUtil;

public class dbCRUD {
	private static Connection conn=null;
	private static Statement stmt=null;
	private static ResultSet rs=null;
	
	public static boolean connDb() {
		dbConnUtil.getConnection();
		conn=(Connection) dbConnUtil.getConn();
		if(conn!=null)
			return true;
		else 
			return false;
	}
	public static boolean create(String sql) {
		try {
			stmt=conn.createStatement();
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			System.out.println("create failed");
		}
		return false;
	}
	
	public static ResultSet retrieve(String sql) {
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			System.out.println(sql);
			return rs;
		} catch (SQLException e) {
			System.out.println("retrieve failed");
		}
		return null;
	}
	
	public static int update(String sql) {
		int i;
		try {
			stmt=conn.createStatement();
			i=stmt.executeUpdate(sql);
			return i;
		} catch (SQLException e) {
			System.out.println("update failed");
		}
		return 0;
	}
	
	public static boolean delete(String sql) {
		boolean flag=false;
		try {
			stmt=conn.createStatement();
			flag=stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println("delete failed");
		}
		return flag;
	}
	
	public static void disconnection() {
		try {
			if(rs!=null)
				rs.close();
		} catch (SQLException e) {
			System.out.println("rs close failed ");
		}
		try {
			if(stmt!=null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("stmt close failed ");
		}
		try {
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			System.out.println("conn close failed ");
		}
	}
}
