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
import com.medicom.core.cache.lactation.McLactationCls;

public class Mc_lactation_cls {
	private String duibierr="mc_lactation_cls数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="select distinct classid, seqnum, full_path, drugname, route, severity, excretedcode, lactationcode, appcode from mc_lactation_cls where classid  order by seqnum";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_lactation_cls");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));
		
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("classid").toString();
			Element ele=cache.get(cpkey);
			
			int status=0;
			List cplist=(List)ele.getObjectValue();
			for(int j=0;j<cplist.size();j++){
				McLactationCls lactationcls=(McLactationCls)cplist.get(j);
				if(sjmap.get("appcode").equals(lactationcls.getAppcode())){
					status=status+1;
				}
				if(sjmap.get("drugname").equals(lactationcls.getDrugname())){
					status=status+1;
				}
				if(sjmap.get("excretedcode").equals(lactationcls.getExcretedcode())){
					status=status+1;
				}
				if(sjmap.get("full_path").equals(lactationcls.getFull_path())){
					status=status+1;
				}
				if(sjmap.get("lactationcode").equals(lactationcls.getLactationcode())){
					status=status+1;
				}
				if(sjmap.get("route").equals(lactationcls.getRoute())){
					status=status+1;
				}
				if(sjmap.get("seqnum").equals(lactationcls.getSeqnum())){
					status=status+1;
				}
				if(sjmap.get("severity").equals(lactationcls.getSeverity())){
					status=status+1;
				}
				if(status<8){
					status=0;
				}
			}
			System.out.println(cpkey);
			if(status<8){
				System.out.println("mc_indic_scr磁盘数据错误退出"+cpkey);
				duibierr="mc_indic_scr报错的key:"+cpkey;
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_lactation_cls循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_lactation_cls");
		
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
			System.out.println("mc_lactation_cls取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_lactation_cls磁盘文件为空");
			cxerr="mc_lactation_cls磁盘文件为空";
		}
		return cxerr;
	}
}
