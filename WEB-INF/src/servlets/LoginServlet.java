package servlets;


import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

import models.*;

public class LoginServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		String nextPage = "profile.jsp";
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean flag = true;
		String msg = "";

		if(email.trim().equals("")){
			flag = false;
			msg += "Email is required <br />";
		}

		if(password.trim().length()<6||password.trim().length()>15){
			flag = false;
			msg += "password must be >5 and <16 characters<br />";
		}
		if(flag){
			User user = new User(email, password);
			if(user.loginUser()){
				session.setAttribute("user", user);
				session.setAttribute("userName",user.getUserName());
			}else{
				nextPage = "login.jsp";
				msg += "Either Email or Password in invalid<br />";
				request.setAttribute("msg", msg);
			}
		}
		
		else{
			nextPage = "login.jsp";
			request.setAttribute("msg", msg);
		}
		
			
		request.getRequestDispatcher(nextPage).forward(request,response);	
	}
}