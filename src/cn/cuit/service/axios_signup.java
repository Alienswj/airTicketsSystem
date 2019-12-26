package cn.cuit.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.cuit.dao.dbCRUD;


public class axios_signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public axios_signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = request.getReader();

	    String str, wholeStr = "";
	    while((str = br.readLine()) != null){
	        wholeStr += str;
		}
		//System.out.println(wholeStr);
	    JSONObject json=new JSONObject(wholeStr);
	    //System.out.print(json.getString("username"));
	    String username=json.getString("username");
	    String password=json.getString("password");
	    PrintWriter out=response.getWriter();
	    dbCRUD.connDb();
		String sql="select count(*) from userinfo";
		try {
			ResultSet rs=dbCRUD.retrieve(sql);
			rs.next();
			long count=rs.getLong("count(*)");
			String userId=String.valueOf(count+1);
			//System.out.println(count);
			sql="insert into userinfo(username,password,userId,accessRight) values('"+username+"','"+password+"','"+userId+"',0);";
			System.out.println(sql);
			if(dbCRUD.create(sql))
				out.print("success");
			else
				out.print("failed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.print("failed");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
