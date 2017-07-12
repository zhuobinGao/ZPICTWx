package com.weixin.test;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.weixin.R.R;

public class SecurityServlet extends HttpServlet {

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
		System.out.println("111");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
		
		System.out.println("======================");
		WXBizMsgCrypt pc = null;
		try {
			pc = new WXBizMsgCrypt(R.Token, R.EncodingAESKey, R.AppID);
			String msgSignature = "e7c879d1133a05fbc1735008859899826f1a2da9";
			String nonce = "253330903";
			String fromXML = "<xml>    <ToUserName><![CDATA[gh_9b34400726c4]]></ToUserName>    <Encrypt><![CDATA[KLgjGGcBbIaf/PxPoMVNyf5UFrULiCGBZHlq+/Wh94J48xXXKIATu3dC5c3F5do3Oqwm6qjfQuYcKp/V0DW1u61N3vH98OXYCoud92E3qlqe2+taU8viGZKu3mpAeSoaxdOeDI0lBE5neVRu9W85t9KMEjaq/5Sd83XJrZHKBCdIPS3LNMP88hJBYtu+biO9/+jFHO6gUtWr7bD2R+PgYJ24xhxNcXc+//JRUATDyDJnt3iHKj9uCgvV6xS34SGaKr8YWgc6bIxXSiz6Up2QXQ6vaeWj/ohtkTd4wo0EidPdiyaSXHM8xr0vMfzhDKuxsVPmKK8MJJeEePcWYP5Csh4/n0A9mTzrxODxWxh8iODUMWs62cVxjFKAuuThXqraZkhYhR2jE562P/F/mkvgnpJKkYUG9cKMsgPuFbPcHto=]]></Encrypt></xml>";
			String result2 = pc.decryptMsg(msgSignature, "1461840101", nonce, fromXML);
			System.out.println(": " + result2);
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
