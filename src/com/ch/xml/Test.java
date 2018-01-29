package com.ch.xml;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;

public class Test {
	public static Map screenreq1;//系统审查旧库
	public static Map screenres1;
	
	public static Map screenreq2;//自定义审查旧库
	public static Map screenres2;
	
	public static Map screenreq3;//屏蔽审查旧库
	public static Map screenres3;
	
	public static Map screenreq4;//系统审查新库
	public static Map screenres4;
	
	public static Map screenreq5;//自定义审查新库
	public static Map screenres5;
	
	public static Map screenreq6;//屏蔽审查新库
	public static Map screenres6;
	
	public static Map screenreq7;//特殊字符
	public static Map screenres7;
	
	public static Map detailreq1;//详细信息
	public static Map detailres1;
	
	public static Map queryreq1;//查询
	public static Map queryres1;
	
	public static Map queryreq2;//浮动窗口
	public static Map queryres2;
	
	public static Map reasonreq1;//用药理由
	public static Map reasonres1;
	
	public static String jsonin=null;
	public static String jsonoutwin=null;
	public static String jsonoutjava=null;
	public static List duibirequest=null;
	public static List errlist=null;
	public static List listerr1=null;
	public static int listerrsum=0;
	
	//连接接口
	@SuppressWarnings({ "deprecation", "resource" })
	public static String getPassResult(String jsonin,String jiekou) {
		String result = null;

		String add_url = "http://172.18.7.59:8081/pass/ws/"+jiekou;
		
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpPost httppost = new HttpPost(add_url);
			try {
				// 设置连接类型
				StringEntity stringEntity = new StringEntity(jsonin, HTTP.UTF_8);
				httppost.setEntity(stringEntity);
				
				// 发送post请求，并接收响应的数据
				HttpResponse httpResponse = httpClient.execute(httppost); 
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					//从响应的数据中获取响应体的数据
					HttpEntity httpEntity = httpResponse.getEntity();
					result = EntityUtils.toString(httpEntity);
//					System.out.println(result);
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} finally {
			//关闭http请求连接
			httpClient.getConnectionManager().shutdown();
		}

		return result;
	}
	public static void main(String args[]) throws DocumentException, IOException{
		errlist=new ArrayList();
		Iterator keys=null;
		Jmeterxml xml=new Jmeterxml();
		xml.Modul();
		
		
//		System.out.println("系统审查旧库");
//		errlist.add("----系统审查旧库----");
//		screenreq1=xml.getScreenreq1();
//		screenres1=xml.getScreenres1();
//		keys=screenreq1.keySet().iterator();
//		while(keys.hasNext()){
//			String key=keys.next().toString();
//			jsonin=screenreq1.get(key).toString();
////			System.out.println(screenreq1.get(key));
//			jsonoutjava=getPassResult(jsonin,"screen");//pass-win输入串，pass审查得到输出串
//			jsonoutwin=screenres1.get(key).toString();//pass-win审查结果
//			Jsonduibi jsonduibi=new Jsonduibi();
//			jsonduibi.setJson(jsonoutwin);
//			jsonduibi.setJson1(jsonoutjava);
//			jsonduibi.Duibi();
//			duibirequest=jsonduibi.listerr;
//			if(duibirequest.size()>0){
//				errlist.add(key);
//			}
//		}
//		System.out.println(errlist);
//		
//		
//		System.out.println("自定义审查旧库");
//		errlist.add("----自定义审查旧库----");
//		screenreq2=xml.getScreenreq2();
//		screenres2=xml.getScreenres2();
//		keys=screenreq2.keySet().iterator();
//		while(keys.hasNext()){
//			String key=keys.next().toString();
//			jsonin=screenreq2.get(key).toString();
////			System.out.println(screenreq1.get(key));
//			jsonoutjava=getPassResult(jsonin,"screen");//pass-win输入串，pass审查得到输出串
//			jsonoutwin=screenres2.get(key).toString();//pass-win审查结果
//			Jsonduibi jsonduibi=new Jsonduibi();//json对比
//			jsonduibi.setJson(jsonoutwin);
//			jsonduibi.setJson1(jsonoutjava);
//			jsonduibi.Duibi();
//			duibirequest=jsonduibi.listerr;
//			if(duibirequest.size()>0){
//				errlist.add(key);
//			}
//		}
//		System.out.println(errlist);
		
//		System.out.println("屏蔽审查旧库");
//		errlist.add("----屏蔽审查旧库----");
//		screenreq3=xml.getScreenreq3();
//		screenres3=xml.getScreenres3();
//		keys=screenreq3.keySet().iterator();
//		while(keys.hasNext()){
//			String key=keys.next().toString();
//			jsonin=screenreq3.get(key).toString();
////			System.out.println(screenreq1.get(key));
//			jsonoutjava=getPassResult(jsonin,"screen");//pass-win输入串，pass审查得到输出串
//			jsonoutwin=screenres3.get(key).toString();//pass-win审查结果
//			Jsonduibi jsonduibi=new Jsonduibi();
//			jsonduibi.setJson(jsonoutwin);
//			jsonduibi.setJson1(jsonoutjava);
//			jsonduibi.Duibi();
//			duibirequest=jsonduibi.listerr;
//			if(duibirequest.size()>0){
//				errlist.add(key);
//			}
//		}
//		System.out.println(errlist);
		
//		System.out.println("系统审查新库");
//		errlist.add("----系统审查新库----");
//		screenreq4=xml.getScreenreq4();
//		screenres4=xml.getScreenres4();
//		keys=screenreq4.keySet().iterator();
//		while(keys.hasNext()){
//			String key=keys.next().toString();
//			jsonin=screenreq4.get(key).toString();
////			System.out.println(screenreq1.get(key));
//			jsonoutjava=getPassResult(jsonin,"screen");//pass-win输入串，pass审查得到输出串
//			jsonoutwin=screenres4.get(key).toString();//pass-win审查结果
//			Jsonduibi jsonduibi=new Jsonduibi();
//			jsonduibi.setJson(jsonoutwin);
//			jsonduibi.setJson1(jsonoutjava);
//			jsonduibi.Duibi();
//			duibirequest=jsonduibi.listerr;
//			if(duibirequest.size()>0){
//				errlist.add(key);
//			}
//		}
//		System.out.println(errlist);
		
//		System.out.println("自定义审查新库");
//		errlist.add("----自定义审查新库----");
//		screenreq5=xml.getScreenreq5();
//		screenres5=xml.getScreenres5();
//		keys=screenreq5.keySet().iterator();
//		while(keys.hasNext()){
//			String key=keys.next().toString();
//			jsonin=screenreq5.get(key).toString();
////			System.out.println(screenreq1.get(key));
//			jsonoutjava=getPassResult(jsonin,"screen");//pass-win输入串，pass审查得到输出串
//			jsonoutwin=screenres5.get(key).toString();//pass-win审查结果
//			Jsonduibi jsonduibi=new Jsonduibi();
//			jsonduibi.setJson(jsonoutwin);
//			jsonduibi.setJson1(jsonoutjava);
//			jsonduibi.Duibi();
//			duibirequest=jsonduibi.listerr;
//			if(duibirequest.size()>0){
//				errlist.add(key);
//			}
//		}
//		System.out.println(errlist);
		
//		System.out.println("屏蔽审查新库");
//		errlist.add("----屏蔽审查新库----");
//		screenreq6=xml.getScreenreq6();
//		screenres6=xml.getScreenres6();
//		keys=screenreq6.keySet().iterator();
//		while(keys.hasNext()){
//			String key=keys.next().toString();
//			jsonin=screenreq6.get(key).toString();
////			System.out.println(screenreq1.get(key));
//			jsonoutjava=getPassResult(jsonin,"screen");//pass-win输入串，pass审查得到输出串
//			jsonoutwin=screenres6.get(key).toString();//pass-win审查结果
//			Jsonduibi jsonduibi=new Jsonduibi();
//			jsonduibi.setJson(jsonoutwin);
//			jsonduibi.setJson1(jsonoutjava);
//			jsonduibi.Duibi();
//			duibirequest=jsonduibi.listerr;
//			if(duibirequest.size()>0){
//				errlist.add(key);
//			}
//		}
//		System.out.println(errlist);
		
//		System.out.println("特殊字符");
//		errlist.add("----特殊字符----");
//		screenreq7=xml.getScreenreq7();
//		screenres7=xml.getScreenres7();
//		keys=screenreq7.keySet().iterator();
//		while(keys.hasNext()){
//			String key=keys.next().toString();
//			jsonin=screenreq7.get(key).toString();
////			System.out.println(screenreq1.get(key));
//			jsonoutjava=getPassResult(jsonin,"screen");//pass-win输入串，pass审查得到输出串
//			jsonoutwin=screenres7.get(key).toString();//pass-win审查结果
//			Jsonduibi jsonduibi=new Jsonduibi();
//			jsonduibi.setJson(jsonoutwin);
//			jsonduibi.setJson1(jsonoutjava);
//			jsonduibi.Duibi();
//			duibirequest=jsonduibi.listerr;
//			if(duibirequest.size()>0){
//				errlist.add(key);
//			}
//		}
//		System.out.println(errlist);
		
		System.out.println("详细信息");
		errlist.add("----详细信息----");
		detailreq1=xml.getDetailreq1();
		detailres1=xml.getDetailres1();
		keys=detailreq1.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next().toString();
			jsonin=detailreq1.get(key).toString();
//			System.out.println(screenreq1.get(key));
			jsonoutjava=getPassResult(jsonin,"detail");//pass-win输入串，pass审查得到输出串
			jsonoutwin=detailres1.get(key).toString();//pass-win审查结果
			Jsonchaxun jsonduibi=new Jsonchaxun();
			jsonduibi.setJson(jsonoutwin);
			jsonduibi.setJson1(jsonoutjava);
			jsonduibi.Chaxunduibi();
			duibirequest=jsonduibi.getListerr1();
			if(duibirequest.size()>0){
				errlist.add(key);
			}
		}
		System.out.println(errlist);
		
//		System.out.println("查询");
//		errlist.add("----查询----");
//		queryreq1=xml.getQueryreq1();
//		queryres1=xml.getQueryres1();
//		keys=queryreq1.keySet().iterator();
//		while(keys.hasNext()){
//			String key=keys.next().toString();
//			jsonin=queryreq1.get(key).toString();
////			System.out.println(screenreq1.get(key));
//			jsonoutjava=getPassResult(jsonin,"query");//pass-win输入串，pass审查得到输出串
//			jsonoutwin=queryres1.get(key).toString();//pass-win审查结果
//			Jsonduibi jsonduibi=new Jsonduibi();
//			jsonduibi.setJson(jsonoutwin);
//			jsonduibi.setJson1(jsonoutjava);
//			jsonduibi.Duibi();
//			duibirequest=jsonduibi.listerr;
//			if(duibirequest.size()>0){
//				errlist.add(key);
//			}
//		}
//		System.out.println(errlist);
//		
//		System.out.println("浮动窗口");
//		errlist.add("----浮动窗口----");
//		queryreq2=xml.getQueryreq2();
//		queryres2=xml.getQueryres2();
//		keys=queryreq2.keySet().iterator();
//		while(keys.hasNext()){
//			String key=keys.next().toString();
//			jsonin=queryreq2.get(key).toString();
////			System.out.println(screenreq1.get(key));
//			jsonoutjava=getPassResult(jsonin,"query");//pass-win输入串，pass审查得到输出串
//			jsonoutwin=queryres2.get(key).toString();//pass-win审查结果
//			Jsonduibi jsonduibi=new Jsonduibi();
//			jsonduibi.setJson(jsonoutwin);
//			jsonduibi.setJson1(jsonoutjava);
//			jsonduibi.Duibi();
//			duibirequest=jsonduibi.listerr;
//			if(duibirequest.size()>0){
//				errlist.add(key);
//			}
//		}
//		System.out.println(errlist);
//		
//		System.out.println("用药理由");
//		errlist.add("----用药理由----");
//		reasonreq1=xml.getReasonreq1();
//		reasonres1=xml.getReasonres1();
//		keys=reasonreq1.keySet().iterator();
//		while(keys.hasNext()){
//			String key=keys.next().toString();
//			jsonin=reasonreq1.get(key).toString();
////			System.out.println(screenreq1.get(key));
//			jsonoutjava=getPassResult(jsonin,"reason");//pass-win输入串，pass审查得到输出串
//			jsonoutwin=reasonres1.get(key).toString();//pass-win审查结果
//			Jsonduibi jsonduibi=new Jsonduibi();
//			jsonduibi.setJson(jsonoutwin);
//			jsonduibi.setJson1(jsonoutjava);
//			jsonduibi.Duibi();
//			duibirequest=jsonduibi.listerr;
//			if(duibirequest.size()>0){
//				errlist.add(key);
//			}
//		}
//		System.out.println(errlist);
		
		if(errlist.size()>0){
			listerrsum=2;
			listerr1=errlist;
		}
	}
}
