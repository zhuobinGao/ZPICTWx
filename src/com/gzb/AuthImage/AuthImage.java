package com.gzb.AuthImage;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 

/**

 * <p><b>AuthImage Description:</b> (��֤��)</p>

 * <b>DATE:</b> 2016��6��2�� ����3:53:12

 */

public class AuthImage extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {  

    static final long serialVersionUID = 1L;  

   

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  

        response.setHeader("Pragma", "No-cache");  

        response.setHeader("Cache-Control", "no-cache");  

        response.setDateHeader("Expires", 0);  

        response.setContentType("image/jpeg");  

           
        	
        //��������ִ�  

        String verifyCode = request.getParameter("date") ;//VerifyCodeUtils.generateVerifyCode(4);  

        //����Ựsession  

        HttpSession session = request.getSession(true);  

        //ɾ����ǰ��

        session.removeAttribute("verCode");

        session.setAttribute("verCode", verifyCode.toLowerCase());  

        
        //����ͼƬ  

        int w = 130, h = 53;  

        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);  

   

    }  

} 