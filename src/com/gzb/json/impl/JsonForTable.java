package com.gzb.json.impl;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.weixin.util.JsonUtil;

public class JsonForTable implements JsonImpl {

	@Override
	public String encodeResultSet(ResultSet rs) throws UnsupportedEncodingException, SQLException{
		// TODO Auto-generated method stub
		return JsonUtil.tableDataFromRusltSet(rs);
	}

}
