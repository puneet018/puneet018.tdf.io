package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import org.json.*;

import models.*;

public class DeleteTopicServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
	
		Integer topicId = Integer.parseInt(request.getParameter("topic_id"));
		Integer subjectId = Integer.parseInt(request.getParameter("subject_id"));
		Topic topic = new Topic();
		if(topic.removeTopic(topicId,subjectId)){
			response.getWriter().write("true");
		}else{
			response.getWriter().write("flase");
		}
	}
}
//now you recompie reload and test
