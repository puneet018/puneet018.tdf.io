package servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import org.json.*;

import models.*;

public class ShowPostDetailServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
	
		Question question = new Question();
		//question.setPostId(postId);
		
		//String nextPage = "post.jsp";
		Integer postId = Integer.parseInt(request.getParameter("post_id"));
		
		Integer questionId = Integer.parseInt(request.getParameter("question_id"));
		System.out.println(questionId+"questionId is here");

		//Question questionDetail = question.questionDetail(postId);
		ArrayList<Reply> allReplies = Reply.collectReplies(questionId);
		
		//request.setAttribute("questionDetail",questionDetail);
		request.setAttribute("allReplies",allReplies);
		
		//System.out.println(questionDetail+"Question detail=+++++++++");

		
		JSONArray ja = new JSONArray();
		int i = 0;
		for(Reply reply : allReplies){
			JSONObject jo = new JSONObject();
			try{
				//jo.put("question_title_unm",returnQuestionTitle.getUser().getUserName());
				//jo.put("title",questionDetail.getQuestionTitle());
				//jo.put("question_detail",questionDetail.getQuestionDetail());
				jo.put("reply_post_id",reply.getPostId());
				jo.put("reply_unm",reply.getUser().getUserName());
				jo.put("reply",reply.getMessage());
				jo.put("reply_lc",reply.getLikeCount());
				jo.put("reply_dc",reply.getDislikeCount());
				//jo.put("like_count",returnQuestionTitle.getLikeCount());
				//jo.put("dislike_count",returnQuestionTitle.getDislikeCount());
				System.out.println(jo.get("reply_unm"));
				System.out.println(jo.get("reply_post_id"));
				ja.put(i,jo);
				//System.out.println("```````````````"+returnQuestionTitle.getQuestionTitle()+"`````````````````");
			}catch(JSONException e){
				e.printStackTrace();
			}
			i++;
			System.out.println("IIIIIII"+i);
			
		}
		
		response.getWriter().write(ja.toString());	
		//request.getRequestDispatcher(nextPage).forward(request,response);
	}
}