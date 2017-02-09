package servlets;


import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

import models.*;

public class LogOutServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		session.invalidate();
		
		String nextPage="home.jsp";

		request.getRequestDispatcher(nextPage).forward(request,response);

	}

}
