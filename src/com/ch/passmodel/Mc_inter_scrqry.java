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
import com.medicom.core.cache.inter.McInterScrqry;
import com.medicom.security.CipherTools;

public class Mc_inter_scrqry {
	private String duibierr="mc_indic_scr数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="SELECT scrqryid,articleid,class_name1,class_name2,routetype1,st_routeids1,routedesc1,routetype2,st_routeids2,routedesc2,slcode,severity,warning FROM mc_inter_scrqry";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);

		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_inter_scrqry");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));

		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("scrqryid").toString();
			Element ele=cache.get(cpkey);
			
			McInterScrqry interscrqry=(McInterScrqry)ele.getObjectValue();	
			int status=0;
			
//			System.out.println(sjmap.get("articleid")+":"+interscrqry.getArticleid());
//			System.out.println(sjmap.get("class_name1")+":"+interscrqry.getClass_name1());
//			System.out.println(sjmap.get("class_name2")+":"+interscrqry.getClass_name2());
//			System.out.println(sjmap.get("routedesc1")+":"+interscrqry.getRoutedesc1());
//			System.out.println(sjmap.get("routedesc2")+":"+interscrqry.getRoutedesc2());
//			System.out.println(sjmap.get("routetype1")+":"+interscrqry.getRoutetype1());
//			System.out.println(sjmap.get("routetype2")+":"+interscrqry.getRoutetype2());
//			System.out.println(sjmap.get("scrqryid")+":"+interscrqry.getScrqryid());
//			System.out.println(sjmap.get("severity")+":"+interscrqry.getSeverity());
//			System.out.println(sjmap.get("slcode")+":"+interscrqry.getSlcode());
//			System.out.println(sjmap.get("st_routeids1")+":"+interscrqry.getSt_routeids1());
//			System.out.println(sjmap.get("st_routeids2")+":"+interscrqry.getSt_routeids2());
//			System.out.println(sjmap.get("warning")+":"+interscrqry.getWarning());
			if(CipherTools.aesEncrypt(sjmap.get("articleid").toString()).equals(interscrqry.getArticleid())){
				status=status+1;
			}
			if(sjmap.get("class_name1").equals(interscrqry.getClass_name1())){
				status=status+1;
			}
			if(sjmap.get("class_name2").equals(interscrqry.getClass_name2())){
				status=status+1;
			}
			if(sjmap.get("routedesc1").equals(interscrqry.getRoutedesc1())){
				status=status+1;
			}
			if(sjmap.get("routedesc2").equals(interscrqry.getRoutedesc2())){
				status=status+1;
			}
			if(sjmap.get("routetype1").equals(interscrqry.getRoutetype1())){
				status=status+1;
			}
			if(sjmap.get("routetype2").equals(interscrqry.getRoutetype2())){
				status=status+1;
			}
			if(sjmap.get("scrqryid").equals(interscrqry.getScrqryid())){
				status=status+1;
			}
			if(sjmap.get("severity").equals(interscrqry.getSeverity())){
				status=status+1;
			}
			if(sjmap.get("slcode").equals(interscrqry.getSlcode())){
				status=status+1;
			}
			if(sjmap.get("st_routeids1").equals(interscrqry.getSt_routeids1())){
				status=status+1;
			}
			if(sjmap.get("st_routeids2").equals(interscrqry.getSt_routeids2())){
				status=status+1;
			}
			if(sjmap.get("warning").equals(interscrqry.getWarning())){
				status=status+1;
			}
			
			System.out.println(cpkey);
			if(status<13){
				System.out.println("mc_inter_scrqry磁盘数据错误退出"+cpkey);
				duibierr="mc_inter_scrqry报错的key:"+cpkey;
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_inter_scrqry循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}
	
	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_inter_scrqry");
		
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
			System.out.println("取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("磁盘文件为空");
			cxerr="磁盘文件为空";
		}
		return cxerr;
	}
}
