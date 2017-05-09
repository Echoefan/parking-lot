package com.parkinglot.dao;
/*
 * dao实现类，执行数据库操作
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
	 *   	getInforById()方法
	 * 		传递Id(goods-->id/商品编号)==>
	 * 		通过执行sql语句
	 * 		获取Infor(报表中的一行)==>
	 * 			例如：getInforById(1)得到如下
	 * 				商品名称	商品利润	销售数量	交易笔数	收入
	 * 				诺基亚5230	66			3				2				198
	 * 		其中诺基亚5230的商品编号=1
	 */
	public Infor getInforById(int Id)
	{
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select g.name as name,(g.sell_price-g.cost_price)as  profit, sum(t.number) as sellnumber,count(t.id) as tscnumber,((g.sell_price-g.cost_price)*sum(t.number)) as income " +
					"from goods g,transaction t where g.id = t.goods_id and g.id=?;";
			/*商品名称(g.name)	name、
			 * 商品利润(g.sell_price-g.cost_price	-->	商品售价-成本价		)	sellnumber
			 * 销售数量(sum(t.number)		-->	查询某一goods_id下返回的销售数量之和，可能存在多次购买同一款商品的记录	)	sellnumber
			 * 交易笔数(count(t.id)	-->	查询某一goods_id下返回的信息行数	)	tscnumber
			 * 收入((g.sell_price-g.cost_price)*sum(t.number)	-->	商品利润*销售数量)		income
			 */
			con = JdbcConn.getConnection();
			//获取数据库连接对象
			psmt  = con.prepareStatement(sql);
			//获取PreparedStatement对象
			psmt.setInt(1,Id);
			/*
			 * sql语句中g.id=?
			 * ？表示参数-->设置参数(1,Id)-->
			 * 1表示第一个?	Id表示传入的变量Id
			 */
			rs = psmt.executeQuery();
			//执行查询并返回结果集ResultSet对象
			if(rs.next())
			{
				Infor infor = new Infor();
				infor.setName(rs.getString("name"));
				infor.setProfit(rs.getInt("profit"));
				infor.setSellNumber(rs.getInt("sellNumber"));
				infor.setTscNumber(rs.getInt("tscNumber"));
				infor.setIncome(rs.getInt("income"));
				return infor;
				//根据sql语句设置的别名获取返回的结果、set到infor对象中、返回infor对象
			}
			else
			{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
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
	
	/*
	 * 		getIds()方法
	 * 		通过sql语句直接获取transaction表(交易信息表)中的goods_id
	 * 		并且根据goods_id分组：Group By goods_id
	 * 		将获取到的goods_id存放到数组中
	 * 		最后返回数组、释放资源
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
					//结果存储到idslist数组中
			}
			return idslist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
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
	
	public static void main(String[] args) {
		Dao dao = new Dao();
		Infor infor =  dao.getInforById(6);
		if(infor!=null)
		{
			System.out.println("getInfor-成功！");
		}
		else
		{
			System.out.println("getInfor-失败！");
		}
		
		List<Integer> idslist = dao.getIds();
		if(idslist!=null&&idslist.size()>0)
		{
			System.out.println("getIds-成功！");
		}
		else
		{
			System.out.println("getIds-失败！");
		}
	}
}
