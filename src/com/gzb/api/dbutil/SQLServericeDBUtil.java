package com.gzb.api.dbutil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gzb.db_source.ConnectionSqlServerManager;
import com.gzb.json.impl.JsonImpl;

public class SQLServericeDBUtil {

	
	public String getSearchResult(String sql,List<String> param, JsonImpl json){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String result = "";
		try{
			conn = ConnectionSqlServerManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql);
			if(param!=null){
				for(int i=0;i<param.size();i++){
					pstm.setString(i+1, param.get(i));
				}
			}
			rs = pstm.executeQuery();
			result = json.encodeResultSet(rs);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();};
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();};
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();};
		}
		return result;
	}
	
	
	public String updateData(String sql,List<String> param){
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			conn = ConnectionSqlServerManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(sql);
			if(param!=null){
				for(int i=0;i<param.size();i++){
					pstm.setString(i+1, param.get(i));
				}
			}
			int count = pstm.executeUpdate();
			conn.commit();
			if(count>0)return "OK";
			return "Fault";
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "Fault";
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();};
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();};
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();};
		}
		
		
	}
	
	
	public List<String> getSearchResultList(String sql,List<String> param){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try{
			conn = ConnectionSqlServerManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql);
			if(param!=null){
				for(int i=0;i<param.size();i++){
					pstm.setString(i+1, param.get(i));
				}
			}
			rs = pstm.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();};
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();};
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();};
		}
		return list;
	}
	
	
	
	
	
	
	
}
