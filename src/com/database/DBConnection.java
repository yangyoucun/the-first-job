package com.database;
 
import java.sql.*;
 
 
/**
 * 数据库连接类
 * @author Administrator
 *
 */
public class DBConnection {
	private static Connection con = null;
	private static String url = "jdbc:mysql://localhost:3306/student?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	private static String user = "root";
	private static String password = "mysql";
 
	public static Connection getCon() {
		//加载数据库驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("加载数据库驱动失败！");
			e.printStackTrace();
		}
		
		//获取数据库连接
		try {
			con = DriverManager.getConnection(url, user, password);
			if(con !=null) {
				System.out.println("数据库连接成功！");
			}
		} catch (SQLException e) {
			System.out.println("数据库连接失败！");
			e.printStackTrace();
		}
		//返回Connection对象
		return con;
	}
		
	
}
