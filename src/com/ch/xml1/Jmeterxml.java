package com.ch.xml1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;


//xml解析
public class Jmeterxml {
	public static List anlinames;
	public static List anlireslist;
	public static Map jiekoureq;
	public static Map jiekoures;
	public static Map  reqaddress;
	public static List duibirequest;
	public static List errlist=null;
	
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
	
	public static Map getScreenreq1() {
		return screenreq1;
	}

	public static Map getScreenres1() {
		return screenres1;
	}

	public static Map getScreenreq2() {
		return screenreq2;
	}


	public static Map getScreenres2() {
		return screenres2;
	}

	public static Map getScreenreq3() {
		return screenreq3;
	}

	public static Map getScreenres3() {
		return screenres3;
	}

	public static Map getScreenreq4() {
		return screenreq4;
	}

	public static Map getScreenres4() {
		return screenres4;
	}

	public static Map getScreenreq5() {
		return screenreq5;
	}

	public static Map getScreenres5() {
		return screenres5;
	}

	public static Map getScreenreq6() {
		return screenreq6;
	}

	public static Map getScreenres6() {
		return screenres6;
	}

	public static Map getScreenreq7() {
		return screenreq7;
	}

	public static Map getScreenres7() {
		return screenres7;
	}

	public static Map getDetailreq1() {
		return detailreq1;
	}

	public static Map getDetailres1() {
		return detailres1;
	}

	public static Map getQueryreq1() {
		return queryreq1;
	}

	public static Map getQueryres1() {
		return queryres1;
	}

	public static Map getQueryreq2() {
		return queryreq2;
	}

	public static Map getQueryres2() {
		return queryres2;
	}

	public static Map getReasonreq1() {
		return reasonreq1;
	}

	public static Map getReasonres1() {
		return reasonres1;
	}

	public static List Modul() throws DocumentException, IOException, TimeoutException{
		screenreq1=new HashMap();//系统审查旧库
		screenres1=new HashMap();
		
		screenreq2=new HashMap();//自定义审查旧库
		screenres2=new HashMap();
		
		screenreq3=new HashMap();//屏蔽审查旧库
		screenres3=new HashMap();
		
		screenreq4=new HashMap();//系统审查新库
		screenres4=new HashMap();
		
		screenreq5=new HashMap();//自定义审查新库
		screenres5=new HashMap();
		
		screenreq6=new HashMap();//屏蔽审查新库
		screenres6=new HashMap();
		
		screenreq7=new HashMap();//特殊字符
		screenres7=new HashMap();
		
		detailreq1=new HashMap();//详细信息
		detailres1=new HashMap();
		
		queryreq1=new HashMap();//查询
		queryres1=new HashMap();
		
		queryreq2=new HashMap();//浮动窗口
		queryres2=new HashMap();
		
		reasonreq1=new HashMap();//用药理由
		reasonres1=new HashMap();
		
		//读取并解析XML文档
		String jmeterdb="";
		try{
			InputStream in=Jmeterxml.class.getClassLoader().getResourceAsStream("json.properties");
			Properties prop=new Properties();		
			prop.load(in);
			jmeterdb=prop.getProperty("jmeterdb");
		}catch (Exception e){
			System.out.println("Jmeter地址读取失败");
			errlist.add("Jmeter文件地址读取失败");
			return errlist;
		}
		SAXReader reader=new SAXReader();
		System.out.println(jmeterdb);
		Document document=reader.read(new File(jmeterdb));
//		Document document=reader.read(new File("C:/Users/Administrator/Desktop/pass-java.xml"));
		//System.out.println(document);
		
		//取得Root节点,XML所有节点的起点
		Element root = document.getRootElement();
//				System.out.println(root.asXML());
		
		Element hashTree=root.element("hashTree");//获取一级节点
//				System.out.println(hashTree.asXML());
		
		Element TestPlan=hashTree.element("TestPlan");//测试计划
//				System.out.println(TestPlan.asXML());
		System.out.println(TestPlan.attributeValue("testname"));//测试计划名称
		
		Element hashTree1=hashTree.element("hashTree");//测试计划内容
		int n=0;//线程组标记
		List ThreadGroupname=new ArrayList();
		for ( Iterator i = hashTree1.elementIterator("ThreadGroup"); i.hasNext();) {
			Element ThreadGroup = (Element) i.next();//线程组名称
			// do something
//			System.out.println("a"+ThreadGroup.attributeValue("testname"));
			ThreadGroupname.add(ThreadGroup.attributeValue("testname"));
			n=n+1;
	    }
//		System.out.println("AAAAA"+n);
		errlist=new ArrayList();
		n=0;
		for ( Iterator i = hashTree1.elementIterator("hashTree"); i.hasNext();) {
			System.out.println("循环次数："+n);
			jiekoureq=new HashMap();
			jiekoures=new HashMap();
			reqaddress=new HashMap();
			Element ThreadGroup=hashTree1.element("ThreadGroup");
			System.out.println("b"+ThreadGroup.attributeValue("testname"));
			Element hashTree2 = (Element) i.next();//线程组内容
			// do something
			for ( Iterator i1 = hashTree2.elementIterator("LoopController"); i1.hasNext();) {
				Element LoopController = (Element) i1.next();
//				System.out.println(LoopController.attributeValue("testname"));//循环控制器名称
				String testname=LoopController.attributeValue("testname");
				// do something
		    }
			anlinames=new ArrayList();//案例名
			List anlireslist=new ArrayList();//断言
			for ( Iterator i1 = hashTree2.elementIterator("hashTree"); i1.hasNext();) {
				Element hashTree3 = (Element) i1.next();//循环控制器内容
				// do something
				for ( Iterator i2 = hashTree3.elementIterator("HTTPSamplerProxy"); i2.hasNext();) {
					Element HTTPSamplerProxy = (Element) i2.next();//HTTP请求
//					System.out.println(HTTPSamplerProxy.attributeValue("testname"));
					anlinames.add(HTTPSamplerProxy.attributeValue("testname"));
					String address="http://";
					String address1="";
					String address2="";
					String address3="";
					for ( Iterator i3 = HTTPSamplerProxy.elementIterator("stringProp"); i3.hasNext();) {
						Element stringProp=(Element)i3.next();
						if("HTTPSampler.domain".equals(stringProp.attributeValue("name"))){
							address1=stringProp.getText();
//							System.out.println(address1);
							continue;
						}
						if("HTTPSampler.port".equals(stringProp.attributeValue("name"))){
							address2=stringProp.getText();
//							System.out.println(address2);
							continue;
						}
						if("HTTPSampler.path".equals(stringProp.attributeValue("name"))){
							if("/pass/ws/screen".equals(stringProp.getText())){
								address3="/pass/ws/PASSwebService.asmx/Mc_DoScreen";
							}else if("/pass/ws/PASSwebService.asmx/Mc_DoScreen".equals(stringProp.getText())){
								address3="/pass/ws/PASSwebService.asmx/Mc_DoScreen";
							}
							if("/pass/ws/query".equals(stringProp.getText())){
								address3="/pass/ws/PASSwebService.asmx/Mc_DoQuery";
							}else if("/pass/ws/PASSwebService.asmx/Mc_DoQuery".equals(stringProp.getText())){
								address3="/pass/ws/PASSwebService.asmx/Mc_DoQuery";
							}
							if("/pass/ws/detail".equals(stringProp.getText())){
								address3="/pass/ws/PASSwebService.asmx/Mc_DoDetail";
							}else if("/pass/ws/PASSwebService.asmx/Mc_DoDetail".equals(stringProp.getText())){
								address3="/pass/ws/PASSwebService.asmx/Mc_DoDetail";
							}
							if("/pass/ws/reason".equals(stringProp.getText())){
								address3="/pass/ws/PASSwebService.asmx/Mc_DoReason";
							}
							if("/pass/ws/module".equals(stringProp.getText())){
								address3="/pass/ws/PASSwebService.asmx/Mc_DoModule";
							}else if("/pass/ws/PASSwebService.asmx/Mc_DoModule".equals(stringProp.getText())){
								address3="/pass/ws/PASSwebService.asmx/Mc_DoModule";
							}
//							System.out.println(address3);
							continue;
						}
					}
					address=address+address1+":"+address2+address3;
//					System.out.println("访问地址:"+address);
					reqaddress.put(HTTPSamplerProxy.attributeValue("testname"), address);
					// do something
					Element elementProp=HTTPSamplerProxy.element("elementProp");
					Element collectionProp=elementProp.element("collectionProp");
					Element elementProp1=collectionProp.element("elementProp");
				    for ( Iterator i3 = elementProp1.elementIterator("stringProp"); i3.hasNext();) {
						Element stringProp = (Element) i3.next();
						// do something
//						System.out.println(stringProp.getText());//HTTP请求内容
						//请求MAP
						jiekoureq.put(HTTPSamplerProxy.attributeValue("testname"), stringProp.getText());
						break;
				    }
			    }
//				int sum=0;
				for ( Iterator i4 = hashTree3.elementIterator("hashTree"); i4.hasNext();) {
					Element hashTree4 = (Element) i4.next();//HTTP响应对象
					if("".equals(hashTree4.getText())){//会把</hashTree>这种空数据节点当做有用节点取出，为空就不处理这个节点
						break;
					}
					// do something
					Element ResponseAssertion=hashTree4.element("ResponseAssertion");
				    Element collectionProp1=ResponseAssertion.element("collectionProp");
				    Element stringProp=collectionProp1.element("stringProp");
//				    System.out.println(stringProp.getText());//HTTP响应内容
				    anlireslist.add(stringProp.getText());
//				    System.out.println("OK:"+sum);
//				    sum=sum+1;
				}
				for(int j=0;j<anlinames.size();j++){
					//断言MAP
			    	jiekoures.put(anlinames.get(j), anlireslist.get(j));
			    }
		    }
			
			Iterator keys=null;
			String jsonin=null;
			String jsonoutjava=null;
			String jsonoutwin=null;
//			System.out.println("系统审查旧库");
			errlist.add("-----------------"+ThreadGroupname.get(n)+"-------------------");
			screenreq1=jiekoureq;//请求
			screenres1=jiekoures;//断言
			keys=screenreq1.keySet().iterator();
			Jmeter jmeter=new Jmeter();
			while(keys.hasNext()){
				String key=keys.next().toString();
				jsonin=screenreq1.get(key).toString();
				System.out.println("请求："+key);
//				System.out.println("请求："+screenreq1.get(key));
				System.out.println("地址："+reqaddress.get(key));
				jsonoutjava=jmeter.getPassResult(jsonin,reqaddress.get(key).toString());//pass-win输入串，pass审查得到输出串
//				System.out.println("接口结果："+jsonoutjava);
				jsonoutwin=screenres1.get(key).toString();//pass-win审查结果
				if(reqaddress.get(key).toString().contains("Mc_DoScreen")){
					System.out.println("1");
					Jsonshencha jsonduibi=new Jsonshencha();
					jsonduibi.setJson(jsonoutwin);
					jsonduibi.setJson1(jsonoutjava);
					jsonduibi.Duibi();
					duibirequest=jsonduibi.getListerr1();
//					System.out.println("案例对比结果:"+duibirequest.size());
					if(duibirequest.size()>0){
						errlist.add(key);
					}
				}
				if(reqaddress.get(key).toString().contains("Mc_DoDetail")){
					System.out.println("2");
					Jsonxiangxi jsonduibi=new Jsonxiangxi();
					jsonduibi.setJson(jsonoutwin);
					jsonduibi.setJson1(jsonoutjava);
					jsonduibi.Xiangxiduibi();
					duibirequest=jsonduibi.getListerr1();
					if(duibirequest.size()>0){
						errlist.add(key);
					}
				}
				if(reqaddress.get(key).toString().contains("Mc_DoQuery")){
					System.out.println("3");
					Jsonchaxun jsonduibi=new Jsonchaxun();
					jsonduibi.setJson(jsonoutwin);
					jsonduibi.setJson1(jsonoutjava);
					jsonduibi.Chaxunduibi();
					duibirequest=jsonduibi.getListerr1();
					if(duibirequest.size()>0){
						errlist.add(key);
					}
				}
				if(reqaddress.get(key).toString().contains("Mc_DoReason")){
					System.out.println("4");
					if(jsonoutwin.length()<200){
						if(!jsonoutjava.equals(jsonoutwin)){
							errlist.add(key);
						}
					}else{
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
//				break;
			}
//			System.out.println(errlist);
//			if(n==0){
//				System.out.println("系统审查旧库");
//				screenreq1=jiekoureq;
//				screenres1=jiekoures;
//			}
//			if(n==1){
//				System.out.println("自定义审查旧库");
//				screenreq2=jiekoureq;
//				screenres2=jiekoures;
//			}
//			if(n==2){
//				System.out.println("屏蔽审查旧库");
//				screenreq3=jiekoureq;
//				screenres3=jiekoures;
//			}
//			if(n==3){
//				System.out.println("系统审查新库");
//				screenreq4=jiekoureq;
//				screenres4=jiekoures;
//			}
//			if(n==4){
//				System.out.println("自定义审查新库");
//				screenreq5=jiekoureq;
//				screenres5=jiekoures;
//			}
//			if(n==5){
//				System.out.println("屏蔽审查新库");
//				screenreq6=jiekoureq;
//				screenres6=jiekoures;
//			}
//			if(n==6){
//				System.out.println("特殊字符");
//				screenreq7=jiekoureq;
//				screenres7=jiekoures;
//			}
//			if(n==7){
//				System.out.println("详细信息");
//				detailreq1=jiekoureq;
//				detailres1=jiekoures;
//			}
//			if(n==8){
//				System.out.println("查询");
//				queryreq1=jiekoureq;
//				queryres1=jiekoures;
//			}
//			if(n==9){
//				System.out.println("浮动窗口");
//				queryreq2=jiekoureq;
//				queryres2=jiekoures;
//			}
//			if(n==10){
//				System.out.println("用药理由");
//				reasonreq1=jiekoureq;
//				reasonres1=jiekoures;
//			}
			n=n+1;
	    }
		return errlist;
	}
	
//	public static void main(String args[]) throws DocumentException, IOException, TimeoutException{
//		Modul();
////		System.out.println("系统审查旧库");
////		System.out.println("案例总数:"+screenreq1.size());
////		System.out.println("自定义审查旧库");
////		System.out.println("案例总数:"+screenreq2.size());
////		System.out.println("屏蔽审查旧库");
////		System.out.println("案例总数:"+screenreq3.size());
////		System.out.println("系统审查新库");
////		System.out.println("案例总数:"+screenreq4.size());
////		System.out.println("自定义审查新库");
////		System.out.println("案例总数:"+screenreq5.size());
////		System.out.println("屏蔽审查新库");
////		System.out.println("案例总数:"+screenreq6.size());
////		System.out.println("特殊字符");
////		System.out.println("案例总数:"+screenreq7.size());
////		System.out.println("详细信息");
////		System.out.println("案例总数:"+detailreq1.size());
////		System.out.println("查询");
////		System.out.println("案例总数:"+queryreq1.size());
////		System.out.println("浮动窗口");
////		System.out.println("案例总数:"+queryreq2.size());
////		System.out.println("用药理由");
////		System.out.println("案例总数:"+reasonreq1.size());
//		
//	}
}
