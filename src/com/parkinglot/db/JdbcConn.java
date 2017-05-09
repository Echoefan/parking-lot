package com.parkinglot.db;
/*
 * Jdbc工具类-->获取数据库连接对象（Connection）
 * user和password根据实际情况，自行调整。
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConn {
	private final static String driver = "com.mysql.jdbc.Driver";
	//数据库驱动
	private final static String url = "jdbc:mysql://localhost:3306/report?useUnicode=true&characterEncoding=utf-8";
	//连接数据库的URL地址
	private final static String user = "root";
	//数据库用户名
	private final static String password = "111111";
	//数据库密码
	
	private static Connection con = null;
	
	/*
	 * 静态代码块加载驱动
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
	 * 获取数据库连接对象
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
	 * 主函数->测试数据库对象是否获取成功
	 * 如果!=null
	 * 表示获取到对象
	 */
	public static void main(String[] args) {
		try
		{
		   Connection conn = JdbcConn.getConnection();
		   if(conn!=null)
		   {
			   System.out.println("数据库连接正常！");
		   }
		   else
		   {
			   System.out.println("数据库连接异常！");
		   }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
}
