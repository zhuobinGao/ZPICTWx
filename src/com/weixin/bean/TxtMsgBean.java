package com.weixin.bean;

import java.util.Date;

public class TxtMsgBean {

	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	private String Content;
	private String MsgId;
	
	private String sendContent;
	
	
	public String getSendContent() {
		return sendContent;
	}
	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}
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
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	
	
	/*
	 <xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[fromUser]]></FromUserName>
<CreateTime>12345678</CreateTime>
<MsgType><![CDATA[text]]></MsgType>
<Content><![CDATA[ÄãºÃ]]></Content>

	 */
	
	public String makeSendContent(String title, String content){
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
//		sbBuffer.append("<PicUrl></PicUrl>");
//		sbBuffer.append("<Url><![CDATA[]]></Url>");
		sbBuffer.append("</item>");
		
		sbBuffer.append("</Articles>");
		sbBuffer.append("</xml>");
		
		
		return sbBuffer.toString();
	}
	

}
