package com.gzb.api.service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gzb.api.dbutil.SQLServericeDBUtil;
import com.gzb.json.impl.JsonForCountResult;
import com.gzb.json.impl.JsonForHtmlSelect;
import com.gzb.json.impl.JsonForJsonArray;
import com.weixin.util.StringUtil;

public class MoneyService extends SQLServericeDBUtil {

	public String getCustomerList(HttpServletRequest request, HttpServletResponse response){
		StringBuffer sqlbuffer = new StringBuffer();
		sqlbuffer.append("SELECT c.CustomerId,c.NameCn FROM CUM_Customer_Pre p \n");
		sqlbuffer.append("left join CUM_Customer c on c.CustomerId=p.CustomerId \n");
		sqlbuffer.append("where PreAmount is not null and IsUsePreAmount='1' and PreAmount>0 \n");
		sqlbuffer.append("order by NameCn collate Chinese_PRC_Stroke_CI_AS asc \n");
		return getSearchResult(sqlbuffer.toString(), null, new JsonForHtmlSelect());
	}
	
	
	
	public String applyForm(HttpServletRequest request, HttpServletResponse response){
		
		StringBuffer sql_check = new StringBuffer();
		StringBuffer sql_checkSame = new StringBuffer();
		StringBuffer sql_apply = new StringBuffer();
		
		List<String> param_check = new ArrayList<String>();
		List<String> param_checkSame = new ArrayList<String>();
		List<String> param_apply = new ArrayList<String>();
		
		sql_check.append("SELECT COUNT(1) FROM CUM_Customer_mind where wxid=? ");
		sql_checkSame.append("SELECT COUNT(1) FROM CUM_Customer_mind where wxid=? and CustomerId=? ");
		sql_apply.append("insert into CUM_Customer_mind(mind_id,wxid,wxname,applyName,applyDate, CustomerId,mindMoney,isOpen) values(?,?,?,?,?, ?,?,? )");
		
		 UUID uuid = UUID.randomUUID();
		 String wxid = StringUtil.trimJson(request.getParameter("wxid"));
		 String wxname = StringUtil.trimJson(request.getParameter("wxname"));
		 String applyUser = StringUtil.trimJson(request.getParameter("applyUser"));
		 String customerid = StringUtil.trimJson(request.getParameter("customerid"));
		 String mindMoney = StringUtil.trimJson(request.getParameter("mindMoney"));
		 String applyDate = StringUtil.getNowYMD();
		 String isOpen = "0";
		 
		 
		param_check.add(wxid);
		String count = getSearchResult(sql_check.toString(), param_check, new JsonForCountResult());
		
		if(Integer.valueOf(count)>=5){
			return "申请数已超过上限";
		}
		
		param_checkSame.add(wxid);
		param_checkSame.add(customerid);
		count = getSearchResult(sql_checkSame.toString(), param_checkSame, new JsonForCountResult());
		if(Integer.valueOf(count)>0){
			return "已有该用户的预警";
		}
	
		param_apply.add(uuid.toString());
		param_apply.add(wxid);
		param_apply.add(wxname);
		param_apply.add(applyUser);
		param_apply.add(applyDate);
		param_apply.add(customerid);
		param_apply.add(mindMoney);
		param_apply.add(isOpen);
		
		String result = updateData(sql_apply.toString(), param_apply);
		
		if(!result.equalsIgnoreCase("OK")){
			result="服务器正忙,请稍候再试";
		}
		
		return result;
	}
	
	
	public String getApplyList(HttpServletRequest request, HttpServletResponse response){
		String wxid = StringUtil.nullToEmpty(request.getParameter("wxid"));
		StringBuffer sBuffer = new StringBuffer();
		List<String> list = new ArrayList<String>();
		list.add(wxid);
		sBuffer.append("SELECT C.NameCn, CM.WXNAME, cast(Round(Cm.MINDMONEY,2) as numeric(16,2)), case when isopen='1' then cast( Round(P.PreAmount,2) as   numeric(16,2)) else '0' end, CASE WHEN CM.MINDMONEY>=P.PreAmount and isopen='1' THEN 'e64340' ELSE '' END,isopen,mind_id \n");
		sBuffer.append("FROM CUM_Customer_mind CM \n");
		sBuffer.append("LEFT JOIN CUM_Customer C ON C.CustomerId=CM.CustomerId \n");
		sBuffer.append("LEFT JOIN CUM_Customer_Pre P ON P.CustomerId=CM.CustomerId \n");
		sBuffer.append("where wxid=? ORDER BY NameCn collate Chinese_PRC_Stroke_CI_AS asc \n");
		System.out.println("getApplyList");
		return getSearchResult(sBuffer.toString(), list, new JsonForJsonArray());
	}
	
	
	
	public String cancelApply(HttpServletRequest request, HttpServletResponse response){
		String mindid = request.getParameter("mindID");
		
		StringBuffer sBuffer = new StringBuffer();
		List<String> list = new ArrayList<String>();
		
		
		sBuffer.append("delete FROM CUM_Customer_mind where mind_id=? ");
		list.add(mindid);
		
		String result = updateData(sBuffer.toString(), list);
		if(!"OK".equals(result)){
			result = "服务器正忙,请稍候再试";
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
}
