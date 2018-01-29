package com.ch.test;

import com.ch.unit.Ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Cpxie1 {
	public static void main(String arg[]){
		//创建ehcache管理器，并读取配置文件
//		CacheManager manager=CacheManager.create("src/ehcache.xml"); 
		//流的方式读取配置文件
//		InputStream is=Cpdu.class.getClassLoader().getResourceAsStream("ehcache.xml");
//		CacheManager manager=CacheManager.create(is);
		//创建一个新缓存cache，并根据配置名读取配置
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		
		Cache cache=manager.getCache("cache-demo");
		
		//cache中存入数据
		for(int i=0;i<5000;i++){
			Element element=new Element(i,"qwewoqieruqoifnaslkfcaslvcdhvuanskcnzxvcashfoweoqhfoausdfhncaskjdfhqwiouefhwuiaefnaskjdfhuwqiefhbsajkdvbdsivhqwoiehfusadfjkasncajk");
			cache.put(element);
			System.out.println(i);
		}
		
		//强制将内存内容转到磁盘
		cache.flush();
//				
//		System.out.println("aaaaa"+cache.get(10));
//		System.out.println("aaaaa"+cache.getSize());
//		System.out.println("aaaaa"+cache.getMemoryStoreSize());
//		System.out.println("aaaaa"+cache.getDiskStoreSize());
		//卸载ehcache管理器，并关闭cache
		manager.shutdown();
		
	}
}

