package listeners;

import java.util.*;

import javax.servlet.*;
import models.*;

public class AppListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent event){
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	ArrayList countries = Country.collectCountries();
	ServletContext context = event.getServletContext();
	context.setAttribute ("countries",countries);
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	ArrayList professions = Profession.collectProfessions();
	context.setAttribute ("professions",professions);
	System.out.println("professions"+professions);
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	ArrayList subjects = Subject.collectSubjects();
	context.setAttribute("subjects",subjects);
	System.out.println("subjects"+subjects);
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}


	public void contextDestroyed(ServletContextEvent event){

	}

}



