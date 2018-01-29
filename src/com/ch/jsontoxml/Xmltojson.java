package com.ch.jsontoxml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

//将请求和断言里面的json改成xml
public class Xmltojson {
	public static void Modul() throws DocumentException{
		System.out.println("开始");
		
		//读取XML内容到document中
		SAXReader reader=new SAXReader();
//		Document document=reader.read(new File("C:/Users/Administrator/Desktop/pass-java.xml"));
		Document document=reader.read(new File("C:/Users/Administrator/Desktop/脚本/123.jmx"));
		//System.out.println(document);
		
		//取得Root节点,XML所有节点的起点
		Element root = document.getRootElement();
//		System.out.println(root.asXML());
		
		Element hashTree=root.element("hashTree");//测试计划
//		System.out.println(hashTree.asXML());
		
		Element hashTree1=hashTree.element("hashTree");//测试计划内容
		for(Iterator i = hashTree1.elementIterator("hashTree"); i.hasNext();){//循环取测试计划内容
			Element hashTree2=(Element)i.next();//线程组内容
//			System.out.println(hashTree2.asXML());
			for(Iterator i1 = hashTree2.elementIterator("hashTree"); i1.hasNext();){//循环取线程组内容
				Element hashTree3=(Element)i1.next();//循环控制器内容
//				System.out.println(hashTree3.asXML());
				
				//循环取循环控制器请求
				for(Iterator i2 = hashTree3.elementIterator("HTTPSamplerProxy"); i2.hasNext();){
//					System.out.println("开始改请求数据");
					Element HTTPSamplerProxy=(Element)i2.next();
					System.out.println(HTTPSamplerProxy.attributeValue("testname"));
					
					//修改IP和端口地址
					int N=0;
					for ( Iterator i21 = HTTPSamplerProxy.elementIterator("stringProp"); i21.hasNext();) {
						Element stringProp=(Element)i21.next();
						if(N==0){
							//IP
							stringProp.setText("172.18.7.159");
							N=N+1;
						}else{
							//端口
							stringProp.setText("8081");
							break;
						}
					}
					
					//xml转json,增加boolProp节点
//					for ( Iterator i21 = HTTPSamplerProxy.elementIterator("boolProp"); i21.hasNext();) {
//						Element boolProp=(Element)i21.next();
//						HTTPSamplerProxy.remove(boolProp);
//						break;
//					}
					HTTPSamplerProxy.addElement("boolProp").addAttribute("name", "HTTPSampler.postBodyRaw").setText("true");
					
					//xml转json,更改stringProp节点,修改连接地址
					for ( Iterator i21 = HTTPSamplerProxy.elementIterator("stringProp"); i21.hasNext();) {
						Element stringProp=(Element)i21.next();
//						System.out.println(stringProp.getText());
						if("/pass/ws/PASSwebService.asmx/Mc_DoScreen".equals(stringProp.getText())){
							stringProp.setText("/pass/ws/screen");
							break;
						}
						if("/pass/ws/PASSwebService.asmx/Mc_DoQuery".equals(stringProp.getText())){
							stringProp.setText("/pass/ws/query");
							break;
						}
						if("/pass/ws/PASSwebService.asmx/Mc_DoDetail".equals(stringProp.getText())){
							stringProp.setText("/pass/ws/detail");
							break;
						}
						if("pass/ws/PASSwebService.asmx/Mc_DoReason".equals(stringProp.getText())){
							stringProp.setText("/pass/ws/reason/");
							break;
						}
						if("/pass/ws/PASSwebService.asmx/Mc_DoModulee".equals(stringProp.getText())){
							stringProp.setText("/pass/ws/modul");
							break;
						}
					}
					
					Element elementProp=HTTPSamplerProxy.element("elementProp");
					//xml转json,修改发送的内容
//					elementProp.addAttribute("guiclass", "HTTPArgumentsPanel");
//					elementProp.addAttribute("testclass", "Arguments");
//					elementProp.addAttribute("testname", "用户定义的变量");
//					elementProp.addAttribute("enabled", "true");
					elementProp.setAttributeValue("guiclass", "");
					elementProp.setAttributeValue("testclass", "");
					elementProp.setAttributeValue("testname", "");
					elementProp.setAttributeValue("enabled", "");
					
					Element collectionProp=elementProp.element("collectionProp");
					if("".equals(collectionProp.getText()) || collectionProp.getText()==null){
						continue;
					}
					Element elementProp1=collectionProp.element("elementProp");
					//xml转json,修改发送的内容
//					elementProp1.setAttributeValue("name", "psJSONStr");
//					System.out.println("aaaa"+elementProp1.attributeValue("name"));
					elementProp1.setAttributeValue("name", "");
					
					Element boolProp=elementProp1.element("boolProp");
//					boolProp.setText("true");
					boolProp.setText("false");
					
//					elementProp1.addElement("boolProp").addAttribute("name", "HTTPArgument.use_equals").setText("true");
//					elementProp1.addElement("stringProp").addAttribute("name", "Argument.name").setText("psJSONStr");
					for(Iterator i4=elementProp1.elementIterator("boolProp");i4.hasNext();){
						Element boolProp1=(Element)i4.next();
						if("HTTPArgument.use_equals".equals(boolProp1.attributeValue("name"))){
							elementProp1.remove(boolProp1);
						}
					}
					for(Iterator i4=elementProp1.elementIterator("stringProp");i4.hasNext();){
						Element stringProp=(Element)i4.next();
						if("Argument.name".equals(stringProp.attributeValue("name"))){
							elementProp1.remove(stringProp);
						}
					}
				}
				
				//循环取循环控制器断言
				for(Iterator i3 = hashTree3.elementIterator("hashTree"); i3.hasNext();){
					Element hashTree4=(Element)i3.next();
					if("".equals(hashTree4.getText())){//会把</hashTree>这种空数据节点当做有用节点取出，为空就不处理这个节点
						break;
					}
//					System.out.println("开始改断言数据");
//					System.out.println(hashTree4.asXML());
					Element ResponseAssertion=hashTree4.element("ResponseAssertion");
//					System.out.println(ResponseAssertion.attributeValue("testname"));
				    Element collectionProp1=ResponseAssertion.element("collectionProp");
				    Element stringProp=collectionProp1.element("stringProp");
				    String stext=stringProp.getText();
				    //json转xml,修改断言的内容
//				    if(stext!=""){
//				    	if(!"<?xml".equals(stext.substring(0,5))){
//						    	String stext1="<?xml version=\"1.0\" encoding=\"utf-8\"?><string xmlns=\"http://www.medicom.com.cn\"><![CDATA["+stext+"]]></string>";
//						    	stringProp.setText(stext1);
//				    	 }
//				    }
				}
			}
		}
		//回写xml
		try{
//			//将document中的内容写入文件中 存在字符集中文乱码
	        OutputFormat format = new OutputFormat();
	        format.setEncoding("UTF-8");
	        FileOutputStream fos = new FileOutputStream("C:/Users/Administrator/Desktop/脚本/123 - 副本1.jmx");
	        XMLWriter output = new XMLWriter(fos, format);
	        output.write(document);
	        output.flush();
	        output.close(); 

		} catch(IOException e){
			System.out.println(e.getMessage());
		}
		System.out.println("结束");
	}
	
	public static void main(String args[]) throws DocumentException{
		Modul();
	}
}
