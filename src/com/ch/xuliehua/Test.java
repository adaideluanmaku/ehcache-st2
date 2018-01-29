package com.ch.xuliehua;

import java.io.InputStream;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.ch.unit.Ehcache;

//�������л����󣬴洢��ehcache�У��ɴ洢���κεط���
public class Test {
	public static void main(String args[]){
		Duixiang xuliehua=new Duixiang();
		xuliehua.setA("���ǵ�һ����A");
		xuliehua.setB("���ǵ�һ����B");
		System.out.println(xuliehua);
		
		//������洢��ĳ���ط�
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
