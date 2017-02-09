package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import org.json.*;

import models.*;

public class RemoveSubjectServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
	
		Integer subjId = Integer.parseInt(request.getParameter("subjectId"));
		Subject subject = new Subject(subjId);
		subject.removeSubject();
		ServletContext context = getServletContext();
		ArrayList<Subject> subjects = (ArrayList<Subject>)context.getAttribute("subjects");
		subjects.remove(subject);
		
		//context.setAttribute("subjects",subjects);
		response.getWriter().write("true");
	}
}
//now you recompie reload and test
