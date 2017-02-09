package servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import models.*;

public class DeletePostServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		HttpSession session = request.getSession();

		Integer postId = Integer.parseInt(request.getParameter("postId"));

		Post delQue = new Post();
		delQue.setPostId(postId);
		
		if(delQue.deletePost()){
			//System.out.println("delQue.deleteQuestion()"+delQue.deleteQuestion());
			response.getWriter().write("true");		
		}else{
			response.getWriter().write("false");
		}

	}
}
