package com.ch.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;


//xml����
public class Jmeterxml {
	public static List anlinames;
	public static List anlireslist;
	public static Map jiekoureq;
	public static Map jiekoures;
	
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

	public static void Modul() throws DocumentException, IOException{
		screenreq1=new HashMap();//ϵͳ���ɿ�
		screenres1=new HashMap();
		
		screenreq2=new HashMap();//�Զ������ɿ�
		screenres2=new HashMap();
		
		screenreq3=new HashMap();//�������ɿ�
		screenres3=new HashMap();
		
		screenreq4=new HashMap();//ϵͳ����¿�
		screenres4=new HashMap();
		
		screenreq5=new HashMap();//�Զ�������¿�
		screenres5=new HashMap();
		
		screenreq6=new HashMap();//��������¿�
		screenres6=new HashMap();
		
		screenreq7=new HashMap();//�����ַ�
		screenres7=new HashMap();
		
		detailreq1=new HashMap();//��ϸ��Ϣ
		detailres1=new HashMap();
		
		queryreq1=new HashMap();//��ѯ
		queryres1=new HashMap();
		
		queryreq2=new HashMap();//��������
		queryres2=new HashMap();
		
		reasonreq1=new HashMap();//��ҩ����
		reasonres1=new HashMap();
		
		//��ȡ������XML�ĵ�
		InputStream in=Jmeterxml.class.getClassLoader().getResourceAsStream("json.properties");
		Properties prop=new Properties();		
		prop.load(in);
		String jmeterdb=prop.getProperty("jmeterdb");
		SAXReader reader=new SAXReader();
		Document document=reader.read(new File(jmeterdb));
//		Document document=reader.read(new File("C:/Users/Administrator/Desktop/pass-java.xml"));
		//System.out.println(document);
		
		//ȡ��Root�ڵ�,XML���нڵ�����
		Element root = document.getRootElement();
//				System.out.println(root.asXML());
		
		Element hashTree=root.element("hashTree");//��ȡһ���ڵ�
//				System.out.println(hashTree.asXML());
		
		Element TestPlan=hashTree.element("TestPlan");//���Լƻ�
//				System.out.println(TestPlan.asXML());
		System.out.println(TestPlan.attributeValue("testname"));//���Լƻ�����
		
		Element hashTree1=hashTree.element("hashTree");//���Լƻ�����
		for ( Iterator i = hashTree1.elementIterator("ThreadGroup"); i.hasNext();) {
			Element ThreadGroup = (Element) i.next();//�߳�������
			// do something
//			System.out.println(ThreadGroup.attributeValue("testname"));
	    }
		int n=0;//�߳�����
		for ( Iterator i = hashTree1.elementIterator("hashTree"); i.hasNext();) {
			System.out.println("ѭ��������"+n);
			jiekoureq=new HashMap();
			jiekoures=new HashMap();
			Element ThreadGroup=hashTree1.element("ThreadGroup");
			Element hashTree2 = (Element) i.next();//�߳�������
			// do something
			for ( Iterator i1 = hashTree2.elementIterator("LoopController"); i1.hasNext();) {
				Element LoopController = (Element) i1.next();
//				System.out.println(LoopController.attributeValue("testname"));//ѭ������������
				// do something
		    }
			anlinames=new ArrayList();//������
			List anlireslist=new ArrayList();//����
			for ( Iterator i1 = hashTree2.elementIterator("hashTree"); i1.hasNext();) {
				Element hashTree3 = (Element) i1.next();//ѭ������������
				// do something
				for ( Iterator i2 = hashTree3.elementIterator("HTTPSamplerProxy"); i2.hasNext();) {
					Element HTTPSamplerProxy = (Element) i2.next();//HTTP����
//					System.out.println(HTTPSamplerProxy.attributeValue("testname"));
					anlinames.add(HTTPSamplerProxy.attributeValue("testname"));
					// do something
					Element elementProp=HTTPSamplerProxy.element("elementProp");
					Element collectionProp=elementProp.element("collectionProp");
					Element elementProp1=collectionProp.element("elementProp");
				    for ( Iterator i3 = elementProp1.elementIterator("stringProp"); i3.hasNext();) {
						Element stringProp = (Element) i3.next();
						// do something
//						System.out.println(stringProp.getText());//HTTP��������
						//����MAP
						jiekoureq.put(HTTPSamplerProxy.attributeValue("testname"), stringProp.getText());
						break;
				    }
			    }
//				int sum=0;
				for ( Iterator i4 = hashTree3.elementIterator("hashTree"); i4.hasNext();) {
					Element hashTree4 = (Element) i4.next();//HTTP��Ӧ����
					if("".equals(hashTree4.getText())){//���</hashTree>���ֿ����ݽڵ㵱�����ýڵ�ȡ����Ϊ�վͲ���������ڵ�
						break;
					}
					// do something
					Element ResponseAssertion=hashTree4.element("ResponseAssertion");
				    Element collectionProp1=ResponseAssertion.element("collectionProp");
				    Element stringProp=collectionProp1.element("stringProp");
//				    System.out.println(stringProp.getText());//HTTP��Ӧ����
				    anlireslist.add(stringProp.getText());
//				    System.out.println("OK:"+sum);
//				    sum=sum+1;
				}
				for(int j=0;j<anlinames.size();j++){
					//����MAP
			    	jiekoures.put(anlinames.get(j), anlireslist.get(j));
			    }
		    }
			if(n==0){
				System.out.println("ϵͳ���ɿ�");
				screenreq1=jiekoureq;
				screenres1=jiekoures;
			}
			if(n==1){
				System.out.println("�Զ������ɿ�");
				screenreq2=jiekoureq;
				screenres2=jiekoures;
			}
			if(n==2){
				System.out.println("�������ɿ�");
				screenreq3=jiekoureq;
				screenres3=jiekoures;
			}
			if(n==3){
				System.out.println("ϵͳ����¿�");
				screenreq4=jiekoureq;
				screenres4=jiekoures;
			}
			if(n==4){
				System.out.println("�Զ�������¿�");
				screenreq5=jiekoureq;
				screenres5=jiekoures;
			}
			if(n==5){
				System.out.println("��������¿�");
				screenreq6=jiekoureq;
				screenres6=jiekoures;
			}
			if(n==6){
				System.out.println("�����ַ�");
				screenreq7=jiekoureq;
				screenres7=jiekoures;
			}
			if(n==7){
				System.out.println("��ϸ��Ϣ");
				detailreq1=jiekoureq;
				detailres1=jiekoures;
			}
			if(n==8){
				System.out.println("��ѯ");
				queryreq1=jiekoureq;
				queryres1=jiekoures;
			}
			if(n==9){
				System.out.println("��������");
				queryreq2=jiekoureq;
				queryres2=jiekoures;
			}
			if(n==10){
				System.out.println("��ҩ����");
				reasonreq1=jiekoureq;
				reasonres1=jiekoures;
			}
			n=n+1;
	    }
	}
//	public static void main(String args[]) throws DocumentException{
//		Modul();
//		System.out.println("ϵͳ���ɿ�");
//		System.out.println("��������:"+screenreq1.size());
//		System.out.println("�Զ������ɿ�");
//		System.out.println("��������:"+screenreq2.size());
//		System.out.println("�������ɿ�");
//		System.out.println("��������:"+screenreq3.size());
//		System.out.println("ϵͳ����¿�");
//		System.out.println("��������:"+screenreq4.size());
//		System.out.println("�Զ�������¿�");
//		System.out.println("��������:"+screenreq5.size());
//		System.out.println("��������¿�");
//		System.out.println("��������:"+screenreq6.size());
//		System.out.println("�����ַ�");
//		System.out.println("��������:"+screenreq7.size());
//		System.out.println("��ϸ��Ϣ");
//		System.out.println("��������:"+detailreq1.size());
//		System.out.println("��ѯ");
//		System.out.println("��������:"+queryreq1.size());
//		System.out.println("��������");
//		System.out.println("��������:"+queryreq2.size());
//		System.out.println("��ҩ����");
//		System.out.println("��������:"+reasonreq1.size());
//		
//	}
}
