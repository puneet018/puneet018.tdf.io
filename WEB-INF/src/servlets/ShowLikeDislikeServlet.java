package servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import models.*;

public class ShowLikeDislikeServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException{
		HttpSession session = request.getSession();
		String msg = "";

		User user = (User)session.getAttribute("user");
		//Post post = (Post)session.getAttribute("post");

		Integer postId = Integer.parseInt(request.getParameter("post_id"));
		Integer status = Integer.parseInt(request.getParameter("status"));
		LikeDislike likeDislike = new LikeDislike();
		
		likeDislike.setPost(new Post(postId));
		likeDislike.setUser(new User(user.getUserId()));
		if(status==1){
			likeDislike.setFeedbackType(new FeedbackType(FeedbackType.LIKE));
		}else{
			likeDislike.setFeedbackType(new FeedbackType(FeedbackType.DISLIKE));
		}

		System.out.println(user.getUserId()+"userId");

		if(likeDislike.likeDislike()){
			Post post = new Post();
			System.out.println(user.getUserId()+"userIdmrerjoij"+status);

			if(post.likeDislikeCount(postId,status)){
				msg="yes";
			}else{
				msg = "no";
			}
			
		}
		response.getWriter().write(msg);
	}

}