package com.ch.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.dom4j.CDATA;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Test {
	public static void main(String args[]) throws IOException, DocumentException{
//		InputStream in=Jsonduibitest.class.getClassLoader().getResourceAsStream("json.properties");
//		Properties pro=new Properties();
//		pro.load(in);
//		String json=pro.getProperty("json3");
//		
//		SAXReader reader=new SAXReader();
//		Document document=reader.read(new StringReader(json));
//		Element root=document.getRootElement();
//		System.out.println(root.getText());
//		double a=0.0;
//		if(a<= 0){
//			System.out.println("aaa");
//		}
		
		for(int i=0;i<10;i++){
			System.out.println(i);
			for(int i1=0;i1<10;i1++){
				System.out.println(i1);
				if(i1==5){
					return;
				}
			}
		}
	}
}
