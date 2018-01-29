package com.ch.BStool;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


/**
 * <ul>
 * <li>�����ƣ�  Passservice </li>
 * <li>��������PASSWEB�ӿڵ��ã�httpclientģ����ýӿ�   </li>
 * <li>�����ˣ�</li>
 * <li>����ʱ�䣺2016��6��13�� </li>
 * <li>�޸ı�ע��</li>
 * </ul>
 */
public class Passservice {
	/**
	 * ���ó�ʱʱ��
	 */
	private static int WS_TIMEOUT = 10 * 1000;

	public String getPassResult(String jsonin,String url) throws TimeoutException, UnsupportedEncodingException {
		//PASS�ӿڵ�������Ҫת���ַ���ΪURL����
		String jsoninxml="psJSONStr="+URLEncoder.encode(jsonin, "UTF-8");
//		String jsoninxml="psJSONStr="+jsonin;
		System.out.println(jsoninxml);
		String result = null;
		String add_url = url;
		try {
			//����һ���ͻ�������
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(add_url);
			
			// ��������ʹ��䳬ʱʱ��
//			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(WS_TIMEOUT).setConnectTimeout(WS_TIMEOUT).build();
//			httppost.setConfig(requestConfig);
			
			//�ж��Ƿ���json���룬�������ֵ���PASS�ӿں�redis�ӿ�
			if(!"".equals(jsonin)){
				// ������������,�����ַ����ȡ���http.UTF-8
				StringEntity stringEntity = new StringEntity(jsoninxml, ContentType.APPLICATION_FORM_URLENCODED);
			
	//			StringEntity stringEntity = new StringEntity(jsonin1);
				stringEntity.setContentType("application/x-www-form-urlencoded");
				stringEntity.setContentEncoding("UTF-8");
				httppost.setEntity(stringEntity);
			}else{
				StringEntity stringEntity = new StringEntity(HTTP.UTF_8);
				
				stringEntity.setContentType("application/x-www-form-urlencoded");
//				stringEntity.setContentEncoding("UTF-8");
				httppost.setEntity(stringEntity);
			}
			
			//���������������
			HttpResponse httpResponse = httpClient.execute(httppost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// ����Ӧ�Ľ���л�ȡ��Ӧ�������
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);
			}else{
				result=httpResponse.getStatusLine().toString();
			}
//			System.out.println("PASS���봮��"+jsonin);
			System.out.println("result: "+result);
			httpClient.close();
		} 
//		catch (ConnectTimeoutException ex) {
//			throw new TimeoutException("���ӳ�ʱ");
//		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}
	
	public static void main(String args[]) throws IOException, TimeoutException{
//		InputStreamReader in=new InputStreamReader(Passservice.class.getClassLoader().getResourceAsStream("json.properties"),"UTF-8");
		Properties pro=new Properties();
		pro.load(new InputStreamReader(Passservice.class.getClassLoader().getResourceAsStream("json.properties"),"UTF-8"));
		String json=null;
		json=pro.getProperty("json");
		
		Passservice passservice=new Passservice();
		String url="http://172.18.3.145/PASS4WebService/PASSwebService.asmx/Mc_DoQuery";
//		System.out.println(json);
		String result=passservice.getPassResult(json, url);
		System.out.println(result);
	}
}
