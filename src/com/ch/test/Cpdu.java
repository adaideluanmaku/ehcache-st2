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
		//����ehcache������������ȡ�����ļ�
		CacheManager manager=CacheManager.create("src/ehcache.xml"); 
//		//manager.addCache(cache);  
//		//����һ���»���cache����������������ȡ����
		Cache cache=manager.getCache("cache-demo1");
		//cache��ȡֵ
		System.out.println("ȡcache-keyΪ9��ֵ"+cache.get(9));
		Element ele=cache.get(9);
		System.out.println("ȥelement�е�valueֵ"+ele.getObjectValue());
//		System.out.println("aaaaa"+cache.getSize());
//		//ж��ehcache�����������ر�cache
//		manager.shutdown();
	}
}

