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
import com.medicom.core.cache.indic.McIndicQrydtl;
import com.medicom.security.CipherTools;

public class Mc_indic_qrydtl {
	private String duibierr="mc_indic_qrydtl数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="SELECT drugid,title,abstract FROM mc_indic_qrydtl where drugid is not null and drugid > 0 order by drugid";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_indic_qrydtl");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));
		
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("drugid").toString();
			Element ele=cache.get(CipherTools.aesEncrypt(cpkey));
			McIndicQrydtl indicqrydtl=(McIndicQrydtl)ele.getObjectValue();	
			
			int status=0;
			System.out.println(CipherTools.aesEncrypt(sjmap.get("drugid").toString())+":"+indicqrydtl.getDrugid());
			System.out.println(sjmap.get("title")+":"+indicqrydtl.getTitle());
			System.out.println(sjmap.get("abstract")+":"+indicqrydtl.getAbstract_());
			if(CipherTools.aesEncrypt(sjmap.get("drugid").toString()).equals(indicqrydtl.getDrugid())){
				status=status+1;
			}
			if(sjmap.get("title").equals(indicqrydtl.getTitle())){
				status=status+1;
			}
			if(sjmap.get("abstract").equals(indicqrydtl.getAbstract_())){
				status=status+1;
			}

			System.out.println(cpkey);
			if(status<3){
				System.out.println("mc_indic_qrydtl磁盘数据错误退出"+cpkey);
				duibierr="mc_indic_qrydtl报错的key:"+cpkey;
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_indic_qrydtl循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_indic_qrydtl");
		
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
			System.out.println("mc_indic_qrydtl取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_indic_qrydtl磁盘文件为空");
			cxerr="磁盘文件为空";
		}
		return cxerr;
	}
}
