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
import com.medicom.security.CipherTools;

public class Mc_allergroup_bingid {
	private String duibierr="mc_allergroup_bingid数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="SELECT distinct groupid, b_ingid FROM mc_allergroup_bingid WHERE groupid > 0 and b_ingid > 0 and groupid is not null and b_ingid is not null ORDER BY groupid, b_ingid";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_allergroup_bingid");
//		System.out.println(cache.getKeys().toString());
//		System.out.println(cache.getSize());
		
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			
			String cpkey=sjmap.get("groupid").toString();
			Element ele=cache.get(cpkey);
			List cplist=(List)ele.getObjectValue();
			int status=0;
			System.out.println(cplist);
			for(int j=0;j<cplist.size();j++){
				if(sjmap.get("b_ingid").toString().equals(cplist.get(j))){
					status=status+1;
				}
//				System.out.println(sjmap.get("b_ingid")+":"+disicd.getDrugid());
			}
			System.out.println(cpkey);
			if(status<1){
				System.out.println("mc_allergroup_bingid磁盘数据错误退出"+cpkey);
				duibierr="mc_allergroup_bingid报错的key:"+cpkey;
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_allergroup_bingid循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_allergroup_bingid");
		
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
			System.out.println("mc_allergroup_bingid取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_allergroup_bingid磁盘文件为空");
			cxerr="mc_allergroup_bingid磁盘文件为空";
		}
		return cxerr;
	}
}
