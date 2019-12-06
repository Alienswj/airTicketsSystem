package cn.cuit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class dbConnUtil {
	private final static String username="root";
	private final static String password="1356";
	private final static String url="jdbc:mysql://localhost:3306/air_tickets_system";
	
	private static Connection conn=null;

	public static void getConnection()
	{
		try {
			System.out.println("加载驱动ing...");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("正在连接数据库...");
			conn=DriverManager.getConnection(url, username, password);
			System.out.println("连接成功！");
		} catch (ClassNotFoundException e) {
			System.out.println("加载驱动失败！");
		} catch (SQLException e) {
			System.out.println("连接失败！");
		}
	}
	
	public static Connection getConn()
	{
		return conn;
	}
}
