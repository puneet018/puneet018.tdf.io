package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import models.*;


public class ShowHomeServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		request.getRequestDispatcher("home.jsp").forward(request,response);
	}
}