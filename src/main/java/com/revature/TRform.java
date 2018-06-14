package com.revature;


import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
import javax.sound.midi.Synthesizer;
import javax.sound.midi.SysexMessage;

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
	    
	   
		rd.forward(request, response);
	      
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!checkPasswordAndRedir(request, response)) {
			return;
		}
		HttpSession session = request.getSession(true);
		 Connection conn = cf.getConnection();

		   session.setAttribute("editingFormID" ,request.getParameter("formID"));
		   System.out.println("setting formID to " +request.getParameter("formID"));
		   if(request.getParameter("formID") == null || request.getParameter("formID").length()==0) {
			   

				String formNew = "{? =call trform_new(?)}";
				CallableStatement formNewCall;

				try {

					formNewCall = conn.prepareCall(formNew);
					formNewCall.registerOutParameter (1, Types.INTEGER);
					formNewCall.setInt(2,(Integer) session.getAttribute("userID") );
					ResultSet rs = formNewCall.executeQuery();	

						session.setAttribute("editingFormID" ,rs.getInt(1));
					
				} catch (SQLException e) {
System.out.println(e.getLocalizedMessage());
e.printStackTrace();
				}

			
			   System.out.println("Creating new form! Id was assigned to " +  session.getAttribute("editingFormID"));

		   }
		
		
		
			String[] primaryKeys = new String[1];
			primaryKeys[0] = "form_id";
			String sql = "update forms_table set grade=NVL(?,grade), date_completed=NVL(?,date_completed) , employee_approval=NVL(?,employee_approval), benCo_approval=NVL(?,benCo_approval), dha_approval =NVL(?,dha_approval), dsa_approval=NVL(?,dsa_approval), grades_approval=NVL(?,grades_approval) , form_status =NVL(?,form_status), description=NVL(?,description), location=NVL(?,location), cost=NVL(?,cost),reason_denial=NVL(?,reason_denial), reason_change=NVL(?,reason_change), reason_reimburse=NVL(?,reason_reimburse), event_type=NVL(?,event_type), files=NVL(?,files) , fName=NVL(?,fName), lName=NVL(?,lName) where form_id = ?";
			
			
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
				
					Part part = (Part) request.getPart(nameOfPart);
					ps.setString(1,  convertStreamToString(part.getInputStream()));

					try {
						ps.setDate(2,  new java.sql.Date(dateParser.parse(convertStreamToString(request.getPart(nameMapsInverse.get("date_completed")).getInputStream())).getTime()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ps.setString(3, convertStreamToString(inputStreamOrNull(request.getPart(nameMapsInverse.get("employee_approval")), null)));
					ps.setString(4, convertStreamToString(inputStreamOrNull(request.getPart(nameMapsInverse.get("benco_approval")), null)));
					ps.setString(5, convertStreamToString(inputStreamOrNull(request.getPart(nameMapsInverse.get("dha_approval")), null)));
					ps.setString(6, convertStreamToString(inputStreamOrNull(request.getPart(nameMapsInverse.get("dsa_approval")), null)));
					ps.setString(7, convertStreamToString(inputStreamOrNull(request.getPart(nameMapsInverse.get("grades_approval")), null)));

					ps.setString(8, convertStreamToString(inputStreamOrNull(request.getPart(nameMapsInverse.get("form_status")), null)));
					ps.setString(9, convertStreamToString(inputStreamOrNull(request.getPart(nameMapsInverse.get("description")), null)));
					ps.setString(10, convertStreamToString(inputStreamOrNull(request.getPart(nameMapsInverse.get("location")), null)));
					System.out.println("location: "+convertStreamToString(inputStreamOrNull(request.getPart(nameMapsInverse.get("location")), null)));

					ps.setDouble(11, Double.parseDouble(convertStreamToString(inputStreamOrNull(request.getPart(nameMapsInverse.get("cost")), null))));
					ps.setString(12, convertStreamToString(inputStreamOrNull(request.getPart(nameMapsInverse.get("reason_denial")), null)));
					ps.setString(13, convertStreamToString(inputStreamOrNull(request.getPart(nameMapsInverse.get("reason_change")), null)));
					ps.setString(14, convertStreamToString(inputStreamOrNull(request.getPart(nameMapsInverse.get("reason_reimburse")), null)));
					ps.setString(15, convertStreamToString(inputStreamOrNull(request.getPart(nameMapsInverse.get("event_type")), null)));
					ps.setBlob(16, (InputStream)null);//inputStreamOrNull(request.getPart(nameMapsInverse.get("forms")), null));
					ps.setString(17, convertStreamToString(inputStreamOrNull(request.getPart(nameMapsInverse.get("fname")), null)));
					ps.setString(18, convertStreamToString(inputStreamOrNull(request.getPart(nameMapsInverse.get("lname")), null)));

					ps.setInt(19,  Integer.parseInt((String) session.getAttribute("editingFormID")));
					//System.out.println(ps);
				rs = ps.executeQuery();
			}catch (SQLException s) {
				System.err.println(s.getMessage());
				s.printStackTrace();
			}
				
		System.out.println("TRform just got a post for username: " + session.getAttribute("username") + " password: " +  session.getAttribute("password")  );
	      

		doGet(request, response);
	}
	

	
	public static InputStream inputStreamOrNull(Object nullable, Object alternative) throws IOException {
		if (nullable!= null) {
			return ((Part) nullable).getInputStream();
		}
		else return (InputStream) alternative;
	}
	
	
}

