package com.ch.xuliehua;

import java.io.InputStream;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

//将ehcache中的序列化对象读取出来
public class Test1 {
	public static void main(String args[]){
		InputStream is=Test1.class.getClassLoader().getResourceAsStream("ehcache3.xml");
		CacheManager manager=CacheManager.create(is);
//		CacheManager manager =new CacheManager("src/ehcache3.xml");
		Cache cache=manager.getCache("cache-demo3");
		
		Element ele=cache.get("1");
		System.out.println(ele.getObjectValue());
		
		Duixiang duixiang=(Duixiang)ele.getObjectValue();
		System.out.println(duixiang.a);
		System.out.println(duixiang.b);
		
		manager.shutdown();
	}
}
