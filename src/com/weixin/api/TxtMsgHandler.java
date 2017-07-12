package com.weixin.api;

import java.util.Date;

import com.gzb.db.controler.CheckControler;
import com.weixin.R.R;
import com.weixin.bean.TxtMsgBean;
import com.weixin.bean.TxtReturnBean;
import com.weixin.util.StringUtil;
import com.weixin.util.TxtUtil;

public class TxtMsgHandler  {

	private TxtMsgBean bean;
	
	private CheckControler controler = new CheckControler();
	
	public TxtMsgHandler(TxtMsgBean bean){
		this.bean = bean;
	}
	
	
	public TxtReturnBean getTxtReturnBean(){
		
		TxtReturnBean txtReturnBean = new TxtReturnBean();
		String mes = "",content="",temp[]=null;
		content = StringUtil.nullToEmpty(bean.getContent());
		
		
		
		String username = bean.getFromUserName();
		String bakMes = "ϵͳ��æ���Ժ�Ŭ��Ϊ���Ŷ���";
		
		if("11".equals(content)){
			 mes = controler.get3DayBerthplan();
			 txtReturnBean.setTitle("δ��������ִ��ڲ�ѯ");
			 txtReturnBean.setContent(mes);
		}else if("12".equals(content)){
			mes = controler.getOnTerimalVessel();
			txtReturnBean.setTitle("�ڸ۴��ڲ�ѯ");
			 txtReturnBean.setContent(mes);
		}else if("13".equals(content)){
			mes = controler.getTodayOutVessel();
			txtReturnBean.setTitle("������۴���");
			txtReturnBean.setContent(mes);
		}else if("21".equals(content)){
			mes = controler.getContainerOwner();
			txtReturnBean.setTitle("������ѯ");
			txtReturnBean.setContent(mes);
		}else if(content.startsWith("22#")){
			
			temp = content.split("#");
			if(temp.length<2){
				return txtReturnBean;
			}
			mes = controler.getEmptyByOnwer(temp[1]);
			txtReturnBean.setTitle("�ڳ������ѯ");
			txtReturnBean.setContent(mes);
		}else if(content.startsWith("23#")){
			
			temp = content.split("#");
			if(temp.length<2){
				return txtReturnBean;
			}
			mes = controler.getDamage(temp[1]);
			txtReturnBean.setTitle("�ڳ������ѯ");
			txtReturnBean.setContent(mes);
			
		}else if(content.startsWith("51#")){
			
			temp = content.split("#");
			if(temp.length<2){
				return txtReturnBean;
			}
			mes = controler.getInGate(temp[1]);
			txtReturnBean.setTitle("�ϳ�СƱ");
			txtReturnBean.setContent(mes);
		}else if(content.startsWith("52#")){
			
			temp = content.split("#");
			if(temp.length<2){
				return txtReturnBean;
			}
			mes = controler.getTruckInfo(temp[1]);
			txtReturnBean.setTitle("�ϳ���Ϣ");
			txtReturnBean.setContent(mes);
		}else if(content.startsWith("61#")){
			
			temp = content.split("#");
			if(temp.length<2){
				return txtReturnBean;
			}
			mes = controler.getEirInfo(content.toUpperCase());
			txtReturnBean.setTitle("�쵥��Ϣ");
			txtReturnBean.setContent(mes);
		}else if(content.startsWith("25#")){
			
			temp = content.split("#");
			if(temp.length<2){
				return txtReturnBean;
			}
			mes = controler.getConDyn(temp[1].toUpperCase());
			txtReturnBean.setTitle("�䶯̬��ѯ");
			txtReturnBean.setContent(mes);
		}
		
		
		if(R.bakListUserId.contains(username)){
			TxtUtil.logbak(new Date().toString()+":"+username+"login");
			txtReturnBean.setContent(bakMes);
		}
		
		
		
		return txtReturnBean;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
}
