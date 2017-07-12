package com.weixin.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TxtUtil {

	
	private static final String fileName = "D://log.txt";
	private static final String fileName2 = "D://logbak.txt";
	private static final String fileName3 = "D://logTemp.txt";
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	private static SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String getNowDate(){
		return sdf.format(new Date());
	}
	
	public static String getNowDateYMD(){
		return sdfYMD.format(new Date());
	}
	
	
	public static void log(String mes){
		
		FileWriter fWriter = null;
		BufferedWriter bwWriter = null;
		
		try{
			bwWriter = new BufferedWriter(new FileWriter(fileName,true));
			bwWriter.write(mes+"\n");
			
		}catch(Exception e){
			e.toString();
		}finally{
			if(bwWriter!=null){
				try {
					bwWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(fWriter!=null){
				try {
					fWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
		
	}
	
	public static void main(String[] args){
		TxtUtil.log("12334");
	}
	
	public static void logbak(String mes){
		FileWriter fWriter = null;
		BufferedWriter bwWriter = null;
		
		try{
			bwWriter = new BufferedWriter(new FileWriter(fileName2,true));
			bwWriter.write(mes+"\n");
			
		}catch(Exception e){
			e.toString();
		}finally{
			if(bwWriter!=null){
				try {
					bwWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(fWriter!=null){
				try {
					fWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	
	public static void logTemple(String mes){
		FileWriter fWriter = null;
		BufferedWriter bwWriter = null;
		
		try{
			bwWriter = new BufferedWriter(new FileWriter(fileName3,true));
			bwWriter.write(mes+"\n");
			
		}catch(Exception e){
			e.toString();
		}finally{
			if(bwWriter!=null){
				try {
					bwWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(fWriter!=null){
				try {
					fWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	
}
