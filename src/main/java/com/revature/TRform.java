package com.revature;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map.Entry;

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

				rd.forward(request, response);
	      
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      HttpSession session = request.getSession(true);

		Hashtable<String, String> form= new Hashtable<String, String>();



		for( Part p : request.getParts()) {
			form.put(p.getName(),convertStreamToString(p.getInputStream()));
		}
		
		
		 Connection conn = cf.getConnection();
			String[] primaryKeys = new String[1];
			primaryKeys[0] = "form_id";
			StringBuilder sql = new StringBuilder();
			
			sql.append("update forms_table set ");
			boolean seen = false;
			for (Entry<String, String> f:form.entrySet()) {
				if (seen) {
					sql.append(",");
				}else {seen=true;}
				sql.append(f.getKey() + "=" + f.getValue());
			}
			
			sql.append("where form_id = ?");

			PreparedStatement ps;
			ResultSet rs;
		
			try {
				
					ps = conn.prepareStatement(sql.toString(), primaryKeys);
					ps.setString(1,  (String) session.getAttribute("editingFormID"));
					
				rs = ps.executeQuery();
			}catch (SQLException s) {
				
				s.printStackTrace();
			}
				
		System.out.println("TRform just got a post for username: " + session.getAttribute("username") + " password: " +  session.getAttribute("password")  +" and data: " + form);
	      

		doGet(request, response);
	}
	

	
	
	
	
}

