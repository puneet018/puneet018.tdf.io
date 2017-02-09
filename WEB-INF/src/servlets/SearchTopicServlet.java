package servlets;


import javax.servlet.*;
import javax.servlet.http.*;

import org.json.*;
import java.io.*;

import models.*;

public class SearchTopicServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		String topicName = request.getParameter("topic");
		if (topicName.trim().equals("")){
			
		}else{
			Topic top = new Topic();
			Topic topic = top.searchTopic(topicName);
			request.setAttribute("topic",topic);
			System.out.println("topic.getTopicId()"+topic.getTopic());
			JSONArray ja = new JSONArray();
			JSONObject jo = new JSONObject();
			try{
					jo.put("topicId",topic.getTopicId());
					jo.put("topicName",topic.getTopic());
					jo.put("postCount",topic.getPostCount());
					//jo.put("lastPost",topic.getLastPost());
					//jo.put("moderator",topic.getTopicCount());`	
					ja.put(0,jo);
			}catch(JSONException e){
				e.printStackTrace();
			}

			response.getWriter().write(ja.toString());
		}

	}
}