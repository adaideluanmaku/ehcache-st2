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
import com.medicom.core.cache.route.McRouteDrugvalid;

public class Mc_route_drugvalid {
	private String duibierr="mc_route_drugvalid数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="select drugid, st_routeids, slcode, severity, warning, abstract from mc_route_drugvalid where drugid > 0 order by drugid";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_route_drugvalid");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("drugid").toString()+"-"+sjmap.get("st_routeids").toString();
			Element ele=cache.get(cpkey);
			
			int status=0;
			McRouteDrugvalid routedrugvalid=(McRouteDrugvalid)ele.getObjectValue();
//			System.out.println(sjmap.get("abstract")+":"+routedrugvalid.getAbstract_());
//			System.out.println(sjmap.get("drugid")+":"+routedrugvalid.getDrugid());
//			System.out.println(sjmap.get("severity")+":"+routedrugvalid.getSeverity());
//			System.out.println(sjmap.get("slcode")+":"+routedrugvalid.getSlcode());
//			System.out.println(sjmap.get("st_routeids")+":"+routedrugvalid.getSt_routeids());
//			System.out.println(sjmap.get("warning")+":"+routedrugvalid.getWarning());
			if(sjmap.get("abstract").equals(routedrugvalid.getAbstract_())){
				status=status+1;
			}
			if(sjmap.get("drugid").equals(routedrugvalid.getDrugid())){
				status=status+1;
			}
			if(sjmap.get("severity").equals(routedrugvalid.getSeverity())){
				status=status+1;
			}
			if(sjmap.get("slcode").equals(routedrugvalid.getSlcode())){
				status=status+1;
			}
			if(sjmap.get("st_routeids").equals(routedrugvalid.getSt_routeids())){
				status=status+1;
			}
			if(sjmap.get("warning").equals(routedrugvalid.getWarning())){
				status=status+1;
			}
			if(status<6){
				status=0;
			}
			System.out.println(cpkey);
			if(status<6){
				System.out.println("mc_route_drugvalid磁盘数据错误退出"+cpkey);
				duibierr="mc_route_drugvalid报错的key:"+cpkey;
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_route_drugvalid循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_route_drugvalid");
		
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
			System.out.println("mc_route_drugvalid取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_route_drugvalid磁盘文件为空");
			cxerr="mc_route_drugvalid磁盘文件为空";
		}
		return cxerr;
	}
}
