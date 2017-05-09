<%@ page language="java" import="java.util.*,com.parkinglot.entity.Infor" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <form action="servlet/ReportServlet" method="post">
  	<input type="submit" value="生成报表"/>
  </form>
   <table border="1">
   	<tr>
   		<th>报表编号</th>
   		<th>商品名称</th>
   		<th>商品利润</th>
   		<th>销售数量</th>
   		<th>交易笔数</th>
   		<th>收入</th>
   	</tr>
  <!-- 
  1.从Session中获取到对象集合 
  2.判断是否null和size()>0、初始化总销售数量、总交易笔数、总收入对象
  3.遍历Infor对象、get各项属性
  -->
  <%
  ArrayList<Infor> inforlist = (ArrayList<Infor>)request.getSession().getAttribute("inforlist");
  if(inforlist!=null&&inforlist.size()>0)
  {
  	int Temp1 = 0,Temp2 = 0,Temp3 = 0;
  	for(int i=0;i<inforlist.size();i++)
  	{
  	Infor infor = new Infor();
  	infor = inforlist.get(i);
  	Temp1+=infor.getSellNumber();
  	Temp2+=infor.getTscNumber();
  	Temp3+=infor.getIncome();
   %>
   <tr>
   	 	<td><%=i+1%></td>
   		<td><%=infor.getName() %></td>
   		<td><%=infor.getProfit() %></td>
   		<td><%=infor.getSellNumber() %></td>
   		<td><%=infor.getTscNumber() %></td>
   		<td><%=infor.getIncome() %></td>
   	</tr>
   <%
   	}
   	%>
   <tr>
   		<td colspan="3" align="center">合计：</td>
   	 	<td><%=Temp1%></td>
   		<td><%=Temp2%></td>
   		<td><%=Temp3%></td>
   	</tr>
   <%
   }
    %>
   </table>
  </body>
</html>
