package com.weixin.bean;

public class TxtReturnBean {
	
	
	private String title;
	private String content;
	
	public String defaultContent = "    您可以根据需求选择底部的功能菜单，或根据系统提示输入以下方括号内的代码进行查询。\n\n"; 
	public String berthMessage = "--船期查询--\n*【11】未来三天班轮船期查询\n*【12】当前在港船期信息查询\n*【13】当天离港船期信息查询\n\n";
	
	public String emptyMessage = "--箱信息查询--\n*【21】箱主查询\n*【22#箱主】在场空箱查询\n*【23#箱主】在场烂箱查询\n*【25#箱号】箱动态查询\n\n";
	
	public String truckMessage = "--拖车查询--\n*【51#车牌号码】拖车小票\n\t\t\t\t(例：车牌YG1234-〉51#1234)\n*【52#车牌号码】拖车信息\n\t\t\t\t(例：车牌YG1234-〉52#1234)\n\n";
	
	public String eirMessage = "--办单查询--\n*【61#业务单号】办单查询\n\t\t业务单号格式:\n\t\t\t\t办单类型-单号-箱主\n\t\t办单类型:\n\t\t\t\tI(进口),O(出口),E(自由空箱),C(自由组合箱)\n\t\t" +
			"参考格式:\n\t\t\t\t61#I-PASU1235-COSCON\n\t\t\t\t61#O-PASU1235-COSCON\n\t\t\t\t61#E-PASU1235-COSCON\n\t\t\t\t61#C-PASU1235-COSCON\n\n";
	
	
	public String waitAMinute = "稍后开放";
	
	public TxtReturnBean(){
		title = "公众查询功能";
		content = defaultContent + berthMessage+emptyMessage+eirMessage+truckMessage;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
	
}
