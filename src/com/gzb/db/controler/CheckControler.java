package com.gzb.db.controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gzb.db_source.ConnectionManager;
import com.weixin.util.StringUtil;

public class CheckControler {

	
	public String get3DayBerthplan(){
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "select to_char(d.etb_time,'yyyy-mm-dd hh24:mi') etb_time, to_char(d.ETD_TIME,'yyyy-mm-dd hh24:mi') ETD_TIME,v.cvesselname,v.evesselname,v.velaliase,v.inboundvoy||'/'||v.outboundvoy, to_char(ets_time,'yyyy-mm-dd hh24:mi') ets_time from vs_berthplan v"
		+" left join vs_berthplandetail d on d.berthplanno=v.berthplanno where 1=1 and v.vesseltype='B' and "
		+"  ( (d.etb_time>sysdate and d.etb_time<= (sysdate+3)) or (d.ets_time>sysdate and d.ets_time<= (sysdate+3)) " 
		+ " or ( d.etb_time>(sysdate-5) and d.etb_time<= (sysdate+3) and v.atb_time is null and v.atd_time is  null )     )" ;
		
		StringBuffer sbBuffer = new StringBuffer();
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				sbBuffer.append("��"+rs.getString("velaliase")+" ("+rs.getString("cvesselname")+")\n");
				sbBuffer.append("\t\t��������:\t"+rs.getString("evesselname")+"\n");
				sbBuffer.append("\t\t�����ں���:\t"+rs.getString(6)+"\n");
				sbBuffer.append("\t\tԤ�ƿ���ʱ��:\t"+StringUtil.nullToEmpty(rs.getString("ets_time"))+"\n");
				sbBuffer.append("\t\tԤ�ƿ���ʱ��:\t"+rs.getString("etb_time")+"\n");
				sbBuffer.append("\t\tԤ���벴ʱ��:\t"+rs.getString("ETD_TIME")+"\n\n");
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		
		return sbBuffer.toString();
	}
	
	/*
	 * select v.velaliase,v.cvesselname,v.evesselname,v.inboundvoy||'/'||v.outboundvoy voyage,
to_char(v.atb_time,'yyyy-mm-dd hh24:mi') atbTime,decode(d.ATS_TIME,null,'δ����','װж����')
from vs_berthplan v 
left join vs_berthplandetail d on v.berthplanno=d.berthplanno
where v.atb_time is not null and v.atd_time is  null
	 * */
	
	
	public String getOnTerimalVessel(){
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select v.velaliase,v.cvesselname,v.evesselname,v.inboundvoy||'/'||v.outboundvoy voyage,\n");
		sql.append("to_char(v.atb_time,'yyyy-mm-dd hh24:mi') atbTime,(select decode(count(1),0,'δ��ҵ','������ҵ') from wi_workitems w where w.berthplandetailid=d.berthplandetailid ) cou \n");
		sql.append("from vs_berthplan v \n");
		sql.append("left join vs_berthplandetail d on v.berthplanno=d.berthplanno \n");
		sql.append("where v.atb_time is not null and v.atd_time is  null \n");
		
		StringBuffer sbBuffer = new StringBuffer();
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while(rs.next()){
				sbBuffer.append("��"+rs.getString("velaliase")+" ("+rs.getString("cvesselname")+")\n");
				sbBuffer.append("\t\t��������:\t"+rs.getString("evesselname")+"\n");
				sbBuffer.append("\t\t�����ں���:\t"+rs.getString("voyage")+"\n");
				sbBuffer.append("\t\t״̬:\t"+rs.getString("cou")+"\n");
				sbBuffer.append("\t\t����ʱ��:\t"+rs.getString("atbTime")+"\n\n");
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		
		return sbBuffer.toString();
	}
	
	/**
	 * 




	 * @return
	 */
	
	public String getTodayOutVessel(){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT C.CVESSELNAME ,B.INBOUNDVOY||'/'||B.OUTBOUNDVOY AS voyage,B.ATB_TIME ,B.Atd_Time,b.velaliase,b.evesselname \n");
		sql.append("FROM  vs_berthplan b,vs_vesselinfo c where  b.atb_time is not null and b.evesselname=c.evesselname  \n");
		sql.append("and  B.Atd_Time >= to_date(TO_CHAR(SYSDATE,'YYYYMMDD')|| ' 00:00','yyyymmdd hh24:mi') \n");
		sql.append("AND B.Atd_Time  <  to_date(TO_CHAR(SYSDATE+1,'YYYYMMDD')|| ' 00:00','yyyymmdd hh24:mi') \n");
		sql.append("order by nlssort(c.cvesselname, 'NLS_SORT=SCHINESE_PINYIN_M')\n");
		
		StringBuffer sbBuffer = new StringBuffer();
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while(rs.next()){
				sbBuffer.append("��"+rs.getString("velaliase")+" ("+rs.getString("CVESSELNAME")+")\n");
				sbBuffer.append("\t\t��������:\t"+rs.getString("evesselname")+"\n");
				sbBuffer.append("\t\t�����ں���:\t"+rs.getString("voyage")+"\n");
				
				sbBuffer.append("\t\t����ʱ��:\t"+rs.getString("ATB_TIME")+"\n");
				
				sbBuffer.append("\t\t�벴ʱ��:\t"+rs.getString("Atd_Time")+"\n\n");
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		
		return sbBuffer.toString();
		
	}
	
	
	
	public String getContainerOwner(){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct c.containerowner,g.cname  from cm_containers c \n");
		sql.append("left join pl_guests g on g.guestcode=c.containerowner where c.partflag='CY' and c.emptyfull='E' order by 1 \n");
		
		
		StringBuffer sbBuffer = new StringBuffer();
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			sbBuffer.append("����\t\t��������\n");
			while(rs.next()){
				sbBuffer.append(rs.getString("containerowner")+"\t\t"+rs.getString("cname")+"\n");
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		
		return sbBuffer.toString();
	}
	
	
	
	public String getEmptyByOnwer(String containerOwner){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
			
		StringBuffer sql = new StringBuffer();
		sql.append("select b.CONTAINEROWNER,B.CONTAINERSIZE||'/'||B.CONTAINERTYPE||'/'||B.CONTAINERHEIGHT as csize ,COUNT(1) AS COU \n");
		sql.append("from V_cm_containers b where 1=1  and b.emptyfull='E' AND B.INAIM NOT IN ('T','Z')  \n");
		sql.append("AND B.PARTFLAG='CY' AND B.LockList IS NULL AND B.ISDAMAGE='N' and b.CONTAINEROWNER=? \n");
		sql.append("GROUP BY B.CONTAINEROWNER,B.CONTAINERSIZE,B.CONTAINERTYPE,B.CONTAINERHEIGHT ORDER BY B.CONTAINEROWNER \n");
		
		StringBuffer sbBuffer = new StringBuffer();
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, StringUtil.nullToEmpty(containerOwner).toUpperCase());
			rs = pstm.executeQuery();
			sbBuffer.append("����---����---����(����20��ʾΪ��)\n");
			String str = "";
			int i = 0;
			while(rs.next()){
				str = rs.getInt("cou")>20?"��":""+rs.getInt("cou");
				sbBuffer.append(rs.getString("CONTAINEROWNER")+"---"+rs.getString("csize")+"---"+str+"\n");
				i++;
			}
			
			if (i<1){
				return "û�������Ϣ!";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		return sbBuffer.toString();
	}
	
	
	public String getDamage(String containerOwner){

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
			
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT B.CONTAINEROWNER ,B.CONTAINERSIZE||'/'||B.CONTAINERTYPE||'/'||B.CONTAINERHEIGHT AS csize,COUNT(1) AS cou  \n");
		sql.append("FROM V_CM_CONTAINERS B  \n");
		sql.append("WHERE B.ISDAMAGE='Y' AND B.PARTFLAG='CY'  and b.INAIM not in ('T','Z') AND B.EMPTYFULL='E' and B.CONTAINEROWNER=? \n");
		sql.append("GROUP BY B.CONTAINEROWNER,B.CONTAINERSIZE,B.CONTAINERTYPE,B.CONTAINERHEIGHT ORDER BY 1,2 \n");
		
		StringBuffer sbBuffer = new StringBuffer();
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, StringUtil.nullToEmpty(containerOwner).toUpperCase());
			rs = pstm.executeQuery();
			sbBuffer.append("����---����---����(����20��ʾΪ��)\n");
			String str = "";
			int i = 0;
			while(rs.next()){
				str = rs.getInt("cou")>20?"��":""+rs.getInt("cou");
				sbBuffer.append(rs.getString("CONTAINEROWNER")+"---"+rs.getString("csize")+"---"+str+"\n");
				i++;
			}
			
			if (i<1){
				return "û�������Ϣ!";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		return sbBuffer.toString();
	}
	
	
	public String getInGate(String truekNo){
		Connection conn = null;
		PreparedStatement pstm = null,pstm2=null;
		ResultSet rs = null,rs2 = null;
			
		StringBuffer sql = new StringBuffer();
		sql.append("select e.truckno,e.gatequeuestate,decode(e.gatequeuestate,'F','δ�Ŷ�','B','�Ŷ���','E','�ɴ�ӡ','P','�Ѵ�ӡ'),e.gatequeuefailreason \n");
		sql.append("from er_trucktimes e \n");
		sql.append("where e.truckstate not in ('O','C') and e.truckno like ? \n");
		
		
		
		StringBuffer sql2 = new StringBuffer();
		sql2.append("SELECT Z.* FROM ( select C.TRUCKNO,e.bl, D.CONTAINERNO,b.eirtype, substr(b.yardcell,0,3)||substr(b.yardcell,5,4)||substr(b.yardcell,10,1) ,B.EIRSTATE,D.CONTAINERSIZE||'/'||D.CONTAINERTYPE||'/'||D.EMPTYFULL as ty \n");
		sql2.append(" from er_eir b,er_trucktimes c,cm_containers d,er_eirmain e   where 1=1 and b.trucktimesid=c.trucktimesid \n");
		sql2.append("  and b.eirstate IN ('I','U') and b.eirtype='T'  AND NVL(B.CONTAINERID,B.PRESELECTCONTAINERID)=D.CONTAINERID \n");
		sql2.append("and b.eirmainid=e.eirmainid UNION ALL  \n");
		sql2.append(" select C.TRUCKNO,e.bl, D.CONTAINERNO,b.eirtype, substr(b.yardcell,0,3)||substr(b.yardcell,5,4)||substr(b.yardcell,10,1) ,B.EIRSTATE,D.CONTAINERSIZE||'/'||D.CONTAINERTYPE||'/'||D.EMPTYFULL as ty \n");
		sql2.append(" from er_eir b,er_trucktimes c,cm_containers d,er_eirmain e  where 1=1  \n");
		sql2.append(" and b.trucktimesid=c.trucktimesid  and b.eirstate IN ('I','U') and b.eirtype='S'  \n");
		sql2.append(" AND B.CONTAINERID=D.RECORDID  and b.eirmainid=e.eirmainid AND D.PARTFLAG='CY' \n");
		sql2.append(" ) Z where z.truckno like ? ORDER BY Z.TRUCKNO,Z.EIRTYPE ");
		
		
		StringBuffer sbBuffer = new StringBuffer();
		String temp ="";
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			pstm2 = conn.prepareStatement(sql2.toString());
			
			
			pstm.setString(1, "%"+StringUtil.nullToEmpty(truekNo).toUpperCase());
			pstm2.setString(1, "%"+StringUtil.nullToEmpty(truekNo).toUpperCase());
			
			rs = pstm.executeQuery();
			String str = "";
			int i = 0;
			if(rs.next()){
				sbBuffer.append("�����ƺ�:"+rs.getString(1)+",");
				sbBuffer.append("\t\t״̬:"+rs.getString(3)+"");
				temp = rs.getString(2);
				if("F".equals(temp)){
					sbBuffer.append("\t\t,ԭ��:"+rs.getString(4)+"\n");
				}else if("P".equals(temp)){
					sbBuffer.append("\n\n");
					rs2 = pstm2.executeQuery();
					sbBuffer.append("���\t---\t\t����\t---\t��λ\t---����\n");
					while(rs2.next()){
						str = "S".equals(rs2.getString(4))?"��":"��";
						sbBuffer.append(rs2.getString(3)+"\t\t---\t"+str+"\t---\t"+rs2.getString(5)+"\t---\t"+rs2.getString("ty")+"\n");
					}
				}
				i++;
			}
			
			if (i<1){
				return "û�������Ϣ!";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(rs2!=null)try {rs2.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm2!=null)try {pstm2.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		return sbBuffer.toString();
		
	}
	
	public String getTruckInfo(String truckNo){
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
			
		StringBuffer sql = new StringBuffer();
		sql.append("select b.truckno,c.cname,b.islock, \n");
		sql.append("(CASE WHEN B.ISLOCK='Y' THEN b.remark ELSE '' END) AS LOMEMO, \n");
		sql.append("(CASE WHEN B.ISLOCK='Y' THEN to_char(b.locktime,'yyyy-mm-dd hh24:mi') ELSE '' END) AS LOTI \n");
		sql.append(" from er_truck b,pl_guests c where b.truckcompanycode=c.guestcode and b.truckno like ?  \n");
		
		System.out.println(sql);
		
		StringBuffer sbBuffer = new StringBuffer();
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, "%"+StringUtil.nullToEmpty(truckNo).toUpperCase());
			rs = pstm.executeQuery();
		
			String str = "",isLock="";
			int i = 0;
			if(rs.next()){
				str = rs.getString("islock");
				sbBuffer.append("*�ϳ�����:\t\t"+rs.getString("truckno")+"\n");
				sbBuffer.append("*�ϳ���˾:\t\t"+rs.getString("cname")+"\n");
				isLock = "Y".equals(rs.getString("islock"))?"����":"������";
				sbBuffer.append("*�Ƿ�����:\t\t"+isLock+"\n");
				
				if("Y".equals(str)){
					sbBuffer.append("*����ʱ��:\t\t"+rs.getString("LOTI")+"\n");
					sbBuffer.append("*����ԭ��:\t\t"+StringUtil.nullToEmpty(rs.getString("LOMEMO"))+"\n");
				}
				
				
				i++;
			}
			
			if (i<1){
				return "û�������Ϣ!";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		return sbBuffer.toString();
	}
	
	
	public String getEirInfo(String code){
		
		
		
		if(!checkCodeVaild(code)){
			return "�����ʽ����ȷ!";
		}
		
		Connection conn = null;
		PreparedStatement pstm = null,pstm1=null;
		ResultSet rs = null,rs1=null;
			
		String sql1 = StringUtil.getSql("eir001");
		String sql2 = StringUtil.getSql("eir002");
		
		
		String type = "";
		String bl = "";
		String containerOwner = "";
		
		String temp[] = code.split("#");
		temp = temp[1].split("-");
		
		type = temp[0];
		bl = temp[1];
		containerOwner = temp[2].toUpperCase();
		
		
		
		StringBuffer sbBuffer = new StringBuffer();
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql1);
			pstm1 = conn.prepareStatement(sql2);
			
			pstm.setString(1, StringUtil.nullToEmpty(bl).toUpperCase());
			pstm.setString(2, StringUtil.nullToEmpty(type).toUpperCase());
			pstm.setString(3, StringUtil.nullToEmpty(containerOwner).toUpperCase());
			
			System.out.println(type+"-"+bl+"-"+containerOwner);
			
			rs = pstm.executeQuery();
		
			String str = "",taketype="";
			int i = 0;
			if(rs.next()){
				System.out.println("============taketypename===========");
				str = rs.getString(1);
				
				pstm1.setString(1, str);
				pstm1.setString(2, str);
				pstm1.setString(3, str);
				
				sbBuffer.append("*������:"+rs.getString("bl")+"\n");
				sbBuffer.append("*�쵥ʱ��:"+rs.getString("createtime")+"\n");
				sbBuffer.append("*�쵥����:"+rs.getString(2)+"\n\n");
				
				str = rs.getString(2);
				
				
				rs1 = pstm1.executeQuery();
				sbBuffer.append("���---\t������\t---�᷵����\n");
				while(rs1.next()){
					sbBuffer.append(rs1.getString("containerno")+"---\t");
					sbBuffer.append(rs1.getString("sth")+"\t---");
					taketype = rs1.getString("taketypename");
					
					if("E".equals(type) && rs1.getString("eid")==null){
						taketype = "����".equals(taketype)?"�տ���":taketype;
					}else if("E".equals(type) && rs1.getString("tid")==null){
						taketype = "����".equals(taketype)?"�����":taketype;
					}
					
//					taketype = "����".equals(taketype)?str:taketype;
					
					sbBuffer.append(taketype+"\n");
				}
				
				
				
				i++;
			}
			
			if (i<1){
				return "û�������Ϣ!";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(rs1!=null)try {rs1.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm1!=null)try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		return sbBuffer.toString();
	}
	
	
	
	public String getConDyn(String containerNO){
		Connection conn = null;
		PreparedStatement pstm = null,pstm1=null;
		ResultSet rs = null,rs1=null;
			
		String sql = StringUtil.getSql("con001");
		
		int i = 0,j=0,k=0,l=1;
		
		String inaim = "";
		StringBuffer sbBuffer = new StringBuffer();
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, containerNO);
			
			rs = pstm.executeQuery();
			
			while(rs.next()){
			
				inaim = rs.getString("inaim");
				if(i==0){
				
					sbBuffer.append("*���:"+containerNO+"\n");
					sbBuffer.append("  ����:"+rs.getString("sth")+"\t\t");
					sbBuffer.append("  ����:"+rs.getString("EMPTYFULL")+"\n");
					sbBuffer.append("  ����:"+rs.getString("TERMINALLOCKLIST")+"\n\n");
				}
				
				if("CY".equals(rs.getString("PARTFLAG"))){
					if(k==0){
						sbBuffer.append("*�ڳ���\n");
					}
					sbBuffer.append("\t����Ŀ��:"+rs.getString("inaim")+"\n");
					sbBuffer.append("\t��������:"+rs.getString("intool")+"\n");
					sbBuffer.append("\t����ʱ��:"+rs.getString("intime")+"\n");
					sbBuffer.append("\t�ѳ�λ��:"+rs.getString("yardcell")+"\n");
					if("����".equals(inaim)){
						sbBuffer.append("\t�ᵥ����:"+rs.getString("do")+"\n");
					}else if("����".equals(inaim)){
						sbBuffer.append("\t���յ���:"+rs.getString("bl")+"\n");
					}
					
					
					sbBuffer.append("\n");
					k++;
				}
				
				if("HS".equals(rs.getString("PARTFLAG"))){
					
						sbBuffer.append("*��ʷ��"+l+"\n");
					
					sbBuffer.append("\t����Ŀ��:\t\t"+rs.getString("inaim")+"\n");
					sbBuffer.append("\t��������:\t\t"+rs.getString("intool")+"\n");
					sbBuffer.append("\t����ʱ��:\t\t"+rs.getString("intime")+"\n");
					sbBuffer.append("\t��������:\t\t"+rs.getString("outtool")+"\n");
					sbBuffer.append("\t����ʱ��:\t\t"+rs.getString("outtime")+"\n");
					if("����".equals(inaim)){
						sbBuffer.append("\t�ᵥ����:\t\t"+rs.getString("do")+"\n");
					}else if("����".equals(inaim)){
						sbBuffer.append("\t���յ���:\t\t"+rs.getString("bl")+"\n");
					}
					sbBuffer.append("\n");
					
					j++;
					l++;
				}
				
				
				i++;
			}
			
			if (i<1){
				return "û�������Ϣ!";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(rs1!=null)try {rs1.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm1!=null)try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		return sbBuffer.toString();
	}
	
	
	private boolean checkCodeVaild(String code){
		code = StringUtil.nullToEmpty(code);
		String[] split1 = code.split("#");
		if(split1.length!=2){
			return false;
		}
		code = split1[1];
		split1 = code.split("-");
		if(split1.length!=3){
			return false;
		}
		code = split1[0];
		boolean flag = "I".equals(code)||"O".equals(code)||"E".equals(code)||"C".equals(code);
		return flag;
	}
	
	
	
}
