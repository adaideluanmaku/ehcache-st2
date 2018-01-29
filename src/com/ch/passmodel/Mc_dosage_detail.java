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
import com.medicom.core.cache.dosage.McDosageLink;
import com.medicom.security.CipherTools;

public class Mc_dosage_detail {
	private String duibierr="mc_dosage_detail数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="select drugid, st_routeid, scrid, abstract from mc_dosage_link where drugid is not null and drugid > 0 order by drugid, st_routeid, scrid";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_dosage_detail");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().toString());
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("drugid").toString()+"-"+sjmap.get("st_routeid").toString();
			Element ele=cache.get(CipherTools.aesEncrypt(cpkey).toString());
			int status=0;
			List cplist=(List)ele.getObjectValue();
			for(int j=0;j<cplist.size();j++){
				McDosageLink disicd=(McDosageLink)cplist.get(j);
				if(CipherTools.aesEncrypt(sjmap.get("drugid").toString()).equals(disicd.getDrugid())){
					status=status+1;
				}
				if(CipherTools.aesEncrypt(sjmap.get("st_routeid").toString()).equals(disicd.getSt_routeid())){
					status=status+1;
				}
				if(CipherTools.aesEncrypt(sjmap.get("scrid").toString()).equals(disicd.getScrid())){
					status=status+1;
				}
				if(sjmap.get("abstract").equals(disicd.getAbstract_())){
					status=status+1;
				}
				if(status<4){
					status=0;
				}
//				System.out.println(CipherTools.aesEncrypt(sjmap.get("drugid").toString())+":"+disicd.getDrugid());
//				System.out.println(CipherTools.aesEncrypt(sjmap.get("st_routeid").toString())+":"+disicd.getSt_routeid());
//				System.out.println(CipherTools.aesEncrypt(sjmap.get("scrid").toString())+":"+disicd.getScrid());
//				System.out.println(sjmap.get("abstract")+":"+disicd.getAbstract_());

			}
			System.out.println(cpkey);
			if(status<4){
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
		Cache cache=manager.getCache("mc_dosage_detail");
		
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
			System.out.println("mc_dosage_detail取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_dosage_detail磁盘文件为空");
			cxerr="mc_dosage_detail磁盘文件为空";
		}
		return cxerr;
	}
}
