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
import com.medicom.core.cache.hepdos.McHepdosScrdtl;
import com.medicom.security.CipherTools;

public class Mc_hepdos_scrdtl {
	private String duibierr="mc_hepdos_scrdtl数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="select drugid,st_routeid,hep_label,age_weigh_label,agelow,unequal_low,agelow_unit,agehigh,unequal_high,agehigh_unit, agedesc, weighLow,weighhigh,calculate_label,dose_each_low,dose_each_low_unit,dose_each_high,dose_each_high_unit,dose_day_low,dose_day_low_unit,dose_day_high,dose_day_high_unit,frequency_low,frequency_low_day,frequency_high, frequency_high_day, abstract from mc_hepdos_scrdtl order by drugid, st_routeid, hep_label";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_hepdos_scrdtl");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(cache.getKeys().get(0).toString()));
		
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("drugid").toString()+"-"+sjmap.get("st_routeid").toString()+"-"+sjmap.get("hep_label").toString();
			Element ele=cache.get(CipherTools.aesEncrypt(cpkey));
			List cplist=(List)ele.getObjectValue();
			int status=0;	
			for(int j=0;j<cplist.size();j++){
				McHepdosScrdtl hepdosscrdtl=(McHepdosScrdtl)cplist.get(j);
				
				if(sjmap.get("abstract").equals(hepdosscrdtl.getAbstract_())){
					status=status+1;
				}
				if(sjmap.get("age_weigh_label").equals(hepdosscrdtl.getAge_weigh_label())){
					status=status+1;
				}
				if(sjmap.get("agedesc").equals(hepdosscrdtl.getAgedesc())){
					status=status+1;
				}
				if(sjmap.get("agehigh").equals(hepdosscrdtl.getAgehigh())){
					status=status+1;
				}
				if(sjmap.get("agehigh_unit").equals(hepdosscrdtl.getAgehigh_unit())){
					status=status+1;
				}
				if(sjmap.get("agelow").equals(hepdosscrdtl.getAgelow())){
					status=status+1;
				}
				if(sjmap.get("agelow_unit").equals(hepdosscrdtl.getAgelow_unit())){
					status=status+1;
				}
				if(sjmap.get("calculate_label").equals(hepdosscrdtl.getCalculate_label())){
					status=status+1;
				}
				if(sjmap.get("dose_day_high").equals(hepdosscrdtl.getDose_day_high())){
					status=status+1;
				}
				if(sjmap.get("dose_day_high_unit").equals(hepdosscrdtl.getDose_day_high_unit())){
					status=status+1;
				}
				if(sjmap.get("dose_day_low").equals(hepdosscrdtl.getDose_day_low())){
					status=status+1;
				}
				if(sjmap.get("dose_day_low_unit").equals(hepdosscrdtl.getDose_day_low_unit())){
					status=status+1;
				}
				if(sjmap.get("dose_each_high").equals(hepdosscrdtl.getDose_each_high())){
					status=status+1;
				}
				if(sjmap.get("dose_each_high_unit").equals(hepdosscrdtl.getDose_each_high_unit())){
					status=status+1;
				}
				if(sjmap.get("dose_each_low").equals(hepdosscrdtl.getDose_each_low())){
					status=status+1;
				}
				if(sjmap.get("dose_each_low_unit").equals(hepdosscrdtl.getDose_each_low_unit())){
					status=status+1;
				}
				if(CipherTools.aesEncrypt(sjmap.get("drugid").toString()).equals(hepdosscrdtl.getDrugid())){
					status=status+1;
				}
				if(sjmap.get("frequency_high").equals(hepdosscrdtl.getFrequency_high())){
					status=status+1;
				}
				if(sjmap.get("frequency_high_day").equals(hepdosscrdtl.getFrequency_high_day())){
					status=status+1;
				}
				if(sjmap.get("frequency_low").equals(hepdosscrdtl.getFrequency_low())){
					status=status+1;
				}
				if(sjmap.get("frequency_low_day").equals(hepdosscrdtl.getFrequency_low_day())){
					status=status+1;
				}
				if(CipherTools.aesEncrypt(sjmap.get("hep_label").toString()).equals(hepdosscrdtl.getHep_label())){
					status=status+1;
				}
				if(CipherTools.aesEncrypt(sjmap.get("st_routeid").toString()).equals(hepdosscrdtl.getSt_routeid())){
					status=status+1;
				}
				if(sjmap.get("unequal_high").equals(hepdosscrdtl.getUnequal_high())){
					status=status+1;
				}
				if(sjmap.get("unequal_low").equals(hepdosscrdtl.getUnequal_low())){
					status=status+1;
				}
				if(sjmap.get("weighhigh").equals(hepdosscrdtl.getWeighhigh())){
					status=status+1;
				}
				if(sjmap.get("weighlow").equals(hepdosscrdtl.getWeighlow())){
					status=status+1;
				}

				if(status<27){
					status=0;
				}
				
//				System.out.println(sjmap.get("abstract")+":"+hepdosscrdtl.getAbstract_());
//				System.out.println(sjmap.get("age_weigh_label")+":"+hepdosscrdtl.getAge_weigh_label());
//				System.out.println(sjmap.get("agedesc")+":"+hepdosscrdtl.getAgedesc());
//				System.out.println(sjmap.get("agehigh")+":"+hepdosscrdtl.getAgehigh());
//				System.out.println(sjmap.get("agehigh_unit")+":"+hepdosscrdtl.getAgehigh_unit());
//				System.out.println(sjmap.get("agelow")+":"+hepdosscrdtl.getAgelow());
//				System.out.println(sjmap.get("agelow_unit")+":"+hepdosscrdtl.getAgelow_unit());
//				System.out.println(sjmap.get("calculate_label")+":"+hepdosscrdtl.getCalculate_label());
//				System.out.println(sjmap.get("dose_day_high")+":"+hepdosscrdtl.getDose_day_high());
//				System.out.println(sjmap.get("dose_day_high_unit")+":"+hepdosscrdtl.getDose_day_high_unit());
//				System.out.println(sjmap.get("dose_day_low")+":"+hepdosscrdtl.getDose_day_low());
//				System.out.println(sjmap.get("dose_day_low_unit")+":"+hepdosscrdtl.getDose_day_low_unit());
//				System.out.println(sjmap.get("dose_each_high")+":"+hepdosscrdtl.getDose_each_high());
//				System.out.println(sjmap.get("dose_each_high_unit")+":"+hepdosscrdtl.getDose_each_high_unit());
//				System.out.println(sjmap.get("dose_each_low")+":"+hepdosscrdtl.getDose_each_low());
//				System.out.println(sjmap.get("dose_each_low_unit")+":"+hepdosscrdtl.getDose_each_low_unit());
//				System.out.println(CipherTools.aesEncrypt(sjmap.get("drugid").toString())+":"+hepdosscrdtl.getDrugid());
//				System.out.println(sjmap.get("frequency_high")+":"+hepdosscrdtl.getFrequency_high());
//				System.out.println(sjmap.get("frequency_high_day")+":"+hepdosscrdtl.getFrequency_high_day());
//				System.out.println(sjmap.get("frequency_low")+":"+hepdosscrdtl.getFrequency_low());
//				System.out.println(sjmap.get("frequency_low_day")+":"+hepdosscrdtl.getFrequency_low_day());
//				System.out.println(CipherTools.aesEncrypt(sjmap.get("hep_label").toString())+":"+hepdosscrdtl.getHep_label());
//				System.out.println(CipherTools.aesEncrypt(sjmap.get("st_routeid").toString())+":"+hepdosscrdtl.getSt_routeid());
//				System.out.println(sjmap.get("unequal_high")+":"+hepdosscrdtl.getUnequal_high());
//				System.out.println(sjmap.get("unequal_low")+":"+hepdosscrdtl.getUnequal_low());
//				System.out.println(sjmap.get("weighhigh")+":"+hepdosscrdtl.getWeighhigh());
//				System.out.println(sjmap.get("weighlow")+":"+hepdosscrdtl.getWeighlow());
				
			}
			System.out.println(cpkey);
			if(status<27){
				System.out.println("mc_hepdos_scrdtl磁盘数据错误退出"+cpkey);
				duibierr="mc_hepdos_scrdtl报错的key:"+cpkey;
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_hepdos_scrdtl循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_hepdos_scrdtl");
		
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
			System.out.println("mc_hepdos_scrdtl取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_hepdos_scrdtl磁盘文件为空");
			cxerr="mc_hepdos_scrdtl磁盘文件为空";
		}
		return cxerr;
	}
}
