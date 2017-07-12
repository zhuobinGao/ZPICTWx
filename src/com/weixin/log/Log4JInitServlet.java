package com.weixin.log;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

public class Log4JInitServlet extends HttpServlet {

	private static final long serialVersionUID = 291439857937321153L;

	
	
	public Log4JInitServlet() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		System.out.println("Log4JInitServlet ");  
        String log4jLocation = config.getInitParameter("log4j-properties-location");  

        ServletContext sc = config.getServletContext();  

        if (log4jLocation == null) {  
            System.err.println("*** og4j-properties-location,");  
            BasicConfigurator.configure();  
        } else {  
            String webAppPath = sc.getRealPath("/");  
            String log4jProp = webAppPath + log4jLocation;  
            File yoMamaYesThisSaysYoMama = new File(log4jProp);  
            if (yoMamaYesThisSaysYoMama.exists()) {  
                System.out.println("" + log4jProp+"");  
                PropertyConfigurator.configure(log4jProp);  
            } else {  
                System.err.println("*** " + log4jProp + " BasicConfigurator");  
                BasicConfigurator.configure();  
            }  
        }  
        super.init(config);  
	}

	
	
	
	

}
