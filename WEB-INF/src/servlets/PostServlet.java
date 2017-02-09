package servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import models.*;

public class PostServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession(false);

		String nextPage = "post.jsp";
		String topic = request.getParameter("topic");
		Integer topicId = Integer.parseInt(request.getParameter("topicId"));
		request.setAttribute("topic",topic);
		request.setAttribute("topicId",topicId);

		ArrayList questions = Question.collectQuestionTitles(topicId);

		request.setAttribute("questions",questions);
		//System.out.println(questions);
		request.getRequestDispatcher(nextPage).forward(request,response);
	}

	
}