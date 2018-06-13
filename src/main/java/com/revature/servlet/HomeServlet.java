package com.revature.servlet;

import static com.revature.servlet.LoginServer.checkPasswordAndRedir;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in doget of homeserverlet!");
		if (!checkPasswordAndRedir(request, response)) {
			return;
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	//	request.getRequestDispatcher("home.html").forward(request, response);;
		RequestDispatcher rd = request.getRequestDispatcher("home.html");
	      rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		System.out.println("in doPost of homeserverlet");
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.write("<h3> ROLL TIED</h3>");
		pw.close();
	}

}
