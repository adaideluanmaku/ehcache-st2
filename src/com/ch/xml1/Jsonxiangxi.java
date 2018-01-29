package com.ch.xml1;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Jsonxiangxi {
	private String json=null;
	private String json1=null;
	private List listerr1;
	private int listerrsum=0;
	private int moduleidstr=-1;
	private int duibistate=1;
	private String ip;
	
	public int getDuibistate() {
		return duibistate;
	}

	public void setDuibistate(int duibistate) {
		this.duibistate = duibistate;
	}

	public int getModuleidstr() {
		return moduleidstr;
	}

	public void setModuleidstr(int moduleidstr) {
		this.moduleidstr = moduleidstr;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getJson1() {
		return json1;
	}

	public void setJson1(String json1) {
		this.json1 = json1;
	}

	public List getListerr1() {
		return listerr1;
	}

	public void setListerr1(List listerr) {
		this.listerr1 = listerr;
	}

	public int getListerrsum() {
		return listerrsum;
	}

	public void setListerrsum(int listerrsum) {
		this.listerrsum = listerrsum;
	}
	
	//�����������Ϊxml��ʽ��ȡ�����CDATA���ݳ���
	public String Xmltojson(String jsoncdata) throws DocumentException{
		SAXReader reader=new SAXReader();
		Document document=reader.read(new StringReader(jsoncdata));
		Element root=document.getRootElement();
//				System.out.println(root.getText());
		String jsoncdata1=root.getText();
		return jsoncdata1;
	}
	
	public void Xiangxiduibi() throws DocumentException{
		List listerr=new ArrayList();
		if("".equals(json)||"".equals(json1)){
			listerrsum=1;
			System.out.println("��ϸ��Ϣ�������벻����");
			listerr.add("��ϸ��Ϣ�������벻����");
			setListerr1(listerr);
			Date data=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("IP:"+ip+"��"+sf.format(data));
		}
		
		if("<?xml".equals(json.substring(0,5))){
			json=Xmltojson(json);
		}
		if("<?xml".equals(json1.substring(0,5))){
			json1=Xmltojson(json1);
		}
		
		try{//��ֹ�����json�����쳣
			JSONObject jsonin=JSONObject.fromObject(json);
			JSONObject jsonin1=JSONObject.fromObject(json1);
			System.out.println(jsonin);
			System.out.println(jsonin1);
			
			if(!jsonin.getString("DetailType").equals(jsonin1.getString("DetailType"))){
				System.out.println("���ԣ�DetailType"+":"+jsonin.getString("DetailType"));
				System.out.println("��Ӧ��DetailType"+":"+jsonin1.getString("DetailType"));
				listerr.add("���ԣ�DetailType"+":"+jsonin.getString("DetailType"));
				listerr.add("��Ӧ��DetailType"+":"+jsonin1.getString("DetailType"));
			}
			JSONArray names=jsonin.names();
			for(int i=0;i<names.size();i++){
				String name=names.getString(i);
				if(!jsonin.getString(name).equals(jsonin1.getString(name))&&!"DetailType".equals(name)){
					System.out.println("���ԣ�"+name+":"+jsonin.getString(name));
					System.out.println("��Ӧ��"+name+":"+jsonin1.getString(name));
					listerr.add("���ԣ�"+name+":"+jsonin.getString(name));
					listerr.add("��Ӧ��"+name+":"+jsonin1.getString(name));
				}
			}
			
			if(listerr.size()!=0){
				listerrsum=1;
			}
			setListerr1(listerr);
			Date data=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("IP:"+ip+"��"+sf.format(data));
		}catch(Exception ex){
			listerrsum=1;
			System.out.println("json��ϸ�ڵ����ƿ��ܴ�������");
			listerr.add("json��ϸ�ڵ����ƿ��ܴ�������");
			setListerr1(listerr);
			Date data=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("IP:"+ip+"��"+sf.format(data));
		}
		
	}
}
