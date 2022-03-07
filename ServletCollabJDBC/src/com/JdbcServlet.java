package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JdbcServlet
 */
@WebServlet("/JdbcServlet")
public class JdbcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public JdbcServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();
	    String uname=request.getParameter("uname");
	    String pass=request.getParameter("pass");
	
	
	ServletContext app=getServletConfig().getServletContext();
	try{
        Class.forName(app.getInitParameter("driver"));
       Connection con = DriverManager.getConnection(app.getInitParameter("url"), app.getInitParameter("user"), app.getInitParameter("pass1"));
       PreparedStatement ps=con.prepareStatement("select name,password from admin where name=? and password=?");
       ps.setString(1, uname);
       ps.setString(2,pass);
       ResultSet rs=ps.executeQuery();
       if(rs.next()) {
    	   RequestDispatcher rd=request.getRequestDispatcher("WelcomeServlet");
    	   rd.forward(request, response);
    	   
       }
       else {
    	   out.println("<h1><center>Wrong Entries</center.</h1>");
    	   RequestDispatcher rd=request.getRequestDispatcher("Login.html");
    	   rd.include(request, response);
       }
	}catch(Exception e) {
		e.printStackTrace();
	}

}
	}
