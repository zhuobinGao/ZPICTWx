package com.weixin.bean;

public class TxtReturnBean {
	
	
	private String title;
	private String content;
	
	public String defaultContent = "    �����Ը�������ѡ��ײ��Ĺ��ܲ˵��������ϵͳ��ʾ�������·������ڵĴ�����в�ѯ��\n\n"; 
	public String berthMessage = "--���ڲ�ѯ--\n*��11��δ��������ִ��ڲ�ѯ\n*��12����ǰ�ڸ۴�����Ϣ��ѯ\n*��13��������۴�����Ϣ��ѯ\n\n";
	
	public String emptyMessage = "--����Ϣ��ѯ--\n*��21��������ѯ\n*��22#�������ڳ������ѯ\n*��23#�������ڳ������ѯ\n*��25#��š��䶯̬��ѯ\n\n";
	
	public String truckMessage = "--�ϳ���ѯ--\n*��51#���ƺ��롿�ϳ�СƱ\n\t\t\t\t(��������YG1234-��51#1234)\n*��52#���ƺ��롿�ϳ���Ϣ\n\t\t\t\t(��������YG1234-��52#1234)\n\n";
	
	public String eirMessage = "--�쵥��ѯ--\n*��61#ҵ�񵥺š��쵥��ѯ\n\t\tҵ�񵥺Ÿ�ʽ:\n\t\t\t\t�쵥����-����-����\n\t\t�쵥����:\n\t\t\t\tI(����),O(����),E(���ɿ���),C(���������)\n\t\t" +
			"�ο���ʽ:\n\t\t\t\t61#I-PASU1235-COSCON\n\t\t\t\t61#O-PASU1235-COSCON\n\t\t\t\t61#E-PASU1235-COSCON\n\t\t\t\t61#C-PASU1235-COSCON\n\n";
	
	
	public String waitAMinute = "�Ժ󿪷�";
	
	public TxtReturnBean(){
		title = "���ڲ�ѯ����";
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
