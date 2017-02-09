package servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import org.json.*;

import models.*;

public class PostQuestionServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		HttpSession session = request.getSession();
		
		//String nextPage = "post.do";

		User user = (User)session.getAttribute("user");
		System.out.println("In PostQuestionServlet");
		String title = request.getParameter("title");
		String desc = request.getParameter("desc");
		//String topicName = request.getParameter("topic");
		Integer topicId = Integer.parseInt(request.getParameter("topicId"));
		boolean flag = true;

		if(title.trim().equals("")){
			flag = false;
		}

		if(desc.trim().equals("")){
			flag = false;
		}
		if (flag){
			Post post = new Post();
			post.setPostType(new PostType(PostType.QUESTION));
			post.setUser(new User(user.getUserId()));
			int postId = post.storePostInfo();

			Topic topic = new Topic();

			Question question = new Question();
			question.setQuestionTitle(title);
			question.setQuestionDetail(desc);
			question.setTopic(new Topic(topicId));
			question.setPostId(postId);


			Question returnQuestionTitle = question.postNewQuestion(topicId);
			System.out.println("```````````````"+returnQuestionTitle.getQuestionTitle()+"`````````````````");
			ArrayList questions = Question.collectQuestionTitles(topicId);
			System.out.println(returnQuestionTitle.getLikeCount()+"{}{}{}{}{}{}{}{}");
			request.setAttribute("returnQuestionTitle",returnQuestionTitle);
			request.setAttribute("questions",questions);
			JSONArray ja = new JSONArray();
			JSONObject jo = new JSONObject();
				try{
					jo.put("u_id",returnQuestionTitle.getUser().getUserId());
					jo.put("question_id",returnQuestionTitle.getQuestionId());
					jo.put("post_id",returnQuestionTitle.getPostId());
					jo.put("question_title_unm",returnQuestionTitle.getUser().getUserName());
					jo.put("title",returnQuestionTitle.getQuestionTitle());
					jo.put("question_detail",returnQuestionTitle.getQuestionDetail());
					jo.put("like_count",returnQuestionTitle.getLikeCount());
					jo.put("dislike_count",returnQuestionTitle.getDislikeCount());
					System.out.println("```````````````"+returnQuestionTitle.getUser().getUserId()+"`````````````````");
					ja.put(0,jo);
				}catch(JSONException e){
					e.printStackTrace();
				}
		response.getWriter().write(ja.toString());
		}else{
		//request.getRequestDispatcher("post.do?topic="+topicName+"&topicId="+topicId).forward(request,response);

		response.getWriter().write("");
		}
	}
}