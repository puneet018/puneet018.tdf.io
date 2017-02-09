package servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

import models.*;

public class SaveProfessionServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");
		Integer professionId = Integer.parseInt(request.getParameter("professionId"));
		System.out.println("professionId========"+professionId);
		String msg = null;
		if(user.saveProfession(professionId)){
			user.setProfession(new Profession(professionId));
			msg = "yes";
		}else{
			msg = "no";
		}
		response.getWriter().write(msg);
	}

}