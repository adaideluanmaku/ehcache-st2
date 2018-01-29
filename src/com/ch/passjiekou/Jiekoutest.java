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



public class Jiekoutest {
	public static String json;
	public static void main(String args[]) throws IOException, TimeoutException, DocumentException, InterruptedException{
		InputStream in=Jiekoutest.class.getClassLoader().getResourceAsStream("json.properties");
		Properties pro=new Properties();
		pro.load(in);
		
		json=pro.getProperty("json2");
		System.out.println("AAAA"+json);
		Passservice passweb=new Passservice();
		String jsonout=passweb.getPassResult(json);
		System.out.println("CCCCC"+jsonout);
		
//		for(int i=0;i<list.size();i++){
//			System.out.println(list.get(i));
//			log.error(list.get(i));
//		}
		
		//½âÎö·µ»Ø´®
//		SAXReader reader=new SAXReader();
//		Document document=reader.read(new StringReader(jsonout));
//		Element root=document.getRootElement();
//		System.out.println(root.getText());
		
	}
}
