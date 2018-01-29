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
		
		//����ʱʹ�ã�����ɾ�����´���
		Logger log=Logger.getLogger(Jiekoutest.class);
		Date time=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss SSS");
		System.out.println("����ʼʱ�䣺"+sf.format(time));
		log.error("����ʼʱ�䣺"+sf.format(time));
		List list=new ArrayList();
		int errnum=0;
//		for(int j=0;j<4;j++){
//			System.out.println("��"+(j+1)+"��500ѭ��");
//			log.error("��"+(j+1)+"��500ѭ��");
			for(int i=0;i<300000;i++){
//				System.out.println("AAAA"+json);
				Passservice passweb=new Passservice();
				String result=passweb.getPassResult(json);
//				System.out.println("CCCCC"+jsonout);
				if(!"<?xml".equals(result.substring(0,5))){
//					Date time2=new Date();
//					list.add(result);
					log.error("��������ʱ����ǰѭ������Ϊ��"+(i+1));
					log.error(result);
					errnum=errnum+1;
				}
			}
//			System.out.println("��ͣ5��");
//			log.error("��ͣ5��");
//			Thread.sleep(5000);
//		}
		Date endtime=new Date();
		System.out.println("�������ʱ�䣺"+sf.format(endtime));
		System.out.println("���������Ϊ��"+errnum);
		log.error("�������ʱ�䣺"+sf.format(endtime));
		log.error("���������Ϊ��"+errnum);
		System.out.println("ʱ���Ϊ��"+(endtime.getTime()-time.getTime()));
		log.error("ʱ���Ϊ��"+(endtime.getTime()-time.getTime()));
		
	}
}
