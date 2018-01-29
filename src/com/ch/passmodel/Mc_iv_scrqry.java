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
import com.medicom.core.cache.iv.McIvScrqry;

public class Mc_iv_scrqry {
	private String duibierr="mc_iv_scrqry���ݶԱȳɹ�";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="select articleid,slcode,severity,warning from mc_iv_scrqry order by articleid";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);

		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_iv_scrqry");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));

		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("articleid").toString();
			Element ele=cache.get(cpkey);
			
			int status=0;

			McIvScrqry ivscrqry=(McIvScrqry)ele.getObjectValue();
//			System.out.println(sjmap.get("diseaseid").toString()+":"+cplist.get(j));
			if(sjmap.get("articleid").equals(ivscrqry.getArticleid())){
				status=status+1;
			}
			if(sjmap.get("severity").equals(ivscrqry.getSeverity())){
				status=status+1;
			}
			if(sjmap.get("slcode").equals(ivscrqry.getSlcode())){
				status=status+1;
			}
			if(sjmap.get("warning").equals(ivscrqry.getWarning())){
				status=status+1;
			}
			if(status<4){
				status=0;
			}
			
			System.out.println(cpkey);
			if(status<4){
				System.out.println("mc_iv_scrqry�������ݴ����˳�"+cpkey);
				duibierr="mc_iv_scrqry�����key:"+cpkey;
				break;
			}
			System.out.println("�Աȳɹ�:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_iv_scrqryѭ������"+list.size());
		list.remove(list);	
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_iv_scrqry");
		
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
			System.out.println("ȡ�������ݣ�"+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("�����ļ�Ϊ��");
			cxerr="�����ļ�Ϊ��";
		}
		return cxerr;
	}
}
