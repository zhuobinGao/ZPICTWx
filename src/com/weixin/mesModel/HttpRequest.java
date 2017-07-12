package com.weixin.mesModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.weixin.R.R;
import com.weixin.util.JsonUtil2;
import com.weixin.util.TxtUtil;

public class HttpRequest {
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        OutputStream outputStream = null;
        try {
            URL realUrl = new URL(url);
//            System.out.println(realUrl);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            outputStream = conn.getOutputStream();
            // 发送请求参数
            
           
                outputStream.write(param.getBytes("UTF-8"));  
                outputStream.close();  
           
            
            // flush输出流的缓冲
            
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
    
    public static Map<String, String> getOAuth(String code){
    	
    	String path = "https://api.weixin.qq.com/sns/oauth2/access_token";
    	StringBuffer sBuffer = new StringBuffer();
    	sBuffer.append("appid=wx23382b585e7bf49c&secret=df3c6906d0411e3a8f99c3682d801963");
    	sBuffer.append("&code="+code+"&grant_type=authorization_code ");
    	
    	String str = sendGet(path, sBuffer.toString());
    	
    	Map<String, String> map = JsonUtil2.toMap(str);
    	
    	
    	path = "https://api.weixin.qq.com/sns/userinfo";
    	StringBuffer param = new StringBuffer();
    	param.append("access_token="+map.get("access_token"));
    	param.append("&openid="+map.get("openid")+"&lang=zh_CN");
    	System.out.println(param);
    	
    	str = sendGet(path, param.toString());
    	map = JsonUtil2.toMap(str);
    	
    	return map;
    }
    
    
    
    
    
    public static String getAccess_token(){
    	 String url = "https://api.weixin.qq.com/cgi-bin/token";
    	 String param = "grant_type=client_credential&appid="+R.AppID+"&secret="+R.AppSecret;
    	 String s=HttpRequest.sendGet(url, param);
//    	 System.out.println(s);
    	 
    	 int index1 = s.indexOf("access_token")+"access_token".length()+3;
    	 int index2 = s.indexOf("expires_in")-3;
//    	 System.out.println(s.substring(index1, index2));
    	 return s.substring(index1, index2);
    }
    
    
    
    public static void setIndustry(){
    	String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?"+"access_token="+getAccess_token();
    	String param = " {  \"industry_id1\":\"15\",  \"industry_id2\":\"2\"}";
    	String sr=HttpRequest.sendPost(url, param);
    	System.out.println(sr);
    }
    
    public static void getIndustry(){
    	String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry";
    	String param = "access_token="+getAccess_token();
    	String s=HttpRequest.sendGet(url, param);
   	 	System.out.println(s);
    }
    
    public static void getMsgModelID(){
    	String url = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token="+getAccess_token();
    	String param = "{\"template_id_short\":\"TM00015\"}";
    	String sr=HttpRequest.sendPost(url, param);
    	System.out.println(sr);
    }
    
    public static void getMsgModelList(){
    	String url = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template";
    	String param = "access_token="+getAccess_token();
    	String s=HttpRequest.sendGet(url, param);
   	 	System.out.println(s);
    }
    
    public static void postMessage(String mes){
    	String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+getAccess_token();;
    	
    	String sr=HttpRequest.sendPost(url, mes);
    	System.out.println(sr);
    }
    
    
    public static String readTxt(String filename) {
    	
    	FileReader fr = null;
    	BufferedReader br = null;
    	String str1 = "";
    	StringBuffer sbBuffer = new StringBuffer();
    	String str = null;
    	try{
    		fr = new FileReader(filename);
    		br = new BufferedReader(fr);
    		while((str=br.readLine())!=null){
    			sbBuffer.append(str);
    		}
    	
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		if(fr!=null)
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	}
    	
    	
    	return sbBuffer.toString();
    	
    	
    }
    
    
    public static void setMenu(){
    	String url = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+getAccess_token();
    	String param = readTxt("D://微信菜单配置2.txt").replaceAll(" ", "");
    	System.out.println("====================================");
    	System.out.println(param);
    	System.out.println("====================================");
    	String s = sendPost(url, param);
    	System.out.println(s);
    }
    
    public static void getMenu(){
    	String url = "https://api.weixin.qq.com/cgi-bin/menu/get";
    	String param = "access_token="+getAccess_token();
    	String s = sendGet(url, param);
    	System.out.println(s);
    }
    
    public static void delMenu(){
    	String url = "https://api.weixin.qq.com/cgi-bin/menu/delete";
    	String param = "access_token="+getAccess_token();
    	String s = sendGet(url, param);
    	System.out.println(s);
    	
    }
    
    
    public static List<String> getUsers(){
    	String url = "https://api.weixin.qq.com/cgi-bin/user/get";
    	String param = "access_token="+getAccess_token();
    	String s = sendGet(url, param);
    	
    	int start = s.indexOf("\"data\":{\"openid\":[")+"\"data\":{\"openid\":[".length();
    	int end = s.indexOf("]},\"next_openid\"");
    	
    	s = s.substring(start,end);
    	
    	String userids[] = s.split(",");
    	List<String> userList = new ArrayList<String>();
    	for(String str : userids){
    		userList.add(str.replaceAll("\"", ""));
    	}
    	
    	return userList;
    }
    
    public static String getUserInfo(String userID){
    	String url = " https://api.weixin.qq.com/cgi-bin/user/info";
    	String param = "access_token="+getAccess_token()+"&openid="+userID+"&lang=zh_CN ";
    	String s = sendGet(url, param);
    	
    	return s;
    }
    
    public static void getAllUserInfo(){
    	List<String> userList = getUsers();
    	for(String str:userList){
    		TxtUtil.log(getUserInfo(str));
    	}
    	
    }
    
    
    public static int getHisMesCount(){
    	String url = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount";
    	String param = "access_token="+getAccess_token();
    	String s=HttpRequest.sendGet(url, param);
   	 	System.out.println(s);
   	 	
   	 	Map<String, String> map = JsonUtil2.toMap(s);
   	 	
   	 	int count = Integer.parseInt(map.get("news_count"));
   	 	count = count>10? count-10:0;
    	return count;
    } 
    
    public static List<Map<String, String>> getHisMes(){
    	
    	/*
    	 *
    	 * {
   "type":TYPE,
   "offset":OFFSET,
   "count":COUNT
}
    	 */
    	
    	
    	StringBuffer sBuffer = new StringBuffer();
    	sBuffer.append("{\n\"type\":\"news\", \n\"offset\":0, \n\"count\":6 }");
    	
    	
    	String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+getAccess_token();
    	
    	String sr=HttpRequest.sendPost(url, sBuffer.toString());
    	
    	System.out.println(sr);
    	
    	sr = JsonUtil2.toMap(sr).get("item");
    	
//    	System.out.println(sr);
    	
//    	System.out.println("===>"+JsonUtil.toMap("{item:"+sr+"}"));
    	
    	
    	List<Map<String, String>> listMap = new ArrayList<Map<String,String>>();
    	
    	List<String> list = new ArrayList<String>();
    	
    	list = JsonUtil2.toList("{item:"+sr+"}","item");
    	
    	
    	
    	Map<String, String> mapTemp = null, mapTemp2=null;
    	
    	String str = "",mediaID = "";
    	for(String temp : list){
    		mapTemp = JsonUtil2.toMap(temp);
    		str =mapTemp.get("content");
    		
    		if(!R.MESSAGEID_LIST.contains(mapTemp.get("media_id"))){
    			continue;
    		}
    		
    		str = JsonUtil2.toList(str, "news_item").get(0);
    		mapTemp2 = JsonUtil2.toMap(str);
    		str = mapTemp.get("media_id");
    		mapTemp2.put("myHttpUrl", R.MESSAGEID_Map.get(str));
    		
    		listMap.add(mapTemp2);
    	}
    	
    	
//    	System.out.println(listMap);
    	
    	
    	
    	
    	
    	
    	return listMap;
    }
    
    
    
    public static void main(String[] args){
    	//setIndustry();
    	//setIndustry();
    	//getIndustry();
    //	getMsgModelID();
    	//getMsgModelList();
//    	setMenu();
//    	delMenu();
//    	setMenu();
//    	System.out.println(getUsers());
//    	getAllUserInfo();
//    	System.out.println("==>\n"+getAccess_token());
    	
//    	int count = getHisMesCount();
//    	System.out.println(count);
//    	getMenu();
//    	setMenu();
    	getHisMes();
    	//postMessage(readTxt());
    }
    
    //o2VBlsx88YU398ih3f8B8-Krqf7I   o2VBls29DZuiOqGnQsFs63CBJyKc
}
