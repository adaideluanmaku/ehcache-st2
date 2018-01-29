package com.ch.passmodel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.ch.jdbc.Mysqlconnection;
import com.ch.unit.Ehcache;
import com.medicom.core.cache.pediatric.McPediatric;

public class Mc_pediatric {
	private String duibierr="mc_pediatric数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="Select pkid,drugid,drugname,routetype,routedesc,st_routeids,agelow,unequal_low,agelow_unit,agehigh,unequal_high,agehigh_unit,agedesc,icd_codes,slcode,severity,warning,abstract from mc_pediatric order by drugid,slcode";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_pediatric");
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
			McPediatric pediatric=(McPediatric)cplist.get(j);
//			System.out.println(sjmap.get("articleid").toString()+":"+lactation.getDrugid());
			if(sjmap.get("abstract").equals(pediatric.getAbstract_())){
				status=status+1;
			}
			if(sjmap.get("agedesc").equals(pediatric.getAgedesc())){
				status=status+1;
			}
			if(sjmap.get("agehigh").equals(pediatric.getAgehigh())){
				status=status+1;
			}
			if(sjmap.get("agehigh_unit").equals(pediatric.getAgehigh_unit())){
				status=status+1;
			}
			if(sjmap.get("agelow").equals(pediatric.getAgelow())){
				status=status+1;
			}
			if(sjmap.get("agelow_unit").equals(pediatric.getAgelow_unit())){
				status=status+1;
			}
			if(sjmap.get("drugid").equals(pediatric.getDrugid())){
				status=status+1;
			}
			if(sjmap.get("drugname").equals(pediatric.getDrugname())){
				status=status+1;
			}
			if(sjmap.get("icd_codes").equals(pediatric.getIcd_codes())){
				status=status+1;
			}
			if(sjmap.get("pkid").equals(pediatric.getPkid())){
				status=status+1;
			}
			if(sjmap.get("routedesc").equals(pediatric.getRoutedesc())){
				status=status+1;
			}
			if(sjmap.get("routetype").equals(pediatric.getRoutetype())){
				status=status+1;
			}
			if(sjmap.get("severity").equals(pediatric.getSeverity())){
				status=status+1;
			}
			if(sjmap.get("slcode").equals(pediatric.getSlcode())){
				status=status+1;
			}
			if(sjmap.get("st_routeids").equals(pediatric.getSt_routeids())){
				status=status+1;
			}
			if(sjmap.get("unequal_high").equals(pediatric.getUnequal_high())){
				status=status+1;
			}
			if(sjmap.get("unequal_low").equals(pediatric.getUnequal_low())){
				status=status+1;
			}
			if(sjmap.get("warning").equals(pediatric.getWarning())){
				status=status+1;
			}
			if(status<18){
				status=0;
			}
		}
		System.out.println(cpkey);
		if(status<18){
			System.out.println("mc_pediatric磁盘数据错误退出"+sjmap.get("drugid").toString()+"-"+sjmap.get("diseaseid").toString());
			duibierr="mc_pediatric报错的key:"+sjmap.get("drugid").toString()+"-"+sjmap.get("diseaseid").toString();
			break;
		}
		System.out.println("对比成功:"+list.size()+":"+(i+1));
	}
		System.out.println("mc_pediatric循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}
	
	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_pediatric");

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
			System.out.println("mc_pediatric取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_pediatric磁盘文件为空");
			cxerr="mc_pediatric磁盘文件为空";
		}
		return cxerr;
	}
}
