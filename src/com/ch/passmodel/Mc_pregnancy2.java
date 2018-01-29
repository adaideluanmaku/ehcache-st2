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
import com.medicom.core.cache.pregnancy.McPregnancy2;

public class Mc_pregnancy2 {
	private String duibierr="mc_pregnancy2数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String disicdsql="select pkid,drugid,drugname,routetype,st_routeids,routedesc,pregdayslow,pregdayshigh,pregphasedesc,slcode,severity,warning,abstract,classid from mc_pregnancy2 where drugid > 0 order by drugid,slcode";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(disicdsql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_pregnancy2");
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
				McPregnancy2 pregnancy2=(McPregnancy2)cplist.get(j);
			    System.out.println(sjmap.get("drugname")+":"+pregnancy2.getDrugname());
				if(sjmap.get("abstract").equals(pregnancy2.getAbstract_())){
					status=status+1;
				}
				if(sjmap.get("classid")==null && pregnancy2.getClassid()==null){
					status=status+1;
				}else if(sjmap.get("classid")!=null && pregnancy2.getClassid()!=null){
					if(sjmap.get("classid").equals(pregnancy2.getClassid())){
						status=status+1;
					}
				}
				if(sjmap.get("drugid").equals(pregnancy2.getDrugid())){
					status=status+1;
				}
				if(sjmap.get("drugname").equals(pregnancy2.getDrugname())){
					status=status+1;
				}
				if(sjmap.get("pkid").equals(pregnancy2.getPkid())){
					status=status+1;
				}
				if(sjmap.get("pregdayshigh").equals(pregnancy2.getPregdayshigh())){
					status=status+1;
				}
				if(sjmap.get("pregdayslow").equals(pregnancy2.getPregdayslow())){
					status=status+1;
				}
				if(sjmap.get("pregphasedesc").equals(pregnancy2.getPregphasedesc())){
					status=status+1;
				}
				if(sjmap.get("routedesc").equals(pregnancy2.getRoutedesc())){
					status=status+1;
				}
				if(sjmap.get("routetype").equals(pregnancy2.getRoutetype())){
					status=status+1;
				}
				if(sjmap.get("severity").equals(pregnancy2.getSeverity())){
					status=status+1;
				}
				if(sjmap.get("slcode").equals(pregnancy2.getSlcode())){
					status=status+1;
				}
				if(sjmap.get("st_routeids").equals(pregnancy2.getSt_routeids())){
					status=status+1;
				}
				if(sjmap.get("warning").equals(pregnancy2.getWarning())){
					status=status+1;
				}
				
				
				if(status<14){
					status=0;
				}
			}
			System.out.println(status);
			if(status<14){
				System.out.println("mc_pregnancy2磁盘数据错误退出"+sjmap.get("drugid").toString()+"-"+sjmap.get("diseaseid").toString());
				duibierr="mc_pregnancy2报错的key:"+sjmap.get("drugid").toString()+"-"+sjmap.get("diseaseid").toString();
				break;
			}
		System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_pregnancy2循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_pregnancy2");
		
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
