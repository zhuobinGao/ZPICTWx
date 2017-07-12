package com.weixin.util;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlUtil {

	private static Document document;
	 
    public static void init() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /*
     * <xml>
    <ToUserName><![CDATA[gh_9b34400726c4]]></ToUserName>
    <Encrypt><![CDATA[Mi6bezBgr+zeFeJTEtFGOl2Bm5V1CRg68xIlXWFyYLAPFjLryflBNnco1Udkg0MHXXFZkJvoJ6n9RbW8iVkHTRrA6bHr6y+4jtz7y05lbYXyyCq2cBzJdHzkWIeFR8cAXgRMf3TNFOCswLg/LioEfVwZh5XZj63/mqR0SSbboLm5CjD2AVYh1V6k+a2NNNg0N2pXvCB7z0H0L0fWyTbtUL6oZsyGT8U8tR6nOWCFRMWUkPVyfGGP2srtcV/sEfeuQ4cqjIttGrCg/k4SiGJ0vRxZWOvOfPNujLNCRikwPCAqAdg9gLgXsXcElGOqJq4ciu3af8YTEA9GKimZjEFWwf3nu2ZFLu1mV+cRDpc+xwWxkvtMqhdHpjnLL0W8NAMSrXCxGZmTm0Rdga5QJvkwC75v8M01vjj+pcxxGFC0Mkg=]]></Encrypt>
</xml>
     * */
    
    public static void parserXml(String str) {
        try {
        	InputStream   in_nocode   =   new   ByteArrayInputStream(str.getBytes());   
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(in_nocode);
             
            NodeList employees = document.getChildNodes();
            for (int i = 0; i < employees.getLength(); i++) {
                Node employee = employees.item(i);
                System.out.println(employee.getNodeName()+":"+employee.getTextContent());
                NodeList employeeInfo = employee.getChildNodes();
                for (int j = 0; j < employeeInfo.getLength(); j++) {
                    Node node = employeeInfo.item(j);
                    System.out.println(node.getNodeName()+":"+node.getTextContent());
                    
                    NodeList employeeMeta = node.getChildNodes();
                    for (int k = 0; k < employeeMeta.getLength(); k++) {
                        System.out.println(employeeMeta.item(k).getNodeName() + ":" + employeeMeta.item(k).getTextContent());
                    }
                }
            }
            System.out.println("解析完毕");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static Map<String, String> parserXMLToMap(String str){
    	
    	 Map<String,String> map = new HashMap<String, String>();
    	 try {
         	 InputStream   in_nocode   =   new   ByteArrayInputStream(str.getBytes("utf-8"));   
         	 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
             DocumentBuilder db = dbf.newDocumentBuilder();
             Document document = db.parse(in_nocode);
             NodeList employees = document.getChildNodes();
             for (int i = 0; i < employees.getLength(); i++) {
                 Node employee = employees.item(i);
                
                 NodeList employeeInfo = employee.getChildNodes();
                 for (int j = 0; j < employeeInfo.getLength(); j++) {
                     Node node = employeeInfo.item(j);
                     map.put(node.getNodeName(), node.getTextContent());
                 }
             }
            
         } catch (Exception e) {
             e.printStackTrace();
         } 
    	return map;
    }
    
    
    public static void main(String[] args){
    	
    	String str = "<xml><ToUserName><![CDATA[gh_9b34400726c4]]></ToUserName>"
						+"<FromUserName><![CDATA[o2VBls29DZuiOqGnQsFs63CBJyKc]]></FromUserName>"
						+"<CreateTime>1461897141</CreateTime>"
						+"<MsgType><![CDATA[text]]></MsgType>"
						+"<Content><![CDATA[有意义]]></Content>"
						+"<MsgId>6278800411116942813</MsgId>"
						+"</xml>";
    	
    	
    	
    	System.out.println(XmlUtil.parserXMLToMap(str));
    }
    
}
