package servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import models.*;

public class ShowRegisterServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{




		request.getRequestDispatcher("register.jsp").forward(request,response);
	}




}