package com.ch.redis;

import java.util.Iterator;
import java.util.Set;

import com.ch.sys.SysMatchRelation;

import redis.clients.jedis.Jedis;

//redis浣块敓鐭嚖鎷烽敓鏂ゆ嫹

public class Reditsdu {
	public static Jedis jedis;
	public static void main(String args[]){
		//redis连接
		jedis = new Jedis("172.18.7.160", 6379);
		jedis.auth("123");
		jedis.select(0);

		//密码
//		jedis.auth("medicom");
		
		System.out.println(jedis.ping());
		
		// // 清空数据
//      System.out.println("// 清空数据"+jedis.flushDB());

//		// 判断key否存在
//		System.out.println("判断key001键是否存在："+jedis.exists("key001")); 
//      System.out.println("新增key001,value001键值对："+jedis.set("key001", "value001")); 
//      System.out.println("判断key001是否存在："+jedis.exists("key001"));
//		System.out.println("系统中删除key001: "+jedis.del("key001"));
//		
//		//读取key数据 
//      System.out.println("新增key001,value001键值对："+jedis.set("key001", "value001")); 
      System.out.println("读取key001的值："+jedis.get("route_pass-6-57"));
//		System.out.println("系统中删除key001: "+jedis.del("key001"));
//		
//        // 删除某个key,若key不存在，则忽略该命令。
//        System.out.println("系统中删除key001: "+jedis.del("key001"));
//        System.out.println("判断key001是否存在："+jedis.exists("key001"));
//
//		 //输出系统中所有的key
//		System.out.println("AAAAA:"+jedis.get("user_drugdis-7-001001-2047833111"));
//		Set<String> keys = jedis.keys("*"); 
//		System.out.println("redis-data:"+keys.size());
//		Iterator<String> it=keys.iterator() ;   
//		int i=0;
//		while(it.hasNext()){   
//		    String key = it.next();   
//		    System.out.println(key);
//		    System.out.println("AAAAA:"+jedis.get(key));
//		    i++;
//		    if(i==10){
//		    	return;
//		    }
//		}

//		// 设置 key001的过期时间
//      System.out.println("新增key001,value001键值对："+jedis.set("key001", "value001")); 
//      System.out.println("设置 key001的过期时间为5秒:"+jedis.expire("key001", 5));
//      try{ 
//          Thread.sleep(2000); 
//      } 
//      catch (InterruptedException e){ 
//      } 
//      // 查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1
//      System.out.println("查看key001的剩余生存时间："+jedis.ttl("key001"));
//      // 移除某个key的生存时间
//      System.out.println("移除key001的生存时间："+jedis.persist("key001"));
//      System.out.println("查看key001的剩余生存时间："+jedis.ttl("key001"));
//      System.out.println("系统中删除key001: "+jedis.del("key001"));
//      // 查看key所储存的值的类型
//      System.out.println("查看key所储存的值的类型："+jedis.type("key001"));

      /*
       * 一些其他方法：1、修改键名：jedis.rename("key6", "key0");
       *             2、将当前db的key移动到给定的db当中：jedis.move("foo", 1)
       */
//		System.out.println(jedis.set("key001", "vlu001"));
//		System.out.println(jedis.get("key001"));
	}
}
