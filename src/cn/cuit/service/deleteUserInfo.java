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

public class deleteUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public deleteUserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json=toJsonUtils.toJSON(request);
		String userId=json.getString("userId");
		String sql="delete from userinfo where userId='"+userId+"'";
		PrintWriter out=response.getWriter();
		dbCRUD.connDb();
		if(dbCRUD.delete(sql)){
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
