package com.weixin.R;

public enum MsgType {

	text("text"),event("event"),other("other");
	
	private String state;
	
	private MsgType(String state){
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public static MsgType getMsgType(String str) {
		 if(str==null)return null;
		
         for (MsgType c : MsgType.values()) {
             if (str.equals(c.getState())) {
                 return c;
             }
         }
         return other;
     }
	
	
}
