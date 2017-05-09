package com.parkinglot.dao.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.parkinglot.db.JdbcConn;
import com.parkinglot.entity.manager.ReservationEntity;

public class ReservationDao {

	public List<ReservationEntity> getAllReservationList() {
		List<ReservationEntity> reservationList = new ArrayList<ReservationEntity>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT reservation.reservation_Id AS reservationId, reservation.start_Time AS startTime, reservation.end_Time AS endTime, reservation.arrival_Time AS arrivalTime, reservation.depart_Time AS departTime, reservation.res_State AS reservationState, reservation.user_Id AS userId, reservation.car_Number AS carNumber FROM reservation";
			con = JdbcConn.getConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ReservationEntity reservation = new ReservationEntity();
				reservation.setArrivalTime(rs.getTimestamp("arrivalTime"));
				reservation.setDepartTime(rs.getTimestamp("departTime"));
				reservation.setStartTime(rs.getTimestamp("startTime"));
				reservation.setEndTime(rs.getTimestamp("endTime"));
				reservation.setCarNumber(rs.getString("carNumber"));
				reservation.setResState(rs.getInt("reservationState"));
				reservation.setReservationId(rs.getInt("reservationId"));
				reservation.setuserId(rs.getInt("userId"));
				reservationList.add(reservation);
			}
			return reservationList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// 释放语句对象
			if (psmt != null) {
				try {
					psmt.close();
					psmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public List<ReservationEntity> getReservationListByTime(Timestamp startTime, Timestamp endTime) {
		List<ReservationEntity> reservations = new ArrayList<ReservationEntity>();

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT reservation.reservation_Id AS reservationId, reservation.start_Time AS startTime, reservation.end_Time AS endTime, reservation.arrival_Time AS arrivalTime, reservation.depart_Time AS departTime, reservation.res_State AS reservationState, reservation.user_Id AS userId, reservation.car_Number AS carNumber"
					+ " FROM reservation" + " WHERE reservation.start_Time >= ? AND reservation.end_Time <= ?";
			con = JdbcConn.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setTimestamp(1, startTime);
			psmt.setTimestamp(2, endTime);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ReservationEntity reservation = new ReservationEntity();
				reservation.setArrivalTime(rs.getTimestamp("arrivalTime"));
				reservation.setDepartTime(rs.getTimestamp("departTime"));
				reservation.setStartTime(rs.getTimestamp("startTime"));
				reservation.setEndTime(rs.getTimestamp("endTime"));
				reservation.setCarNumber(rs.getString("carNumber"));
				reservation.setResState(rs.getInt("reservationState"));
				reservation.setReservationId(rs.getInt("reservationId"));
				reservation.setuserId(rs.getInt("userId"));
				reservations.add(reservation);
			}
			return reservations;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// 释放语句对象
			if (psmt != null) {
				try {
					psmt.close();
					psmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public int[] countReservationByTime(Timestamp startTime, Timestamp endTime) {
		int divisor = 1800000; // convert 1800000 milliseconds to 30 minutes
		int timeNodeNum = (int) ((endTime.getTime() - startTime.getTime()) / divisor + 1);
		int[] countReservation = new int[timeNodeNum];

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		for (int i = 0; i < timeNodeNum; i++) {
			try {
				String sql = "SELECT COUNT(*) AS number " + "FROM `reservation` "
						+ "WHERE reservation.start_Time <= ? AND reservation.end_Time >= ?";
				con = JdbcConn.getConnection();
				psmt = con.prepareStatement(sql);
				Timestamp timeNode = new Timestamp(startTime.getTime() + i*divisor);
				psmt.setTimestamp(1, timeNode);
				psmt.setTimestamp(2, timeNode);
				rs = psmt.executeQuery();
				while (rs.next()) {
					countReservation[i] = rs.getInt("number");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				// 释放数据集对象
				if (rs != null) {
					try {
						rs.close();
						rs = null;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				// 释放语句对象
				if (psmt != null) {
					try {
						psmt.close();
						psmt = null;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
		return countReservation;
	}
}
