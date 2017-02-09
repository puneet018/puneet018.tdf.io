package servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;
import java.io.*;

import models.*;

public class ShowLoginServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
	
	
		request.getRequestDispatcher("login.jsp").forward(request,response);
	
	}
}

