package com.ch.xml;

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

public class Jsonchaxun {
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
	
	//如果输入数据为xml格式，取里面的CDATA数据出来
	public String Xmltojson(String jsoncdata) throws DocumentException{
//		System.out.println("aaaaaa:"+jsoncdata);
		SAXReader reader=new SAXReader();
		Document document=reader.read(new StringReader(jsoncdata));
		Element root=document.getRootElement();
//			System.out.println(root.getText());
		String jsoncdata1=root.getText();
		return jsoncdata1;
	}
	
	public void Chaxunduibi() throws DocumentException{
		List listerr=new ArrayList();
		if("".equals(json)||"".equals(json1)){
			listerrsum=1;
			System.out.println("查询数据输入不完整");
			listerr.add("查询数据输入不完整");
			setListerr1(listerr);
			Date data=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("IP:"+ip+"，"+sf.format(data));
		}
		
		if("<?xml".equals(json.substring(0,5))){
			json=Xmltojson(json);
		}
		if("<?xml".equals(json1.substring(0,5))){
			json1=Xmltojson(json1);
		}
		
		try{
			JSONObject jsonin=JSONObject.fromObject(json);
			JSONObject jsonin1=JSONObject.fromObject(json1);
			System.out.println(jsonin);
			System.out.println(jsonin1);
			
			if(!jsonin.getString("DisplayMode").equals(jsonin1.getString("DisplayMode"))){
				System.out.println("断言：--DisplayMode"+":  "+jsonin.getString("DisplayMode"));
				System.out.println("响应：--DisplayMode"+":  "+jsonin1.getString("DisplayMode"));
				listerr.add("断言：--DisplayMode"+":  "+jsonin.getString("DisplayMode"));
				listerr.add("响应：--DisplayMode"+":  "+jsonin1.getString("DisplayMode"));
			}
			
			JSONArray names=jsonin.names();
			for(int i=0;i<names.size();i++){
				String name=names.getString(i);
				if(!jsonin.getString(name).equals(jsonin1.getString(name))&&!"DisplayMode".equals(name)&&!"ReferenceResults".equals(name)){
					System.out.println("断言：--"+name+":  "+jsonin.getString(name));
					System.out.println("响应：--"+name+":  "+jsonin1.getString(name));
					listerr.add("断言：--"+name+":  "+jsonin.getString(name));
					listerr.add("响应：--"+name+":  "+jsonin1.getString(name));
				}
			}
			
			JSONArray ReferenceResults=jsonin.getJSONArray("ReferenceResults");
			JSONArray ReferenceResults1=jsonin1.getJSONArray("ReferenceResults");
			
			if((ReferenceResults.size()==0||ReferenceResults1.size()==0)&&ReferenceResults.size()!=ReferenceResults1.size()){
				System.out.println("断言：--ReferenceResults总数:  "+ReferenceResults.size());
				System.out.println("响应：--ReferenceResults总数:  "+ReferenceResults1.size());
				listerr.add("断言：--ReferenceResults总数:  "+ReferenceResults.size());
				listerr.add("响应：--ReferenceResults总数:  "+ReferenceResults1.size());
			}
			
			if(ReferenceResults.size()!=0&&ReferenceResults1.size()!=0&&ReferenceResults.size()==ReferenceResults1.size()){
				for(int i=0;i<ReferenceResults.size();i++){
					JSONObject ReferenceResultsn=ReferenceResults.getJSONObject(i);
					JSONObject ReferenceResultsn1=ReferenceResults1.getJSONObject(i);
					
					JSONArray ReferenceResultsnnames=ReferenceResultsn.names();
					for(int j=0;j<ReferenceResultsnnames.size();j++){
						String ReferenceResultsnname=ReferenceResultsnnames.getString(j);
						if(!ReferenceResultsn.get(ReferenceResultsnname).equals(ReferenceResultsn1.get(ReferenceResultsnname))&&!ReferenceResultsnname.equals("BriefItems")){
							System.out.println("断言：--"+ReferenceResultsnname+":  "+ReferenceResultsn.getString(ReferenceResultsnname));
							System.out.println("响应：--"+ReferenceResultsnname+":  "+ReferenceResultsn1.getString(ReferenceResultsnname));
							listerr.add("断言：--"+ReferenceResultsnname+":  "+ReferenceResultsn.getString(ReferenceResultsnname));
							listerr.add("响应：--"+ReferenceResultsnname+":  "+ReferenceResultsn1.getString(ReferenceResultsnname));
						}
					}
					
					if(!ReferenceResultsn.get("BriefItems").equals(ReferenceResultsn1.get("BriefItems"))){
						System.out.println("断言：--BriefItems:  "+ReferenceResultsn.getString("BriefItems"));
						System.out.println("响应：--BriefItems:  "+ReferenceResultsn1.getString("BriefItems"));
						listerr.add("断言：--BriefItems:  "+ReferenceResultsn.getString("BriefItems"));
						listerr.add("响应：--BriefItems:  "+ReferenceResultsn1.getString("BriefItems"));
					}
				}
			}
			
			if(listerr.size()!=0){
				listerrsum=1;
			}
			setListerr1(listerr);
			Date data=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("IP:"+ip+"，"+sf.format(data));
		}catch(Exception ex){
			listerrsum=1;
			System.out.println("json查询节点名称可能存在问题");
			listerr.add("json查询节点名称可能存在问题");
			setListerr1(listerr);
			Date data=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("IP:"+ip+"，"+sf.format(data));
		}
	}
}
