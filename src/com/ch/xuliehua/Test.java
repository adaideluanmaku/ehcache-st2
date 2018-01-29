package com.ch.xuliehua;

import java.io.InputStream;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.ch.unit.Ehcache;

//创建序列化对象，存储到ehcache中（可存储到任何地方）
public class Test {
	public static void main(String args[]){
		Duixiang xuliehua=new Duixiang();
		xuliehua.setA("我是第一个的A");
		xuliehua.setB("我是第一个的B");
		System.out.println(xuliehua);
		
		//将对象存储的某个地方
		InputStream is=Test.class.getClassLoader().getResourceAsStream("ehcache3.xml");
		CacheManager manager=CacheManager.create(is);
//		CacheManager manager = CacheManager.create("src/ehcache3.xml");
		
		Cache cache=manager.getCache("cache-demo3");
		Element ele=new Element("1",xuliehua);
		cache.put(ele);
		cache.flush();
		manager.shutdown();
		
	}
}
