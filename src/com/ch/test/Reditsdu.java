package com.ch.test;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class Reditsdu {
	public static void main(String args[]){
		//redis����
		Jedis jedis = new Jedis("172.18.3.153", 6379);
		System.out.println(jedis.ping());
		
		// ������� 
//      System.out.println("��տ����������ݣ�"+jedis.flushDB());

//		// �ж�key����� 
//		System.out.println("�ж�key001���Ƿ���ڣ�"+jedis.exists("key001")); 
//		System.out.println("����key001,value001��ֵ�ԣ�"+jedis.set("key001", "value001")); 
//		System.out.println("�ж�key001�Ƿ���ڣ�"+jedis.exists("key001"));
//		System.out.println("ϵͳ��ɾ��key001: "+jedis.del("key001"));
//		
//		//��ȡkey���� 
//		System.out.println("����key001,value001��ֵ�ԣ�"+jedis.set("key001", "value001")); 
//		System.out.println("��ȡkey001��ֵ��"+jedis.get("key001"));
//		System.out.println("ϵͳ��ɾ��key001: "+jedis.del("key001"));
//		
//		// ɾ��ĳ��key,��key�����ڣ�����Ը����
//		System.out.println("ϵͳ��ɾ��key001: "+jedis.del("key001"));
//		System.out.println("�ж�key001�Ƿ���ڣ�"+jedis.exists("key001"));
//
//		//���ϵͳ�����е�key
//		System.out.println("����key002,value002��ֵ�ԣ�"+jedis.set("key002", "value002"));
//		System.out.println("ϵͳ�����м����£�");
//		Set<String> keys = jedis.keys("*"); 
//		Iterator<String> it=keys.iterator() ;   
//		while(it.hasNext()){   
//		    String key = it.next();   
//		    System.out.println(key);   
//		}

//		// ���� key001�Ĺ���ʱ��
//		System.out.println("����key001,value001��ֵ�ԣ�"+jedis.set("key001", "value001")); 
//		System.out.println("���� key001�Ĺ���ʱ��Ϊ5��:"+jedis.expire("key001", 5));
//		try{ 
//		    Thread.sleep(2000); 
//		} 
//		catch (InterruptedException e){ 
//		} 
//		// �鿴ĳ��key��ʣ������ʱ��,��λ���롿.����������߲����ڵĶ�����-1
//		System.out.println("�鿴key001��ʣ������ʱ�䣺"+jedis.ttl("key001"));
//		// �Ƴ�ĳ��key������ʱ��
//		System.out.println("�Ƴ�key001������ʱ�䣺"+jedis.persist("key001"));
//		System.out.println("�鿴key001��ʣ������ʱ�䣺"+jedis.ttl("key001"));
//		System.out.println("ϵͳ��ɾ��key001: "+jedis.del("key001"));
//		// �鿴key�������ֵ������
//		System.out.println("�鿴key�������ֵ�����ͣ�"+jedis.type("key001"));

        /*
         * һЩ����������1���޸ļ�����jedis.rename("key6", "key0");
         *             2������ǰdb��key�ƶ���������db���У�jedis.move("foo", 1)
         */
//		System.out.println(jedis.set("key001", "vlu001"));
//		System.out.println(jedis.get("key001"));
	}
}
