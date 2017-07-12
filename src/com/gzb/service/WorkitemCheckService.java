package com.gzb.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.gzb.db_source.ConnectionManager;
import com.weixin.util.JsonUtil;

public class WorkitemCheckService {

	
	public String searchWorkItem(HttpServletRequest request){
		
		
		String nowYMD = request.getParameter("nowYMD");
		
		Connection conn  = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = getSql("workitemCheck.txt");
		System.out.println(sql);
		String result = "";
		try{
			conn = ConnectionManager.getInstance().getConnection();
			
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nowYMD);
			
			System.out.println("nowYMD");
			
			rs = pstm.executeQuery();
			result = JsonUtil.tableDataFromRusltSet(rs);
			
		}catch(Exception e){
			e.printStackTrace();
			result = e.toString();
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return result;
	}
	
	
	private String getSql(String filename){
		StringBuffer sBuffer = new StringBuffer();
		URL url = WorkitemCheckModel.class.getResource(filename);
	
		File file = new File(url.getFile());
		System.out.println(file.exists());
		try {
			FileReader fr = new FileReader(url.getFile().substring(1).replace("%20", " "));
			BufferedReader br = new BufferedReader(fr);
			String str = "";
			while((str=br.readLine())!=null){
				sBuffer.append(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sBuffer.toString();
	}
	
	
	
	
}
