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
import com.medicom.core.cache.rendos.McRendosScrdtl;
import com.medicom.security.CipherTools;

public class Mc_rendos_scrdtl {
	private String duibierr="mc_rendos_scrdtl数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String disicdsql="select drugid,st_routeid,ren_label, age_weigh_label,agelow,unequal_low, agelow_unit,agehigh,unequal_high,agehigh_unit, agedesc, weighLow,weighhigh, calculate_label,dose_each_low,dose_each_low_unit,dose_each_high,dose_each_high_unit,dose_day_low,dose_day_low_unit,dose_day_high,dose_day_high_unit,frequency_low,frequency_low_day,frequency_high, frequency_high_day, abstract from mc_rendos_scrdtl order by drugid, st_routeid, ren_label";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(disicdsql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_rendos_scrdtl");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("drugid").toString()+"-"+sjmap.get("st_routeid").toString()+"-"+sjmap.get("ren_label").toString();
			Element ele=cache.get(CipherTools.aesEncrypt(cpkey));
			List cplist=(List)ele.getObjectValue();
			int status=0;
			for(int j=0;j<cplist.size();j++){
				McRendosScrdtl rendosscrdtl=(McRendosScrdtl)cplist.get(j);
				
//				System.out.println(sjmap.get("abstract")+":"+rendosscrdtl.getAbstract_());
//				System.out.println(sjmap.get("age_weigh_label")+":"+rendosscrdtl.getAge_weigh_label());
//				System.out.println(sjmap.get("agedesc")+":"+rendosscrdtl.getAgedesc());
//				System.out.println(sjmap.get("agehigh")+":"+rendosscrdtl.getAgehigh());
//				System.out.println(sjmap.get("agehigh_unit")+":"+rendosscrdtl.getAgehigh_unit());
//				System.out.println(sjmap.get("agelow")+":"+rendosscrdtl.getAgelow());
//				System.out.println(sjmap.get("agelow_unit")+":"+rendosscrdtl.getAgelow_unit());
//				System.out.println(sjmap.get("calculate_label")+":"+rendosscrdtl.getCalculate_label());
//				System.out.println(sjmap.get("dose_day_high")+":"+rendosscrdtl.getDose_day_high());
//				System.out.println(sjmap.get("dose_day_high_unit")+":"+rendosscrdtl.getDose_day_high_unit());
//				System.out.println(sjmap.get("dose_day_low")+":"+rendosscrdtl.getDose_day_low());
//				System.out.println(sjmap.get("dose_day_low_unit")+":"+rendosscrdtl.getDose_day_low_unit());
//				System.out.println(sjmap.get("dose_each_high")+":"+rendosscrdtl.getDose_each_high());
//				System.out.println(sjmap.get("dose_each_high_unit")+":"+rendosscrdtl.getDose_each_high_unit());
//				System.out.println(sjmap.get("dose_each_low")+":"+rendosscrdtl.getDose_each_low());
//				System.out.println(sjmap.get("dose_each_low_unit")+":"+rendosscrdtl.getDose_each_low_unit());
//				System.out.println(sjmap.get("drugid")+":"+rendosscrdtl.getDrugid());
//				System.out.println(sjmap.get("frequency_high")+":"+rendosscrdtl.getFrequency_high());
//				System.out.println(sjmap.get("frequency_high_day")+":"+rendosscrdtl.getFrequency_high_day());
//				System.out.println(sjmap.get("frequency_low")+":"+rendosscrdtl.getFrequency_low());
//				System.out.println(sjmap.get("frequency_low_day")+":"+rendosscrdtl.getFrequency_low_day());
//				System.out.println(sjmap.get("ren_label")+":"+rendosscrdtl.getRen_label());
//				System.out.println(sjmap.get("st_routeid")+":"+rendosscrdtl.getSt_routeid());
//				System.out.println(sjmap.get("unequal_high")+":"+rendosscrdtl.getUnequal_high());
//				System.out.println(sjmap.get("unequal_low")+":"+rendosscrdtl.getUnequal_low());
//				System.out.println(sjmap.get("weighhigh")+":"+rendosscrdtl.getWeighhigh());
//				System.out.println(sjmap.get("weighlow")+":"+rendosscrdtl.getWeighlow());
				
				if(sjmap.get("abstract").equals(rendosscrdtl.getAbstract_())){
					status=status+1;
				}
				if(sjmap.get("age_weigh_label").equals(rendosscrdtl.getAge_weigh_label())){
					status=status+1;
				}
				if(sjmap.get("agedesc").equals(rendosscrdtl.getAgedesc())){
					status=status+1;
				}
				if(sjmap.get("agehigh").equals(rendosscrdtl.getAgehigh())){
					status=status+1;
				}
				if(sjmap.get("agehigh_unit").equals(rendosscrdtl.getAgehigh_unit())){
					status=status+1;
				}
				if(sjmap.get("agelow").equals(rendosscrdtl.getAgelow())){
					status=status+1;
				}
				if(sjmap.get("agelow_unit").equals(rendosscrdtl.getAgelow_unit())){
					status=status+1;
				}
				if(sjmap.get("calculate_label").equals(rendosscrdtl.getCalculate_label())){
					status=status+1;
				}
				if(sjmap.get("dose_day_high").equals(rendosscrdtl.getDose_day_high())){
					status=status+1;
				}
				if(sjmap.get("dose_day_high_unit").equals(rendosscrdtl.getDose_day_high_unit())){
					status=status+1;
				}
				if(sjmap.get("dose_day_low").equals(rendosscrdtl.getDose_day_low())){
					status=status+1;
				}
				if(sjmap.get("dose_day_low_unit").equals(rendosscrdtl.getDose_day_low_unit())){
					status=status+1;
				}
				if(sjmap.get("dose_each_high").equals(rendosscrdtl.getDose_each_high())){
					status=status+1;
				}
				if(sjmap.get("dose_each_high_unit").equals(rendosscrdtl.getDose_each_high_unit())){
					status=status+1;
				}
				if(sjmap.get("dose_each_low").equals(rendosscrdtl.getDose_each_low())){
					status=status+1;
				}
				if(sjmap.get("dose_each_low_unit").equals(rendosscrdtl.getDose_each_low_unit())){
					status=status+1;
				}
				if(CipherTools.aesEncrypt(sjmap.get("drugid").toString()).equals(rendosscrdtl.getDrugid())){
					status=status+1;
				}
				if(sjmap.get("frequency_high").equals(rendosscrdtl.getFrequency_high())){
					status=status+1;
				}
				if(sjmap.get("frequency_high_day").equals(rendosscrdtl.getFrequency_high_day())){
					status=status+1;
				}
				if(sjmap.get("frequency_low").equals(rendosscrdtl.getFrequency_low())){
					status=status+1;
				}
				if(sjmap.get("frequency_low_day").equals(rendosscrdtl.getFrequency_low_day())){
					status=status+1;
				}
				if(CipherTools.aesEncrypt(sjmap.get("ren_label").toString()).equals(rendosscrdtl.getRen_label())){
					status=status+1;
				}
				if(CipherTools.aesEncrypt(sjmap.get("st_routeid").toString()).equals(rendosscrdtl.getSt_routeid())){
					status=status+1;
				}
				if(sjmap.get("unequal_high").equals(rendosscrdtl.getUnequal_high())){
					status=status+1;
				}
				if(sjmap.get("unequal_low").equals(rendosscrdtl.getUnequal_low())){
					status=status+1;
				}
				if(sjmap.get("weighhigh").equals(rendosscrdtl.getWeighhigh())){
					status=status+1;
				}
				if(sjmap.get("weighlow").equals(rendosscrdtl.getWeighlow())){
					status=status+1;
				}
				if(status<27){
					status=0;
				}
			}
			System.out.println(cpkey);
			if(status<27){
				System.out.println("mc_rendos_scrdtl磁盘数据错误退出"+cpkey);
				duibierr="mc_rendos_scrdtl报错的key:"+cpkey;
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_rendos_scrdtl循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_rendos_scrdtl");
		
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
			System.out.println("mc_rendos_scrdtl取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_rendos_scrdtl磁盘文件为空");
			cxerr="mc_rendos_scrdtl磁盘文件为空";
		}
		return cxerr;
	}
}
