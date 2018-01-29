package com.ch.passthread;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.CacheManager;

import com.ch.unit.Ehcache;

public class Threadrun {
	public static void main(String args[]) throws InterruptedException {
    	String err=null;
		List list=new ArrayList();
		
		Ehcache ehcache=new Ehcache();
		ehcache.setEh(0);
		CacheManager manager=ehcache.getManager();
		
		//一个线程
		Passthread mc_drugs = new Passthread("mc_drugs");
		//等待线程结束
		mc_drugs.t.join();
		//清空cache
		manager.removeCache("mc_drugs");
		//返回值
		list.add(mc_drugs.getErr());
		//一个线程
		
		Passthread mc_route_name = new Passthread("mc_route_name");
		Passthread mc_lactation = new Passthread("mc_lactation");
		Passthread mc_lactation_cls = new Passthread("mc_lactation_cls");
		Passthread mc_adr = new Passthread("mc_adr");
		//等待线程结束
		mc_route_name.t.join(); 
		mc_lactation.t.join();
		mc_lactation_cls.t.join();
		mc_adr.t.join(); 
		//清空cache
		manager.removeCache("mc_route_name"); 
		manager.removeCache("mc_lactation");
		manager.removeCache("mc_lactation_cls");
		manager.removeCache("mc_adr"); 
		//返回值
		list.add(mc_route_name.getErr()); 
		list.add(mc_lactation.getErr());
		list.add(mc_lactation_cls.getErr());
		list.add(mc_adr.getErr()); 
				
		Passthread mc_adr_disicd = new Passthread("mc_adr_disicd");
		mc_adr_disicd.t.join(); 
		manager.removeCache("mc_adr_disicd"); 
		list.add(mc_adr_disicd.getErr()); 
		
		Passthread mc_indic_disicd_valid = new Passthread("mc_indic_disicd_valid");
		Passthread mc_indic_qrydtl = new Passthread("mc_indic_qrydtl");		
		Passthread mc_indic_scr = new Passthread("mc_indic_scr");
		mc_indic_disicd_valid.t.join(); 
		mc_indic_qrydtl.t.join(); 
		mc_indic_scr.t.join(); 
		manager.removeCache("mc_indic_disicd_valid"); 
		manager.removeCache("mc_indic_qrydtl"); 
		manager.removeCache("mc_indic_scr"); 
		list.add(mc_indic_disicd_valid.getErr()); 
		list.add(mc_indic_qrydtl.getErr()); 
		list.add(mc_indic_scr.getErr()); 
				
				
		Passthread mc_adult = new Passthread("mc_adult");
		Passthread mc_pediatric = new Passthread("mc_pediatric");
		Passthread mc_hepdos_scrdtl = new Passthread("mc_hepdos_scrdtl");
		Passthread mc_route_drug = new Passthread("mc_route_drug");
		Passthread mc_route_drugvalid = new Passthread("mc_route_drugvalid");
		Passthread mc_route_form = new Passthread("mc_route_form");
		mc_adult.t.join(); 
		mc_pediatric.t.join(); 
		mc_hepdos_scrdtl.t.join(); 
		mc_route_drug.t.join(); 
		mc_route_drugvalid.t.join(); 
		mc_route_form.t.join(); 
		manager.removeCache("mc_adult"); 
		manager.removeCache("mc_pediatric"); 
		manager.removeCache("mc_hepdos_scrdtl"); 
		manager.removeCache("mc_route_drug"); 
		manager.removeCache("mc_route_drugvalid"); 
		manager.removeCache("mc_route_form"); 
		list.add(mc_adult.getErr()); 
		list.add(mc_pediatric.getErr()); 
		list.add(mc_hepdos_scrdtl.getErr()); 
		list.add(mc_route_drug.getErr()); 
		list.add(mc_route_drugvalid.getErr()); 
		list.add(mc_route_form.getErr());
		
		Passthread mc_modulename = new Passthread("mc_modulename");
		Passthread mc_dosage_detail = new Passthread("mc_dosage_detail");
		Passthread mc_dosage_link = new Passthread("mc_dosage_link");
		//等待线程结束
		mc_modulename.t.join(); 
		mc_dosage_detail.t.join();
		mc_dosage_link.t.join(); 
		//清空cache
		manager.removeCache("mc_modulename"); 
		manager.removeCache("mc_dosage_detail"); 
		manager.removeCache("mc_dosage_link"); 
		//返回值
		list.add(mc_modulename.getErr()); 
		list.add(mc_dosage_detail.getErr()); 
		list.add(mc_dosage_link.getErr()); 
				
		Passthread mc_dosage_scr = new Passthread("mc_dosage_scr");
		Passthread mc_brief_content = new Passthread("mc_brief_content");
		Passthread mc_geriatric = new Passthread("mc_geriatric");
		mc_dosage_scr.t.join(); 
		mc_brief_content.t.join(); 
		mc_geriatric.t.join(); 
		manager.removeCache("mc_dosage_scr"); 
		manager.removeCache("mc_brief_content"); 
		manager.removeCache("mc_geriatric"); 
		list.add(mc_dosage_scr.getErr()); 
		list.add(mc_brief_content.getErr()); 
		list.add(mc_geriatric.getErr()); 
				
				
		Passthread mc_druglevel_link = new Passthread("mc_druglevel_link");
		Passthread mc_druglevel_scrqry = new Passthread("mc_druglevel_scrqry");
		Passthread mc_pregnancy_cls = new Passthread("mc_pregnancy_cls");
		Passthread mc_pregnancy1 = new Passthread("mc_pregnancy1");
		Passthread mc_pregnancy2 = new Passthread("mc_pregnancy2");
		Passthread mc_rendos_scrdtl = new Passthread("mc_rendos_scrdtl");
		Passthread mc_iv_link = new Passthread("mc_iv_link");
		Passthread mc_iv_scrqry = new Passthread("mc_iv_scrqry");
		mc_druglevel_link.t.join(); 
		mc_druglevel_scrqry.t.join(); 
		mc_pregnancy_cls.t.join(); 
		mc_pregnancy1.t.join(); 
		mc_pregnancy2.t.join(); 
		mc_rendos_scrdtl.t.join(); 
		mc_iv_link.t.join(); 
		mc_iv_scrqry.t.join();
		manager.removeCache("mc_druglevel_link"); 
		manager.removeCache("mc_druglevel_scrqry"); 
		manager.removeCache("mc_pregnancy_cls"); 
		manager.removeCache("mc_pregnancy1"); 
		manager.removeCache("mc_pregnancy2"); 
		manager.removeCache("mc_rendos_scrdtl"); 
		manager.removeCache("mc_iv_link"); 
		manager.removeCache("mc_iv_scrqry"); 
		list.add(mc_druglevel_link.getErr()); 
		list.add(mc_druglevel_scrqry.getErr()); 
		list.add(mc_pregnancy_cls.getErr()); 
		list.add(mc_pregnancy1.getErr()); 
		list.add(mc_pregnancy2.getErr()); 
		list.add(mc_rendos_scrdtl.getErr()); 
		list.add(mc_iv_link.getErr()); 
		list.add(mc_iv_scrqry.getErr()); 
		
		
		Passthread mc_iv_single = new Passthread("mc_iv_single");
		Passthread mc_inter_genlink = new Passthread("mc_inter_genlink");
		mc_iv_single.t.join(); 
		mc_inter_genlink.t.join();
		manager.removeCache("mc_iv_single"); 
		manager.removeCache("mc_inter_genlink"); 
		list.add(mc_iv_single.getErr()); 
		list.add(mc_inter_genlink.getErr()); 
		
		Passthread mc_inter_genlink2 = new Passthread("mc_inter_genlink2");
		mc_inter_genlink2.t.join();
		manager.removeCache("mc_inter_genlink2");
		list.add(mc_inter_genlink2.getErr());
		
		Passthread mc_inter_scrqry = new Passthread("mc_inter_scrqry");
		Passthread mc_sex = new Passthread("mc_sex");
		mc_inter_scrqry.t.join(); 
		mc_sex.t.join(); 
		manager.removeCache("mc_inter_scrqry"); 
		manager.removeCache("mc_sex"); 
		list.add(mc_inter_scrqry.getErr()); 
		list.add(mc_sex.getErr()); 
		
		Passthread mc_aller_scr = new Passthread("mc_aller_scr");
		mc_aller_scr.t.join(); 
		manager.removeCache("mc_aller_scr"); 
		list.add(mc_aller_scr.getErr()); 
		
		Passthread mc_allergroup_bingid = new Passthread("mc_allergroup_bingid");
		Passthread mc_drug_ing0 = new Passthread("mc_drug_ing0");
		Passthread mc_drug_ing1 = new Passthread("mc_drug_ing1");
		mc_allergroup_bingid.t.join(); 
		mc_drug_ing0.t.join(); 
		mc_drug_ing1.t.join(); 	
		manager.removeCache("mc_allergroup_bingid"); 
		manager.removeCache("mc_drug_ing0"); 
		manager.removeCache("mc_drug_ing1"); 
		list.add(mc_allergroup_bingid.getErr()); 
		list.add(mc_drug_ing0.getErr()); 
		list.add(mc_drug_ing1.getErr()); 
				
		Passthread mc_contr_disicd = new Passthread("mc_contr_disicd");
		mc_contr_disicd.t.join(); 
		manager.removeCache("mc_contr_disicd"); 
		list.add(mc_contr_disicd.getErr()); 
		
		Passthread mc_contr_scr = new Passthread("mc_contr_scr");
		mc_contr_scr.t.join(); 
		manager.removeCache("mc_contr_scr");
		list.add(mc_contr_scr.getErr());
		
		Passthread mc_drug_ing2 = new Passthread("mc_drug_ing2");
		mc_drug_ing2.t.join(); 
		manager.removeCache("mc_drug_ing2"); 
		list.add(mc_drug_ing2.getErr()); 
		
		Passthread mc_drug_ing3 = new Passthread("mc_drug_ing3");
		Passthread mc_duptherapy = new Passthread("mc_duptherapy");
		mc_drug_ing3.t.join(); 
		mc_duptherapy.t.join();  
		manager.removeCache("mc_drug_ing3"); 
		manager.removeCache("mc_duptherapy");
		list.add(mc_drug_ing3.getErr()); 
		list.add(mc_duptherapy.getErr());
		
		manager.shutdown();
		
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
    }
}
