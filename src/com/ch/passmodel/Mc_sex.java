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
import com.medicom.core.cache.sex.McSex;

public class Mc_sex {
	private String duibierr="mc_sex数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="select pkid,drugid,drugname,routetype,routedesc, st_routeids,sexcode,slcode,severity,warning,abstract from mc_sex order by drugid, slcode";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_sex");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("drugid").toString();
			Element ele=cache.get(cpkey);
			
			int status=0;
			List cplist=(List)ele.getObjectValue();
			for(int j=0;j<cplist.size();j++){
				McSex sex=(McSex)cplist.get(j);
//				System.out.println(sjmap.get("abstract")+":"+routedrugvalid.getAbstract_());
				if(sjmap.get("abstract").equals(sex.getAbstract_())){
					status=status+1;
				}
				if(sjmap.get("drugid").equals(sex.getDrugid())){
					status=status+1;
				}
				if(sjmap.get("drugname").equals(sex.getDrugname())){
					status=status+1;
				}
				if(sjmap.get("pkid").equals(sex.getPkid())){
					status=status+1;
				}
				if(sjmap.get("routedesc").equals(sex.getRoutedesc())){
					status=status+1;
				}
				if(sjmap.get("routetype").equals(sex.getRoutetype())){
					status=status+1;
				}
				if(sjmap.get("severity").equals(sex.getSeverity())){
					status=status+1;
				}
				if(sjmap.get("sexcode").equals(sex.getSexcode())){
					status=status+1;
				}
				if(sjmap.get("slcode").equals(sex.getSlcode())){
					status=status+1;
				}
				if(sjmap.get("st_routeids").equals(sex.getSt_routeids())){
					status=status+1;
				}
				if(sjmap.get("warning").equals(sex.getWarning())){
					status=status+1;
				}
				
				if(status<11){
					status=0;
				}
			}
			System.out.println(cpkey);
			if(status<11){
				System.out.println("mc_sex磁盘数据错误退出"+cpkey);
				duibierr="mc_sex报错的key:"+cpkey;
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_sex循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_sex");
		
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
			System.out.println("mc_sex取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_sex磁盘文件为空");
			cxerr="mc_sex磁盘文件为空";
		}
		return cxerr;
	}
}
