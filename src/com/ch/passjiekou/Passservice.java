package com.ch.passjiekou;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

	public String getPassResult(String jsonin) throws TimeoutException, UnsupportedEncodingException {
		//PASS�ӿڵ�������Ҫת���ַ���ΪURL����
		String jsonin1="psJSONStr="+URLEncoder.encode(jsonin, "UTF-8");
//		System.out.println(jsonin1);
//		System.out.println(jsonin1); 

		String result = null;
//		String add_url = "http://172.18.7.159:8081/pass/ws/PASSwebService.asmx/Mc_DoScreen";
//		String add_url = "http://172.18.7.159:8081/pass/ws/query";
//		String add_url = "http://172.18.3.160:8081/pass/ws/isshowpa";
//		String add_url = "http://172.18.3.150:9099/pass/ws/isshowpa";
		String add_url = "http://172.18.3.160/PASS4WebService/PASSwebService.asmx/Mc_DoScreen";//passwin�ӿ�
		try {
			//����һ���ͻ�������
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(add_url);
			
			// ��������ʹ��䳬ʱʱ��
//			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(WS_TIMEOUT).setConnectTimeout(WS_TIMEOUT).build();
//			httppost.setConfig(requestConfig);
			
			// ������������,�����ַ����ȡ���http.UTF-8
			StringEntity stringEntity = new StringEntity(jsonin1, ContentType.APPLICATION_FORM_URLENCODED);
//			StringEntity stringEntity = new StringEntity(jsonin1);
//			stringEntity.setContentType("application/x-www-form-urlencoded");
//			stringEntity.setContentEncoding("UTF-8");
			httppost.setEntity(stringEntity);
			
			//���������������
			HttpResponse httpResponse = httpClient.execute(httppost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// ����Ӧ�Ľ���л�ȡ��Ӧ�������
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);
				// System.out.println(result);
			}else{
				result=httpResponse.getStatusLine().toString();
			}
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
}
