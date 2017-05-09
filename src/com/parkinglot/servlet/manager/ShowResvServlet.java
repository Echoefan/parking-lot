package com.parkinglot.servlet.manager;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parkinglot.dao.manager.ReservationDao;
import com.parkinglot.entity.manager.ReservationEntity;

public class ShowResvServlet extends HttpServlet {

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
		
		List<ReservationEntity> reservationList = null;
		
		if (startTime == null || endTime == null)
			reservationList = reservationDao.getAllReservationList();
		else { 
			Timestamp startTimestamp = Timestamp.valueOf(startTime);
			Timestamp endTimestamp = Timestamp.valueOf(endTime);
			reservationList = reservationDao.getReservationListByTime(startTimestamp, endTimestamp);
		}
		
		if(reservationList != null && reservationList.size() > 0) {
			System.out.println("reservationList-size: " + reservationList.size());
		}
		else {
			System.out.println("null");
		}
		
		req.getSession().setAttribute("reservationList", reservationList);
		
		resp.sendRedirect("../index.jsp");
	}

}
