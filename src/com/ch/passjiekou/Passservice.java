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
 * <li>类名称：  Passservice </li>
 * <li>类描述：PASSWEB接口调用，httpclient模拟调用接口   </li>
 * <li>创建人：</li>
 * <li>创建时间：2016年6月13日 </li>
 * <li>修改备注：</li>
 * </ul>
 */
public class Passservice {
	/**
	 * 设置超时时间
	 */
	private static int WS_TIMEOUT = 10 * 1000;

	public String getPassResult(String jsonin) throws TimeoutException, UnsupportedEncodingException {
		//PASS接口调整，需要转换字符串为URL编码
		String jsonin1="psJSONStr="+URLEncoder.encode(jsonin, "UTF-8");
//		System.out.println(jsonin1);
//		System.out.println(jsonin1); 

		String result = null;
//		String add_url = "http://172.18.7.159:8081/pass/ws/PASSwebService.asmx/Mc_DoScreen";
//		String add_url = "http://172.18.7.159:8081/pass/ws/query";
//		String add_url = "http://172.18.3.160:8081/pass/ws/isshowpa";
//		String add_url = "http://172.18.3.150:9099/pass/ws/isshowpa";
		String add_url = "http://172.18.3.160/PASS4WebService/PASSwebService.asmx/Mc_DoScreen";//passwin接口
		try {
			//创建一个客户端请求
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(add_url);
			
			// 设置请求和传输超时时间
//			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(WS_TIMEOUT).setConnectTimeout(WS_TIMEOUT).build();
//			httppost.setConfig(requestConfig);
			
			// 设置连接类型,包括字符集等。。http.UTF-8
			StringEntity stringEntity = new StringEntity(jsonin1, ContentType.APPLICATION_FORM_URLENCODED);
//			StringEntity stringEntity = new StringEntity(jsonin1);
//			stringEntity.setContentType("application/x-www-form-urlencoded");
//			stringEntity.setContentEncoding("UTF-8");
			httppost.setEntity(stringEntity);
			
			//申明发送请求对象
			HttpResponse httpResponse = httpClient.execute(httppost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// 从响应的结果中获取响应体的数据
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);
				// System.out.println(result);
			}else{
				result=httpResponse.getStatusLine().toString();
			}
			httpClient.close();
		} 
//		catch (ConnectTimeoutException ex) {
//			throw new TimeoutException("连接超时");
//		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}
}
