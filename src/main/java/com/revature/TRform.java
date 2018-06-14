package com.revature;


import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import org.w3c.dom.NamedNodeMap;

import com.google.gson.Gson;
 import static com.revature.servlet.LoginServer.*;
 
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
		if (!checkPasswordAndRedir(request, response)) {
			return;
		}
		

		RequestDispatcher rd = request.getRequestDispatcher("TRform.html");
	      HttpSession session = request.getSession(true);
	    
	      session.setAttribute("editingFormID",request.getAttribute("formID"));
		rd.forward(request, response);
	      
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!checkPasswordAndRedir(request, response)) {
			return;
		}
		HttpSession session = request.getSession(true);

		
		
		 Connection conn = cf.getConnection();
			String[] primaryKeys = new String[1];
			primaryKeys[0] = "form_id";
			String sql = "update forms_table set grade=?, date_completed=? , employee_approval=?, benCo_approval=?,dha_approval =?, dsa_approval=?, grades_approval=? ,form_status =?, description=?, location=?, cost =?,reason_denial =?, reason_change =?, reason_reimburse=?,event_type=?, forms=? ,fName=?, lName=? where form_id = ?";
			
			
			/*
			StringBuilder sql = new StringBuilder();
			sql.append("update forms_table set ");
			boolean seen = false;
			for (Entry<String, String> f:form.entrySet()) {
				if(f.getValue()!= null&&!f.getKey().equals("files")) {
					if (seen) {
						sql.append(", ");
					}else {seen=true;}
					sql.append(f.getKey() + "=\'" + f.getValue()+"\' ");
				}
			}
			sql.append("where form_id = ?");
			//*/
			
			
			PreparedStatement ps;
			ResultSet rs;
			
			try {
		        SimpleDateFormat dateParser = new SimpleDateFormat("dd-MM-yyyy");

					ps = conn.prepareStatement(sql.toString(), primaryKeys);
					//Expanded for debugging reassons
					String nameOfPart = nameMapsInverse.get("grade");
					Part part = request.getPart(nameOfPart);
					ps.setString(1,  convertStreamToString(part.getInputStream()));

					try {
						ps.setDate(2,  new java.sql.Date(dateParser.parse(convertStreamToString(request.getPart(nameMapsInverse.get("date_completed")).getInputStream())).getTime()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ps.setString(3, convertStreamToString(request.getPart(nameMapsInverse.get("employee_approval")).getInputStream()));
					ps.setString(4, convertStreamToString(request.getPart(nameMapsInverse.get("benCo_approval")).getInputStream()));
					ps.setString(5, convertStreamToString(request.getPart(nameMapsInverse.get("dha_approval")).getInputStream()));
					ps.setString(6, convertStreamToString(request.getPart(nameMapsInverse.get("dsa_approval")).getInputStream()));
					ps.setString(7, convertStreamToString(request.getPart(nameMapsInverse.get("grades_approval")).getInputStream()));

					ps.setString(8, convertStreamToString(request.getPart(nameMapsInverse.get("form_status")).getInputStream()));
					ps.setString(9, convertStreamToString(request.getPart(nameMapsInverse.get("description")).getInputStream()));
					ps.setString(10, convertStreamToString(request.getPart(nameMapsInverse.get("location")).getInputStream()));
					ps.setDouble(11, Double.parseDouble(convertStreamToString(request.getPart(nameMapsInverse.get("cost")).getInputStream())));
					
					ps.setString(12, convertStreamToString(request.getPart(nameMapsInverse.get("reason_denial")).getInputStream()));
					ps.setString(13, convertStreamToString(request.getPart(nameMapsInverse.get("reason_change")).getInputStream()));
					ps.setString(14, convertStreamToString(request.getPart(nameMapsInverse.get("reason_reimburse")).getInputStream()));
					ps.setString(15, convertStreamToString(request.getPart(nameMapsInverse.get("event_type")).getInputStream()));
					ps.setBlob(16, request.getPart(nameMapsInverse.get("forms")).getInputStream());
					ps.setString(17, convertStreamToString(request.getPart(nameMapsInverse.get("fname")).getInputStream()));
					ps.setString(18, convertStreamToString(request.getPart(nameMapsInverse.get("lname")).getInputStream()));

					ps.setString(19,  (String) session.getAttribute("editingFormID"));
					System.out.println(sql);

				rs = ps.executeQuery();
			}catch (SQLException s) {
				
				s.printStackTrace();
			}
				
		System.out.println("TRform just got a post for username: " + session.getAttribute("username") + " password: " +  session.getAttribute("password")  );
	      

		doGet(request, response);
	}
	

	
	
	
	
}

