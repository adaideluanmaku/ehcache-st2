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
import com.medicom.core.cache.adr.McAdr;
import com.medicom.core.cache.adr.McAdrDisicd;
import com.medicom.security.CipherTools;

public class Mc_adr {
	private String duibierr="mc_adr数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="select articleid,drugid,diseaseid,st_routeids,routetype,routedesc,slcode,severity,warning from mc_adr order by drugid,diseaseid";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_adr");
		
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
//			System.out.println(cache.getKeys().get(1).toString());
			String cpkey=CipherTools.aesEncrypt(sjmap.get("drugid").toString()+"-"+sjmap.get("diseaseid").toString());
			Element ele=cache.get(cpkey);
			List cplist=(List)ele.getObjectValue();
			int status=0;

			for(int j=0;j<cplist.size();j++){
				McAdr disicd=(McAdr)cplist.get(j);
				
				if(CipherTools.aesEncrypt(sjmap.get("articleid").toString()).equals(disicd.getArticleid())){
					status=status+1;
				}
				if(CipherTools.aesEncrypt(sjmap.get("drugid").toString()).equals(disicd.getDrugid())){
					status=status+1;
				}
				if(sjmap.get("diseaseid").equals(disicd.getDiseaseid())){
					status=status+1;
				}
				if(sjmap.get("st_routeids").equals(disicd.getSt_routeids())){
					status=status+1;
				}
				if(sjmap.get("routetype").equals(disicd.getRoutetype())){
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
				if(status<9){
					status=0;
				}
//				System.out.println(CipherTools.aesEncrypt(sjmap.get("articleid").toString())+":"+disicd.getDrugid());
//				System.out.println(CipherTools.aesEncrypt(sjmap.get("drugid").toString())+":"+disicd.getDiseaseid());
//				System.out.println(sjmap.get("diseaseid")+":"+disicd.getRoutetype());
//				System.out.println(sjmap.get("st_routeids")+":"+disicd.getSt_routeids());
//				System.out.println(sjmap.get("routetype")+":"+disicd.getRoutedesc());
//				System.out.println(sjmap.get("routedesc")+":"+disicd.getSlcode());
//				System.out.println(sjmap.get("slcode")+":"+disicd.getSeverity());
//				System.out.println(sjmap.get("severity")+":"+disicd.getWarning());
//				System.out.println(sjmap.get("warning")+":"+disicd.getWarning());
			}
			System.out.println(cpkey);
			if(status<9){
				System.out.println("mc_adr磁盘数据错误退出"+sjmap.get("drugid").toString()+"-"+sjmap.get("diseaseid").toString());
				duibierr="mc_adr报错的key:"+sjmap.get("drugid").toString()+"-"+sjmap.get("diseaseid").toString();
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_adr循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_adr");
		
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
			System.out.println("mc_adr取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_adr磁盘文件为空");
			cxerr="mc_adr磁盘文件为空";
		}
		return cxerr;
	}
}
