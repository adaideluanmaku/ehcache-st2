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

public class Mc_drug_ing2 {
	private String duibierr="mc_drug_ing2数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="select genid,min(gen_name) as gen_name FROM mc_drug_ing where is_scr_duping=1 and is_scr_duping is not null and genid > 0 and gen_name <> ' ' and genid is not null and gen_name is not null group by genid order by genid";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_drug_ing2");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("genid").toString();
			Element ele=cache.get(cpkey);

			if(!sjmap.get("gen_name").equals(ele.getObjectValue().toString())){
				System.out.println("mc_drug_ing2磁盘数据错误退出:"+cpkey);
				duibierr="mc_drug_ing2报错的key:"+cpkey;
				break;
			}
			System.out.println(cpkey);
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_drug_ing2循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_drug_ing2");
		
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
			System.out.println("mc_drug_ing2取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_drug_ing2磁盘文件为空");
			cxerr="mc_drug_ing2磁盘文件为空";
		}
		return cxerr;
	}
}
