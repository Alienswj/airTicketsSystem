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

import org.json.JSONObject;

import cn.cuit.dao.dbCRUD;

public class axios_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public axios_login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
				System.out.println("1");
				username=(String)session.getAttribute("username");
				String identity=session.getAttribute("isManager").equals("is")?"(管理员)":"(会员)";
				System.out.println(username+identity+" 已登录");
				out.print(username+identity+" 已登录");
				//response.sendRedirect("airTicketsSystem.html");
				//request.getRequestDispatcher("airTicketsSystem.html").forward(request, response);
			}else {
				//System.out.println("2");
				//out.print("null");
			}
		} else {
			if(request.getContentLength()!=0) {
				BufferedReader br = request.getReader();
			    String str, wholeStr = "";
			    while((str = br.readLine()) != null){
			        wholeStr += str;
				}
				System.out.println(wholeStr);
			    JSONObject json=new JSONObject(wholeStr);
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
					System.out.println("3");
					session.setAttribute("username", username);
					session.setAttribute("password", password);
					session.setAttribute("isLogin","true");
					String isManager=rs.getInt("accessRight")==1?"is":"not";
					session.setAttribute("isManager", isManager);
					session.setMaxInactiveInterval(60*30);
					String identity=session.getAttribute("isManager").equals("is")?"(管理员)":"(会员)";
					System.out.println(username+identity+" 已登录");
					out.print(username+identity+" 已登录");
					//request.getRequestDispatcher("airTicketsSystem.html").forward(request, response);
				} else {
						//System.out.println("4");
						//out.print("false");
				}
			} catch (SQLException e) {
				out.print("false");
				System.out.println("db connection failed");
			}
		}}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
