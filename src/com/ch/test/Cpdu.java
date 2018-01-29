package com.ch.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.ch.jdbc.Mysqlconnection;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Cpdu {
	public static void main(String arg[]) throws ClassNotFoundException, SQLException{
		//创建ehcache管理器，并读取配置文件
		CacheManager manager=CacheManager.create("src/ehcache.xml"); 
//		//manager.addCache(cache);  
//		//创建一个新缓存cache，并根据配置名读取配置
		Cache cache=manager.getCache("cache-demo1");
		//cache中取值
		System.out.println("取cache-key为9的值"+cache.get(9));
		Element ele=cache.get(9);
		System.out.println("去element中的value值"+ele.getObjectValue());
//		System.out.println("aaaaa"+cache.getSize());
//		//卸载ehcache管理器，并关闭cache
//		manager.shutdown();
	}
}

