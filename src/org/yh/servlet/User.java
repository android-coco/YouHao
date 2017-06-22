package org.yh.servlet;

@Table("tb_user")
public class User
{
	private int id;
	private String name;
	private int age;

	User(String name, int age)
	{
		this.id = 0; // default
		this.name = name;
		this.age = age;
	}

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

	@Override
	public String toString()
	{
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
