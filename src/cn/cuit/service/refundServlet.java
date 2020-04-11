package cn.cuit.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.cuit.dao.dbCRUD;
import cn.cuit.utils.toJsonUtils;

public class refundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public refundServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=(String) request.getSession().getAttribute("userId");
	    JSONObject json=toJsonUtils.toJSON(request);
	    String airId=json.getString("airId");
	    String airDate=json.getString("airDate");
	    String sql="delete from ordersinfo where userId='"+userId+"' and airId='"+airId+"' and airDate='"+airDate+"'";
	    String sql2="update tiksinfo set remainTickets=remainTickets+1 where airId='"+airId+"' and airDate='"+airDate+"'";
	    PrintWriter out=response.getWriter();
	    dbCRUD.connDb();
		if(dbCRUD.delete(sql)){
			if(dbCRUD.update(sql2)==1){
				//System.out.print("success");
				out.print("success");
			}else{
				//System.out.print("2");
				out.print("failed");
			}
		}else{
			//System.out.print("3");
			out.print("failed");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
