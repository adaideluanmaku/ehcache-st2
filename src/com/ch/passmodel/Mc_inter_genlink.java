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

public class Mc_inter_genlink {
	private String duibierr="Inter_Genlink���ݶԱȳɹ�";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="SELECT a.pkid,a.genid1,a.genid2,a.scrqryid FROM mc_inter_genlink a , mc_inter_scrqry b where a.scrqryid = b.scrqryid and b.slcode < 3 and a.genid1 > 0 and a.genid2 > 0 ORDER BY a.pkid, a.genid1, a.genid2, a.scrqryid";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_inter_genlink");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));
		
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("genid1").toString()+"-"+sjmap.get("genid2").toString();
			Element ele = cache.get(cpkey);
			List cplist=(List)ele.getObjectValue();

			int status=0;
			for(int j=0;j<cplist.size();j++){
//				System.out.println(sjmap.get("scrqryid").toString()+":"+cplist.get(j));
				if(sjmap.get("scrqryid").toString().equals(cplist.get(j))){
					status=status+1;
				}
			}
			System.out.println(cpkey);
			if(status<1){
				System.out.println("Inter_Genlink�������ݴ����˳�"+cpkey);
				duibierr="Inter_Genlink�����key:"+cpkey;
				break;
			}
			System.out.println("�Աȳɹ�:"+list.size()+":"+(i+1));
		}
		System.out.println("Inter_Genlinkѭ������"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_inter_genlink");
		
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
			System.out.println("mc_inter_genlinkȡ�������ݣ�"+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_inter_genlink�����ļ�Ϊ��");
			cxerr="mc_inter_genlink�����ļ�Ϊ��";
		}
		return cxerr;
	}
}
