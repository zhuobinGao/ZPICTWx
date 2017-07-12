package com.weixin.bean;


public class EncodingMsgBean {

	private String ToUserName;
	private String Encrypt;
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getEncrypt() {
		return Encrypt;
	}
	public void setEncrypt(String encrypt) {
		Encrypt = encrypt;
	}
	
	public String getDecrptAESContent(){
		
	
		return new String("");
	}
	
	
	
	@Override
	public String toString() {
		return "EncodingMsgBean [ToUserName=" + ToUserName + ", Encrypt="
				+ Encrypt + "]";
	}
	
	
	
}
