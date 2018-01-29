package com.ch.xml;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;

//PASSWEB接口调用，httpclient模拟调用接口
public class Jmeter {
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
	
	public String jsonin=null;
	public String jsonoutwin=null;
	public String jsonoutjava=null;
	public List duibirequest=null;
	public List errlist=null;
	public List listerr1=null;
	public int listerrsum=0;
	public int duibistate=1;
	
	/**
	 * 设置超时时间
	 */
	private static int WS_TIMEOUT = 30 * 1000;
	
	//连接接口
	@SuppressWarnings({ "deprecation", "resource" })
	public static String getPassResult(String jsonin,String jiekou) throws UnsupportedEncodingException, TimeoutException {
		//PASS接口调整，需要转换字符串为URL编码
				String jsonin1="psJSONStr="+URLEncoder.encode(jsonin, "UTF-8");
//				System.out.println(jsonin1);  
				String result = null;
//				System.out.println("AAAA"+jsonin);
				String add_url = "http://172.18.7.159:8081/pass/ws/PASSwebService.asmx/"+jiekou;

				try {
					//创建一个客户端请求
					CloseableHttpClient httpClient = HttpClients.createDefault();

					HttpPost httppost = new HttpPost(add_url);
					// 设置请求和传输超时时间
					RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(WS_TIMEOUT).setConnectTimeout(WS_TIMEOUT).build();
					httppost.setConfig(requestConfig);
					// 设置连接类型,包括字符集等。。http.UTF-8
//					StringEntity stringEntity = new StringEntity(jsonin, ContentType.APPLICATION_FORM_URLENCODED);
					StringEntity stringEntity = new StringEntity(jsonin1);
					stringEntity.setContentType("application/x-www-form-urlencoded");
					stringEntity.setContentEncoding("UTF-8");
					httppost.setEntity(stringEntity);
					
					//申明发送请求对象
					HttpResponse httpResponse = httpClient.execute(httppost);
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						// 从响应的结果中获取响应体的数据
						HttpEntity httpEntity = httpResponse.getEntity();
						result = EntityUtils.toString(httpEntity);
						// System.out.println(result);
					}
					httpClient.close();
				} catch (ConnectTimeoutException ex) {
					throw new TimeoutException("连接超时");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				return result;
	}
	public String execute() throws DocumentException, TimeoutException, IOException{
		errlist=new ArrayList();
		Iterator keys=null;
		
		//获取JMETER脚本数据
		Jmeterxml xml=new Jmeterxml();
		xml.Modul();
		
		
		System.out.println("系统审查旧库");
		errlist.add("----系统审查旧库----");
		screenreq1=xml.getScreenreq1();
		screenres1=xml.getScreenres1();
		keys=screenreq1.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next().toString();
			jsonin=screenreq1.get(key).toString();
//			System.out.println(screenreq1.get(key));
			jsonoutjava=getPassResult(jsonin,"Mc_DoScreen");//pass-win输入串，pass审查得到输出串
			jsonoutwin=screenres1.get(key).toString();//pass-win审查结果
			Jsonshencha jsonduibi=new Jsonshencha();
			jsonduibi.setJson(jsonoutwin);
			jsonduibi.setJson1(jsonoutjava);
			jsonduibi.Duibi();
			duibirequest=jsonduibi.getListerr1();
			if(duibirequest.size()>0){
				errlist.add(key);
			}
		}
		System.out.println(errlist);
		
		
		System.out.println("自定义审查旧库");
		errlist.add("----自定义审查旧库----");
		screenreq2=xml.getScreenreq2();
		screenres2=xml.getScreenres2();
		keys=screenreq2.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next().toString();
			jsonin=screenreq2.get(key).toString();
//			System.out.println(screenreq1.get(key));
			jsonoutjava=getPassResult(jsonin,"Mc_DoScreen");//pass-win输入串，pass审查得到输出串
			jsonoutwin=screenres2.get(key).toString();//pass-win审查结果
			Jsonshencha jsonduibi=new Jsonshencha();//json对比
			jsonduibi.setJson(jsonoutwin);
			jsonduibi.setJson1(jsonoutjava);
			jsonduibi.Duibi();
			duibirequest=jsonduibi.getListerr1();
			if(duibirequest.size()>0){
				errlist.add(key);
			}
		}
		System.out.println(errlist);
		
		System.out.println("屏蔽审查旧库");
		errlist.add("----屏蔽审查旧库----");
		screenreq3=xml.getScreenreq3();
		screenres3=xml.getScreenres3();
		keys=screenreq3.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next().toString();
			jsonin=screenreq3.get(key).toString();
//			System.out.println(screenreq1.get(key));
			jsonoutjava=getPassResult(jsonin,"Mc_DoScreen");//pass-win输入串，pass审查得到输出串
			jsonoutwin=screenres3.get(key).toString();//pass-win审查结果
			Jsonshencha jsonduibi=new Jsonshencha();
			jsonduibi.setJson(jsonoutwin);
			jsonduibi.setJson1(jsonoutjava);
			jsonduibi.Duibi();
			duibirequest=jsonduibi.getListerr1();
			if(duibirequest.size()>0){
				errlist.add(key);
			}
		}
		System.out.println(errlist);
		
//		System.out.println("系统审查新库");
//		errlist.add("----系统审查新库----");
//		screenreq4=xml.getScreenreq4();
//		screenres4=xml.getScreenres4();
//		keys=screenreq4.keySet().iterator();
//		while(keys.hasNext()){
//			String key=keys.next().toString();
//			jsonin=screenreq4.get(key).toString();
////			System.out.println(screenreq1.get(key));
//			jsonoutjava=getPassResult(jsonin,"Mc_DoScreen");//pass-win输入串，pass审查得到输出串
//			jsonoutwin=screenres4.get(key).toString();//pass-win审查结果
//			Jsonshencha jsonduibi=new Jsonshencha();
//			jsonduibi.setJson(jsonoutwin);
//			jsonduibi.setJson1(jsonoutjava);
//			jsonduibi.Duibi();
//			duibirequest=jsonduibi.getListerr1();
//			if(duibirequest.size()>0){
//				errlist.add(key);
//			}
//		}
//		System.out.println(errlist);
//		
//		System.out.println("自定义审查新库");
//		errlist.add("----自定义审查新库----");
//		screenreq5=xml.getScreenreq5();
//		screenres5=xml.getScreenres5();
//		keys=screenreq5.keySet().iterator();
//		while(keys.hasNext()){
//			String key=keys.next().toString();
//			jsonin=screenreq5.get(key).toString();
////			System.out.println(screenreq1.get(key));
//			jsonoutjava=getPassResult(jsonin,"Mc_DoScreen");//pass-win输入串，pass审查得到输出串
//			jsonoutwin=screenres5.get(key).toString();//pass-win审查结果
//			Jsonshencha jsonduibi=new Jsonshencha();
//			jsonduibi.setJson(jsonoutwin);
//			jsonduibi.setJson1(jsonoutjava);
//			jsonduibi.Duibi();
//			duibirequest=jsonduibi.getListerr1();
//			if(duibirequest.size()>0){
//				errlist.add(key);
//			}
//		}
//		System.out.println(errlist);
//		
//		System.out.println("屏蔽审查新库");
//		errlist.add("----屏蔽审查新库----");
//		screenreq6=xml.getScreenreq6();
//		screenres6=xml.getScreenres6();
//		keys=screenreq6.keySet().iterator();
//		while(keys.hasNext()){
//			String key=keys.next().toString();
//			jsonin=screenreq6.get(key).toString();
////			System.out.println(screenreq1.get(key));
//			jsonoutjava=getPassResult(jsonin,"Mc_DoScreen");//pass-win输入串，pass审查得到输出串
//			jsonoutwin=screenres6.get(key).toString();//pass-win审查结果
//			Jsonshencha jsonduibi=new Jsonshencha();
//			jsonduibi.setJson(jsonoutwin);
//			jsonduibi.setJson1(jsonoutjava);
//			jsonduibi.Duibi();
//			duibirequest=jsonduibi.getListerr1();
//			if(duibirequest.size()>0){
//				errlist.add(key);
//			}
//		}
//		System.out.println(errlist);
		
		System.out.println("特殊字符");
		errlist.add("----特殊字符----");
		screenreq7=xml.getScreenreq7();
		screenres7=xml.getScreenres7();
		keys=screenreq7.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next().toString();
			jsonin=screenreq7.get(key).toString();
//			System.out.println(screenreq7.get(key));
			jsonoutjava=getPassResult(jsonin,"Mc_DoScreen");//pass-win输入串，pass审查得到输出串
			jsonoutwin=screenres7.get(key).toString();//pass-win审查结果
			Jsonshencha jsonduibi=new Jsonshencha();
			jsonduibi.setJson(jsonoutwin);
			jsonduibi.setJson1(jsonoutjava);
			jsonduibi.Duibi();
			duibirequest=jsonduibi.getListerr1();
			if(duibirequest.size()>0){
				errlist.add(key);
			}
		}
		System.out.println(errlist);
		
		System.out.println("详细信息");
		errlist.add("----详细信息----");
		detailreq1=xml.getDetailreq1();
		detailres1=xml.getDetailres1();
		keys=detailreq1.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next().toString();
			jsonin=detailreq1.get(key).toString();
//			System.out.println(screenreq1.get(key));
			jsonoutjava=getPassResult(jsonin,"Mc_DoDetail");//pass-win输入串，pass审查得到输出串
			jsonoutwin=detailres1.get(key).toString();//pass-win审查结果
			Jsonxiangxi jsonduibi=new Jsonxiangxi();
			jsonduibi.setJson(jsonoutwin);
			jsonduibi.setJson1(jsonoutjava);
			jsonduibi.Xiangxiduibi();
			duibirequest=jsonduibi.getListerr1();
			if(duibirequest.size()>0){
				errlist.add(key);
			}
		}
		System.out.println(errlist);
		
		System.out.println("查询");
		errlist.add("----查询----");
		queryreq1=xml.getQueryreq1();
		queryres1=xml.getQueryres1();
		keys=queryreq1.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next().toString();
			jsonin=queryreq1.get(key).toString();
//			System.out.println(screenreq1.get(key));
			jsonoutjava=getPassResult(jsonin,"Mc_DoQuery");//pass-win输入串，pass审查得到输出串
			jsonoutwin=queryres1.get(key).toString();//pass-win审查结果
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
		
		System.out.println("浮动窗口");
		errlist.add("----浮动窗口----");
		queryreq2=xml.getQueryreq2();
		queryres2=xml.getQueryres2();
		keys=queryreq2.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next().toString();
			jsonin=queryreq2.get(key).toString();
//			System.out.println(queryreq2.get(key));
			jsonoutjava=getPassResult(jsonin,"Mc_DoQuery");//pass-win输入串，pass审查得到输出串
			jsonoutwin=queryres2.get(key).toString();//pass-win审查结果
			Jsonchaxun jsonduibi=new Jsonchaxun();
//			System.out.println(jsonoutwin);
//			System.out.println(jsonoutjava);
			jsonduibi.setJson(jsonoutwin);
			jsonduibi.setJson1(jsonoutjava);
			jsonduibi.Chaxunduibi();
			duibirequest=jsonduibi.getListerr1();
//			System.out.println(duibirequest);
			if(duibirequest.size()>0){
				errlist.add(key);
			}
		}
		System.out.println(errlist);
		
		System.out.println("用药理由");
		errlist.add("----用药理由----");
		reasonreq1=xml.getReasonreq1();
		reasonres1=xml.getReasonres1();
		keys=reasonreq1.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next().toString();
			jsonin=reasonreq1.get(key).toString();
//			System.out.println(reasonreq1.get(key));
			jsonoutjava=getPassResult(jsonin,"Mc_DoReason");//pass-win输入串，pass审查得到输出串
			jsonoutwin=reasonres1.get(key).toString();//pass-win审查结果
//			System.out.println("json:"+jsonoutwin);
//			System.out.println("json1:"+jsonoutjava);
			if(jsonoutwin.length()<200){
				if(!jsonoutjava.equals(jsonoutwin)){
					errlist.add(key);
				}
			}else{
				jsonoutjava=getPassResult(jsonin,"Mc_DoScreen");//pass-win输入串，pass审查得到输出串
				Jsonshencha jsonduibi=new Jsonshencha();
				jsonduibi.setJson(jsonoutwin);
				jsonduibi.setJson1(jsonoutjava);
				jsonduibi.Duibi();
				duibirequest=jsonduibi.getListerr1();
				if(duibirequest.size()>0){
					errlist.add(key);
				}
			}
		}
		System.out.println(errlist);
		
		if(errlist.size()>0){
			listerrsum=2;
			listerr1=errlist;
		}
		return "success";
	}
}
