package com.weixin.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weixin.R.MsgType;
import com.weixin.bean.EventMsgBean;
import com.weixin.bean.TxtMsgBean;
import com.weixin.bean.TxtReturnBean;
import com.weixin.test.Test;
import com.weixin.util.DecriptTest;
import com.weixin.util.StringUtil;
import com.weixin.util.TxtUtil;

public class APIService extends HttpServlet {


	private static final long serialVersionUID = 4437463004393990518L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String echostr="";
		
		echostr = StringUtil.nullToEmpty(request.getParameter("echostr"));
		
		
		Map<String,String> map = checkVaild(request);
		
		if(!"true".equals(map.get("vaild"))){
			return;
		}
		System.out.println("OK");
		out.print(echostr);
		out.flush();
//		out.close();
		
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		
		String signature="",echostr="",timestamp="",nonce="";
		map.put("vaild", "false");
		
		signature = StringUtil.nullToEmpty(request.getParameter("signature"));
		map.put("signature", signature);
		
		map.put("msg_signature", StringUtil.nullToEmpty(request.getParameter("msg_signature")));
		
		timestamp = StringUtil.nullToEmpty(request.getParameter("timestamp"));
		map.put("timestamp", timestamp);
		
		nonce = StringUtil.nullToEmpty(request.getParameter("nonce"));
		map.put("nonce", nonce);
		
		echostr = StringUtil.nullToEmpty(request.getParameter("echostr"));
		map.put("echostr", echostr);
		
		System.out.println("xxxxx:"+map);
		
		String msg = acceptMsg(request);
		System.out.println("====>msg:\n"+msg);
		String sendMsg = "";String replyMsg = "";
		TxtMsgBean bean = null;
		TxtReturnBean returnBean = null;
		EventMsgBean evetnBean = null;
		try {
			MsgHandler handler = new MsgHandler();
			handler.getMsgContent(msg, map);
			handler.createBean();
			
			switch (MsgType.getMsgType(handler.getMsgType())) {
			case text:
				sendMsg = "¿ª·¢ÖÐ...";
				bean = handler.txtBean;
				returnBean = new TxtMsgHandler(bean).getTxtReturnBean();
				bean.getContent();
				bean.setSendContent(sendMsg);
				replyMsg = handler.encMsgContent(bean.makeSendContent(returnBean.getTitle(),returnBean.getContent()), map);
				//handler.SendMsg(replyMsg, response);
				//out.print(replyMsg);
				OutputStream osTxt = response.getOutputStream();
				osTxt.write(replyMsg.getBytes("utf-8"));
				break;

			case event:
				evetnBean = handler.eventBean;
				returnBean = new EventMsgHandler(evetnBean).getTxtReturnBean();
				
				replyMsg = handler.encMsgContent(evetnBean.makeSendContent(returnBean.getTitle(),returnBean.getContent()), map);
			
				OutputStream osEvent = response.getOutputStream();
				osEvent.write(replyMsg.getBytes("utf-8"));
				break;
			case other:
				break;
			default:
				break;
			}
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
	private Map<String, String> checkVaild(HttpServletRequest request){
		
		Map<String, String> map = new HashMap<String, String>();
		
		String signature="",echostr="",timestamp="",nonce="";
		
		ArrayList<String> list = new ArrayList<String>();
		
		map.put("vaild", "false");
		
		signature = StringUtil.nullToEmpty(request.getParameter("signature"));
		map.put("signature", signature);
		
		map.put("msg_signature", StringUtil.nullToEmpty(request.getParameter("msg_signature")));
		
		timestamp = StringUtil.nullToEmpty(request.getParameter("timestamp"));
		map.put("timestamp", timestamp);
		
		nonce = StringUtil.nullToEmpty(request.getParameter("nonce"));
		map.put("nonce", nonce);
		
		echostr = StringUtil.nullToEmpty(request.getParameter("echostr"));
		map.put("echostr", echostr);
		
		list.add(DecriptTest.Token);
		list.add(timestamp);
		list.add(nonce);
		
		StringBuffer sbBuffer = new StringBuffer();
		Collections.sort(list);
		for(String str : list){
			sbBuffer.append(str);
		}

		if(signature.equals(DecriptTest.SHA1(sbBuffer.toString()))){
			map.put("vaild", "true");
		}
		
		return map;
	}
	
	
	private String acceptMsg(HttpServletRequest request) {
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer sBuffer = new StringBuffer();
		try {
			isr = new InputStreamReader(request.getInputStream());
			br = new BufferedReader(isr);
			String str = "";
			while((str=br.readLine())!=null){
				sBuffer.append(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(br!=null)try {br.close();} catch (IOException e) {e.printStackTrace();}
			if(isr!=null)try {br.close();} catch (IOException e) {e.printStackTrace();}
		}
		return sBuffer.toString();
	}
	
	
}
