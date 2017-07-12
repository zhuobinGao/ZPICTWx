package com.gzb.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.gzb.db_source.ConnectionManager;
import com.weixin.mesModel.HttpRequest;
import com.weixin.util.TxtUtil;

public class WorkitemCheckModel implements Runnable {

	
	
	

	@Override
	public void run() {

		
		List<String> list = new ArrayList<String>();
		
		list.add("o2VBls29DZuiOqGnQsFs63CBJyKc");
		list.add("o2VBlsxZvffVptTr3z7brCp8Iri0");
		list.add("o2VBls9V07LeNbEOb7iScc0ds2KY");
		list.add("o2VBlsxyh2XSpncM9nwK4Nw2-yas");
		list.add("o2VBlszqnMTpm6-etuFn0alwr06c");
		list.add("o2VBls_9a4ZcpwEGnG9c_BT2JdVE");
		list.add("o2VBls04QUsa-ceSBxRgYU0sLiT8");
		list.add("o2VBls97khsSo-mAxNyRj535qz2c");
//		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		String nowYMD = sdf2.format(new Date());
		
		Calendar calendar = Calendar.getInstance();
		calendar.getTime();
		
		
		StringBuffer temp = new StringBuffer();
		String data = "";
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
		
		System.out.println("list:\n"+list);
		System.out.println("===================================");
		
		while(true){
			
			nowYMD = getDBDay();
		    System.out.println("while nowYMD after getDBDay:\t"+nowYMD);
		    TxtUtil.logTemple("while nowYMD after getDBDay:\t"+nowYMD);
			data = getSendData(nowYMD);
			
			System.out.println("while: nowYMD:\t"+nowYMD);
			  TxtUtil.logTemple("while: nowYMD:\t"+nowYMD);
			if(!"NONE".equals(data)){
			    System.out.println(new Random().nextInt(1000));
				for(String str : list){
					System.out.println("inFor nowYMD:"+nowYMD);
					TxtUtil.logTemple("inFor nowYMD:"+nowYMD);
					
					temp = new StringBuffer();
					temp.append(" {");
					temp.append(" \"touser\":\""+str+"\",");
					temp.append(" \"template_id\":\"-0gIcSURXvNTdbSN2Tbad7NdGCl86n8i232Q1k4RS9w\",");
					temp.append(" \"url\":\"http://ictwx.zjport.com/WXService/page/page/asset/workitemCheck.jsp?ymd="+nowYMD+"\",");
					temp.append(data);
					temp.append(" }");
					
					System.out.println(temp);
					
					TxtUtil.logTemple("{"+temp.toString()+"}");
					HttpRequest.sendPost(url+HttpRequest.getAccess_token(), temp.toString());
					
					System.out.println(temp);
				
				}
			
			}
			
			
			
			try {
				Thread.sleep(1000*60*5);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
	}
	

	private String doSomething(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh24:mm:ss");
		
		String sql ="with temp1 as ( "
				+" select b.putdevice,b.puttime,b.putman,b.liftman,b.liftdevice,b.lifttime,b.containerid,c.containerno,b.lifttime lt, "
                +" lead (b.lifttime,1) over (order by b.liftdevice,b.lifttime),b.workitemno,lead (b.workitemno,1) over (order by b.liftdevice,b.lifttime), "
                +"  (lead (b.lifttime,1) over (order by b.liftdevice,b.lifttime)-b.lifttime)*24*60*60 mintime, "
                +" b.sourcepos,b.target "
				+" from wi_workitems b  "
				+" left join cm_containers c on c.containerid=b.containerid "
				+" where 1=1  "
				+" and b.queuetype='S' "
				+" and b.status='E' "
				+" ) "
				+" select t.putdevice,u.us_name,to_char(t.lt,'yyyymmdd hh24'),t.containerno,containerid,count(1),round(avg(mintime)) "
				+" from temp1  t "
				+" left join pl_user u on u.us_logid=t.putman "
				+" where 1=1  "
				+" and mintime<=60 and mintime>0 "
				
				+" and lt>=to_date('20161104','yyyymmdd') "
				+" and not exists ( "
				+"  select 1 from IT_CHECK_WORKITEM m where m.putdevice=t.putdevice  and m.us_name=u.us_name and m.puttiem=to_char(t.lt,'yyyymmdd hh24') and m.containerid=t.containerid "  
				+" ) "
				+" group by  t.putdevice,us_name,to_char(t.lt,'yyyymmdd hh24'),t.containerid,t.containerno "
				+" having count(1)>3 ";
				
		
		String sqlinsert = "  INSERT INTO it_check_workitem(PUTDEVICE,US_NAME,PUTTIEM,CONTAINERNO,CONTAINERID,COUT1,AVG1) ";
		
		String sqlorder = " order by 3 desc,t.putdevice,us_name";
		
		Connection conn = null;
		PreparedStatement pstm1=null,pstm2=null;
		ResultSet rs = null;
		
		StringBuffer first = new StringBuffer();
		StringBuffer remark = new StringBuffer();
		String result = "NONE";
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm1 = conn.prepareStatement(sql+sqlorder);
			pstm2 = conn.prepareStatement(sqlinsert+sql);

			rs = pstm1.executeQuery();

			while(rs.next()){
				result = "OK";
				first.append(rs.getString(1)).append(",");
				remark.append(rs.getString(7)+"秒,");
			}
			if("OK".equals(result)){
				first.setLength(first.length()-1);
				remark.setLength(remark.length()-1);
			}
			pstm2.executeUpdate();
			conn.commit();
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {if(rs!=null)rs.close();} catch (SQLException e) {e.printStackTrace();}
			try {if(pstm1!=null)pstm1.close();} catch (SQLException e) {e.printStackTrace();}
			try {if(pstm2!=null)pstm2.close();} catch (SQLException e) {e.printStackTrace();}
			try {if(conn!=null)conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		
		
		StringBuffer mes = new StringBuffer();
		mes.append("\"data\":{ ");
		mes.append("\"first\":{\"value\":\"").append(first.toString()).append("翻箱作业异常 \",\"color\":\"#173177\"},");
		mes.append("\"keyword1\":{\"value\":\"").append("CTOS检测进程 （测试）\",\"color\":\"#173177\"},");
		mes.append("\"keyword2\":{\"value\":\"").append(sdf.format(new Date())).append(" \",\"color\":\"#173177\"},");
		mes.append("\"keyword3\":{\"value\":\"").append("平均翻箱时间 "+remark+" \",\"color\":\"#173177\"},");
		mes.append("\"remark\":{\"value\":\"").append("\",\"color\":\"#173177\"}");
		mes.append(" } ");
		
		if(result.equals("OK")){
			return mes.toString();
		}
		
		return result;
	}
	
	
	
	
	public String getSql(String filename){
		StringBuffer sBuffer = new StringBuffer();
		URL url = WorkitemCheckModel.class.getResource(filename);
	
		File file = new File(url.getFile());
		
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
	
	
	
	public String getSendData(String nowYMD){
		
		
		Connection conn = null;
		PreparedStatement pstm1=null,pstm2=null;
		ResultSet rs = null;
		
		Set<String> set = new HashSet<String>();
		String sql1 = getSql("workitemCheck3.txt");
		
		
		String sql2 = "insert into IT_CHECK_WORKITEM(Puttiem,putdevice,Us_Name) "+sql1;
		String result = "NONE";
		
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			pstm1 = conn.prepareStatement(sql1);
			pstm2 = conn.prepareStatement(sql2);
			
			pstm1.setString(1, nowYMD);
			pstm2.setString(1, nowYMD);
			
			
			System.out.println("getSendData Method:"+nowYMD);
			
			rs = pstm1.executeQuery();
			while(rs.next()){
				result = "OK";
				set.add(rs.getString(2)+"("+rs.getString(3)+")");
				
			}
			
			pstm2.executeUpdate();
			
			
			conn.commit();
			
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			if(rs!=null){try {rs.close();} catch (SQLException e) {	e.printStackTrace();}}
			if(pstm1!=null){try {pstm1.close();} catch (SQLException e) {	e.printStackTrace();}}
			if(pstm2!=null){try {pstm2.close();} catch (SQLException e) {	e.printStackTrace();}}
			if(conn!=null){try {conn.close();} catch (SQLException e) {	e.printStackTrace();}}
		}
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddhhmm");
		
		
		StringBuffer mes = new StringBuffer();
		mes.append("\"data\":{ ");
		mes.append("\"first\":{\"value\":\"").append("翻箱作业异常 \",\"color\":\"#173177\"},");
		mes.append("\"keyword1\":{\"value\":\"").append(sdf2.format(new Date())+"\",\"color\":\"#173177\"},");
		mes.append("\"keyword2\":{\"value\":\"").append(sdf.format(new Date())).append(" \",\"color\":\"#173177\"},");
		mes.append("\"keyword3\":{\"value\":\"").append("异常机械列表 "+set.toString()+" \",\"color\":\"#173177\"},");
		mes.append("\"remark\":{\"value\":\"").append("\",\"color\":\"#173177\"}");
		mes.append(" } ");
		
		if(result.equals("OK")){
			System.out.println("send:"+mes);
			return mes.toString();
			
			
		}
		
		
		
		
		
		return result;
	}
	
	
	
	public String getDBDay(){
		Connection conn = null;
		PreparedStatement pstm1=null;
		ResultSet rs = null;
		
	
		String sql1 = "select to_char(sysdate,'yyyymmdd') from dual";
		
		
		
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		String nowYMD = sdf2.format(new Date());
		
		
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			pstm1 = conn.prepareStatement(sql1);
		
			
		
			rs = pstm1.executeQuery();
		    if(rs.next()){
		    	nowYMD = rs.getString(1);
		    }
		
			
			
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			if(rs!=null){try {rs.close();} catch (SQLException e) {	e.printStackTrace();}}
			if(pstm1!=null){try {pstm1.close();} catch (SQLException e) {	e.printStackTrace();}}
		
			if(conn!=null){try {conn.close();} catch (SQLException e) {	e.printStackTrace();}}
		}
		System.out.println("getDBData Method："+nowYMD);
		
		return nowYMD;
	}
	
	
	
	
	
	
	public static void main(String[] args){
//		new WorkitemCheckModel().run();
		
		new WorkitemCheckModel().run();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
