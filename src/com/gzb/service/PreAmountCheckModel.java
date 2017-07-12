package com.gzb.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gzb.db_source.ConnectionSqlServerManager;
import com.weixin.mesModel.HttpRequest;
import com.weixin.util.TxtUtil;

public class PreAmountCheckModel implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<PreAmountCheckBean> checkList = null;
		List<PreAmountCheckBean> sendList = null;
		
		if(true){
			
			checkList = getSendList();
			
			System.out.println("checkList");
			
			sendList = checkMessage(checkList);
			
			System.out.println("sendList");
			
			SendCheckMes(sendList);
			
			System.out.println("over");
			
			try {
				Thread.sleep(1000*60*10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}

	
	private List<PreAmountCheckBean> getSendList(){
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("SELECT C.NameCn, P.CustomerId, wxUser, wxUserId, cast(round(mindAmount,2) as numeric(20,2)) , P.IsUsePreAmount, cast(round(P.PreAmount,2) as numeric(20,2)),\n");
		sBuffer.append("  p.IsUseCredit, p.CreditAmount, p.ValidityDate, p.UsedCredit \n");
		sBuffer.append("  FROM CUM_Customer_Mind M\n");
		sBuffer.append("  LEFT JOIN CUM_Customer_Pre p ON P.CustomerId=M.CustomerId\n");
		sBuffer.append("  LEFT JOIN CUM_Customer C on M.CustomerId=C.CustomerId\n");
		sBuffer.append("  where mindAmount>=PreAmount and p.IsUsePreAmount='1' \n");
		
		sBuffer.append("  and isOpen='1' and isSend='0' ");
		
		System.out.println(sBuffer.toString());
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		PreAmountCheckBean bean = null;
		List<PreAmountCheckBean> list = new ArrayList<PreAmountCheckBean>();
		
		try{
			conn = ConnectionSqlServerManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sBuffer.toString());
			
			pstm.setString(1, TxtUtil.getNowDateYMD());
			
			rs = pstm.executeQuery();
			while(rs.next()){
				bean = new PreAmountCheckBean();
				bean.setFristData("你好,"+rs.getString(1));
				bean.setKeyword1(rs.getString(7));
				bean.setKeyword2(TxtUtil.getNowDate());
				bean.setUserid(rs.getString(4));
				bean.setUserName(rs.getString(3));
				bean.setRemark("帐户余额已过预警线:["+rs.getString(5)+"]");
				bean.setCustomID(rs.getString(2));
				list.add(bean);
			}
		}catch(Exception e){
			
		}finally{
			if(rs!=null){
				try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			}
			if(pstm!=null){
				try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			}
			if(conn!=null){
				try {conn.close();} catch (SQLException e) {e.printStackTrace();}
			}
		}
		return list;
	}
	
	private List<PreAmountCheckBean> checkMessage(List<PreAmountCheckBean> list){
		return list;
	}
	
	
	private void SendCheckMes(List<PreAmountCheckBean> list){
		
		List<String> sendList = new ArrayList<String>();
		StringBuffer mes = new StringBuffer();
		for(PreAmountCheckBean bean : list){
		
			mes.setLength(0);
			
			mes.append(" {");
			mes.append(" \"touser\":\""+bean.getUserid()+"\",");
			mes.append(" \"template_id\":\"MOZHdcVkQBM9Ocx3jwWNmAGq9fWQtjyTDvSu9a-LyqI\",");
			mes.append(" \"url\":\"\",");
		    
			mes.append("\"data\":{ ");
			mes.append("\"first\":{\"value\":\"").append(bean.getFristData()).append(" \",\"color\":\"#173177\"},");
			mes.append("\"keyword1\":{\"value\":\"").append(bean.getKeyword1()+"\",\"color\":\"#173177\"},");
			mes.append("\"keyword2\":{\"value\":\"").append(bean.getKeyword2()).append(" \",\"color\":\"#173177\"},");
			mes.append("\"remark\":{\"value\":\"").append(bean.getRemark()).append("\",\"color\":\"#173177\"}");
			mes.append(" } ");
			
			mes.append("   }");
			
			sendList.add(mes.toString());
		}
		
		
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
		String result = "";
	    List<Integer> listInt = new ArrayList<Integer>();
		for(int i=0; i<sendList.size(); i++){
	    	result = HttpRequest.sendPost( url+HttpRequest.getAccess_token(),  sendList.get(i) );
	    	System.out.println(i+":\t"+result);
	    	if(result.contains("errcode\":0")){
	    		listInt.add(i);
	    	}
	    }
		
		
		String update = "update CUM_Customer_Mind  set sendDate=? where CustomerId=? and wxUserId=? ";
		String ymd = TxtUtil.getNowDateYMD();
		
		for(int index : listInt){
			List<String> tempList = new ArrayList<String>();
			tempList.add(ymd);
			tempList.add(list.get(index).getCustomID());
			tempList.add(list.get(index).getUserid());
			
			update(update,tempList);
			
		}
	}
	
	
	
	
	
	public String update(String sql, List<String> params){
		Connection conn  = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String result = "do not thing";
		
		try{
			conn = ConnectionSqlServerManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(sql);
			for(int i=0; i<params.size(); i++){
				
				pstm.setString(i+1, params.get(i));
			}
			int count = pstm.executeUpdate();
			if(count>=1){
				result = "OK";
			}
			conn.commit();
		}catch(Exception e){
			result = e.toString();
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return result;
	}
	
	
	
	
	
	
	
	public static void main(String[] args){
		
		new Thread(new PreAmountCheckModel()).start();
		
		
	}
	
	
	
	
	
}
