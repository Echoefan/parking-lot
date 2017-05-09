package com.parkinglot.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parkinglot.dao.Dao;
import com.parkinglot.entity.Infor;

public class ReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Dao dao = new Dao();
	/**
	 * Constructor of the object.
	 */
	public ReportServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 * 
	 * <pre>
	 * 遍历获取到的idslist数组
	 * 将id传入getInforById()方法
	 * 再将遍历得到的Infor对象存储到inforlist数组中
	 * 传入到Session中、便于视图层获取
	 * 重定向到index.jsp页面
	 * </pre>
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		List<Integer> idslist = dao.getIds();
		ArrayList<Infor> inforlist = new ArrayList<Infor>();
		if(idslist!=null&&idslist.size()>0)
		{
			System.out.println("idslist-size："+idslist.size());
			for (int i = 1; i <= idslist.size(); i++)
			{
				Infor infor = new Infor();
				infor = dao.getInforById(i);
				inforlist.add(infor);
			}
		}
		else
		{
			System.out.println("null");
		}
		request.getSession().setAttribute("inforlist", inforlist);
		response.sendRedirect("../index.jsp");
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
