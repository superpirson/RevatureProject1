package com.revature;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
 import static com.revature.servlet.LoginServer.convertStreamToString;
@MultipartConfig
public class TRform extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static ConnFactory cf =ConnFactory.getInstance();


    public TRform() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("TRform.html");
	      HttpSession session = request.getSession(true);

		//get a user's data and send it to Joe
		Gson gBuilder=new Gson();
	      //do a quick SQL query for the current user
	      Connection conn = cf.getConnection();
			String[] primaryKeys = new String[1];
			primaryKeys[0] = "account_id";
			String sql = "select * from accounts_table where username= ?";

			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(sql, primaryKeys);
		
			ps.setString(1, (String) session.getAttribute("username"));
			ResultSet rs;

				rs = ps.executeQuery();

			while (rs.next()) {
				for (int i = 0; i<rs.getMetaData().getColumnCount(); i++) {
					response.getWriter().append(gBuilder.toJson(rs.getObject(i)));
				}
			}
				rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      HttpSession session = request.getSession(true);

		Hashtable<String, String> form= new Hashtable<String, String>();
		Hashtable<String, String> nameMaps= new Hashtable<String, String>();
		nameMaps.put("first-name", "fName");
		nameMaps.put("date", "date_completed");
		nameMaps.put("cost", "cost");
		nameMaps.put("reason", "reason");
		nameMaps.put("location", "location");
		nameMaps.put("description", "description");
		nameMaps.put("last-name", "lName");
		nameMaps.put("grading-format", "grade");
		nameMaps.put("event-type", "");
		nameMaps.put("reason", "reason");
		nameMaps.put("reason", "reason");
		nameMaps.put("reason", "reason");



		for( Part p : request.getParts()) {
			form.put(p.getName(),convertStreamToString(p.getInputStream()));
		}
	
		System.out.println("TRform just got a post for username: " + session.getAttribute("username") + " password: " +  session.getAttribute("password")  +" and data: " + form);
	      

		doGet(request, response);
	}
	

	
	
	
	
}

