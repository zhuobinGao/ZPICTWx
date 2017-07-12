package com.gzb.api.controler;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gzb.api.service.MoneyService;
import com.weixin.util.StringUtil;

public class MoneyControlerServlet extends HttpServlet {

	
	private static final long serialVersionUID = 3197135760468791988L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String typeString = StringUtil.nullToEmpty(request.getParameter("type"));
		
		MoneyService moneyService = new MoneyService();
		String data = "";
		
		switch (typeString) {
		case "getCustomer":
			data = moneyService.getCustomerList(request, response);
			break;
		case "applyForm":
			data = moneyService.applyForm(request, response);
			break;
		case "applyList":
			data = moneyService.getApplyList(request, response);
			break;
		case "cancelForm":
			data = moneyService.cancelApply(request, response);
			break;
		default:
			break;
		
		}
		
		
		OutputStream osTxt = response.getOutputStream();
		osTxt.write(data.getBytes("utf-8"));
		osTxt.flush();
		
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
