package com.ch.passmodel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.ch.jdbc.Mysqlconnection;
import com.ch.unit.Ehcache;
import com.medicom.core.cache.duplicate.McDuptherapy;

public class Mc_duptherapy {
	private String duibierr="mc_duptherapy数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="SELECT distinct genid, routetype, dupcid, dupcname, duplicatemax FROM mc_duptherapy ORDER BY genid, routetype, dupcid";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_duptherapy");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));
		
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("genid").toString()+"-"+sjmap.get("routetype").toString();
			Element ele=cache.get(cpkey);
			
			int status=0;
			List cplist=(List)ele.getObjectValue();
			for(int j=0;j<cplist.size();j++){
				McDuptherapy duptherapy=(McDuptherapy)cplist.get(j);
				if(sjmap.get("dupcid").equals(duptherapy.getDupcid())){
					status=status+1;
				}
				if(sjmap.get("dupcname").equals(duptherapy.getDupcname())){
					status=status+1;
				}
				if(sjmap.get("duplicatemax").equals(duptherapy.getDuplicatemax())){
					status=status+1;
				}
				if(sjmap.get("genid").equals(duptherapy.getGenid())){
					status=status+1;
				}
				if(sjmap.get("routetype").equals(duptherapy.getRoutetype())){
					status=status+1;
				}
				if(status<5){
					status=0;
				}
//				System.out.println(CipherTools.aesEncrypt(sjmap.get("scrid").toString())+":"+cplist.get(j));
			}
			System.out.println(cpkey);
			if(status<5){
				System.out.println("mc_duptherapy磁盘数据错误退出"+cpkey);
				duibierr="mc_duptherapy报错的key:"+cpkey;
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_duptherapy循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_duptherapy");
		
		Random ran=new Random();
		Element ele = null;
		Object keyObj = cache.getKeys().get(ran.nextInt(cache.getKeys().size()));
		if (keyObj instanceof Integer) {
			ele = cache.get((Integer) keyObj);
		} else if (keyObj instanceof String) {
			ele = cache.get((String) keyObj);
		} else if (keyObj instanceof Long) {
			ele = cache.get((Long) keyObj);
		}
		
		if(ele!=null){	
			System.out.println("mc_duptherapy取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_duptherapy磁盘文件为空");
			cxerr="mc_duptherapy磁盘文件为空";
		}
		return cxerr;
	}
}
