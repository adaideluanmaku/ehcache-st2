package com.ch.redis;

import redis.clients.jedis.Jedis;

public class Test {
	public static void main(String args[]){
		//redis连接
		Jedis jedis = new Jedis("172.18.3.153", 7000);
		Jedis jedis1 = new Jedis("172.18.3.153", 6379);
		System.out.println(jedis.ping());
		System.out.println(jedis1.ping());
//		System.out.println("测试单医院1603程序1303数据:"+jedis.get("mc_dict_drug_pass"));
	}
}
