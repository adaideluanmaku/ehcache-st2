package com.ch.ehcache;

import java.sql.SQLException;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Cacheread1 {
	public static void main(String arg[]) throws ClassNotFoundException, SQLException{
		//����ehcache������������ȡ�����ļ�
		CacheManager manager=CacheManager.create("src/ehcache2.xml"); 
//		//manager.addCache(cache);  
//		//����һ���»���cache����������������ȡ����
		Cache cache=manager.getCache("mc_route_name");
		//cache��ȡֵ		
		List cachekey=cache.getKeys();
		for(int i=0; i<cachekey.size(); i++){
			System.out.println(cachekey.get(i));
		}
		Element ele=cache.get("98");
		System.out.println("aaaaa:"+ele.getValue());
//		Element ele=cache.get(9);
//		System.out.println("ȥelement�е�valueֵ"+ele.getObjectValue());
//		System.out.println("aaaaa"+cache.getSize());
//		//ж��ehcache�����������ر�cache
		manager.shutdown();
	}
}
