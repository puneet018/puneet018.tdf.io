package servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import models.*;

public class ShowTopicServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		
		String nextPage = "topic.jsp";
		
		String subName=request.getParameter("subName");
		Integer subId = Integer.parseInt(request.getParameter("subId"));
		
		
		ArrayList topics = Topic.collectTopics(subId);
				
		request.setAttribute("topics",topics);
		request.setAttribute("subName",subName);
		request.setAttribute("subId",subId);
		System.out.println(topics);
		request.getRequestDispatcher(nextPage).forward(request,response);	
	}
}
