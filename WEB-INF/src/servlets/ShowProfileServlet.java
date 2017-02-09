package servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;
import java.io.*;

import models.*;

public class ShowProfileServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		String nextPage = "profile.jsp";
	
		request.getRequestDispatcher(nextPage).forward(request,response);
	
	}
}
