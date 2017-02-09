package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Date;


import models.*;
import utils.*;


public class RegisterServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session=request.getSession();

		String nextPage="login.jsp";

		String userName = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm_password");
		



		boolean flag=true;
		String msg="";

		if(userName.trim().equals("")){
			flag=false;
			msg=msg+"User Name must be required <br />";
		}

		if(email.trim().equals("")){
			flag=false;
			msg=msg+"Email must be required <br />";

		}
		if(password.trim().equals("")){
			flag=false;
			msg=msg+"password must required <br />";
		}
		if(password.trim().length()<6||password.trim().length()>15){
			flag=false;
			msg=msg+"password should be greater than 6 and smaller than 16 <br />";
					
		}
		if(!password.trim().equals(confirmPassword.trim())){
			flag=false;
			msg=msg+"password and confirm password does not match <br />";
		
		}
		if (flag){
			
			User user=new User();
			user.setUserName(userName);
			user.setEmail(email);
			user.setPassword(password);
			String activationCode = new Date().getTime()+session.getId();
			user.setActivationCode(activationCode);
	
			
		if(user.registerUser()){
			MailSend.sendAccountActivationMail(email, activationCode);
			}else{
				
				msg = msg + "Duplicate email record inserted<br />";
				request.setAttribute("msg", msg);
				nextPage = "register.jsp";
			}
			
		}
		
		else{
			nextPage = "register.jsp";
			request.setAttribute("msg", msg);
		}

		request.getRequestDispatcher(nextPage).forward(request,response);

			
	}
}