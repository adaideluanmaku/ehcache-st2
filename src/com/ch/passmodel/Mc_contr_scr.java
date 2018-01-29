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
import com.medicom.core.cache.contr.McContrScr;
import com.medicom.security.CipherTools;

public class Mc_contr_scr {
	private String duibierr="mc_contr_scr数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="select drugid,diseaseid,st_routeids,routetype,routedesc,slcode,severity,warning from mc_contr_scr ORDER BY drugid,slcode,diseaseid";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_contr_scr");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().toString());
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("drugid").toString()+"-"+sjmap.get("diseaseid").toString();
			Element ele=cache.get(CipherTools.aesEncrypt(cpkey).toString());
			int status=0;
			List cplist=(List)ele.getObjectValue();
			for(int j=0;j<cplist.size();j++){
				McContrScr disicd=(McContrScr)cplist.get(j);
				if(CipherTools.aesEncrypt(sjmap.get("drugid").toString()).equals(disicd.getDrugid())){
					status=status+1;
				}
				if(sjmap.get("diseaseid").equals(disicd.getDiseaseid())){
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
				if(sjmap.get("severity").equals(disicd.getSeverity())){
					status=status+1;
				}
				if(sjmap.get("warning").equals(disicd.getWarning())){
					status=status+1;
				}
				if(status<8){
					status=0;
				}
//				System.out.println(sjmap.get("drugid")+":"+disicd.getDrugid());
//				System.out.println(sjmap.get("diseaseid")+":"+disicd.getDiseaseid());
//				System.out.println(sjmap.get("routetype")+":"+disicd.getRoutetype());
//				System.out.println(sjmap.get("st_routeids")+":"+disicd.getSt_routeids());
//				System.out.println(sjmap.get("routedesc")+":"+disicd.getRoutedesc());
//				System.out.println(sjmap.get("slcode")+":"+disicd.getSlcode());
//				System.out.println(sjmap.get("severity")+":"+disicd.getSeverity());
//				System.out.println(sjmap.get("warning")+":"+disicd.getWarning());
			}
			System.out.println(cpkey);
			if(status<8){
				System.out.println("mc_contr_scr磁盘数据错误退出:"+cpkey);
				duibierr="mc_contr_scr报错的key:"+cpkey;
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_contr_scr循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_contr_scr");
		
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
			System.out.println("mc_contr_scr取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_contr_scr磁盘文件为空");
			cxerr="mc_contr_scr磁盘文件为空";
		}
		return cxerr;
	}
}
