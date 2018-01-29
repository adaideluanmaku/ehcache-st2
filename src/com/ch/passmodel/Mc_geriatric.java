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
import com.medicom.core.cache.geriatric.McGeriatric;

public class Mc_geriatric {
	private String duibierr="mc_geriatric数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="select pkid, drugid,drugname,routetype,routedesc,st_routeids,agelow,unequal_low,agelow_unit,agehigh,unequal_high,agehigh_unit,agedesc,renal,hepatic,cardio,pulm,neur,endo,icd_codes,slcode,severity,warning,abstract from mc_geriatric order by drugid,slcode";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_geriatric");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));
		
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("drugid").toString();
			Element ele=cache.get(cpkey);
			List cplist=(List)ele.getObjectValue();
			int status=0;	
			for(int j=0;j<cplist.size();j++){
				McGeriatric geriatric=(McGeriatric)cplist.get(j);
				
				if(sjmap.get("abstract").equals(geriatric.getAbstract_())){
					status=status+1;
				}
				if(sjmap.get("agedesc").equals(geriatric.getAgedesc())){
					status=status+1;
				}
				if(sjmap.get("agehigh").equals(geriatric.getAgehigh())){
					status=status+1;
				}
				if(sjmap.get("agehigh_unit").equals(geriatric.getAgehigh_unit())){
					status=status+1;
				}
				if(sjmap.get("agelow").equals(geriatric.getAgelow())){
					status=status+1;
				}
				if(sjmap.get("agelow_unit").equals(geriatric.getAgelow_unit())){
					status=status+1;
				}
				if(sjmap.get("cardio").equals(geriatric.getCardio())){
					status=status+1;
				}
				if(sjmap.get("drugid").equals(geriatric.getDrugid())){
					status=status+1;
				}
				if(sjmap.get("drugname").equals(geriatric.getDrugname())){
					status=status+1;
				}
				if(sjmap.get("endo").equals(geriatric.getEndo())){
					status=status+1;
				}
				if(sjmap.get("hepatic").equals(geriatric.getHepatic())){
					status=status+1;
				}
				if(sjmap.get("icd_codes").equals(geriatric.getIcd_codes())){
					status=status+1;
				}
				if(sjmap.get("neur").equals(geriatric.getNeur())){
					status=status+1;
				}
				if(sjmap.get("pkid").equals(geriatric.getPkid())){
					status=status+1;
				}
				if(sjmap.get("pulm").equals(geriatric.getPulm())){
					status=status+1;
				}
				if(sjmap.get("renal").equals(geriatric.getRenal())){
					status=status+1;
				}
				//routedesc字段存在为null的数据
				if(sjmap.get("routedesc")==null && geriatric.getRoutedesc()==null){
					status=status+1;
				}
				if(sjmap.get("routedesc")!=null && geriatric.getRoutedesc()!=null){
					if(sjmap.get("routedesc").equals(geriatric.getRoutedesc())){
						status=status+1;
					}
				}
				if(sjmap.get("routetype").equals(geriatric.getRoutetype())){
					status=status+1;
				}
				if(sjmap.get("severity").equals(geriatric.getSeverity())){
					status=status+1;
				}
				if(sjmap.get("slcode").equals(geriatric.getSlcode())){
					status=status+1;
				}
				if(sjmap.get("st_routeids").equals(geriatric.getSt_routeids())){
					status=status+1;
				}
				if(sjmap.get("unequal_high").equals(geriatric.getUnequal_high())){
					status=status+1;
				}
				if(sjmap.get("unequal_low").equals(geriatric.getUnequal_low())){
					status=status+1;
				}
				if(sjmap.get("warning").equals(geriatric.getWarning())){
					status=status+1;
				}
				if(status<24){
					status=0;
				}
//				System.out.println(sjmap.get("abstract")+":"+geriatric.getAbstract_());
//				System.out.println(sjmap.get("agedesc")+":"+geriatric.getAgedesc());
//				System.out.println(sjmap.get("agehigh")+":"+geriatric.getAgehigh());
//				System.out.println(sjmap.get("agehigh_unit")+":"+geriatric.getAgehigh_unit());
//				System.out.println(sjmap.get("agelow")+":"+geriatric.getAgelow());
//				System.out.println(sjmap.get("agelow_unit")+":"+geriatric.getAgelow_unit());
//				System.out.println(sjmap.get("cardio")+":"+geriatric.getCardio());
//				System.out.println(sjmap.get("drugid")+":"+geriatric.getDrugid());
//				System.out.println(sjmap.get("drugname")+":"+geriatric.getDrugname());
//				System.out.println(sjmap.get("endo")+":"+geriatric.getEndo());
//				System.out.println(sjmap.get("hepatic")+":"+geriatric.getHepatic());
//				System.out.println(sjmap.get("icd_codes")+":"+geriatric.getIcd_codes());
//				System.out.println(sjmap.get("neur")+":"+geriatric.getNeur());
//				System.out.println(sjmap.get("pkid")+":"+geriatric.getPkid());
//				System.out.println(sjmap.get("pulm")+":"+geriatric.getPulm());
//				System.out.println(sjmap.get("renal")+":"+geriatric.getRenal());
//				System.out.println(sjmap.get("routedesc")+":"+geriatric.getRoutedesc());
//				System.out.println(sjmap.get("routetype")+":"+geriatric.getRoutetype());
//				System.out.println(sjmap.get("severity")+":"+geriatric.getSeverity());
//				System.out.println(sjmap.get("slcode")+":"+geriatric.getSlcode());
//				System.out.println(sjmap.get("st_routeids")+":"+geriatric.getSt_routeids());
//				System.out.println(sjmap.get("unequal_high")+":"+geriatric.getUnequal_high());
//				System.out.println(sjmap.get("unequal_low")+":"+geriatric.getUnequal_low());
//				System.out.println(sjmap.get("warning")+":"+geriatric.getWarning());
				
			}
			System.out.println(cpkey);
			if(status<24){
				System.out.println("mc_geriatric磁盘数据错误退出"+cpkey);
				duibierr="mc_geriatric报错的key:"+cpkey;
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_geriatric循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_geriatric");
		
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
			System.out.println("mc_geriatric取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_geriatric磁盘文件为空");
			cxerr="mc_geriatric磁盘文件为空";
		}
		return cxerr;
	}
}
