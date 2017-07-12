package com.gzb.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gzb.api.dbutil.SQLServericeDBUtil;
import com.gzb.db_source.ConnectionSqlServerManager;
import com.weixin.mesModel.HttpRequest;
import com.weixin.util.StringUtil;
import com.weixin.util.TxtUtil;

public class PreAmountCheckAutoRun extends SQLServericeDBUtil implements Runnable{

	private static Logger logger = Logger.getLogger(PreAmountCheckAutoRun.class);
	@Override
	public void run() {
		
		while(true){
			new Thread(new Runnable() {
				@Override
				public void run() {
					List<String> list = updateSendFlag();
					sendMessage(list);
					resetSendFlag(list);
					
				}
			}).start();
			
			
			try {
				Thread.sleep(1000*60*10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	private List<String> updateSendFlag(){
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("SELECT mind_id FROM CUM_Customer_Mind M \n");
		sBuffer.append("LEFT JOIN CUM_Customer_Pre p ON P.CustomerId=M.CustomerId LEFT JOIN CUM_Customer C on M.CustomerId=C.CustomerId \n");
		sBuffer.append(" where mindMoney>=PreAmount and p.IsUsePreAmount='1' and isOpen='1' and ( isSend!='1' or isSend is null ) \n");
		List<String> list = getSearchResultList(sBuffer.toString(), null);
		return list;
	}
	
	private void sendMessage(List<String> list){
		List<PreAmountCheckBean> beanList = getSendList(list);
		SendCheckMes(beanList);
		updateFlag(list);
	}
	
	
	private void resetSendFlag(List<String> list){
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("update CUM_Customer_mind set isSend='0' where ( isSend='1' or isSend is null) \n");
		sBuffer.append("and mind_id in ( SELECT m.mind_id FROM CUM_Customer_Mind m \n");
		sBuffer.append(" LEFT JOIN CUM_Customer_Pre p ON P.CustomerId=M.CustomerId \n");
		sBuffer.append(" LEFT JOIN CUM_Customer C on M.CustomerId=C.CustomerId \n");
		sBuffer.append(" where mindMoney<PreAmount and p.IsUsePreAmount='1' ) \n");
		updateData(sBuffer.toString(), null);
	}
	
	private List<PreAmountCheckBean> getSendList(List<String> datalist){
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("SELECT C.NameCn, P.CustomerId, wxname, wxid, cast(round(mindMoney,2) as numeric(20,2)) money , P.IsUsePreAmount, cast(round(P.PreAmount,2) as numeric(20,2)),\n");
		sBuffer.append("    p.IsUseCredit, p.CreditAmount, p.ValidityDate, p.UsedCredit \n");
		sBuffer.append("    FROM CUM_Customer_Mind m\n");
		sBuffer.append("  LEFT JOIN CUM_Customer_Pre p ON P.CustomerId=M.CustomerId\n");
		sBuffer.append("  LEFT JOIN CUM_Customer C on M.CustomerId=C.CustomerId\n");
		sBuffer.append("  where mindMoney>=PreAmount and p.IsUsePreAmount='1' \n");
		sBuffer.append("  and isOpen='1' and ( isSend!='1' or IsSend is null) ");
		
		if(datalist.size()>0){
			sBuffer.append("  and m.mind_id in ( ");
			for(String dataStr : datalist){
				sBuffer.append("'").append(dataStr).append("',");
			}
			sBuffer.setLength(sBuffer.length()-1);
			sBuffer.append(" ) ");
		}
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		PreAmountCheckBean bean = null;
		List<PreAmountCheckBean> list = new ArrayList<PreAmountCheckBean>();
		
		try{
			conn = ConnectionSqlServerManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sBuffer.toString());
			
		
			
			rs = pstm.executeQuery();
			while(rs.next()){
				bean = new PreAmountCheckBean();
				bean.setFristData("你好,"+rs.getString(1));
				bean.setKeyword1(rs.getString(7));
				bean.setKeyword2(TxtUtil.getNowDate());
				bean.setUserid(rs.getString(4));
				bean.setUserName(rs.getString(3));
				bean.setRemark("帐户余额已低过预警线:["+rs.getString(5)+"]");
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
	    	
	    	logger.info(sendList.get(i));
	    	logger.info(result);
	    	
	    	if(!result.contains("errcode\":0")){
	    		logger.error(result);
	    		listInt.add(i);
	    	}
	    }
		
		
	}
	
	private void updateFlag(List<String> datalist){
		List<String> param = new ArrayList<String>();
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("update CUM_Customer_mind set sendCount=sendCount+1 ,isSend='1' ,lastSendDate=? where 1=1 and isopen='1' ");
		if(datalist.size()>0){
			sBuffer.append(" and mind_id in ( ");
			for(String dataStr : datalist){
				sBuffer.append("'").append(dataStr).append("',");
			}
			sBuffer.setLength(sBuffer.length()-1);
			sBuffer.append(" ) ");
			param.add(StringUtil.getNowDate("YY/MM/dd HH:mm"));
			updateData(sBuffer.toString(), param);
			
		}
		
		
		
	}
	

}
