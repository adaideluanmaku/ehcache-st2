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
import com.medicom.core.cache.druglevel.McDruglevelScrqry;

public class Mc_druglevel_scrqry {
	private String duibierr="mc_druglevel_scrqry数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="select scrqryid,calculate_label,druglevel_low,druglevel_high,min_druglevel,max_druglevel,solventlevel_low,solventlevel_high,suggestion from mc_druglevel_scrqry order by scrqryid";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_druglevel_scrqry");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(Integer.parseInt(cache.getKeys().get(0).toString())));

		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("scrqryid").toString();
			Element ele=cache.get(cpkey);
			int status=0;
			McDruglevelScrqry druglevelscrqry=(McDruglevelScrqry)ele.getObjectValue();
			
//			System.out.println(CipherTools.aesEncrypt(sjmap.get("scrid").toString())+":"+cplist.get(j));
			if(sjmap.get("calculate_label").equals(druglevelscrqry.getCalculate_label())){
				status=status+1;
			}				
			if(sjmap.get("druglevel_high").equals(druglevelscrqry.getDruglevel_high())){
				status=status+1;
			}				
			if(sjmap.get("druglevel_low").equals(druglevelscrqry.getDruglevel_low())){
				status=status+1;
			}				
			if(sjmap.get("max_druglevel").equals(druglevelscrqry.getMax_druglevel())){
				status=status+1;
			}				
			if(sjmap.get("min_druglevel").equals(druglevelscrqry.getMin_druglevel())){
				status=status+1;
			}				
			if(sjmap.get("scrqryid").equals(druglevelscrqry.getScrqryid())){
				status=status+1;
			}				
			if(sjmap.get("solventlevel_high").equals(druglevelscrqry.getSolventlevel_high())){
				status=status+1;
			}				
			if(sjmap.get("solventlevel_low").equals(druglevelscrqry.getSolventlevel_low())){
				status=status+1;
			}				
			if(sjmap.get("suggestion").equals(druglevelscrqry.getSuggestion())){
				status=status+1;
			}				
			
			System.out.println(cpkey);
			if(status<9){
				System.out.println("mc_contr_scr磁盘数据错误退出:"+cpkey);
				duibierr="mc_contr_scr报错的key:"+cpkey;
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_druglevel_scrqry循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_druglevel_scrqry");
		
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
			System.out.println("mc_druglevel_scrqry取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_druglevel_scrqry磁盘文件为空");
			cxerr="mc_druglevel_scrqry磁盘文件为空";
		}
		return cxerr;
	}
}
