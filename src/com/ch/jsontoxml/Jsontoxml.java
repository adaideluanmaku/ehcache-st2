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

//������Ͷ��������json�ĳ�xml
public class Jsontoxml {
	public static void Modul() throws DocumentException{
		System.out.println("��ʼ");
		
		//��ȡXML���ݵ�document��
		SAXReader reader=new SAXReader();
//		Document document=reader.read(new File("C:/Users/Administrator/Desktop/pass-java.xml"));
		Document document=reader.read(new File("C:/Users/Administrator/Desktop/�ű�/ʵʩ����PASS-1606-test-json.jmx"));
		//System.out.println(document);
		
		//ȡ��Root�ڵ�,XML���нڵ�����
		Element root = document.getRootElement();
//		System.out.println(root.asXML());
		
		Element hashTree=root.element("hashTree");//���Լƻ�
//		System.out.println(hashTree.asXML());
		
		Element hashTree1=hashTree.element("hashTree");//���Լƻ�����
		for(Iterator i = hashTree1.elementIterator("hashTree"); i.hasNext();){//ѭ��ȡ���Լƻ�����
			Element hashTree2=(Element)i.next();//�߳�������
//			System.out.println(hashTree2.asXML());
			for(Iterator i1 = hashTree2.elementIterator("hashTree"); i1.hasNext();){//ѭ��ȡ�߳�������
				Element hashTree3=(Element)i1.next();//ѭ������������
//				System.out.println(hashTree3.asXML());
				
				//ѭ��ȡѭ������������
				for(Iterator i2 = hashTree3.elementIterator("HTTPSamplerProxy"); i2.hasNext();){
//					System.out.println("��ʼ����������");
					Element HTTPSamplerProxy=(Element)i2.next();
//					System.out.println(HTTPSamplerProxy.attributeValue("testname"));
					
					//�޸�IP�Ͷ˿ڵ�ַ
					int N=0;
					for ( Iterator i21 = HTTPSamplerProxy.elementIterator("stringProp"); i21.hasNext();) {
						Element stringProp=(Element)i21.next();
						if(N==0){
							//IP
							stringProp.setText("172.18.7.159");
							N=N+1;
						}else{
							//�˿�
							stringProp.setText("8081");
							break;
						}
					}
					
					//jsonתxml,ɾ��boolProp�ڵ�
					for ( Iterator i21 = HTTPSamplerProxy.elementIterator("boolProp"); i21.hasNext();) {
						Element boolProp=(Element)i21.next();
						HTTPSamplerProxy.remove(boolProp);
						break;
					}
					
					//jsonתxml,����stringProp�ڵ�,�޸����ӵ�ַ
					for ( Iterator i21 = HTTPSamplerProxy.elementIterator("stringProp"); i21.hasNext();) {
						Element stringProp=(Element)i21.next();
//						System.out.println(stringProp.getText());
						if("/pass/ws/screen".equals(stringProp.getText())){
							stringProp.setText("/pass/ws/PASSwebService.asmx/Mc_DoScreen");
							break;
						}
						if("/pass/ws/query".equals(stringProp.getText())){
							stringProp.setText("/pass/ws/PASSwebService.asmx/Mc_DoQuery");
							break;
						}
						if("/pass/ws/detail".equals(stringProp.getText())){
							stringProp.setText("/pass/ws/PASSwebService.asmx/Mc_DoDetail");
							break;
						}
						if("/pass/ws/reason".equals(stringProp.getText())){
							stringProp.setText("/pass/ws/PASSwebService.asmx/Mc_DoReason");
							break;
						}
						if("/pass/ws/module".equals(stringProp.getText())){
							stringProp.setText("/pass/ws/PASSwebService.asmx/Mc_DoModule");
							break;
						}
					}
					
					Element elementProp=HTTPSamplerProxy.element("elementProp");
					//jsonתxml,�޸ķ��͵�����
					elementProp.addAttribute("guiclass", "HTTPArgumentsPanel");
					elementProp.addAttribute("testclass", "Arguments");
					elementProp.addAttribute("testname", "�û�����ı���");
					elementProp.addAttribute("enabled", "true");
//					
					Element collectionProp=elementProp.element("collectionProp");
					Element elementProp1=collectionProp.element("elementProp");
					//jsonתxml,�޸ķ��͵�����
					elementProp1.setAttributeValue("name", "psJSONStr");
					Element boolProp=elementProp1.element("boolProp");
					boolProp.setText("true");
					elementProp1.addElement("boolProp").addAttribute("name", "HTTPArgument.use_equals").setText("true");
					elementProp1.addElement("stringProp").addAttribute("name", "Argument.name").setText("psJSONStr");
				}
				
				//ѭ��ȡѭ������������
				for(Iterator i3 = hashTree3.elementIterator("hashTree"); i3.hasNext();){
					Element hashTree4=(Element)i3.next();
					if("".equals(hashTree4.getText())){//���</hashTree>���ֿ����ݽڵ㵱�����ýڵ�ȡ����Ϊ�վͲ���������ڵ�
						break;
					}
//					System.out.println("��ʼ�Ķ�������");
//					System.out.println(hashTree4.asXML());
					Element ResponseAssertion=hashTree4.element("ResponseAssertion");
//					System.out.println(ResponseAssertion.attributeValue("testname"));
				    Element collectionProp1=ResponseAssertion.element("collectionProp");
				    Element stringProp=collectionProp1.element("stringProp");
				    String stext=stringProp.getText();
				    //jsonתxml,�޸Ķ��Ե�����
				    if(stext!=""){
				    	if(!"<?xml".equals(stext.substring(0,5))){
						    	String stext1="<?xml version=\"1.0\" encoding=\"utf-8\"?><string xmlns=\"http://www.medicom.com.cn\"><![CDATA["+stext+"]]></string>";
						    	stringProp.setText(stext1);
				    	 }
				    }
				}
			}
		}
		//��дxml
		try{
//			//��document�е�����д���ļ��� �����ַ�����������
	        OutputFormat format = new OutputFormat();
	        format.setEncoding("UTF-8");
	        FileOutputStream fos = new FileOutputStream("C:/Users/Administrator/Desktop/�ű�/ʵʩ����PASS-1606-test-xml.jmx");
	        XMLWriter output = new XMLWriter(fos, format);
	        output.write(document);
	        output.flush();
	        output.close(); 

		} catch(IOException e){
			System.out.println(e.getMessage());
		}
		System.out.println("����");
	}
	
	public static void main(String args[]) throws DocumentException{
		Modul();
	}
}
