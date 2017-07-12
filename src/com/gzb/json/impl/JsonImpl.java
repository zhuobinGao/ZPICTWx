package com.gzb.json.impl;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface JsonImpl {

	
	
	public String encodeResultSet(ResultSet rs) throws UnsupportedEncodingException, SQLException;
	
	
	
	
}
