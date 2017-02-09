package servlets;


import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import org.json.*;
import java.io.*;

import models.*;

public class SearchPostServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		String post = request.getParameter("post");
		System.out.println("POSTTTTTTTTTTT"+post);

		if (post.trim().equals("")){
			
		}else{
			Question ques = new Question();
			ArrayList <Question> questions = ques.searchPost(post);
			request.setAttribute("questions",questions);
			JSONArray ja = new JSONArray();
			int i=0;
			for (Question question : questions){
				JSONObject jo = new JSONObject();
				try{
						jo.put("squestionId",question.getQuestionId());
						jo.put("squestionTitle",question.getQuestionTitle());
						jo.put("squestionDetail",question.getQuestionDetail());
						jo.put("spostId",question.getPostId());
						jo.put("sQueUserName",question.getUser().getUserName());
						jo.put("suserId",question.getUser().getUserId());	
						jo.put("slikeCount",question.getLikeCount());	
						jo.put("sdisLikeCount",question.getDislikeCount());
						//System.out.println("userName+++++"+jo.get("userName"));
						ja.put(i,jo);
				}catch(JSONException e){
					e.printStackTrace();
				}
				i++;
			}

			response.getWriter().write(ja.toString());
		}

	}
}