package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import java.sql.*;

import java.text.*;

import models.*;

public class SaveDobServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");
		
		String msg = null;
		if(user!=null){	
			System.out.println(request.getParameter("dob")+"````````");
			Date dob = Date.valueOf(request.getParameter("dob"));
			if(user.saveDob(dob)){
				user.setDateOfBirth(dob);
				msg = "yes";
			}else{
				msg = "no";
			}
		}
		response.getWriter().write(msg);
	}
}