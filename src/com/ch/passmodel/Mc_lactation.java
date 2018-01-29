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
import com.medicom.core.cache.lactation.McLactation;

public class Mc_lactation {
	private String duibierr="mc_lactation数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="select pkid,drugid,drugname,routetype,routedesc, st_routeids,slcode,severity,warning,abstract,classid from mc_lactation order by drugid, slcode";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_lactation");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));
		
		for(int i=0;i<list.size();i++){
		Map sjmap=(Map)list.get(i);
		String cpkey=sjmap.get("drugid").toString();
		Element ele=cache.get(cpkey);
		List cplist=(List)ele.getObjectValue();
		int status=0;
		for(int j=0;j<cplist.size();j++){
			McLactation lactation=(McLactation)cplist.get(j);
			
			if(sjmap.get("abstract").equals(lactation.getAbstract_())){
				status=status+1;
			}
			if(sjmap.get("classid").equals(lactation.getClassid())){
				status=status+1;
			}
			if(sjmap.get("drugid").equals(lactation.getDrugid())){
				status=status+1;
			}
			if(sjmap.get("drugname").equals(lactation.getDrugname())){
				status=status+1;
			}
			if(sjmap.get("pkid").equals(lactation.getPkid())){
				status=status+1;
			}
			if(sjmap.get("routedesc").equals(lactation.getRoutedesc())){
				status=status+1;
			}
			if(sjmap.get("routetype").equals(lactation.getRoutetype())){
				status=status+1;
			}
			if(sjmap.get("severity").equals(lactation.getSeverity())){
				status=status+1;
			}
			if(sjmap.get("slcode").equals(lactation.getSlcode())){
				status=status+1;
			}
			if(sjmap.get("st_routeids").equals(lactation.getSt_routeids())){
				status=status+1;
			}
			if(sjmap.get("warning").equals(lactation.getWarning())){
				status=status+1;
			}
			
			if(status<11){
				status=0;
			}
//			System.out.println(sjmap.get("articleid").toString()+":"+lactation.getDrugid());
		}
		System.out.println(cpkey);
		if(status<11){
			System.out.println("mc_lactation磁盘数据错误退出"+sjmap.get("drugid").toString()+"-"+sjmap.get("diseaseid").toString());
			duibierr="mc_lactation报错的key:"+sjmap.get("drugid").toString()+"-"+sjmap.get("diseaseid").toString();
			break;
		}
		System.out.println("对比成功:"+list.size()+":"+(i+1));
	}
		System.out.println("mc_lactation循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_lactation");
		
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
			System.out.println("mc_lactation取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_lactation磁盘文件为空");
			cxerr="mc_lactation磁盘文件为空";
		}
		return cxerr;
	}
}
