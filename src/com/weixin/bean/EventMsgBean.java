package com.weixin.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.weixin.mesModel.HttpRequest;
import com.weixin.util.StringUtil;

public class EventMsgBean {
	
	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	private String Event;
	private String EventKey;
	
	
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	
	public String makeSendContent(String title, String content){
		if("ÂëÍ·¹«¸æ".equals(title)){
			return makeTerminialMes(title,content);
		}
		
		
		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append("<xml>");
		sbBuffer.append("<ToUserName><![CDATA["+this.FromUserName+"]]></ToUserName>");
		sbBuffer.append("<FromUserName><![CDATA["+this.ToUserName+"]]></FromUserName>");
		sbBuffer.append("<CreateTime>"+new Date().getTime()+"</CreateTime>");
//		sbBuffer.append("<MsgType><![CDATA[text]]></MsgType>");
//		sbBuffer.append("<Content><![CDATA["+this.sendContent+"]]></Content>");
//		sbBuffer.append("</xml>");
		sbBuffer.append("<MsgType><![CDATA[news]]></MsgType>");
		sbBuffer.append("<ArticleCount>1</ArticleCount>");
		sbBuffer.append("<Articles>");
		sbBuffer.append("<item>");
		sbBuffer.append("<Title><![CDATA["+title+"]]></Title>"); 
		sbBuffer.append("<Description><![CDATA["+content+"]]></Description>");
		sbBuffer.append("<PicUrl></PicUrl>");
		sbBuffer.append("<Url><![CDATA[]]></Url>");
		sbBuffer.append("</item>");
		
		sbBuffer.append("</Articles>");
		sbBuffer.append("</xml>");
		
		
		return sbBuffer.toString();
	}
	
	
	public String makeTerminialMes(String title, String content){
		System.out.println("makeTerminialMes");
		
		int count = 0 ;
		String temp = "";
		StringBuffer sbBuffer = new StringBuffer();
		StringBuffer appendBuffer = new StringBuffer();
		
		List<Map<String, String>> list = HttpRequest.getHisMes();
		
		
		sbBuffer.append("<xml>");
		sbBuffer.append("<ToUserName><![CDATA["+this.FromUserName+"]]></ToUserName>");
		sbBuffer.append("<FromUserName><![CDATA["+this.ToUserName+"]]></FromUserName>");
		sbBuffer.append("<CreateTime>"+new Date().getTime()+"</CreateTime>");
		sbBuffer.append("<MsgType><![CDATA[news]]></MsgType>");
		
		
		
		
//		sbBuffer.append("<item>");
//		sbBuffer.append("<Title><![CDATA["+title+"]]></Title>"); 
//		sbBuffer.append("<Description><![CDATA["+content+"]]></Description>");
//		sbBuffer.append("<PicUrl></PicUrl>");
//		sbBuffer.append("<Url><![CDATA[]]></Url>");
//		sbBuffer.append("</item>");
		
		
		for(Map<String, String> map : list){
			
			temp = StringUtil.nullToEmpty(map.get("content_source_url"));
			
			System.out.println("".equals(temp)+"temp");
			
//			if(!"".equals(temp)){
			if(true){
//				appendBuffer.append("<Title><![CDATA["++"]]></Title>"); 
//				sbBuffer.append("<Description><![CDATA["+content+"]]></Description>");
//				sbBuffer.append("<PicUrl></PicUrl>");
//				sbBuffer.append("<Url><![CDATA[]]></Url>");
//				sbBuffer.append("</item>");
				appendBuffer.append("<item>");
				appendBuffer.append("<Title><![CDATA["+map.get("title")+"]]></Title>");
				appendBuffer.append("<Description><![CDATA["+map.get("digest")+"]]></Description>");
				appendBuffer.append("<PicUrl><![CDATA["+map.get("thumb_url")+"]]></PicUrl>");
				appendBuffer.append("<Url><![CDATA["+map.get("myHttpUrl")+"]]></Url>");
				appendBuffer.append("</item>");
				
				count++;
			}
			
		}
		
		sbBuffer.append("<ArticleCount>"+count+"</ArticleCount>");
		sbBuffer.append("<Articles>");
		sbBuffer.append(appendBuffer.toString());
		sbBuffer.append("</Articles>");
		sbBuffer.append("</xml>");
		
		
		
		System.out.println(sbBuffer.toString());
		
		if(count==0){
			sbBuffer.setLength(0);
			sbBuffer.append("<xml>");
			sbBuffer.append("<ToUserName><![CDATA["+this.FromUserName+"]]></ToUserName>");
			sbBuffer.append("<FromUserName><![CDATA["+this.ToUserName+"]]></FromUserName>");
			sbBuffer.append("<CreateTime>"+new Date().getTime()+"</CreateTime>");
//			sbBuffer.append("<MsgType><![CDATA[text]]></MsgType>");
//			sbBuffer.append("<Content><![CDATA["+this.sendContent+"]]></Content>");
//			sbBuffer.append("</xml>");
			sbBuffer.append("<MsgType><![CDATA[news]]></MsgType>");
			sbBuffer.append("<ArticleCount>1</ArticleCount>");
			sbBuffer.append("<Articles>");
			sbBuffer.append("<item>");
			sbBuffer.append("<Title><![CDATA["+title+"]]></Title>"); 
			sbBuffer.append("<Description><![CDATA["+content+"]]></Description>");
			sbBuffer.append("<PicUrl></PicUrl>");
			sbBuffer.append("<Url><![CDATA[]]></Url>");
			sbBuffer.append("</item>");
			
			sbBuffer.append("</Articles>");
			sbBuffer.append("</xml>");
			
		}
		
		
		
		return sbBuffer.toString();
	}
	
	
}
