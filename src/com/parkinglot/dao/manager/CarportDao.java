package com.parkinglot.dao.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.parkinglot.db.JdbcConn;

public class CarportDao {
	
	public int[]  getCurCarportStatus(int floorNo)
	{
		int[] carportStatus = new int[100];
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT carport.occupation AS occupation "
					+ "FROM `carport` "
					+ "WHERE carport.carport_Id >= ? AND carport.carport_Id <= ? "
					+ "ORDER BY carport.carport_Id ASC";
			con = JdbcConn.getConnection();
			psmt  = con.prepareStatement(sql);
			psmt.setInt(1, (floorNo-1)*100+1);
			psmt.setInt(2, floorNo*100);
			rs = psmt.executeQuery();
			int i = 0;
			while(rs.next()) {
				carportStatus[i] = rs.getInt("occupation");
				i++;
			}
			return carportStatus;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			// �ͷ����ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
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
}
