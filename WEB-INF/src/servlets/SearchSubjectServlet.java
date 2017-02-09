package servlets;


import javax.servlet.*;
import javax.servlet.http.*;

import org.json.*;
import java.io.*;

import models.*;

public class SearchSubjectServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		String subjectName = request.getParameter("subject");
		if (subjectName.trim().equals("")){
			
		}else{
			Subject subj = new Subject();
			Subject subject = subj.searchSubject(subjectName);
			request.setAttribute("subject",subject);
			System.out.println("subject.getSubject()"+subject.getSubject());
			JSONArray ja = new JSONArray();
			JSONObject jo = new JSONObject();
			try{
					jo.put("subjectId",subject.getSubjectId());
					jo.put("subjectName",subject.getSubject());
					jo.put("topicCount",subject.getTopicCount());
					ja.put(0,jo);
			}catch(JSONException e){
				e.printStackTrace();
			}

			response.getWriter().write(ja.toString());
		}

	}
}