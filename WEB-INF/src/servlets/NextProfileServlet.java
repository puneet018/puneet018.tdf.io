package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import models.*;

public class NextProfileServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		request.getRequestDispatcher("nextprofile.jsp").forward(request,response);
	}

}