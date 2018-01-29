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
import com.medicom.core.cache.sys.McDrugs;

public class Mc_drugs {
	private String duibierr="mc_drugs数据对比成功";
	private String cxerr=null;
	
	public String getDuibierr() throws ClassNotFoundException, SQLException {
		Mysqlconnection jdbc=new Mysqlconnection();
		Connection conn=jdbc.getConn();
		int top1=0;
		int top2=0;
		for(int z=0;z<5;z++){
			if(z==0){
				top1=0;
				top2=50000;
			}
			if(z==1){
				top1=50000;
				top2=50000;
			}
			if(z==2){
				top1=100000;
				top2=50000;
			}
			if(z==3){
				top1=150000;
				top2=50000;
			}
			if(z==4){
				top1=200000;
				top2=51117;
			}
			String sql="SELECT b.drugcode,b.drugname, b.st_cbrand,b.genid,b.st_formid,b.form_name,b.st_strength,b.st_compid,b.st_comp_name,b.approvalcode,b.doseunit,b.mdid,b.is_solvent,b.split_sfdanum,b.matchdesc,b.menulabel, b.tpn, b.pi_link, b.pi_count,b.pe_count,b.chp_count,b.is_inject, b.modulelabel FROM mc_drugs b order by b.drugcode asc limit "+top1+","+top2;
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			List list=jdbc.getlist(rs);
			
			Ehcache ehcache=new Ehcache();
			CacheManager manager=ehcache.getManager();
			Cache cache=manager.getCache("mc_drugs");
//			System.out.println(cache.getSize());
//			System.out.println(cache.getKeys().get(0).toString());
//			System.out.println(cache.get(cache.getKeys().get(0).toString()));
			
			for(int i=0;i<list.size();i++){
				Map sjmap=(Map)list.get(i);
				String cpkey=sjmap.get("drugcode").toString();
				Element ele=cache.get(cpkey);
				int status=0;
				McDrugs drugs=(McDrugs)ele.getObjectValue();
				
//				System.out.println(CipherTools.aesEncrypt(sjmap.get("scrid").toString())+":"+cplist.get(j));
				if(sjmap.get("approvalcode").equals(drugs.getApprovalcode())){
					status=status+1;
				}	
				if(sjmap.get("chp_count")==null && drugs.getChp_count()==null){
					status=status+1;
				}else if(sjmap.get("chp_count")!=null && drugs.getChp_count()!=null){
					if(sjmap.get("chp_count").equals(drugs.getChp_count())){
						status=status+1;
					}	
				}
				if(sjmap.get("doseunit")==null && drugs.getDoseunit()==null){
					status=status+1;
				}else if(sjmap.get("doseunit")!=null && drugs.getDoseunit()!=null){
					if(sjmap.get("doseunit").equals(drugs.getDoseunit())){
						status=status+1;
					}	
				}				
				if(sjmap.get("drugcode").equals(drugs.getDrugcode())){
					status=status+1;
				}				
				if(sjmap.get("drugname").equals(drugs.getDrugname())){
					status=status+1;
				}				
				if(sjmap.get("form_name").equals(drugs.getForm_name())){
					status=status+1;
				}				
				if(sjmap.get("genid").equals(drugs.getGenid())){
					status=status+1;
				}				
				if(sjmap.get("is_inject")==null && drugs.getIs_inject()==null){
					status=status+1;
				}else if(sjmap.get("is_inject")!=null && drugs.getIs_inject()!=null){
					if(sjmap.get("is_inject").equals(drugs.getIs_inject())){
						status=status+1;
					}	
				}	
				if(sjmap.get("is_solvent")==null && drugs.getIs_solvent()==null){
					status=status+1;
				}else if(sjmap.get("is_solvent")!=null && drugs.getIs_solvent()!=null){
					if(sjmap.get("is_solvent").equals(drugs.getIs_solvent())){
						status=status+1;
					}	
				}
				if(sjmap.get("matchdesc")==null && drugs.getMatchdesc()==null){
					status=status+1;
				}else if(sjmap.get("matchdesc")!=null && drugs.getMatchdesc()!=null){
					if(sjmap.get("matchdesc").equals(drugs.getMatchdesc())){
						status=status+1;
					}	
				}	
				if(sjmap.get("mdid")==null && drugs.getMdid()==null){
					status=status+1;
				}else if(sjmap.get("mdid")!=null && drugs.getMdid()!=null){
					if(sjmap.get("mdid").equals(drugs.getMdid())){
						status=status+1;
					}	
				}			
				if(sjmap.get("menulabel").equals(drugs.getMenulabel())){
					status=status+1;
				}				
				if(sjmap.get("modulelabel").equals(drugs.getModulelabel())){
					status=status+1;
				}	
				if(sjmap.get("pe_count")==null && drugs.getPe_count()==null){
					status=status+1;
				}else if(sjmap.get("pe_count")!=null && drugs.getPe_count()!=null){
					if(sjmap.get("pe_count").equals(drugs.getPe_count())){
						status=status+1;
					}	
				}	
				if(sjmap.get("pi_count")==null && drugs.getPi_count()==null){
					status=status+1;
				}else if(sjmap.get("pi_count")!=null && drugs.getPi_count()!=null){
					if(sjmap.get("pi_count").equals(drugs.getPi_count())){
						status=status+1;
					}	
				}
				if(sjmap.get("pi_link")==null && drugs.getPi_link()==null){
					status=status+1;
				}else if(sjmap.get("pi_link")!=null && drugs.getPi_link()!=null){
					if(sjmap.get("pi_link").equals(drugs.getPi_link())){
						status=status+1;
					}	
				}			
				if(sjmap.get("split_sfdanum").equals(drugs.getSplit_sfdanum())){
					status=status+1;
				}				
				if(sjmap.get("st_cbrand").equals(drugs.getSt_cbrand())){
					status=status+1;
				}				
				if(sjmap.get("st_comp_name").equals(drugs.getSt_comp_name())){
					status=status+1;
				}	
				if(sjmap.get("st_compid")==null && drugs.getSt_compid()==null){
					status=status+1;
				}else if(sjmap.get("st_compid")!=null && drugs.getSt_compid()!=null){
					if(sjmap.get("st_compid").equals(drugs.getSt_compid())){
						status=status+1;
					}	
				}					
				if(sjmap.get("st_formid").equals(drugs.getSt_formid())){
					status=status+1;
				}				
				if(sjmap.get("st_strength").equals(drugs.getSt_strength())){
					status=status+1;
				}
				if(sjmap.get("tpn")==null && drugs.getTpn()==null){
					status=status+1;
				}else if(sjmap.get("tpn")!=null && drugs.getTpn()!=null){
					if(sjmap.get("tpn").equals(drugs.getTpn())){
						status=status+1;
					}	
				}		
				
				System.out.println(cpkey);
				if(status<1){
					System.out.println("mc_drugs磁盘数据错误退出:"+cpkey);
					duibierr="mc_drugs报错的key:"+cpkey;
					break;
				}
				System.out.println("对比成功:"+list.size()+":"+(i+1));
			}
			System.out.println("mc_drugs循环总数"+list.size());
			list.remove(list);
			st.close();
			rs.close();
		}
		conn.close();
		return duibierr;
	}

	public String getCxerr() {
		Ehcache ehcache=new Ehcache();
		CacheManager manager=ehcache.getManager();
		Cache cache=manager.getCache("mc_drugs");
		
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
			System.out.println("mc_drugs取磁盘数据："+ele.getObjectKey()+":"+ele.getObjectValue());
			cxerr=ele.getObjectKey().toString()+":"+ele.getObjectValue().toString();
		}else{
			System.out.println("mc_drugs磁盘文件为空");
			cxerr="mc_contr_disicd磁盘文件为空";
		}
		return cxerr;
	}
}
