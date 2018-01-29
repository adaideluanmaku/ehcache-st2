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

public class Mc_aller_scr {
	private String duibierr="mc_aller_scr数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="SELECT b_ingid1,b_ingid2 FROM mc_aller_scr ORDER BY b_ingid1,b_ingid2";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_aller_scr");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().toString());
		
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("b_ingid1").toString()+"-"+sjmap.get("b_ingid2").toString();
			Element ele=cache.get(cpkey);
			
			if(ele==null || Integer.parseInt(ele.getObjectValue().toString())!=0){
				System.out.println("mc_aller_scr磁盘数据错误退出:"+cpkey);
				duibierr="mc_aller_scr报错的key:"+cpkey;
				break;
			}
			System.out.println(cpkey);
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_aller_scr循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_aller_scr");
		
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
			System.out.println("mc_aller_scr取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_aller_scr磁盘文件为空");
			cxerr="mc_aller_scr磁盘文件为空";
		}
		return cxerr;
	}
}
