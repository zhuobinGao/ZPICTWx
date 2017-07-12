package com.weixin.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;





public class StringUtil {
	
	public static Map<String, String> sqlMap;

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String nullToEmpty(String str){
		if(str==null)return "";
		return str.trim();
	}
	
	public static String trimJson(String str){
		if(str==null)return "";
		str = str.replace("'", "");
		str = str.replace("\"", "");
		str = str.replace("{", "");
		str = str.replace("}", "");
		str = str.replace("[", "");
		str = str.replace("]", "");
		str = str.replace("*", "");
		return str.trim();
	}
	
	
	public static String getNowYMD(){
		return sdf.format(new Date());
	}
	
	public static String getNowDate(String sdfString){
		return new SimpleDateFormat(sdfString).format(new Date());
	}
	
	
	
	
	public static void getSqlMap(){
		SAXBuilder builder = new SAXBuilder(); 
		Document doc = null;
		sqlMap = new HashMap<String, String>();
		try {
			doc = builder.build(StringUtil.class.getResourceAsStream("BillingSqlContent.xml"));
			Element foo = doc.getRootElement(); 
			List<Element> allChildren = foo.getChildren(); 
			String id = "", value="";
			for(Element e : allChildren){
				id = e.getAttributeValue("id");
				value = e.getValue();
				sqlMap.put(id, value);
			}
			
//			Connection conn = ConnectionSqlServerManager.getInstance().getConnection();
//			System.out.println(conn);
//			conn.close();
//			
//			
//			conn = ConnectionManager.getInstance().getConnection();
//			System.out.println(conn);
//			conn.close();
			
			
			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static String getSql(String sqlid){
		if(sqlMap==null)getSqlMap();
		return StringUtil.nullToEmpty(sqlMap.get(sqlid));
	}
	
}
