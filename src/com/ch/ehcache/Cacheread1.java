package com.ch.ehcache;

import java.sql.SQLException;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Cacheread1 {
	public static void main(String arg[]) throws ClassNotFoundException, SQLException{
		//创建ehcache管理器，并读取配置文件
		CacheManager manager=CacheManager.create("src/ehcache2.xml"); 
//		//manager.addCache(cache);  
//		//创建一个新缓存cache，并根据配置名读取配置
		Cache cache=manager.getCache("mc_route_name");
		//cache中取值		
		List cachekey=cache.getKeys();
		for(int i=0; i<cachekey.size(); i++){
			System.out.println(cachekey.get(i));
		}
		Element ele=cache.get("98");
		System.out.println("aaaaa:"+ele.getValue());
//		Element ele=cache.get(9);
//		System.out.println("去element中的value值"+ele.getObjectValue());
//		System.out.println("aaaaa"+cache.getSize());
//		//卸载ehcache管理器，并关闭cache
		manager.shutdown();
	}
}
