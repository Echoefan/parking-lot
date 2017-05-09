package com.parkinglot.dao;
/*
 * daoʵ���ִ࣬�����ݿ����
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.parkinglot.db.JdbcConn;
import com.parkinglot.entity.Infor;

public class Dao{
	/*
	 *   	getInforById()����
	 * 		����Id(goods-->id/��Ʒ���)==>
	 * 		ͨ��ִ��sql���
	 * 		��ȡInfor(�����е�һ��)==>
	 * 			���磺getInforById(1)�õ�����
	 * 				��Ʒ����	��Ʒ����	��������	���ױ���	����
	 * 				ŵ����5230	66			3				2				198
	 * 		����ŵ����5230����Ʒ���=1
	 */
	public Infor getInforById(int Id)
	{
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select g.name as name,(g.sell_price-g.cost_price)as  profit, sum(t.number) as sellnumber,count(t.id) as tscnumber,((g.sell_price-g.cost_price)*sum(t.number)) as income " +
					"from goods g,transaction t where g.id = t.goods_id and g.id=?;";
			/*��Ʒ����(g.name)	name��
			 * ��Ʒ����(g.sell_price-g.cost_price	-->	��Ʒ�ۼ�-�ɱ���		)	sellnumber
			 * ��������(sum(t.number)		-->	��ѯĳһgoods_id�·��ص���������֮�ͣ����ܴ��ڶ�ι���ͬһ����Ʒ�ļ�¼	)	sellnumber
			 * ���ױ���(count(t.id)	-->	��ѯĳһgoods_id�·��ص���Ϣ����	)	tscnumber
			 * ����((g.sell_price-g.cost_price)*sum(t.number)	-->	��Ʒ����*��������)		income
			 */
			con = JdbcConn.getConnection();
			//��ȡ���ݿ����Ӷ���
			psmt  = con.prepareStatement(sql);
			//��ȡPreparedStatement����
			psmt.setInt(1,Id);
			/*
			 * sql�����g.id=?
			 * ����ʾ����-->���ò���(1,Id)-->
			 * 1��ʾ��һ��?	Id��ʾ����ı���Id
			 */
			rs = psmt.executeQuery();
			//ִ�в�ѯ�����ؽ����ResultSet����
			if(rs.next())
			{
				Infor infor = new Infor();
				infor.setName(rs.getString("name"));
				infor.setProfit(rs.getInt("profit"));
				infor.setSellNumber(rs.getInt("sellNumber"));
				infor.setTscNumber(rs.getInt("tscNumber"));
				infor.setIncome(rs.getInt("income"));
				return infor;
				//����sql������õı�����ȡ���صĽ����set��infor�����С�����infor����
			}
			else
			{
				return null;
			}
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
	
	/*
	 * 		getIds()����
	 * 		ͨ��sql���ֱ�ӻ�ȡtransaction��(������Ϣ��)�е�goods_id
	 * 		���Ҹ���goods_id���飺Group By goods_id
	 * 		����ȡ����goods_id��ŵ�������
	 * 		��󷵻����顢�ͷ���Դ
	 */
	public List<Integer>  getIds()
	{
		List<Integer>idslist = new ArrayList<Integer>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select goods_id from transaction group by goods_id";
			con = JdbcConn.getConnection();
			psmt  = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next())
			{
					int id = rs.getInt("goods_id");
					idslist.add(id);
					//����洢��idslist������
			}
			return idslist;
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
	
	public static void main(String[] args) {
		Dao dao = new Dao();
		Infor infor =  dao.getInforById(6);
		if(infor!=null)
		{
			System.out.println("getInfor-�ɹ���");
		}
		else
		{
			System.out.println("getInfor-ʧ�ܣ�");
		}
		
		List<Integer> idslist = dao.getIds();
		if(idslist!=null&&idslist.size()>0)
		{
			System.out.println("getIds-�ɹ���");
		}
		else
		{
			System.out.println("getIds-ʧ�ܣ�");
		}
	}
}
