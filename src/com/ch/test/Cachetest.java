package com.ch.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.ch.jdbc.Mysqlconnection;
import com.ch.passthread.Passthread;
import com.ch.unit.Ehcache;
import com.medicom.core.cache.adr.McAdrDisicd;
import com.medicom.core.cache.contr.McContrScr;
import com.medicom.security.CipherTools;

public class Cachetest {
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
String duibierr;
		
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="SELECT a.pkid, a.genid1, a.genid2, a.scrqryid FROM mc_inter_genlink a , mc_inter_scrqry b where a.scrqryid = b.scrqryid and b.slcode >= 3 and a.genid1 > 0 and a.genid2 > 0 ORDER BY a.pkid, a.genid1, a.genid2, a.scrqryid";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_inter_genlink2");
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
				System.out.println("Inter_Genlink2磁盘数据错误退出"+cpkey);
				duibierr="Inter_Genlink2报错的key:"+cpkey;
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("Inter_Genlink2循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		manager.shutdown();
	}
}