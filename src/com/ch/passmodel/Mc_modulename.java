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
import com.medicom.core.cache.sys.McModuleName;

public class Mc_modulename {
	private String duibierr="mc_modulename数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="Select pkid,moduletype,moduleid, modulename,pharm_modulename,seqnum,has_std,has_custom,has_shield,is_show_menu from mc_modulename order by moduletype, seqnum";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_modulename");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));
		
		for(int i=0;i<list.size();i++){
		Map sjmap=(Map)list.get(i);
		String cpkey=sjmap.get("moduletype").toString();
		Element ele=cache.get(cpkey);
		List cplist=(List)ele.getObjectValue();
		int status=0;
		for(int j=0;j<cplist.size();j++){
			McModuleName modulename=(McModuleName)cplist.get(j);
//			System.out.println(sjmap.get("articleid").toString()+":"+lactation.getDrugid());
			if(sjmap.get("has_custom").equals(modulename.getHas_custom())){
				status=status+1;
			}
			if(sjmap.get("has_shield").equals(modulename.getHas_shield())){
				status=status+1;
			}
			if(sjmap.get("has_std").equals(modulename.getHas_std())){
				status=status+1;
			}
			if(sjmap.get("is_show_menu").equals(modulename.getIs_show_menu())){
				status=status+1;
			}
			if(sjmap.get("moduleid").equals(modulename.getModuleid())){
				status=status+1;
			}
			if(sjmap.get("modulename").equals(modulename.getModulename())){
				status=status+1;
			}
			if(sjmap.get("moduletype").equals(modulename.getModuletype())){
				status=status+1;
			}
			if(sjmap.get("pharm_modulename")==null && modulename.getPharm_modulename()==null){
				status=status+1;
			}else if(sjmap.get("pharm_modulename")!=null && modulename.getPharm_modulename()!=null){
				if(sjmap.get("pharm_modulename").equals(modulename.getPharm_modulename())){
					status=status+1;
				}
			}
			if(sjmap.get("pkid").equals(modulename.getPkid())){
				status=status+1;
			}
			if(sjmap.get("seqnum").equals(modulename.getSeqnum())){
				status=status+1;
			}
			if(status<10){
				status=0;
			}
		}
		System.out.println(cpkey);
		if(status<10){
			System.out.println("mc_modulename磁盘数据错误退出"+sjmap.get("drugid").toString()+"-"+sjmap.get("diseaseid").toString());
			duibierr="mc_modulename报错的key:"+sjmap.get("drugid").toString()+"-"+sjmap.get("diseaseid").toString();
			break;
		}
		System.out.println("对比成功:"+list.size()+":"+(i+1));
	}
		System.out.println("mc_modulename循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_modulename");
		
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
			System.out.println("mc_modulename取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_modulename磁盘文件为空");
			cxerr="mc_modulename磁盘文件为空";
		}
		return cxerr;
	}
}
