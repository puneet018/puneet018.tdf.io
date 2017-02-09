package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import org.json.*;

import models.*;

public class SelectTopicServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		
		Topic topic = new Topic();
		ArrayList<Topic> topics = topic.selectTopics();

		request.setAttribute("topics",topics);

		JSONArray ja = new JSONArray();
		int i = 0;
		for(Topic alltopic : topics){
			JSONObject jo = new JSONObject();
			System.out.println("topicssssssssssssss"+alltopic.getTopic());
			try{
					jo.put("topicId",alltopic.getTopicId());
					jo.put("topic",alltopic.getTopic());
					ja.put(i,jo);
					}catch(JSONException e){
						e.printStackTrace();
					}i++;
		}

				response.getWriter().write(ja.toString());
	}
}