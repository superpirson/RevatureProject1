package com.revature;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static com.revature.ConnFactory.*;
/**
 * Servlet implementation class FormInfo
 */
public class FormInfo extends HttpServlet {
	public static ConnFactory cf =ConnFactory.getInstance();

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		handleRequest(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		handleRequest(req, res);
	}

	public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
	      HttpSession session = req.getSession(true);
	      String userName = (String) session.getAttribute("username");
	      String password=(String) session.getAttribute("password");
	      String accountType = (String) session.getAttribute("account_type");
	      Integer editingFormID =session.getAttribute("editingFormID") != null && ((String)session.getAttribute("editingFormID")).length() >0 ? Integer.parseInt((String) session.getAttribute("editingFormID")): null;

//	     / StringBuilder s = new StringBuilder();
		PrintWriter out = res.getWriter();
		  res.setContentType("text/plain");
		  String formID = req.getParameter("formID");
		  
			//get a user's data and send it to Joe
			Gson gBuilder=new GsonBuilder().create();
		      //do a quick SQL query for the current user
		      Connection conn = cf.getConnection();
				String[] primaryKeys = new String[1];
				primaryKeys[0] = "form_id";
				String sql = "select * from forms_table where form_id=  ?";

				PreparedStatement ps;
				ResultSet rs;
			
				try {
					if (formID == null || formID.length()<=0 || formID.trim().equals("")) {
						sql= "select * from forms_table";
						ps = conn.prepareStatement(sql, primaryKeys);

					}else {
						ps = conn.prepareStatement(sql, primaryKeys);
						//ps.setString(1, (String) session.getAttribute("username"));
							ps.setString(1,  formID);
					}
				
					rs = ps.executeQuery();
					
					res.getWriter().append("{\"forms\":[");
					boolean seen=false;
				while (rs.next()) {
					if (!seen) {
						seen =true;
					}else {
						res.getWriter().append(",");
					}
					res.getWriter().append("{");
					
					for (int i = 1; i<=rs.getMetaData().getColumnCount(); i++) {
						if (i!=1) {
							res.getWriter().append(",");
						}
						Object o = rs.getObject(i);
						String dataAdd = gBuilder.toJson(o);
//						/nameMapsInverse.get(
						res.getWriter().append("\""+rs.getMetaData().getColumnLabel(i)+"\":" + dataAdd );

						//s.append(" ["+o+"] = " +dataAdd);
					}
					
					
					String formNew = "{? =call trform_new(?)}";
					CallableStatement formNewCall;

					try {

						formNewCall = conn.prepareCall(formNew);
						formNewCall.registerOutParameter (1, Types.INTEGER);
						formNewCall.setInt(2,(Integer) session.getAttribute("userID") );
						 formNewCall.executeQuery();	

							session.setAttribute("editingFormID" ,Integer.toString(formNewCall.getInt(1)));
						
					} catch (SQLException e) {
	System.out.println(e.getLocalizedMessage());
	e.printStackTrace();
					}
					
					
					
					res.getWriter().append("}");

				}
				res.getWriter().append("],");
				//add in the current user's info for conveince
				res.getWriter().append("\"username\":"+gBuilder.toJson(userName)+",");
				res.getWriter().append("\"password\":"+gBuilder.toJson(password)+",");
				res.getWriter().append("\"accountType\":"+gBuilder.toJson(accountType)+",");
				res.getWriter().append("\"editingFormID\":"+gBuilder.toJson(editingFormID)+",");
				
				
				//Begin Displaying Visible users
				//TODO:: Implement Raf's thing
				Blobs blob = new Blobs();
				
				
				
				//Append version and close json
				res.getWriter().append("\"version\":1}");

			//	System.out.println("FormInfo just got a request from username: " + session.getAttribute("username") + " password: " +  session.getAttribute("password")  +" and asked for " + formID + ". We sent: " +s);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}
