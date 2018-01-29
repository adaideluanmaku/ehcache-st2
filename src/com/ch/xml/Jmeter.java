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

//PASSWEB�ӿڵ��ã�httpclientģ����ýӿ�
public class Jmeter {
	public static Map screenreq1;//ϵͳ���ɿ�
	public static Map screenres1;
	
	public static Map screenreq2;//�Զ������ɿ�
	public static Map screenres2;
	
	public static Map screenreq3;//�������ɿ�
	public static Map screenres3;
	
	public static Map screenreq4;//ϵͳ����¿�
	public static Map screenres4;
	
	public static Map screenreq5;//�Զ�������¿�
	public static Map screenres5;
	
	public static Map screenreq6;//��������¿�
	public static Map screenres6;
	
	public static Map screenreq7;//�����ַ�
	public static Map screenres7;
	
	public static Map detailreq1;//��ϸ��Ϣ
	public static Map detailres1;
	
	public static Map queryreq1;//��ѯ
	public static Map queryres1;
	
	public static Map queryreq2;//��������
	public static Map queryres2;
	
	public static Map reasonreq1;//��ҩ����
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
	 * ���ó�ʱʱ��
	 */
	private static int WS_TIMEOUT = 30 * 1000;
	
	//���ӽӿ�
	@SuppressWarnings({ "deprecation", "resource" })
	public static String getPassResult(String jsonin,String jiekou) throws UnsupportedEncodingException, TimeoutException {
		//PASS�ӿڵ�������Ҫת���ַ���ΪURL����
				String jsonin1="psJSONStr="+URLEncoder.encode(jsonin, "UTF-8");
//				System.out.println(jsonin1);  
				String result = null;
//				System.out.println("AAAA"+jsonin);
				String add_url = "http://172.18.7.159:8081/pass/ws/PASSwebService.asmx/"+jiekou;

				try {
					//����һ���ͻ�������
					CloseableHttpClient httpClient = HttpClients.createDefault();

					HttpPost httppost = new HttpPost(add_url);
					// ��������ʹ��䳬ʱʱ��
					RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(WS_TIMEOUT).setConnectTimeout(WS_TIMEOUT).build();
					httppost.setConfig(requestConfig);
					// ������������,�����ַ����ȡ���http.UTF-8
//					StringEntity stringEntity = new StringEntity(jsonin, ContentType.APPLICATION_FORM_URLENCODED);
					StringEntity stringEntity = new StringEntity(jsonin1);
					stringEntity.setContentType("application/x-www-form-urlencoded");
					stringEntity.setContentEncoding("UTF-8");
					httppost.setEntity(stringEntity);
					
					//���������������
					HttpResponse httpResponse = httpClient.execute(httppost);
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						// ����Ӧ�Ľ���л�ȡ��Ӧ�������
						HttpEntity httpEntity = httpResponse.getEntity();
						result = EntityUtils.toString(httpEntity);
						// System.out.println(result);
					}
					httpClient.close();
				} catch (ConnectTimeoutException ex) {
					throw new TimeoutException("���ӳ�ʱ");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				return result;
	}
	public String execute() throws DocumentException, TimeoutException, IOException{
		errlist=new ArrayList();
		Iterator keys=null;
		
		//��ȡJMETER�ű�����
		Jmeterxml xml=new Jmeterxml();
		xml.Modul();
		
		
		System.out.println("ϵͳ���ɿ�");
		errlist.add("----ϵͳ���ɿ�----");
		screenreq1=xml.getScreenreq1();
		screenres1=xml.getScreenres1();
		keys=screenreq1.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next().toString();
			jsonin=screenreq1.get(key).toString();
//			System.out.println(screenreq1.get(key));
			jsonoutjava=getPassResult(jsonin,"Mc_DoScreen");//pass-win���봮��pass���õ������
			jsonoutwin=screenres1.get(key).toString();//pass-win�����
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
		
		
		System.out.println("�Զ������ɿ�");
		errlist.add("----�Զ������ɿ�----");
		screenreq2=xml.getScreenreq2();
		screenres2=xml.getScreenres2();
		keys=screenreq2.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next().toString();
			jsonin=screenreq2.get(key).toString();
//			System.out.println(screenreq1.get(key));
			jsonoutjava=getPassResult(jsonin,"Mc_DoScreen");//pass-win���봮��pass���õ������
			jsonoutwin=screenres2.get(key).toString();//pass-win�����
			Jsonshencha jsonduibi=new Jsonshencha();//json�Ա�
			jsonduibi.setJson(jsonoutwin);
			jsonduibi.setJson1(jsonoutjava);
			jsonduibi.Duibi();
			duibirequest=jsonduibi.getListerr1();
			if(duibirequest.size()>0){
				errlist.add(key);
			}
		}
		System.out.println(errlist);
		
		System.out.println("�������ɿ�");
		errlist.add("----�������ɿ�----");
		screenreq3=xml.getScreenreq3();
		screenres3=xml.getScreenres3();
		keys=screenreq3.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next().toString();
			jsonin=screenreq3.get(key).toString();
//			System.out.println(screenreq1.get(key));
			jsonoutjava=getPassResult(jsonin,"Mc_DoScreen");//pass-win���봮��pass���õ������
			jsonoutwin=screenres3.get(key).toString();//pass-win�����
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
		
//		System.out.println("ϵͳ����¿�");
//		errlist.add("----ϵͳ����¿�----");
//		screenreq4=xml.getScreenreq4();
//		screenres4=xml.getScreenres4();
//		keys=screenreq4.keySet().iterator();
//		while(keys.hasNext()){
//			String key=keys.next().toString();
//			jsonin=screenreq4.get(key).toString();
////			System.out.println(screenreq1.get(key));
//			jsonoutjava=getPassResult(jsonin,"Mc_DoScreen");//pass-win���봮��pass���õ������
//			jsonoutwin=screenres4.get(key).toString();//pass-win�����
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
//		System.out.println("�Զ�������¿�");
//		errlist.add("----�Զ�������¿�----");
//		screenreq5=xml.getScreenreq5();
//		screenres5=xml.getScreenres5();
//		keys=screenreq5.keySet().iterator();
//		while(keys.hasNext()){
//			String key=keys.next().toString();
//			jsonin=screenreq5.get(key).toString();
////			System.out.println(screenreq1.get(key));
//			jsonoutjava=getPassResult(jsonin,"Mc_DoScreen");//pass-win���봮��pass���õ������
//			jsonoutwin=screenres5.get(key).toString();//pass-win�����
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
//		System.out.println("��������¿�");
//		errlist.add("----��������¿�----");
//		screenreq6=xml.getScreenreq6();
//		screenres6=xml.getScreenres6();
//		keys=screenreq6.keySet().iterator();
//		while(keys.hasNext()){
//			String key=keys.next().toString();
//			jsonin=screenreq6.get(key).toString();
////			System.out.println(screenreq1.get(key));
//			jsonoutjava=getPassResult(jsonin,"Mc_DoScreen");//pass-win���봮��pass���õ������
//			jsonoutwin=screenres6.get(key).toString();//pass-win�����
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
		
		System.out.println("�����ַ�");
		errlist.add("----�����ַ�----");
		screenreq7=xml.getScreenreq7();
		screenres7=xml.getScreenres7();
		keys=screenreq7.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next().toString();
			jsonin=screenreq7.get(key).toString();
//			System.out.println(screenreq7.get(key));
			jsonoutjava=getPassResult(jsonin,"Mc_DoScreen");//pass-win���봮��pass���õ������
			jsonoutwin=screenres7.get(key).toString();//pass-win�����
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
		
		System.out.println("��ϸ��Ϣ");
		errlist.add("----��ϸ��Ϣ----");
		detailreq1=xml.getDetailreq1();
		detailres1=xml.getDetailres1();
		keys=detailreq1.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next().toString();
			jsonin=detailreq1.get(key).toString();
//			System.out.println(screenreq1.get(key));
			jsonoutjava=getPassResult(jsonin,"Mc_DoDetail");//pass-win���봮��pass���õ������
			jsonoutwin=detailres1.get(key).toString();//pass-win�����
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
		
		System.out.println("��ѯ");
		errlist.add("----��ѯ----");
		queryreq1=xml.getQueryreq1();
		queryres1=xml.getQueryres1();
		keys=queryreq1.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next().toString();
			jsonin=queryreq1.get(key).toString();
//			System.out.println(screenreq1.get(key));
			jsonoutjava=getPassResult(jsonin,"Mc_DoQuery");//pass-win���봮��pass���õ������
			jsonoutwin=queryres1.get(key).toString();//pass-win�����
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
		
		System.out.println("��������");
		errlist.add("----��������----");
		queryreq2=xml.getQueryreq2();
		queryres2=xml.getQueryres2();
		keys=queryreq2.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next().toString();
			jsonin=queryreq2.get(key).toString();
//			System.out.println(queryreq2.get(key));
			jsonoutjava=getPassResult(jsonin,"Mc_DoQuery");//pass-win���봮��pass���õ������
			jsonoutwin=queryres2.get(key).toString();//pass-win�����
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
		
		System.out.println("��ҩ����");
		errlist.add("----��ҩ����----");
		reasonreq1=xml.getReasonreq1();
		reasonres1=xml.getReasonres1();
		keys=reasonreq1.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next().toString();
			jsonin=reasonreq1.get(key).toString();
//			System.out.println(reasonreq1.get(key));
			jsonoutjava=getPassResult(jsonin,"Mc_DoReason");//pass-win���봮��pass���õ������
			jsonoutwin=reasonres1.get(key).toString();//pass-win�����
//			System.out.println("json:"+jsonoutwin);
//			System.out.println("json1:"+jsonoutjava);
			if(jsonoutwin.length()<200){
				if(!jsonoutjava.equals(jsonoutwin)){
					errlist.add(key);
				}
			}else{
				jsonoutjava=getPassResult(jsonin,"Mc_DoScreen");//pass-win���봮��pass���õ������
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
