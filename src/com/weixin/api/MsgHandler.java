package com.weixin.api;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.weixin.R.MsgType;
import com.weixin.R.R;
import com.weixin.bean.EventMsgBean;
import com.weixin.bean.TxtMsgBean;


public class MsgHandler {

	
	private String result;
	
	public TxtMsgBean txtBean;
	public EventMsgBean eventBean;
	
	private String msgType;
	
	public MsgHandler(){}
	
	
	public String getMsgContent(String msg,	Map<String, String> map) throws Exception{
				
		WXBizMsgCrypt pc = new WXBizMsgCrypt(R.Token, R.EncodingAESKey, R.AppID);
		String msgSignature = map.get("msg_signature");
		String nonce = map.get("nonce");
		String timeStamp = map.get("timestamp");
		this.result = pc.decryptMsg(msgSignature, timeStamp, nonce, msg);
		System.out.println(": " + result);
		return result;
	}
	
	
	public String encMsgContent(String replyMsg, Map<String, String> map) throws Exception{
		
		WXBizMsgCrypt pc = new WXBizMsgCrypt(R.Token, R.EncodingAESKey, R.AppID);
		String nonce = map.get("nonce");
		String timeStamp = map.get("timestamp");
		String mingwen = pc.encryptMsg(replyMsg, timeStamp, nonce);
		return mingwen;
	}
	
	public void SendMsg(String str,HttpServletResponse response) {
		try {  
            OutputStream os = response.getOutputStream();  
            os.write(str.getBytes("UTF-8"));  
            os.flush();  
            os.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		
		
	}
	
	
	
	
	
	
	private void makeTxtBean(Map<String, String> map){
		txtBean = new TxtMsgBean();
		txtBean.setMsgId(map.get("MsgId"));
		txtBean.setFromUserName(map.get("FromUserName"));
		txtBean.setCreateTime(map.get("CreateTime"));
		txtBean.setContent(map.get("Content"));
		txtBean.setToUserName(map.get("ToUserName"));
		txtBean.setMsgType(map.get("MsgType"));
	}
	
	private void makeEventBean(Map<String, String> map){
		eventBean = new EventMsgBean();
		eventBean.setToUserName(map.get("ToUserName"));
		eventBean.setFromUserName(map.get("FromUserName"));
		eventBean.setCreateTime(map.get("CreateTime"));
		eventBean.setMsgType(map.get("MsgType"));
		eventBean.setEvent(map.get("Event"));
		eventBean.setEventKey(map.get("EventKey"));
	}
	
	
	public void createBean(){
		Map<String, String> map = com.weixin.util.XmlUtil.parserXMLToMap(result);
		msgType = map.get("MsgType");
		
		switch (MsgType.getMsgType(msgType)) {
		case text:
			makeTxtBean(map);
			break;
		case event:
			makeEventBean(map);
			break;
		default:
			break;
		}
		
		
	}


	public String getMsgType() {
		return msgType;
	}


	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	
	
	
	
}
