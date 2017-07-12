package com.weixin.util;

import org.apache.commons.codec.binary.Base64;


public class ThreeDes {

   
    /**
     * 加密以string  明文输入，string密文输出;
     * @param strMing
     * @return
     */
    public  String  getencString(String strMing)
    {
//        byte[]  byteMi=null;
//        byte[]  byteMing=null;
//        String  strMi="";
//        
//        BASE64Encoder  encoder  =new BASE64Encoder();
//        try {
//            byteMing=strMing.getBytes("utf-8");
//            byteMi=getEncCode(byteMing,key);
//            strMi=encoder.encode(byteMi);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }finally
//        {
//            encoder=null;
//            byteMi=null;
//            byteMing=null;
//        }
//        return  strMi;
    	
    	byte[] b=strMing.getBytes();  
        Base64 base64=new Base64();  
        b=base64.encode(b);  
        String s=new String(b);  
        return s;  
    }
    
    /**
     * 解密以string 密文输入,String 明文输出;
     * @param strMi
     * @return
     */
    public  String  getDecString(String strMi)
    {
//        BASE64Decoder  base64Decoder=new BASE64Decoder();
//        byte[] byteMing=null;
//        byte[] byteMi=null;
//        String strMing="";
//        try {
//            byteMi=base64Decoder.decodeBuffer(strMi);
//            byteMing=getDecCode(byteMi,key);
//            strMing=new String(byteMing,"utf-8");
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }finally
//        {
//            base64Decoder=null;
//            byteMing=null;
//            byteMi=null;
//        }
//        return strMing;
    	  byte[] b=strMi.getBytes();  
          Base64 base64=new Base64();  
          b=base64.decode(b);  
          String s=new String(b);  
          return s;  
        
    }
    /**
     * 加密以byte[] 明文输入，byte[] 密文输出;
     * @param byts
     * @return
     */
 
  
    
    public static  void  main(String[] args)
    {
        ThreeDes  td=new ThreeDes();
        //Key k=td.getKey("testkey4545212144");
        //dQkridyRrwVFiVOwWHLtiA==
//        Key k=td.getKey("dQkridyRrwVFiVOwWHLtiA==");
//        System.out.println("获得的密钥key是:"+k);
        
        String  encyStr=td.getencString("jdbc:oracle:thin:@10.2.3.30:1521:ctosp1");
        System.out.println("加密后的密文是:"+encyStr);
        
        String  decyStr=td.getDecString(encyStr);
        System.out.println("解密后的明文是:"+decyStr);
    }
}