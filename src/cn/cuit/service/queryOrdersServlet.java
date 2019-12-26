package cn.cuit.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.cuit.dao.dbCRUD;

public class queryOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public queryOrdersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String userId=(String) session.getAttribute("userId");
		String sql="SELECT * FROM ordersinfo,airlineinfo WHERE ordersinfo.airId=airlineinfo.airId and ordersinfo.userId ='"+userId+"'";
		String sql2="select * from travinfo where userId='"+userId+"'";
		JSONArray orders=new JSONArray();
		JSONObject json=new JSONObject();
		try {
			dbCRUD.connDb();
			ResultSet rs=dbCRUD.retrieve(sql);
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
				orders.add(info);
			}
			rs=dbCRUD.retrieve(sql2);
			while(rs.next())
			{
				json.put("travName",rs.getString("travName"));
				json.put("phoneNum",rs.getString("phoneNum"));
			}
			json.put("orders",orders);
			PrintWriter out=response.getWriter();
			out.print(json);
		}catch(SQLException e)
		{
			System.out.println("查询出错！");
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
