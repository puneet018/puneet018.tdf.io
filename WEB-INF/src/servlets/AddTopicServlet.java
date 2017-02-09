package servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;
import java.io.*;

import org.json.*;

import models.*;

public class AddTopicServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		HttpSession session = request.getSession();
		
		String newTopic = request.getParameter("topic");

		int topicSize = newTopic.trim().length();

		if(topicSize>=1&&topicSize<=25){
			Integer subjId = Integer.parseInt(request.getParameter("subjectId"));
			System.out.println("`````````````subject id``````````````"+subjId);
			
			Topic topic = new Topic();
			Subject subject = new Subject(subjId);

			Topic returnTopic = topic.addTopic(newTopic,subject);
			request.setAttribute("returnTopic",returnTopic);
			JSONArray ja = new JSONArray();
			JSONObject jo = new JSONObject();

			try{

				jo.put("topic_id",returnTopic.getTopicId());
				jo.put("topic",returnTopic.getTopic());
				jo.put("post_count",returnTopic.getPostCount());
							
				ja.put(0,jo);
				}catch(JSONException e){
					e.printStackTrace();
				}

				response.getWriter().write(ja.toString());
		}
	}
}