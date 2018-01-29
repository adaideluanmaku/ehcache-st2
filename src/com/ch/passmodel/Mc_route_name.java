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
import com.medicom.core.cache.sys.McRouteName;

public class Mc_route_name {
	private String duibierr="mc_route_name数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String disicdsql="select routeid,st_routeid,route_name,search_code,iv_rttype,sp_rttype,inter_rttype,duping_rttype,dupthe_rttype,contr_rttype,adr_rttype,oper_rttype,routelabel from mc_route_name";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(disicdsql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_route_name");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(Integer.parseInt(cache.getKeys().get(0).toString())));
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("routeid").toString();
			Element ele=cache.get(cpkey);
			
			int status=0;
			
			McRouteName routename=(McRouteName)ele.getObjectValue();
//			System.out.println(sjmap.get("abstract")+":"+routedrugvalid.getAbstract_());
			if(sjmap.get("adr_rttype").equals(routename.getAdr_rttype())){
				status=status+1;
			}
			if(sjmap.get("contr_rttype").equals(routename.getContr_rttype())){
				status=status+1;
			}
			if(sjmap.get("duping_rttype").equals(routename.getDuping_rttype())){
				status=status+1;
			}
			if(sjmap.get("dupthe_rttype").equals(routename.getDupthe_rttype())){
				status=status+1;
			}
			if(sjmap.get("inter_rttype").equals(routename.getInter_rttype())){
				status=status+1;
			}
			if(sjmap.get("iv_rttype").equals(routename.getIv_rttype())){
				status=status+1;
			}
			if(sjmap.get("oper_rttype").equals(routename.getOper_rttype())){
				status=status+1;
			}
			if(sjmap.get("route_name").equals(routename.getRoute_name())){
				status=status+1;
			}
			if(sjmap.get("routeid").equals(routename.getRouteid())){
				status=status+1;
			}
			if(sjmap.get("routelabel").equals(routename.getRoutelabel())){
				status=status+1;
			}
			if(sjmap.get("search_code").equals(routename.getSearch_code())){
				status=status+1;
			}
			if(sjmap.get("sp_rttype").equals(routename.getSp_rttype())){
				status=status+1;
			}
			if(sjmap.get("st_routeid").equals(routename.getSt_routeid())){
				status=status+1;
			}

			System.out.println(cpkey);
			if(status<13){
				System.out.println("mc_route_name磁盘数据错误退出"+cpkey);
				duibierr="mc_route_name报错的key:"+cpkey;
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_route_name循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_route_name");
		
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
			System.out.println("mc_route_name取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_route_name磁盘文件为空");
			cxerr="mc_route_name磁盘文件为空";
		}
		return cxerr;
	}
}
