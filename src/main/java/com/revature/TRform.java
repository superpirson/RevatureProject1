package com.revature;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import static com.revature.servlet.LoginServer.convertStreamToString;;



@MultipartConfig
public class TRform extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public TRform() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("TRform.html");
		rd.forward(request, response);
	      
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      HttpSession session = request.getSession(true);

		Hashtable<String, String> form= new Hashtable<String, String>();
		for( Part p : request.getParts()) {
			form.put(p.getName(),convertStreamToString(p.getInputStream()));
		}
		System.out.println("TRform just got a post for username: " + session.getAttribute("username") + " password: " +  session.getAttribute("password")  +" and data: " + form);
	      

		doGet(request, response);
	}
	
	/*
	// callable statements
	CallableStatement myCall;
	
	myCall = myConn.prepareCall("{ call create_fname(?) }");
	myCall.setString(1, firstname);
	
	myCall = myConn.prepareCall("{ call create_lname(?) }");
	
	
	myCall = myConn.prepareCall("{ call create_grade(?) }");
	
	
	myCall = myConn.prepareCall("{ call create_date_completed(?) }");
	
	
	myCall = myConn.prepareCall("{ call create_emp_approval(?) }");
	
	
	myCall = myConn.prepareCall("{ call create_benco_approval(?) }");
	
	
	myCall = myConn.prepareCall("{ call create_dha_approval(?) }");
	
	
	myCall = myConn.prepareCall("{ call create_dsa_approval(?) }");
	
	
	myCall = myConn.prepareCall("{ call create_grades_approval(?) }");
	
	
	myCall = myConn.prepareCall("{ call create_form_status(?) }");
	
	
	myCall = myConn.prepareCall("{ call create_submitted_by(?) }");
	
	
	myCall = myConn.prepareCall("{ call create_description(?) }");
	
	
	myCall = myConn.prepareCall("{ call create_location(?) }");
	
	
	myCall = myConn.prepareCall("{ call create_cost(?) }");
	
	
	myCall = myConn.prepareCall("{ call create_reason(?) }");
	
	
	
	myCall.execute();
	
	
	*/
	
	
	
	
}

