package com.gzb.json.impl;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonForCountResult implements JsonImpl {

	@Override
	public String encodeResultSet(ResultSet rs) throws UnsupportedEncodingException, SQLException {
		String result = "0";
		if(rs.next()){
			result = rs.getString(1);
			System.out.println(rs.getString(1)+"|"+result);
		}
		
		
		return result;
	}


}
