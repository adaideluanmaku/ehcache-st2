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
import com.medicom.core.cache.dosage.McDosageScr;

public class Mc_dosage_scr {
	private String duibierr="mc_dosage_scr数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		String sql="Select b.scrid, b.age_weigh_label,b.agelow,b.unequal_low,b.agelow_unit,b.agehigh,b.unequal_high,b.agehigh_unit,b.agedesc,b.weighLow,b.weighhigh,b.calculate_label,b.dose_each_low,b.dose_each_low_unit,b.dose_each_high,b.dose_each_high_unit,b.dose_day_low,b.dose_day_low_unit,b.dose_day_high,b.dose_day_high_unit,b.maxdose_each,b.maxdose_each_unit,b.maxdose_day,b.maxdose_day_unit,b.frequency_low,b.frequency_low_day,b.frequency_high,b.frequency_high_day,b.duration_low,b.duration_high,b.duration_max,b.treatment_dose,b.treatment_dose_unit,b.warndose_each,b.warndose_each_unit,b.warndose_day,b.warndose_day_unit,b.is_maxwarn from mc_dosage_scr b order by b.scrid";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_dosage_scr");
//		System.out.println(cache.getSize());
//		System.out.println(cache.getKeys().get(0).toString());
//		System.out.println(cache.get(Integer.parseInt(cache.getKeys().get(0).toString())));
		for(int i=0;i<list.size();i++){
			Map sjmap=(Map)list.get(i);
			String cpkey=sjmap.get("scrid").toString();
			Element ele=cache.get(cpkey);
			int status=0;

			McDosageScr scr=(McDosageScr)ele.getObjectValue();
//			System.out.println(sjmap.get("weighdesc")+":"+scr.getWeighdesc());

			if(sjmap.get("age_weigh_label").equals(scr.getAge_weigh_label())){
				status=status+1;
			}
			if(sjmap.get("agedesc").equals(scr.getAgedesc())){
				status=status+1;
			}
			if(sjmap.get("agehigh").equals(scr.getAgehigh())){
				status=status+1;
			}
			if(sjmap.get("agelow").equals(scr.getAgelow())){
				status=status+1;
			}
			if(sjmap.get("agehigh_unit").equals(scr.getAgehigh_unit())){
				status=status+1;
			}
			if(sjmap.get("agelow").equals(scr.getAgelow())){
				status=status+1;
			}
			if(sjmap.get("agelow").equals(scr.getAgelow())){
				status=status+1;
			}
			if(sjmap.get("agelow_unit").equals(scr.getAgelow_unit())){
				status=status+1;
			}
			if(sjmap.get("calculate_label").equals(scr.getCalculate_label())){
				status=status+1;
			}
			if(sjmap.get("dose_day_high").equals(scr.getDose_day_high())){
				status=status+1;
			}
			if(sjmap.get("dose_day_high_unit").equals(scr.getDose_day_high_unit())){
				status=status+1;
			}
			if(sjmap.get("dose_day_low").equals(scr.getDose_day_low())){
				status=status+1;
			}
			if(sjmap.get("dose_day_low_unit").equals(scr.getDose_day_low_unit())){
				status=status+1;
			}
			if(sjmap.get("dose_each_high").equals(scr.getDose_each_high())){
				status=status+1;
			}
			if(sjmap.get("dose_each_high_unit").equals(scr.getDose_each_high_unit())){
				status=status+1;
			}
			if(sjmap.get("dose_each_low").equals(scr.getDose_each_low())){
				status=status+1;
			}
			if(sjmap.get("dose_each_low_unit").equals(scr.getDose_each_low_unit())){
				status=status+1;
			}
			if(sjmap.get("duration_high").equals(scr.getDuration_high())){
				status=status+1;
			}
			if(sjmap.get("duration_low").equals(scr.getDuration_low())){
				status=status+1;
			}
			if(sjmap.get("duration_max").equals(scr.getDuration_max())){
				status=status+1;
			}
			if(sjmap.get("frequency_high").equals(scr.getFrequency_high())){
				status=status+1;
			}
			if(sjmap.get("frequency_high_day").equals(scr.getFrequency_high_day())){
				status=status+1;
			}
			if(sjmap.get("frequency_low").equals(scr.getFrequency_low())){
				status=status+1;
			}
			if(sjmap.get("frequency_low_day").equals(scr.getFrequency_low_day())){
				status=status+1;
			}
			if(sjmap.get("is_maxwarn").equals(scr.getIs_maxwarn())){
				status=status+1;
			}
			if(sjmap.get("maxdose_day").equals(scr.getMaxdose_day())){
				status=status+1;
			}
			if(sjmap.get("maxdose_day_unit").equals(scr.getMaxdose_day_unit())){
				status=status+1;
			}
			if(sjmap.get("maxdose_each").equals(scr.getMaxdose_each())){
				status=status+1;
			}
			if(sjmap.get("maxdose_each_unit").equals(scr.getMaxdose_each_unit())){
				status=status+1;
			}
			if(sjmap.get("scrid").equals(scr.getScrid())){
				status=status+1;
			}
			if(sjmap.get("treatment_dose").equals(scr.getTreatment_dose())){
				status=status+1;
			}
			if(sjmap.get("treatment_dose_unit").equals(scr.getTreatment_dose_unit())){
				status=status+1;
			}
			if(sjmap.get("unequal_high").equals(scr.getUnequal_high())){
				status=status+1;
			}
			if(sjmap.get("unequal_low").equals(scr.getUnequal_low())){
				status=status+1;
			}
			if(sjmap.get("warndose_day").equals(scr.getWarndose_day())){
				status=status+1;
			}
			if(sjmap.get("warndose_day_unit").equals(scr.getWarndose_day_unit())){
				status=status+1;
			}
			if(sjmap.get("warndose_each").equals(scr.getWarndose_each())){
				status=status+1;
			}
			if(sjmap.get("warndose_each_unit").equals(scr.getWarndose_each_unit())){
				status=status+1;
			}
			if(sjmap.get("weighdesc")==null && scr.getWeighdesc()==null){
				status=status+1;
			}else if(sjmap.get("weighdesc")!=null && scr.getWeighdesc()!=null){
				if(sjmap.get("weighdesc").equals(scr.getWeighdesc())){
					status=status+1;
				}
			}
			if(sjmap.get("weighhigh").equals(scr.getWeighhigh())){
				status=status+1;
			}
			if(sjmap.get("weighlow").equals(scr.getWeighlow())){
				status=status+1;
			}			

			System.out.println(cpkey);
			if(status<39){
				System.out.println("mc_dosage_scr磁盘数据错误退出:"+cpkey);
				duibierr="mc_dosage_scr报错的key:"+cpkey;
				break;
			}
			System.out.println("对比成功:"+list.size()+":"+(i+1));
		}
		System.out.println("mc_dosage_scr循环总数"+list.size());
		list.remove(list);
		st.close();
		rs.close();
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_dosage_scr");
		
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
			System.out.println("mc_dosage_scr取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_dosage_scr磁盘文件为空");
			cxerr="mc_dosage_scr磁盘文件为空";
		}
		return cxerr;
	}
}
