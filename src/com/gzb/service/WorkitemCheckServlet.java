package com.gzb.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.gzb.db_source.ConnectionManager;
import com.weixin.mesModel.HttpRequest;



public class WorkitemCheckServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3727424360448716905L;

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		
		new Thread(new WorkitemCheckModel()).start();
//		new Thread(new PreAmountCheckModel()).start();
		new Thread(new PreAmountCheckAutoRun()).start();
	}
	
	
	
	
	
	
	
	

}
