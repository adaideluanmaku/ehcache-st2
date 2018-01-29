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
import com.medicom.core.cache.iv.McIvSingle;

public class Mc_iv_single {
	private String duibierr="mc_iv_single数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="select genid,routetype,articleid,slcode,severity,warning from mc_iv_single where genid is not null and genid > 0 and routetype is not null and routetype > 0 order by genid, routetype";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);

		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_iv_single");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));

		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("genid").toString()+"-"+sjmap.get("routetype").toString();
			Element ele=cache.get(cpkey);
			
			int status=0;
			McIvSingle ivsingle=(McIvSingle)ele.getObjectValue();
//			System.out.println(sjmap.get("articleid")+":"+ivsingle.getArticleid());
//			System.out.println(sjmap.get("genid").toString()+":"+ivsingle.getGenid());
//			System.out.println(sjmap.get("routetype").toString()+":"+ivsingle.getRoutetype());
//			System.out.println(sjmap.get("severity").toString()+":"+ivsingle.getSeverity());
//			System.out.println(sjmap.get("slcode").toString()+":"+ivsingle.getSlcode());
//			System.out.println(sjmap.get("warning").toString()+":"+ivsingle.getWarning());
			if(sjmap.get("articleid").equals(ivsingle.getArticleid())){
				status=status+1;
			}
			if(sjmap.get("genid").equals(ivsingle.getGenid())){
				status=status+1;
			}
			if(sjmap.get("routetype").equals(ivsingle.getRoutetype())){
				status=status+1;
			}
			if(sjmap.get("severity").equals(ivsingle.getSeverity())){
				status=status+1;
			}
			if(sjmap.get("slcode").equals(ivsingle.getSlcode())){
				status=status+1;
			}
			if(sjmap.get("warning").equals(ivsingle.getWarning())){
				status=status+1;
			}
				
			System.out.println(cpkey);
			if(status<6){
				System.out.println("mc_iv_single磁盘数据错误退出"+cpkey);
				duibierr="mc_iv_single报错的key:"+cpkey;
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_iv_single循环总数"+list.size());
		list.remove(list);	
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_iv_single");
		
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
			System.out.println("mc_iv_single取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_iv_single磁盘文件为空");
			cxerr="mc_iv_single磁盘文件为空";
		}
		return cxerr;
	}
}
