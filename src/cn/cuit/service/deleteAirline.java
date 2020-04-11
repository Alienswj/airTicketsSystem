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

public class deleteAirline extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public deleteAirline() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json=toJsonUtils.toJSON(request);
		String airId=json.getString("airId");
		String sql="delete from ordersinfo where airId='"+airId+"';";
		String sql1="SET foreign_key_checks = 0;";
		String sql2="delete from airlineinfo where airId='"+airId+"';";
		String sql3="SET foreign_key_checks = 1;";
		PrintWriter out=response.getWriter();
		dbCRUD.connDb();
		dbCRUD.delete(sql);
		dbCRUD.delete(sql1);
		if(dbCRUD.delete(sql2)){
			out.print("success");
		}else {
			out.print("failed");
		}
		dbCRUD.delete(sql3);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
