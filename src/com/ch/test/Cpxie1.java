package com.ch.test;

import com.ch.unit.Ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Cpxie1 {
	public static void main(String arg[]){
		//����ehcache������������ȡ�����ļ�
//		CacheManager manager=CacheManager.create("src/ehcache.xml"); 
		//���ķ�ʽ��ȡ�����ļ�
//		InputStream is=Cpdu.class.getClassLoader().getResourceAsStream("ehcache.xml");
//		CacheManager manager=CacheManager.create(is);
		//����һ���»���cache����������������ȡ����
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		
		Cache cache=manager.getCache("cache-demo");
		
		//cache�д�������
		for(int i=0;i<5000;i++){
			Element element=new Element(i,"qwewoqieruqoifnaslkfcaslvcdhvuanskcnzxvcashfoweoqhfoausdfhncaskjdfhqwiouefhwuiaefnaskjdfhuwqiefhbsajkdvbdsivhqwoiehfusadfjkasncajk");
			cache.put(element);
			System.out.println(i);
		}
		
		//ǿ�ƽ��ڴ�����ת������
		cache.flush();
//				
//		System.out.println("aaaaa"+cache.get(10));
//		System.out.println("aaaaa"+cache.getSize());
//		System.out.println("aaaaa"+cache.getMemoryStoreSize());
//		System.out.println("aaaaa"+cache.getDiskStoreSize());
		//ж��ehcache�����������ر�cache
		manager.shutdown();
		
	}
}

