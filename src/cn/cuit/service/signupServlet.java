package cn.cuit.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cuit.dao.dbCRUD;

public class signupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		dbCRUD.connDb();
		String sql="select count(*) from userinfo";
		PrintWriter out=response.getWriter();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
