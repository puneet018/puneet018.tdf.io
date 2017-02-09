package servlets;


import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import models.*;

public class ActivationServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		
		HttpSession session = request.getSession();

		String activationCode = request.getParameter("actcode");
		String email = request.getParameter("email");
		String nextPage = "login.jsp";

		
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}