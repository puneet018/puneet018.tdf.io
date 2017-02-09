package servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import org.json.*;

import models.*;

public class ReplyServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		HttpSession session = request.getSession();
		
		//String nextPage = "post.do";

		User user = (User)session.getAttribute("user");
		System.out.println("In PostreplyServlet");
		String rply = request.getParameter("reply");
		System.out.println(rply+"#################");
		//String topicName = request.getParameter("topic");
		Integer topicId = Integer.parseInt(request.getParameter("topicId"));
		Integer questionId = Integer.parseInt(request.getParameter("question_id"));
		boolean flag = true;

		if(rply.trim().equals("")){
			flag = false;
		}

		if (flag){
			Post post = new Post();
			post.setPostType(new PostType(PostType.REPLY));
			post.setUser(new User(user.getUserId()));
			int postId = post.storePostInfo();

			Topic topic = new Topic();

			Reply replyObj = new Reply();
			replyObj.setMessage(rply);
			replyObj.setQuestion(new Question(questionId));
			replyObj.setPostId(postId);


			Reply reply = replyObj.postNewReply(rply);
			
			//ArrayList replies = Reply.collectReply(QuestionId);
			request.setAttribute("reply",reply);
			//request.setAttribute("replies",replies);
			JSONArray ja = new JSONArray();
			JSONObject jo = new JSONObject();
				try{
					jo.put("reply_unm",user.getUserName());
					jo.put("message",reply.getMessage());
					jo.put("like_count",reply.getLikeCount());
					jo.put("dislike_count",reply.getDislikeCount());
			
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