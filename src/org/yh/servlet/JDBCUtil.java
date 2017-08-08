package org.yh.servlet;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class JDBCUtil
{
	private static final String DRIVENAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/youhao";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";

	private Connection conn = null;
	static
	{
		try
		{
			Class.forName(DRIVENAME); // classLoader,加载对应驱动
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public Connection getConn()
	{
		Connection conn = null;
		try
		{
			conn = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e)
		{
			System.out.println("数据库连接失败：" + e.getMessage());
		}
		return conn;
	}

	public int insert(User user)
	{
		conn = getConn();
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

	public int update(User user)
	{
		conn = getConn();
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

	public User getByName(String name)
	{
		conn = getConn();
		String sql = "select * from user where name= '" + name + "'";
		PreparedStatement pstmt;
		User mUser = null;
		try
		{
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData data = rs.getMetaData();
			int col = rs.getMetaData().getColumnCount();
			//System.out.println("============================");
			while (rs.next())
			{
				mUser = new User();
				for (int i = 1; i <= col; i++)
				{
					//System.out.print(rs.getString(i) + "\t");
					if ((i == 2) && (rs.getString(i).length() < 8))
					{
						//System.out.print("\t");
					}
					if ("id".equals(data.getColumnName(i)))
					{
						mUser.setId(rs.getInt(i));
					}
					if ("name".equals(data.getColumnName(i)))
					{
						mUser.setName(rs.getString(i));
					}
					if ("age".equals(data.getColumnName(i)))
					{
						mUser.setAge(rs.getInt(i));
					}
					if ("pass".equals(data.getColumnName(i)))
					{
						mUser.setPass(rs.getString(i));
					}
				}
				//System.out.println("");
			}
			//System.out.println("============================");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return mUser;
	}

	public ArrayList<User> getAll()
	{
		conn = getConn();
		String sql = "select * from user";
		PreparedStatement pstmt;
		ArrayList<User> mUsers = null;
		try
		{
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData data = rs.getMetaData();
			int col = rs.getMetaData().getColumnCount();
			System.out.println("============================");
			User mUser = null;
			mUsers = new ArrayList<>();
			while (rs.next())
			{
				mUser = new User();
				for (int i = 1; i <= col; i++)
				{
					System.out.print(rs.getString(i) + "\t");
					if ((i == 2) && (rs.getString(i).length() < 8))
					{
						System.out.print("\t");
					}
					if ("id".equals(data.getColumnName(i)))
					{
						mUser.setId(rs.getInt(i));
					}
					if ("name".equals(data.getColumnName(i)))
					{
						mUser.setName(rs.getString(i));
					}
					if ("age".equals(data.getColumnName(i)))
					{
						mUser.setAge(rs.getInt(i));
					}
					if ("pass".equals(data.getColumnName(i)))
					{
						mUser.setPass(rs.getString(i));
					}
				}
				mUsers.add(mUser);
				System.out.println("");
			}
			System.out.println("============================");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return mUsers;
	}

	public int delete(String name)
	{
		conn = getConn();
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

	/**
	 * MD5加密
	 */
	public String md5(String string)
	{
		byte[] hash;
		try
		{
			hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash)
		{
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}
}
