package com.gzb.json.impl;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.weixin.util.StringUtil;

public class JsonForJsonArray implements JsonImpl {

	@Override
	public String encodeResultSet(ResultSet rs) throws UnsupportedEncodingException, SQLException {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("{ \"data\": [");
		int index = 0;
		ResultSetMetaData sdmd = rs.getMetaData();
		int count = sdmd.getColumnCount();
		while(rs.next()){
			index++;
			sBuffer.append("[");
			for(int i=1;i<=count;i++){
				sBuffer.append("\""+ StringUtil.trimJson(rs.getString(i)) +"\",");
			}
			sBuffer.setLength(sBuffer.length()-1);
			sBuffer.append("],");
		}
		if(index>0){
			sBuffer.setLength(sBuffer.length()-1);
		}
		
		sBuffer.append("]}");	
		return sBuffer.toString();
	}

}
