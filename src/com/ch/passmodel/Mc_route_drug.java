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
import com.medicom.core.cache.route.McRouteDrug;

public class Mc_route_drug {
	private String duibierr="mc_route_drug数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String disicdsql="select drugid, st_routeid, slcode, severity, warning, abstract from mc_route_drug where drugid > 0 and st_routeid > 0 order by drugid, st_routeid, slcode";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(disicdsql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_route_drug");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("drugid").toString()+"-"+sjmap.get("st_routeid").toString();
			Element ele=cache.get(cpkey);
			
			int status=0;
			McRouteDrug routedrug=(McRouteDrug)ele.getObjectValue();
			
			System.out.println(sjmap.get("abstract")+":"+routedrug.getAbstract_());
			if(sjmap.get("abstract").equals(routedrug.getAbstract_())){
				status=status+1;
			}
			if(sjmap.get("drugid").equals(routedrug.getDrugid())){
				status=status+1;
			}
			if(sjmap.get("severity").equals(routedrug.getSeverity())){
				status=status+1;
			}
			if(sjmap.get("slcode").equals(routedrug.getSlcode())){
				status=status+1;
			}
			if(sjmap.get("st_routeid").equals(routedrug.getSt_routeid())){
				status=status+1;
			}
			if(sjmap.get("warning").equals(routedrug.getWarning())){
				status=status+1;
			}
			System.out.println(cpkey);
			if(status<6){
				System.out.println("mc_route_drug磁盘数据错误退出"+cpkey);
				duibierr="mc_route_drug报错的key:"+cpkey;
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_route_drug循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_route_drug");
		
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
			System.out.println("mc_route_drug取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_route_drug磁盘文件为空");
			cxerr="mc_route_drug磁盘文件为空";
		}
		return cxerr;
	}
}
