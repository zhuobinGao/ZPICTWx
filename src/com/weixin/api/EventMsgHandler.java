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
			 txtReturnBean.setTitle("������Ϣ");
			 txtReturnBean.setContent(txtReturnBean.defaultContent+txtReturnBean.berthMessage);
		}
		
		if("emptyCtn_check".equals(bean.getEventKey())){
			 txtReturnBean.setTitle("����Ϣ��ѯ");
			 txtReturnBean.setContent(txtReturnBean.defaultContent+txtReturnBean.emptyMessage);
		}
		
		if("truck_check".equals(bean.getEventKey())){
			txtReturnBean.setTitle("�ϳ���ѯ");
			 txtReturnBean.setContent(txtReturnBean.defaultContent+txtReturnBean.truckMessage);
		}
		
		if("preIn_check".equals(bean.getEventKey())){
			txtReturnBean.setTitle("�쵥��ѯ");
			txtReturnBean.setContent(txtReturnBean.defaultContent+txtReturnBean.eirMessage);
		}
		
		if("ygtd".equals(bean.getEventKey())){
			txtReturnBean.setTitle("Ա��ͨ��");
			txtReturnBean.setContent(txtReturnBean.defaultContent+txtReturnBean.waitAMinute);
		}
		
		if("terimal_mes".equals(bean.getEventKey())){
			txtReturnBean.setTitle("��ͷ����");
			txtReturnBean.setContent(txtReturnBean.defaultContent+txtReturnBean.waitAMinute);
		}
		
		if(R.bakListUserId.contains(username)){
			TxtUtil.logbak(new Date().toString()+":"+username+"login");
		}
		
		return txtReturnBean;
	}
	
	
	
}
