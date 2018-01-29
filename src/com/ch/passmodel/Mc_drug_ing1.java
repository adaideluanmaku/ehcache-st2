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

public class Mc_drug_ing1 {
	private String duibierr="mc_drug_ing1���ݶԱȳɹ�";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="select b_ingid,min(b_ing_name) as b_ing_name FROM mc_drug_ing where b_ingid > 0 and b_ing_name <> ' ' and b_ingid is not null and b_ing_name is not null group by b_ingid order by b_ingid";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_drug_ing1");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("b_ingid").toString();
			Element ele=cache.get(cpkey);

			String b_ing_name=(String)ele.getObjectValue();
			int status=0;
//			System.out.println(sjmap.get("b_ing_name")+"��"+cplist.get(j));
			if(sjmap.get("b_ing_name").equals(b_ing_name)){
				status=status+1;
			}
			if(status<1){
				System.out.println("mc_drug_ing1�������ݴ����˳�:"+cpkey);
				duibierr="mc_drug_ing1�����key:"+cpkey;
				break;
			}
			System.out.println(cpkey);
			System.out.println("�Աȳɹ�:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_drug_ing1ѭ������"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_drug_ing1");
		
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
			System.out.println("mc_drug_ing1ȡ�������ݣ�"+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_drug_ing1�����ļ�Ϊ��");
			cxerr="mc_drug_ing1�����ļ�Ϊ��";
		}
		return cxerr;
	}
}
