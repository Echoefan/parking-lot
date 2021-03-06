package com.parkinglot.servlet.manager;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parkinglot.dao.manager.ReservationDao;

public class CourtResvServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ReservationDao reservationDao = new ReservationDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		resp.setContentType("text/html;charset=utf-8");
		
		String startTime = req.getParameter("startTime");
		String endTime = req.getParameter("endTime");
		
		int[] countReservation = null;
		
		if (startTime == null || endTime == null)
			;  // should never happen
		else { 
			Timestamp startTimestamp = Timestamp.valueOf(startTime);
			Timestamp endTimestamp = Timestamp.valueOf(endTime);
			countReservation = reservationDao.countReservationByTime(startTimestamp, endTimestamp);
		}
		
		if(countReservation != null) {
			System.out.println(countReservation.toString());
		}
		else {
			System.out.println("null");
		}
		
		req.getSession().setAttribute("countReservation", countReservation);
		
		resp.sendRedirect("../index.jsp");
	}
}
