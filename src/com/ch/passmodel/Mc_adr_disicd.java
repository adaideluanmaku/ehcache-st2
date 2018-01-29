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
import com.medicom.core.cache.adr.McAdrDisicd;
import com.medicom.security.CipherTools;

public class Mc_adr_disicd {
	private String duibierr="mc_adr_disicd���ݶԱȳɹ�";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="Select diseaseid,icd_code,filter_level,is_first_lev from mc_adr_disicd order by icd_code,is_first_lev";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_adr_disicd");
		
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);

			String cpkey=sjmap.get("icd_code").toString();
			Element ele=cache.get(cpkey);
			List cplist=(List)ele.getObjectValue();
			int status=0;
			for(int j=0;j<cplist.size();j++){
				McAdrDisicd disicd=(McAdrDisicd)cplist.get(j);
				
				if(CipherTools.aesEncrypt(sjmap.get("diseaseid").toString()).equals(disicd.getDiseaseid())){
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
//				System.out.println(CipherTools.aesEncrypt(sjmap.get("diseaseid").toString())+":"+disicd.getDiseaseid());
//				System.out.println(sjmap.get("icd_code")+":"+disicd.getIcd_code());
//				System.out.println(sjmap.get("filter_level")+":"+disicd.getFilter_level());
//				System.out.println(sjmap.get("is_first_lev")+":"+disicd.getIs_first_lev());
			}
			System.out.println(cpkey);
			if(status<4){
				System.out.println("mc_adr_disicd�������ݴ����˳�:"+cpkey);
				duibierr="mc_adr_disicd�����key:"+cpkey;
				break;
			}
			System.out.println("�Աȳɹ�:"+list.size()+":"+(i+1));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
		}
		System.out.println("mc_adr_disicdѭ������"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_adr_disicd");
		
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
			System.out.println("mc_adr_disicdȡ�������ݣ�"+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_adr_disicd�����ļ�Ϊ��");
			cxerr="mc_adr_disicd�����ļ�Ϊ��";
		}
		return cxerr;
	}
}
