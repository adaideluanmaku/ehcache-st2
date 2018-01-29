package com.ch.passjiekou;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Test {
	public static String json;
	public static void main(String args[]) throws IOException, TimeoutException, DocumentException, InterruptedException{
		InputStream in=Jiekoutest.class.getClassLoader().getResourceAsStream("json.properties");
		Properties pro=new Properties();
		pro.load(in);
		
		json=pro.getProperty("json2");
//		System.out.println("AAAA"+json);
//		Passservice passweb=new Passservice();
//		String jsonout=passweb.getPassResult(json);
//		System.out.println("CCCCC"+jsonout);
		
		//测试时使用，可以删除以下代码
		Logger log=Logger.getLogger(Jiekoutest.class);
		Date time=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss SSS");
		System.out.println("请求开始时间："+sf.format(time));
		log.error("请求开始时间："+sf.format(time));
		List list=new ArrayList();
		int errnum=0;
//		for(int j=0;j<4;j++){
//			System.out.println("第"+(j+1)+"次500循环");
//			log.error("第"+(j+1)+"次500循环");
			for(int i=0;i<300000;i++){
//				System.out.println("AAAA"+json);
				Passservice passweb=new Passservice();
				String result=passweb.getPassResult(json);
//				System.out.println("CCCCC"+jsonout);
				if(!"<?xml".equals(result.substring(0,5))){
//					Date time2=new Date();
//					list.add(result);
					log.error("发生错误时，当前循环总数为："+(i+1));
					log.error(result);
					errnum=errnum+1;
				}
			}
//			System.out.println("暂停5秒");
//			log.error("暂停5秒");
//			Thread.sleep(5000);
//		}
		Date endtime=new Date();
		System.out.println("请求结束时间："+sf.format(endtime));
		System.out.println("请求错误数为："+errnum);
		log.error("请求结束时间："+sf.format(endtime));
		log.error("请求错误数为："+errnum);
		System.out.println("时间差为："+(endtime.getTime()-time.getTime()));
		log.error("时间差为："+(endtime.getTime()-time.getTime()));
		
	}
}
