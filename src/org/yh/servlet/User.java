package org.yh.servlet;

@Table("tb_user")
public class User
{
	private int id;
	private String name;
	private int age;
	private String pass;

	
	public User(){};

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}
	public String getPass()
	{
		return pass;
	}
	public void setPass(String pass)
	{
		this.pass = pass;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", pass=" + pass + "]";
	}

}
