package com.weixin.api;

import java.util.Date;

import com.weixin.R.R;
import com.weixin.bean.EventMsgBean;
import com.weixin.bean.TxtReturnBean;
import com.weixin.util.TxtUtil;

public class EventMsgHandler {

	
	private EventMsgBean bean;
	
	public EventMsgHandler(EventMsgBean bean){
		this.bean = bean;
	}
	
	public TxtReturnBean getTxtReturnBean(){
		
		String username = bean.getFromUserName();
		
		TxtReturnBean txtReturnBean = new TxtReturnBean();
		if("vessel_check".equals(bean.getEventKey())){
			 txtReturnBean.setTitle("船期信息");
			 txtReturnBean.setContent(txtReturnBean.defaultContent+txtReturnBean.berthMessage);
		}
		
		if("emptyCtn_check".equals(bean.getEventKey())){
			 txtReturnBean.setTitle("箱信息查询");
			 txtReturnBean.setContent(txtReturnBean.defaultContent+txtReturnBean.emptyMessage);
		}
		
		if("truck_check".equals(bean.getEventKey())){
			txtReturnBean.setTitle("拖车查询");
			 txtReturnBean.setContent(txtReturnBean.defaultContent+txtReturnBean.truckMessage);
		}
		
		if("preIn_check".equals(bean.getEventKey())){
			txtReturnBean.setTitle("办单查询");
			txtReturnBean.setContent(txtReturnBean.defaultContent+txtReturnBean.eirMessage);
		}
		
		if("ygtd".equals(bean.getEventKey())){
			txtReturnBean.setTitle("员工通道");
			txtReturnBean.setContent(txtReturnBean.defaultContent+txtReturnBean.waitAMinute);
		}
		
		if("terimal_mes".equals(bean.getEventKey())){
			txtReturnBean.setTitle("码头公告");
			txtReturnBean.setContent(txtReturnBean.defaultContent+txtReturnBean.waitAMinute);
		}
		
		if(R.bakListUserId.contains(username)){
			TxtUtil.logbak(new Date().toString()+":"+username+"login");
		}
		
		return txtReturnBean;
	}
	
	
	
}
