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
import com.medicom.core.cache.pregnancy.McPregnancy1;

public class Mc_pregnancy1 {
	private String duibierr="mc_pregnancy1数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String disicdsql="select pkid, drugid,routetype,st_routeids ,slcode,severity,warning,abstract,classid from mc_pregnancy1 order by drugid,slcode";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(disicdsql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_pregnancy1");
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
				McPregnancy1 pregnancy1=(McPregnancy1)cplist.get(j);
	//			System.out.println(sjmap.get("articleid").toString()+":"+lactation.getDrugid());
				if(sjmap.get("abstract").equals(pregnancy1.getAbstract_())){
					status=status+1;
				}
				if(sjmap.get("classid")==null && pregnancy1.getClassid()==null){
					status=status+1;
				}else if(sjmap.get("classid")!=null && pregnancy1.getClassid()!=null){
					if(sjmap.get("classid").equals(pregnancy1.getClassid())){
						status=status+1;
					}
				}
				if(sjmap.get("drugid").equals(pregnancy1.getDrugid())){
					status=status+1;
				}
				if(sjmap.get("pkid").equals(pregnancy1.getPkid())){
					status=status+1;
				}
				if(sjmap.get("routetype").equals(pregnancy1.getRoutetype())){
					status=status+1;
				}
				if(sjmap.get("severity").equals(pregnancy1.getSeverity())){
					status=status+1;
				}
				if(sjmap.get("slcode").equals(pregnancy1.getSlcode())){
					status=status+1;
				}
				if(sjmap.get("st_routeids").equals(pregnancy1.getSt_routeids())){
					status=status+1;
				}
				if(sjmap.get("warning").equals(pregnancy1.getWarning())){
					status=status+1;
				}
				
				if(status<9){
					status=0;
				}
			}
			System.out.println(cpkey);
			if(status<9){
				System.out.println("mc_pregnancy1磁盘数据错误退出"+sjmap.get("drugid").toString()+"-"+sjmap.get("diseaseid").toString());
				duibierr="mc_pregnancy1报错的key:"+sjmap.get("drugid").toString()+"-"+sjmap.get("diseaseid").toString();
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_pregnancy1循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_pregnancy1");
		
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
			System.out.println("mc_pregnancy1取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_pregnancy1磁盘文件为空");
			cxerr="mc_pregnancy1磁盘文件为空";
		}
		return cxerr;
	}
}
