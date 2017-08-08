package org.yh.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/a.do")
public class MyServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	JDBCUtil jdbcUtil = null;
	Map<String, Object> reulst = null; //结果 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyServlet()
	{
		super();
		jdbcUtil = new JDBCUtil();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// 根据参数名获取参数值
		// Enumeration<String> em = request.getParameterNames();
		// while (em.hasMoreElements())
		// {
		// String name = (String) em.nextElement();
		// String value = request.getParameter(name);
		// System.out.println(value+"");
		// }
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		User user = jdbcUtil.getByName(name);
		reulst = new HashMap<String, Object>();
		if (null == name || "".equals(name))
		{
			reulst.put("reulst", 1);
			reulst.put("error", "用户名不能为空！");
		} else if (null == pass || "".equals(pass))
		{
			reulst.put("reulst", 2);
			reulst.put("error", "密码不能为空！");
		} else if (null == user)
		{
			reulst.put("reulst", 3);
			reulst.put("error", "用户名错误！");
		} else if (!jdbcUtil.md5(pass).equals(user.getPass()))
		{
			reulst.put("reulst", 4);
			reulst.put("error", "密码错误！");
		} else
		{
			reulst.put("reulst", 0);
			reulst.put("error", "登录成功");
		}
		// 解决中文乱码问题
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		//response.getWriter().append("Served at: ").append(request.getContextPath()).append("  " + result);
		//response.getWriter().println("1111");
		ResponseJsonUtils.json(response, reulst);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
