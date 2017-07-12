package com.weixin.mesModel;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gzb.service.WorkitemCheckService;

public class CommonServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4231417867344870764L;

	WorkitemCheckService workitem = new WorkitemCheckService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String param = request.getParameter("param");
		String result = "";
		
		System.out.println(param);
		
		switch (param) {
		case "workItemCheck":
			result = workitem.searchWorkItem(request);
			break;
		
		 
			
			
			
		default:
			break;
		}
		
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		OutputStream os = response.getOutputStream();
		
		System.out.println(result);
		os.write(result.getBytes("UTF-8"));
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	
	}

}
