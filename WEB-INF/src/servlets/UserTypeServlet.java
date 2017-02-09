package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import org.json.*;

import models.*;

public class UserTypeServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		Integer typeId = Integer.parseInt(request.getParameter("type_id"));
		Integer otherUserId = Integer.parseInt(request.getParameter("other_user_id"));
		String msg = "";
		User user = new User(); 
		if(user.changeUserType(typeId,otherUserId)){
			if(typeId==3){
				Integer topicId = Integer.parseInt(request.getParameter("topic_id"));
				Integer statusId = Integer.parseInt(request.getParameter("status_id"));
				Moderator moderator = new Moderator();
				Integer moderatorId = moderator.addModerator(topicId,statusId,otherUserId);		
				}else{
					Moderator moderator = new Moderator();
					moderator.deleteModerator(otherUserId);
			}
			msg = "yes";
		}else{
			msg = "no";
		}
		response.getWriter().write(msg);
	}
}