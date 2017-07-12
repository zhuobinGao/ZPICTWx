package com.weixin.test;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.weixin.R.R;


public class Test {
	 public static String getIpAddr(HttpServletRequest request) {
	     String ip = request.getHeader("x-forwarded-for");
	     if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
	         ip = request.getHeader("Proxy-Client-IP");
	     }
	     if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
	         ip = request.getHeader("WL-Proxy-Client-IP");
	     }
	     if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
	         ip = request.getHeader("X-Real-IP");
	     }
	     if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
	         ip = request.getRemoteAddr();
	     }
	     return ip;
	 }
	 
	 public static String getContent(String FromUserName, String ToUserName){
		 StringBuffer sbBuffer = new StringBuffer();
			sbBuffer.append("<xml>");
			sbBuffer.append("<ToUserName><![CDATA["+ToUserName+"]]></ToUserName>");
			sbBuffer.append("<FromUserName><![CDATA["+FromUserName+"]]></FromUserName>");
			sbBuffer.append("<CreateTime>"+new Date().getTime()+"</CreateTime>");
//			sbBuffer.append("<MsgType><![CDATA[text]]></MsgType>");
//			sbBuffer.append("<Content><![CDATA["+this.sendContent+"]]></Content>");
//			sbBuffer.append("</xml>");
			sbBuffer.append("<MsgType><![CDATA[news]]></MsgType>");
			sbBuffer.append("<ArticleCount>1</ArticleCount>");
			sbBuffer.append("<Articles>");
			sbBuffer.append("<item>");
			sbBuffer.append("<Title><![CDATA[测试标题]]></Title>"); 
			sbBuffer.append("<Description><![CDATA[*模板测试文本\t制表符\n换行符]]></Description>");
			sbBuffer.append("<PicUrl></PicUrl>");
			sbBuffer.append("<Url><![CDATA[]]></Url>");
			sbBuffer.append("</item>");
			
			sbBuffer.append("</Articles>");
			sbBuffer.append("</xml>");
			
			
			return sbBuffer.toString();
	 }
	 
	 public static void main(String[] args) throws AesException, ParserConfigurationException, SAXException, IOException{
			
//			WXBizMsgCrypt pc = new WXBizMsgCrypt(R.Token, R.EncodingAESKey, R.AppID);
//			String msgSignature = "e7c879d1133a05fbc1735008859899826f1a2da9";
//			String nonce = "253330903";
//			String fromXML = "<xml>    <ToUserName><![CDATA[gh_9b34400726c4]]></ToUserName>    <Encrypt><![CDATA[KLgjGGcBbIaf/PxPoMVNyf5UFrULiCGBZHlq+/Wh94J48xXXKIATu3dC5c3F5do3Oqwm6qjfQuYcKp/V0DW1u61N3vH98OXYCoud92E3qlqe2+taU8viGZKu3mpAeSoaxdOeDI0lBE5neVRu9W85t9KMEjaq/5Sd83XJrZHKBCdIPS3LNMP88hJBYtu+biO9/+jFHO6gUtWr7bD2R+PgYJ24xhxNcXc+//JRUATDyDJnt3iHKj9uCgvV6xS34SGaKr8YWgc6bIxXSiz6Up2QXQ6vaeWj/ohtkTd4wo0EidPdiyaSXHM8xr0vMfzhDKuxsVPmKK8MJJeEePcWYP5Csh4/n0A9mTzrxODxWxh8iODUMWs62cVxjFKAuuThXqraZkhYhR2jE562P/F/mkvgnpJKkYUG9cKMsgPuFbPcHto=]]></Encrypt></xml>";
//			String result2 = pc.decryptMsg(msgSignature, "1461840101", nonce, fromXML);
//			System.out.println(": " + result2);
		 
		 String str = "6083350B-1F6C-4800-9726-EA6A101A1D59";
		 System.out.println(str.length());//28 36
		 
	 }
}
