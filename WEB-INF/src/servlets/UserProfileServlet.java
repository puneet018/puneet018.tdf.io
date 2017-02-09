package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import org.json.*;

import models.*;

public class UserProfileServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		Integer qstnUsrId = Integer.parseInt(request.getParameter("qstn_usr_id"));
		System.out.println("aaaaaaaaaaaaaa");
		User user = new User(qstnUsrId);

		User userData = user.showProfile();
		request.setAttribute("userData",userData);

		
		
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();	
		try{
				jo.put("userId",userData.getUserId());
				jo.put("userName",userData.getUserName());
				jo.put("email",userData.getEmail());
				jo.put("joiningDate",userData.getJoiningDate());
				jo.put("questionCount",userData.getUserQuestionCount());
				jo.put("replyCount",userData.getUserReplyCount());
				jo.put("userType",userData.getUserType().getUserTypeId());
				if(userData.getUserType().getUserTypeId()==2){
					Moderator moderator = new Moderator();
					Moderator topic = moderator.TopicName(qstnUsrId); 
					request.setAttribute("topic",topic);
					jo.put("topic",topic.getTopic().getTopic());
				}
				jo.put("statusId",userData.getStatus().getStatusId());
				jo.put("status",userData.getStatus().getStatus());
				ja.put(0,jo);
				}catch(JSONException e){
					e.printStackTrace();
				}

				response.getWriter().write(ja.toString());
	}
}