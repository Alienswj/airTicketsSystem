package cn.cuit.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.cuit.dao.dbCRUD;

public class axios_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public axios_login() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    String username=null;
	    String password=null;
	    HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		if(session.getAttribute("isLogin")!=null&&!session.getAttribute("isLogin").equals("false"))
		{
			System.out.println("null");
			if(session.getAttribute("isLogin").equals("true")) {
				System.out.println("1：用户已登录");
				username=(String)session.getAttribute("username");
				String identity=session.getAttribute("isManager").equals("is")?"(管理员)":"(会员)";
				System.out.println(username+identity+" 已登录");
				JSONObject info=new JSONObject();
				info.put("userId",session.getAttribute("userId"));
				info.put("username",username);
				info.put("identity",identity);
				out.print(info);
			}else {
				System.out.println("2：已注销");
			}
		} else {
			if(request.getContentLength()!=0) {
				BufferedReader br = request.getReader();
			    String str, wholeStr = "";
			    while((str = br.readLine()) != null){
			        wholeStr += str;
				}
				System.out.println(wholeStr);
			    JSONObject json=JSON.parseObject(wholeStr);
			    username=json.getString("username");
			    password=json.getString("password");
				String sql="select username,password,userId,accessRight from userinfo where username='"+username+"' and password='"+password+"'";
				System.out.println(sql);
				dbCRUD.connDb();
				ResultSet rs=dbCRUD.retrieve(sql);
				try {
					rs.next();
					if(username.equals(rs.getString("username"))&&password.equals(rs.getString("password")))
					{
						System.out.println("3：第一次登陆且成功");
						session.setAttribute("username", username);
						session.setAttribute("password", password);
						session.setAttribute("userId",rs.getString("userId"));
						session.setAttribute("isLogin","true");
						String isManager=rs.getInt("accessRight")==1?"is":"not";
						session.setAttribute("isManager", isManager);
						session.setMaxInactiveInterval(60*30);
						String identity=session.getAttribute("isManager").equals("is")?"(管理员)":"(会员)";
						JSONObject info=new JSONObject();
						info.put("userId",rs.getString("userId"));
						info.put("username",username);
						info.put("identity",identity);
						out.print(info);
						//request.getRequestDispatcher("airTicketsSystem.html").forward(request, response);
					} else {
						System.out.println("4：第一次登陆且失败");
					}
				} catch (SQLException e) {
					out.print("false");
					System.out.println("db connection failed");
				}
			}	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
