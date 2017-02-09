package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import org.json.*;

import models.*;

public class AddSubjectServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		
		String subj = request.getParameter("subject");

		int charSize = subj.trim().length();
		System.out.println("new subject"+subj);

		if(charSize>=1&&charSize<=20){
			Subject subject = new Subject();
				Subject addSubj = subject.addSubject(subj);
				if(!(addSubj==null)){
					ServletContext context = getServletContext();
					ArrayList<Subject> subjects = (ArrayList<Subject>)context.getAttribute("subjects");
					subjects.add(addSubj);
					System.out.println("subjectsQQQQQQQQQqqq"+subjects);
					
					JSONArray ja = new JSONArray();
					JSONObject jo = new JSONObject();
					

					try{

						jo.put("subject_id",addSubj.getSubjectId());
						jo.put("subject",addSubj.getSubject());
						jo.put("topic_count",addSubj.getTopicCount());
						
						ja.put(0,jo);
					}catch(JSONException e){
						e.printStackTrace();
					}

					response.getWriter().write(ja.toString());
				}else{
					System.out.println("@@@@@@@@@@@@@");
					response.getWriter().write("");
				
				}
				
		}
	}
}