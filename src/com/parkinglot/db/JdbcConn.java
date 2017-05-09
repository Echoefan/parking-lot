package com.parkinglot.db;
/*
 * Jdbc������-->��ȡ���ݿ����Ӷ���Connection��
 * user��password����ʵ����������е�����
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConn {
	private final static String driver = "com.mysql.jdbc.Driver";
	//���ݿ�����
	private final static String url = "jdbc:mysql://localhost:3306/report?useUnicode=true&characterEncoding=utf-8";
	//�������ݿ��URL��ַ
	private final static String user = "root";
	//���ݿ��û���
	private final static String password = "111111";
	//���ݿ�����
	
	private static Connection con = null;
	
	/*
	 * ��̬������������
	 */
	static
	{
		try
		{
			Class.forName(driver);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * ��ȡ���ݿ����Ӷ���
	 */
	public static Connection getConnection() throws Exception
	{
		if(con==null)
		{
			con = DriverManager.getConnection(url, user, password);
			return con;
		}
		return con;
	}
	
	/*
	 * ������->�������ݿ�����Ƿ��ȡ�ɹ�
	 * ���!=null
	 * ��ʾ��ȡ������
	 */
	public static void main(String[] args) {
		try
		{
		   Connection conn = JdbcConn.getConnection();
		   if(conn!=null)
		   {
			   System.out.println("���ݿ�����������");
		   }
		   else
		   {
			   System.out.println("���ݿ������쳣��");
		   }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
}
