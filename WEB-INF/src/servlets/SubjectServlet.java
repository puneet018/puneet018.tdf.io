package servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;
import java.io.*;

import models.*;

public class SubjectServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		HttpSession session = request.getSession();
	
		request.getRequestDispatcher("subject.jsp").forward(request,response);
	
	}
}