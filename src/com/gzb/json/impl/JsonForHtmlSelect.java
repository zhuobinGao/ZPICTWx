package com.gzb.json.impl;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonForHtmlSelect implements JsonImpl {

	@Override
	public String encodeResultSet(ResultSet rs)
			throws UnsupportedEncodingException, SQLException {
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("<option value='-1'></option>");
		while(rs.next()){
			sBuffer.append("<option value=");
			sBuffer.append("'").append(rs.getString(1)).append("' >");
			sBuffer.append(rs.getString(2)).append("</option>");
		}
		return sBuffer.toString();
	}

}
