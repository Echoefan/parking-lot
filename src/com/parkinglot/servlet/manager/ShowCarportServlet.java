package com.parkinglot.servlet.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parkinglot.dao.manager.CarportDao;

public class ShowCarportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CarportDao carportDao = new CarportDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		resp.setContentType("text/html;charset=utf-8");
		
		int[] carportStatus = null;
		String floorNo = req.getParameter("floorNo");
		
		if (floorNo == null)
			carportStatus = carportDao.getCurCarportStatus(1);
		else { 
			carportStatus = carportDao.getCurCarportStatus(Integer.parseInt(floorNo));
		}
		
		if(carportStatus != null) {
			System.out.println(carportStatus.toString());
		}
		else {
			System.out.println("null");
		}
		
		req.getSession().setAttribute("carportStatus", carportStatus);
		
		resp.sendRedirect("../index.jsp");
	}

}
