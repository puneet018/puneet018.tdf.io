package servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

import models.*;

public class SaveCountryServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");
		Integer countryId = Integer.parseInt(request.getParameter("countryId"));
		
		String msg = null;
		if(user.saveCountry(countryId)){
			user.setCountry(new Country(countryId));
			msg = "yes";
		}else{
			msg = "no";
		}
		response.getWriter().write(msg);
	}

}