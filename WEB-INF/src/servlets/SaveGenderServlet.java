package servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

import models.*;

public class SaveGenderServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");
		Integer genId = Integer.parseInt(request.getParameter("genId"));
		
		String msg = null;
		if(user.saveGender(genId)){
			user.setGender(new Gender(genId));
			msg = "yes";
		}else{
			msg = "no";
		}
		response.getWriter().write(msg);
	}

}