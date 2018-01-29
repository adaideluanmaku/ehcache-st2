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
import com.medicom.core.cache.adult.McAdult;

public class Mc_adult {
	private String duibierr="mc_adult数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="select pkid,drugid,drugname,routetype,st_routeids,routedesc,slcode,agelow,unequal_low,agelow_unit,agehigh,unequal_high,agehigh_unit,agedesc,severity,warning,abstract from mc_adult order by drugid,slcode";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_adult");
		
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
//			System.out.println(cache.getKeys().get(1).toString());
			String cpkey=sjmap.get("drugid").toString();
			Element ele=cache.get(cpkey);
			List cplist=(List)ele.getObjectValue();
			int status=0;

			for(int j=0;j<cplist.size();j++){
				McAdult disicd=(McAdult)cplist.get(j);
				
				if(sjmap.get("pkid").equals(disicd.getPkid())){
					status=status+1;
				}
				if(sjmap.get("drugid").equals(disicd.getDrugid())){
					status=status+1;
				}
				if(sjmap.get("drugname").equals(disicd.getDrugname())){
					status=status+1;
				}
				if(sjmap.get("routetype").equals(disicd.getRoutetype())){
					status=status+1;
				}
				if(sjmap.get("st_routeids").equals(disicd.getSt_routeids())){
					status=status+1;
				}	
				if(sjmap.get("routedesc").equals(disicd.getRoutedesc())){
					status=status+1;
				}	
				if(sjmap.get("slcode").equals(disicd.getSlcode())){
					status=status+1;
				}	
				if(sjmap.get("agelow").equals(disicd.getAgelow())){
					status=status+1;
				}	
				if(sjmap.get("unequal_low").equals(disicd.getUnequal_low())){
					status=status+1;
				}
				if(sjmap.get("agelow_unit").equals(disicd.getAgelow_unit())){
					status=status+1;
				}
				if(sjmap.get("agehigh").equals(disicd.getAgehigh())){
					status=status+1;
				}
				if(sjmap.get("unequal_high").equals(disicd.getUnequal_high())){
					status=status+1;
				}
				if(sjmap.get("agehigh_unit").equals(disicd.getAgehigh_unit())){
					status=status+1;
				}
				if(sjmap.get("agedesc").equals(disicd.getAgedesc())){
					status=status+1;
				}
				if(sjmap.get("severity").equals(disicd.getSeverity())){
					status=status+1;
				}
				if(sjmap.get("warning").equals(disicd.getWarning())){
					status=status+1;
				}
				if(sjmap.get("abstract").equals(disicd.getAbstract_())){
					status=status+1;
				}
				if(status<17){
					status=0;
				}
//				System.out.println(sjmap.get("pkid")+":"+disicd.getPkid());
//				System.out.println(sjmap.get("drugid")+":"+disicd.getDrugid());
//				System.out.println(sjmap.get("abstract")+":"+disicd.getAbstract_());
//				System.out.println(sjmap.get("agedesc")+":"+disicd.getAgedesc());
//				System.out.println(sjmap.get("agehigh")+":"+disicd.getAgehigh());
//				System.out.println(sjmap.get("agehigh_unit")+":"+disicd.getAgehigh_unit());
//				System.out.println(sjmap.get("agelow")+":"+disicd.getAgelow());
//				System.out.println(sjmap.get("agelow_unit")+":"+disicd.getAgelow_unit());
//				System.out.println(sjmap.get("drugname")+":"+disicd.getDrugname());
//				System.out.println(sjmap.get("routedesc")+":"+disicd.getRoutedesc());
//				System.out.println(sjmap.get("routetype")+":"+disicd.getRoutetype());
//				System.out.println(sjmap.get("severity")+":"+disicd.getSeverity());
//				System.out.println(sjmap.get("slcode")+":"+disicd.getSlcode());
//				System.out.println(sjmap.get("st_routeids")+":"+disicd.getSt_routeids());
//				System.out.println(sjmap.get("unequal_high")+":"+disicd.getUnequal_high());
//				System.out.println(sjmap.get("unequal_low")+":"+disicd.getUnequal_low());
//				System.out.println(sjmap.get("warning")+":"+disicd.getWarning());
				
			}
			System.out.println(cpkey);
			if(status<17){
				System.out.println("mc_adult磁盘数据错误退出"+cpkey);
				duibierr="mc_adult报错的key:"+cpkey;
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_adult循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_adult");
		
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
			System.out.println("mc_adult取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_adult磁盘文件为空");
			cxerr="mc_adult磁盘文件为空";
		}
		return cxerr;
	}
}
