package cn.cuit.service;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.*;

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
		BufferedReader br = request.getReader();

	    String str, wholeStr = "";
	    while((str = br.readLine()) != null){
	        wholeStr += str;
		}
		//System.out.println(wholeStr);
	    JSONObject json=JSON.parseObject(wholeStr);
	    
		String departure=json.getString("departure");
		String destination=json.getString("destination");
		String airDate=json.getString("airDate");
		dbCRUD.connDb();
		String sql="select * from airlineinfo,tiksinfo where airlineinfo.airId=tiksinfo.airId and departure='"+departure+"' and destination='"+destination+"' and airDate='"+airDate+"'";
		try {
			ResultSet rs=dbCRUD.retrieve(sql);
			JSONArray ticket=new JSONArray();
			JSONObject tickets=new JSONObject();
			while(rs.next())
			{
				JSONObject info=new JSONObject();
				info.put("airId",rs.getString("airId"));
				info.put("airLine",rs.getString("airLine"));
				info.put("departure",rs.getString("departure"));
				info.put("destination",rs.getString("destination"));
				info.put("depTime",rs.getTime("depTime"));
				info.put("landTime",rs.getTime("landTime"));
				info.put("price",rs.getInt("price"));
				info.put("airDate",rs.getDate("airDate"));
				info.put("remainTickets",rs.getInt("remainTickets"));
				ticket.add(info);
			}
			String jsonObject=ticket.toJSONString();
			//System.out.print(jsonObject);
			tickets.put("tickets", ticket);
			PrintWriter out=response.getWriter();
			out.print(tickets);
		} catch (SQLException e) {
			System.out.print("failed");
		}finally {
			//System.out.print(tickets);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
