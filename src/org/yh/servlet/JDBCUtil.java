package org.yh.servlet;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class JDBCUtil
{
	
	private static Connection getConn()
	{
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/youhao";
		String username = "root"; 
		String password = "123456";
		Connection conn = null;
		try
		{
			Class.forName(driver); // classLoader,加载对应驱动
			conn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}

	public static int insert(User user)
	{
		Connection conn = getConn();
		int i = 0;
		String sql = "insert into user (Name,Age) values(?,?)";
		PreparedStatement pstmt;
		try
		{
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setInt(2, user.getAge());
			i = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return i;
	}

	public static int update(User user)
	{
		Connection conn = getConn();
		int i = 0;
		String sql = "update user set Age='" + user.getAge() + "' where Name='" + user.getName() + "'";
		PreparedStatement pstmt;
		try
		{
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			i = pstmt.executeUpdate();
			System.out.println("resutl: " + i);
			pstmt.close();
			conn.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return i;
	}

	public static Integer getAll()
	{
		Connection conn = getConn();
		String sql = "select * from user";
		PreparedStatement pstmt;
		try
		{
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int col = rs.getMetaData().getColumnCount();
			System.out.println("============================");
			while (rs.next())
			{
				for (int i = 1; i <= col; i++)
				{
					System.out.print(rs.getString(i) + "\t");
					if ((i == 2) && (rs.getString(i).length() < 8))
					{
						System.out.print("\t");
					}
				}
				System.out.println("");
			}
			System.out.println("============================");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static int delete(String name)
	{
		Connection conn = getConn();
		int i = 0;
		String sql = "delete from user where Name='" + name + "'";
		PreparedStatement pstmt;
		try
		{
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			i = pstmt.executeUpdate();
			System.out.println("resutl: " + i);
			pstmt.close();
			conn.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return i;
	}
}
