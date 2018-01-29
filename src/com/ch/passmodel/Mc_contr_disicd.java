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
import com.medicom.core.cache.contr.McContrDisicd;

public class Mc_contr_disicd {
	private String duibierr="mc_contr_disicd���ݶԱȳɹ�";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="Select diseaseid,icd_code,filter_level,is_first_lev from mc_contr_disicd ORDER BY icd_code,is_first_lev,filter_level,diseaseid";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_contr_disicd");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().toString());
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("icd_code").toString();
			Element ele=cache.get(cpkey);
			
			int status=0;
			List cplist=(List)ele.getObjectValue();
			for(int j=0;j<cplist.size();j++){
				McContrDisicd disicd=(McContrDisicd)cplist.get(j);
				if(sjmap.get("diseaseid").equals(disicd.getDiseaseid())){
					status=status+1;
				}
				if(sjmap.get("icd_code").equals(disicd.getIcd_code())){
					status=status+1;
				}
				if(sjmap.get("filter_level").equals(disicd.getFilter_level())){
					status=status+1;
				}
				if(sjmap.get("is_first_lev").equals(disicd.getIs_first_lev())){
					status=status+1;
				}
				if(status<4){
					status=0;
				}
			}
			System.out.println(cpkey);
			if(status<4){
				System.out.println("mc_brief_content�������ݴ����˳�:"+cpkey);
				duibierr="mc_brief_content�����key:"+cpkey;
				break;
			}
			System.out.println("�Աȳɹ�:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_brief_contentѭ������"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_contr_disicd");
		
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
			System.out.println("mc_contr_disicdȡ�������ݣ�"+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_contr_disicd�����ļ�Ϊ��");
			cxerr="mc_contr_disicd�����ļ�Ϊ��";
		}
		return cxerr;
	}
}
