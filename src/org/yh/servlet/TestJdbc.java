package org.yh.servlet;

public class TestJdbc
{
	public static void main(String[] args)
	{
		//JDBCUtil.insert(new User("Achilles", 14));
		//JDBCUtil.insert(new User("Bean", 14));
		//ArrayList<User> x = JDBCUtil.getAll();
		JDBCUtil jdbcUtil = new JDBCUtil();
		User x1 = jdbcUtil.getByName("");
		System.out.println(x1);
		//JDBCUtil.update(new User("Bean",7));
		//JDBCUtil.delete("Achilles");
		//JDBCUtil.getAll();
	}
	
	
}
