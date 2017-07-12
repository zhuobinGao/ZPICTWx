package com.weixin.util;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonUtil {

	
	public static String tableDataFromRusltSet(ResultSet rs) throws SQLException, UnsupportedEncodingException{
		
		
		StringBuffer sBuffer = new StringBuffer();
		
		java.sql.ResultSetMetaData rsmd = rs.getMetaData();
		
		System.out.println(rsmd.getColumnCount()+"111");
		
		sBuffer.append("{ \"data\": [    ");
		
		
		while(rs.next()){
			sBuffer.append(" [ ");
			sBuffer.append("\"\",");
			for(int i=1; i<=rsmd.getColumnCount(); i++){
				sBuffer.append("\""+jsonTrim(rs.getString(i))+"\",");
			}
			sBuffer.setLength(sBuffer.length()-1);
			sBuffer.append(" ],");
		}
		sBuffer.setLength(sBuffer.length()-1);
		sBuffer.append(" ]}");
		
//		System.out.println(sBuffer.toString());
		
		return sBuffer.toString();
	}
	
	
	public static String autoCompleteFromResultSet(ResultSet rs) throws SQLException, UnsupportedEncodingException{
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(" [    ");
		while(rs.next()){
			sBuffer.append("{ ");
			sBuffer.append("\"label\": \""+jsonTrim(rs.getString(1))+"\", ");
			sBuffer.append("\"value\": \""+jsonTrim(rs.getString(2))+"\" ");
			sBuffer.append("},");
//			sBuffer.append("\""+jsonTrim(rs.getString(1))+"\",");
		}
		sBuffer.setLength(sBuffer.length()-1);
		sBuffer.append(" ]");
		return sBuffer.toString();
	}

	
	
	
	
	
	
	
	
	
	
	public static String jsonTrim(String str){
		if(str==null){return "";}
		str = str.trim();
		str = str.replace("\t", "");
		
		return str;
	}
	
	
}
