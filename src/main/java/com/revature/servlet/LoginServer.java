package com.revature.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class LoginServer
 */
public class LoginServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static com.revature.ConnFactory cf =com.revature.ConnFactory.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Create a new session cookie!
	      HttpSession session = request.getSession(true);
	    
	      
	      System.out.println("Session " + session.getId() + " just rejoined. It was made at " + session.getCreationTime() + " and last seen at " + session.getLastAccessedTime());
		response.getWriter().append("Served by doget at LoginServer at: ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("login.html");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("In doPost of loginserver!");
	      HttpSession session = request.getSession(true);
	      System.out.println(request.toString());
	      //Login a new User
	      Connection conn = cf.getConnection();
			String[] primaryKeys = new String[1];
			primaryKeys[0] = "account_id";
			String sql = "select * from accounts_table where username= ?";
			
			/*try {
			PreparedStatement ps = conn.prepareStatement(sql, primaryKeys);
			ps.setString(1, x);
			ResultSet rs;

				rs = ps.executeQuery();
			if (!rs.next()) {
				return false;
			}
			return rs.getBoolean(1);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		//response.sendRedirect("home");
		//request.getRequestDispatcher("home.html").forward(request, response);
	}

}
