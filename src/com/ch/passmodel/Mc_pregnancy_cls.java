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
import com.medicom.core.cache.pregnancy.McPregnancyCls;

public class Mc_pregnancy_cls {
	private String duibierr="mc_pregnancy_cls数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String disicdsql="select distinct classid,seqnum,full_path,drugname,route, severity,pregnancydesc, fda, briggs from mc_pregnancy_cls where classid > 0 order by classid, seqnum";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(disicdsql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_pregnancy_cls");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));
		
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("classid").toString();
			Element ele=cache.get(cpkey);
			List cplist=(List)ele.getObjectValue();
			int status=0;
			for(int j=0;j<cplist.size();j++){
				McPregnancyCls pregnancycls=(McPregnancyCls)cplist.get(j);
	//			System.out.println(sjmap.get("articleid").toString()+":"+lactation.getDrugid());
				if(sjmap.get("briggs").equals(pregnancycls.getBriggs())){
					status=status+1;
				}
				if(sjmap.get("classid").equals(pregnancycls.getClassid())){
					status=status+1;
				}
				if(sjmap.get("drugname").equals(pregnancycls.getDrugname())){
					status=status+1;
				}
				if(sjmap.get("fda").equals(pregnancycls.getFda())){
					status=status+1;
				}
				if(sjmap.get("full_path").equals(pregnancycls.getFull_path())){
					status=status+1;
				}
				if(sjmap.get("pregnancydesc").equals(pregnancycls.getPregnancydesc())){
					status=status+1;
				}
				if(sjmap.get("route").equals(pregnancycls.getRoute())){
					status=status+1;
				}
				if(sjmap.get("seqnum").equals(pregnancycls.getSeqnum())){
					status=status+1;
				}
				if(sjmap.get("severity").equals(pregnancycls.getSeverity())){
					status=status+1;
				}
				
				if(status<9){
					status=0;
				}
			}
			System.out.println(cpkey);
			if(status<9){
				System.out.println("mc_pregnancy_cls磁盘数据错误退出"+sjmap.get("drugid").toString()+"-"+sjmap.get("diseaseid").toString());
				duibierr="mc_pregnancy_cls报错的key:"+sjmap.get("drugid").toString()+"-"+sjmap.get("diseaseid").toString();
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_pregnancy_cls循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_pregnancy_cls");
		
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
			System.out.println("mc_pregnancy_cls取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_pregnancy_cls磁盘文件为空");
			cxerr="mc_pregnancy_cls磁盘文件为空";
		}
		return cxerr;
	}
}
