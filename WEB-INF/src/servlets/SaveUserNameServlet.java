package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import models.*;

public class SaveUserNameServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");
		
		String msg = null;
		if(user!=null){		
			String userName = request.getParameter("unm");
			if(user.saveUserName(userName)){
				user.setUserName(userName);
				msg = "yes";
			}else{
				msg = "no";
			}
		}
		
		response.getWriter().write(msg);
	}
}