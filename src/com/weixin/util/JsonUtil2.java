package com.weixin.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class JsonUtil2 {

	 public static Map<String,String> toMap(String jsonString) throws JSONException {

	        JSONObject jsonObject = new JSONObject(jsonString);
	        
	        Map<String,String> result = new HashMap<String,String>();
	        Iterator<String> iterator = jsonObject.keys();
	        String key = null;
	        String value = null;
	        
	        while (iterator.hasNext()) {
	            key = (String) iterator.next();
	            value = (String)(jsonObject.get(key)+"");
	            result.put(key, value);

	        }
	        return result;

	}
	
	public static List<String> toList(String jsonString,String key) throws JSONException{
		
		JSONObject jsonObject = new JSONObject(jsonString);
	    List<String> list = new ArrayList<String>();
	    JSONArray likes=jsonObject.getJSONArray(key); //得到likes数组
	    
	    for(int i=0;i<likes.length();i++){
	    	list.add(likes.get(i)+"");
	    }
	    
		return list;
	}
	
	 
	 
	 
	
	public static void main(String[] args){
		String meString = "{\"voice_count\":0}";
		
		System.out.println(toMap(meString));
		
		
	} 
	
}
