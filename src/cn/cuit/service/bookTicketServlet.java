package cn.cuit.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.cuit.dao.dbCRUD;

public class bookTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public bookTicketServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = request.getReader();
	    String str, wholeStr = "";
	    while((str = br.readLine()) != null){
	        wholeStr += str;
		}
	    JSONObject json=JSON.parseObject(wholeStr);
	    String userId=json.getString("userId");
	    String airId=json.getString("airId");
	    String airDate=json.getString("airDate");
	    HttpSession session=request.getSession();
	    PrintWriter out=response.getWriter();
	    if(session.getAttribute("isLogin")!=null&&userId.equals(session.getAttribute("userId"))&&session.getAttribute("isLogin").equals("true"))
	    {
	    	String sql="insert into ordersinfo value('"+airId+"','"+userId+"','"+airDate+"')";
	    	String sql2="update tiksinfo set remainTickets=remainTickets-1 where airId='"+airId+"' and airDate='"+airDate+"'";
	    	dbCRUD.connDb();
	    	if(dbCRUD.create(sql)){
	    		if(dbCRUD.update(sql2)!=0){
	    			out.print("success");
	    		}else{
	    			out.print("failed");
	    		}
	    	}else {
	    		out.print("failed");
	    	}
	    }else {
	    	out.print("failed");
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
