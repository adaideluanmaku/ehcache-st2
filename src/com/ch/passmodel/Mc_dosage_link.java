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
import com.medicom.security.CipherTools;

public class Mc_dosage_link {
	private String duibierr="mc_dosage_link数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="SELECT distinct drugid, st_routeid, scrid FROM mc_dosage_link where drugid > 0 and st_routeid > 0 and scrid >0 order BY drugid, st_routeid, scrid";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_dosage_link");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("drugid").toString()+"-"+sjmap.get("st_routeid").toString();
			Element ele=cache.get(CipherTools.aesEncrypt(cpkey).toString());
			int status=0;
			List cplist=(List)ele.getObjectValue();
			for(int j=0;j<cplist.size();j++){
				if(CipherTools.aesEncrypt(sjmap.get("scrid").toString()).equals(cplist.get(j))){
					status=status+1;
				}				
//				System.out.println(CipherTools.aesEncrypt(sjmap.get("scrid").toString())+":"+cplist.get(j));
			}
			System.out.println(cpkey);
			if(status<1){
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
		Cache cache=manager.getCache("mc_dosage_link");
		
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
			System.out.println("mc_dosage_link取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_dosage_link磁盘文件为空");
			cxerr="mc_dosage_link磁盘文件为空";
		}
		return cxerr;
	}
}
