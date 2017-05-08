package org.yh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/A.do")
public class MyServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyServlet()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//根据参数名获取参数值
//		Enumeration<String> em = request.getParameterNames();
//		while (em.hasMoreElements())
//		{
//			String name = (String) em.nextElement();
//			String value = request.getParameter(name);
//			System.out.println(value+"");
//		}
		String result = "";
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		if (null == name)
		{
			result = "用户名不能为空！";
		}else if(null == pass)
		{
			result = "密码不能为空！";
		}else if(!name.equals("youhao"))
		{
			result = "用户名错误！";
		}else if(!pass.equals("123456"))
		{
			result = "密码错误！";
		}else
		{
			result = "登录成功";
		}
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath()).append("  "+result);
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
