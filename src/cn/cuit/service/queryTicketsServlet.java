package cn.cuit.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cuit.dao.dbCRUD;

public class queryTicketsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public queryTicketsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String departure=request.getParameter("departure");
		String destination=request.getParameter("destination");
		String airDate=request.getParameter("airDate");
		dbCRUD.connDb();
		String sql="select * from airlineinfo,tiksinfo where airlineinfo.airId=tiksinfo.airId and departure='"+departure+"' and destination='"+destination+"' and airDate='"+airDate+"'";
		System.out.println(sql);
		PrintWriter out=response.getWriter();
		try {
			ResultSet rs=dbCRUD.retrieve(sql);
			out.println("<html>");
	        out.println("<head>");
	        out.println("<title>查询结果</title>");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<table border=\"1\" align=\"center\">");
	        out.println("<tr>");
	        out.println("<th>航班号</th>");
	        out.println("<th>航空公司</th>");
	        out.println("<th>起飞地</th>");
	        out.println("<th>目的地</th>");
	        out.println("<th>起飞时间</th>");
	        out.println("<th>降落时间</th>");
	        out.println("<th>价格</th>");
	        out.println("<th>日期</th>");
	        out.println("<th>余票量</th>");
	        out.println("<th></th>");
	        out.println("</tr>");
	        while(rs.next())
	        {
	        	out.println("<tr>");
	        	out.println("<td align=\"center\">"+rs.getString("airId")+"</td>");
	        	out.println("<td align=\"center\">"+rs.getString("airLine")+"</td>");
	        	out.println("<td align=\"center\">"+rs.getString("departure")+"</td>");
	        	out.println("<td align=\"center\">"+rs.getString("destination")+"</td>");
	        	out.println("<td align=\"center\">"+rs.getTime("depTime")+"</td>");
	        	out.println("<td align=\"center\">"+rs.getTime("landTime")+"</td>");
	        	out.println("<td align=\"center\">"+rs.getInt("price")+"</td>");
	        	out.println("<td align=\"center\">"+rs.getDate("airDate")+"</td>");
	        	out.println("<td align=\"center\">"+rs.getInt("remainTickets")+"</td>");
	        	out.println("<td align=\"center\">"+"</td>");
	        	out.println("<tr>");
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.print("failed");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
