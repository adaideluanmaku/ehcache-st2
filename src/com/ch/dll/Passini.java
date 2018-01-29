package com.ch.dll;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

/**
 * 读取INI配置文件
 * **/
public class Passini {
	public Hashtable iniHash;
	public void hashINI() throws IOException{
	  String Section="",Key="",Value="";
	  if(iniHash==null){
		  iniHash=new Hashtable();
	  } 
	  if(!isEmpty()) iniHash.clear();
//	  try{
	     BufferedReader bufReader = new BufferedReader(new FileReader("E:/workplaceNew1/ehcache-st2/src/PASS4Invoke.ini"));
	     String strLine="";
	     int firstLeftSquareBrackets=0,firstRightSquareBrackets=0;
	     while( (strLine=bufReader.readLine())!=null){
		     strLine=strLine.trim();
		     if("".equals(strLine)) continue;
		     firstLeftSquareBrackets=strLine.indexOf("[");
		     firstRightSquareBrackets=strLine.indexOf("]"); 
		     if(firstLeftSquareBrackets>=0 && firstRightSquareBrackets>=0 && firstLeftSquareBrackets<firstRightSquareBrackets){
		    	 Section=strLine.substring(firstLeftSquareBrackets+1,firstRightSquareBrackets);
		     }else{
		    	 int index=0;
		    	 index=strLine.indexOf("="); 
		    	 Key=strLine.substring(0,index).trim();
		    	 Value=strLine.substring(index+1).trim();   
		    	 String hashKey="";
		    	 hashKey=Section+"."+Key;      
		    	 iniHash.put(hashKey.toLowerCase(),Value);
		     }
	   }
	   bufReader.close();
//	   }catch(Exception e){ 
//		   System.out.println("读取配置文件发生错误。"+e.getMessage());
//		   }
	  }
	 public boolean isEmpty(){
		if(iniHash==null) return true;
		try {
			return iniHash.isEmpty(); 
			}catch(NullPointerException e) { 
				return true; 
				}
	 }
	 public String getHashValue(String Section,String Key) throws IOException{  
		if(isEmpty()) hashINI();
		if(isEmpty()) return "";
		String hashKey=Section+"."+Key;
		String Value=(String)iniHash.get(hashKey.toLowerCase());
		return (Value==null)?"":Value;  
	 }
}
