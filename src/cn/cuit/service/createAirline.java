package cn.cuit.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.cuit.dao.dbCRUD;
import cn.cuit.utils.toJsonUtils;

public class createAirline extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public createAirline() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject json=toJsonUtils.toJSON(request);
		String airId=json.getString("airId");
	    String airLine=json.getString("airLine");
	    String departure=json.getString("departure");
        String destination=json.getString("destination");
        String depTime=json.getString("depTime");
        String landTime=json.getString("landTime");
        String price=json.getString("price");
        String sql="insert into airlineinfo values('"+airId+"','"+airLine+"','"+departure+"','"+destination+"','"+depTime+"','"+landTime+"','"+price+"')";
        PrintWriter out=response.getWriter();
        dbCRUD.connDb();
        if(dbCRUD.create(sql)){
        	out.print("success");
        }else {
        	out.print("failed");
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
