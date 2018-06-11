package com.revature.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class LoginServer
 */
@MultipartConfig
public class LoginServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static com.revature.ConnFactory cf =com.revature.ConnFactory.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      Connection conn = cf.getConnection();

		//Create a new session cookie!
	      HttpSession session = request.getSession(true);
	    
	      
	      System.out.println("get from Session " + session.getId() + ". It was made at " + session.getCreationTime() + " and last seen at " + session.getLastAccessedTime());
		response.getWriter().append("Served by doget at LoginServer at: ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("login.html");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//setup response text
		//request.getParameter(name)
		//
		/*
		System.out.println("In doPost of loginserver! got:");
		while(request.getReader().ready()) {
			System.out.println(request.getReader().readLine());
			
		}
		//*/
	      HttpSession session = request.getSession(true);

		String userName = convertStreamToString(request.getPart("username").getInputStream());
		String password = convertStreamToString(request.getPart("password").getInputStream());

		System.out.println("Loginserver just got a post for username: " + userName + " password: " + password + " and user currently has username: "+session.getAttribute("username") + " password: " +  session.getAttribute("password") );
	      //Login a new User
	      session.setAttribute("username", userName);
	      session.setAttribute("password", password);
	      
	      
	      
	      Connection conn = cf.getConnection();
			String[] primaryKeys = new String[1];
			primaryKeys[0] = "account_id";
			String sql = "select * from accounts_table where username= ?";
			
			try {
			PreparedStatement ps = conn.prepareStatement(sql, primaryKeys);
			ps.setString(1, userName);
			ResultSet rs;

				rs = ps.executeQuery();
			if (!rs.next()) {
				return;
			}
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//response.sendRedirect("home");
		//request.getRequestDispatcher("home.html").forward(request, response);
	}
	public static String convertStreamToString(java.io.InputStream is) {
		 java.util.Scanner s =null;
		try {s= new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	   }finally {
		   s.close();
	   }
	}
}
