package com.weixin.util;

import org.apache.commons.codec.binary.Base64;


public class ThreeDes {

   
    /**
     * ������string  �������룬string�������;
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
     * ������string ��������,String �������;
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
     * ������byte[] �������룬byte[] �������;
     * @param byts
     * @return
     */
 
  
    
    public static  void  main(String[] args)
    {
        ThreeDes  td=new ThreeDes();
        //Key k=td.getKey("testkey4545212144");
        //dQkridyRrwVFiVOwWHLtiA==
//        Key k=td.getKey("dQkridyRrwVFiVOwWHLtiA==");
//        System.out.println("��õ���Կkey��:"+k);
        
        String  encyStr=td.getencString("jdbc:oracle:thin:@10.2.3.30:1521:ctosp1");
        System.out.println("���ܺ��������:"+encyStr);
        
        String  decyStr=td.getDecString(encyStr);
        System.out.println("���ܺ��������:"+decyStr);
    }
}