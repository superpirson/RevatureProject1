package com.revature.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map.Entry;

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
	
	public static Hashtable<String, String> nameMapsToSQL= new Hashtable<String, String>();
	public static  Hashtable<String, String> nameMapsInverse= new Hashtable<String, String>();

	static {
		nameMapsToSQL.put("first-name", "fName");
		nameMapsToSQL.put("date", "date_completed");
		nameMapsToSQL.put("cost", "cost");
		nameMapsToSQL.put("reason", "reason");
		nameMapsToSQL.put("location", "location");
		nameMapsToSQL.put("description", "description");
		nameMapsToSQL.put("last-name", "lName");
		nameMapsToSQL.put("grading-format", "grade");
		nameMapsToSQL.put("event-type", "event_type");
		nameMapsToSQL.put("reason-change", "reason_change");
		nameMapsToSQL.put("reason-denial", "reason_denial");
		nameMapsToSQL.put("reason", "reason");
		//Build the inverse
		for(Entry<String, String> e:nameMapsToSQL.entrySet()) {
			nameMapsInverse.put(e.getValue(), e.getKey());
		}
	}
	private static final long serialVersionUID = 1L;
	public static com.revature.ConnFactory cf =com.revature.ConnFactory.getInstance();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	     // Connection conn = cf.getConnection();
	     Connection conn = cf.getConnection();
		//Create a new session cookie!
	     HttpSession session = request.getSession(true);
	    
	     String password=(String) session.getAttribute("password");
	      if ( !(password== null|| password.length()<1)) {
	    	  //User is already logged in (Yes we have no security)
	    	  if (request.getAttribute("logout").equals("true")) {
	    		  //user wants to logout
					System.out.println("Logging out  " + session.getId() + " from username "+session.getAttribute("username"));

	    		  session.invalidate();
	    	  }else {
				response.sendRedirect("home"); 
				return;
	    	  }
	      }
		//response.getWriter().append("Served by doget at LoginServer at: ").append(request.getContextPath());
		//doPost(request, response);
	    System.out.println("get from Session " + session.getId() + ". It was made at " + session.getCreationTime() + " and last seen at " + session.getLastAccessedTime());
		response.getWriter().append("Served by doget at LoginServer at: ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("login.html");
	     rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		System.out.println("In doPost of loginserver!");

		RequestDispatcher rd = request.getRequestDispatcher("home.html");
	      rd.forward(request, response);

	   if (true) return;
	   //*/
	   
	   
		//setup response text
		//request.getParameter(name)
		//
		/*
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
		/*	for (int i = 1; i<=rs.getMetaData().getColumnCount(); i++) {
			System.out.println("got "+rs.getMetaData().getColumnName(i));
			} */
			String DBpass=rs.getString("pass");
			if (DBpass == null || password== null || password.length()<1) {
				System.err.println("ERROR! Null password!");
				return;
			}

			
			if (DBpass.equals(password)) {
				System.out.println("sussfully logged in " + session.getId() + " into username "+userName);
				//Establish user data in cookie
				session.setAttribute("account_type", rs.getString("account_type"));
				session.setAttribute("fname", rs.getString("fname"));
				session.setAttribute("lname", rs.getString("lname"));
				session.setAttribute("reportsto", rs.getString("reportsto"));
				response.sendRedirect("home"); 
				
		  		
			}else {
			      session.setAttribute("password", null);
			      
			}
			

			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("sending redir to home");
			//forget Redirecting!!!!
	
	  		//

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
	public static boolean checkPasswordAndRedir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	      HttpSession session = request.getSession(true);
	      String password=(String) session.getAttribute("password");
	      if ( password== null|| password.length()<1) {
	  		//RequestDispatcher rd = request.getRequestDispatcher("login.html");
	  		//rd.forward(request, response);
	  		response.sendRedirect("login");
	  		

	    	  System.out.println("Note: We had a user with invalid credentials, ID " + session.getId() + ", Who has been kicked to the login");
	  		
	  		return false;
	      }
	      return true;

	}
	
	
	
}
