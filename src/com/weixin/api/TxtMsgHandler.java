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
		String bakMes = "系统正忙请稍后，努力为您排队中";
		
		if("11".equals(content)){
			 mes = controler.get3DayBerthplan();
			 txtReturnBean.setTitle("未来三天班轮船期查询");
			 txtReturnBean.setContent(mes);
		}else if("12".equals(content)){
			mes = controler.getOnTerimalVessel();
			txtReturnBean.setTitle("在港船期查询");
			 txtReturnBean.setContent(mes);
		}else if("13".equals(content)){
			mes = controler.getTodayOutVessel();
			txtReturnBean.setTitle("当天离港船舶");
			txtReturnBean.setContent(mes);
		}else if("21".equals(content)){
			mes = controler.getContainerOwner();
			txtReturnBean.setTitle("箱主查询");
			txtReturnBean.setContent(mes);
		}else if(content.startsWith("22#")){
			
			temp = content.split("#");
			if(temp.length<2){
				return txtReturnBean;
			}
			mes = controler.getEmptyByOnwer(temp[1]);
			txtReturnBean.setTitle("在场空箱查询");
			txtReturnBean.setContent(mes);
		}else if(content.startsWith("23#")){
			
			temp = content.split("#");
			if(temp.length<2){
				return txtReturnBean;
			}
			mes = controler.getDamage(temp[1]);
			txtReturnBean.setTitle("在场烂箱查询");
			txtReturnBean.setContent(mes);
			
		}else if(content.startsWith("51#")){
			
			temp = content.split("#");
			if(temp.length<2){
				return txtReturnBean;
			}
			mes = controler.getInGate(temp[1]);
			txtReturnBean.setTitle("拖车小票");
			txtReturnBean.setContent(mes);
		}else if(content.startsWith("52#")){
			
			temp = content.split("#");
			if(temp.length<2){
				return txtReturnBean;
			}
			mes = controler.getTruckInfo(temp[1]);
			txtReturnBean.setTitle("拖车信息");
			txtReturnBean.setContent(mes);
		}else if(content.startsWith("61#")){
			
			temp = content.split("#");
			if(temp.length<2){
				return txtReturnBean;
			}
			mes = controler.getEirInfo(content.toUpperCase());
			txtReturnBean.setTitle("办单信息");
			txtReturnBean.setContent(mes);
		}else if(content.startsWith("25#")){
			
			temp = content.split("#");
			if(temp.length<2){
				return txtReturnBean;
			}
			mes = controler.getConDyn(temp[1].toUpperCase());
			txtReturnBean.setTitle("箱动态查询");
			txtReturnBean.setContent(mes);
		}
		
		
		if(R.bakListUserId.contains(username)){
			TxtUtil.logbak(new Date().toString()+":"+username+"login");
			txtReturnBean.setContent(bakMes);
		}
		
		
		
		return txtReturnBean;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
}
