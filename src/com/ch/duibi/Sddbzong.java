package com.ch.duibi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.CacheManager;

import com.ch.passmodel.Mc_adr;
import com.ch.passmodel.Mc_adr_disicd;
import com.ch.passmodel.Mc_adult;
import com.ch.passmodel.Mc_allergroup_bingid;
import com.ch.passmodel.Mc_brief_content;
import com.ch.passmodel.Mc_contr_disicd;
import com.ch.passmodel.Mc_contr_scr;
import com.ch.passmodel.Mc_dosage_detail;
import com.ch.passmodel.Mc_dosage_link;
import com.ch.passmodel.Mc_dosage_scr;
import com.ch.passmodel.Mc_drug_ing0;
import com.ch.passmodel.Mc_drug_ing1;
import com.ch.passmodel.Mc_drug_ing2;
import com.ch.passmodel.Mc_drug_ing3;
import com.ch.passmodel.Mc_druglevel_link;
import com.ch.passmodel.Mc_druglevel_scrqry;
import com.ch.passmodel.Mc_drugs;
import com.ch.passmodel.Mc_duptherapy;
import com.ch.passmodel.Mc_geriatric;
import com.ch.passmodel.Mc_hepdos_scrdtl;
import com.ch.passmodel.Mc_indic_disicd_valid;
import com.ch.passmodel.Mc_indic_qrydtl;
import com.ch.passmodel.Mc_indic_scr;
import com.ch.passmodel.Mc_inter_genlink;
import com.ch.passmodel.Mc_inter_genlink2;
import com.ch.passmodel.Mc_inter_scrqry;
import com.ch.passmodel.Mc_iv_link;
import com.ch.passmodel.Mc_iv_scrqry;
import com.ch.passmodel.Mc_iv_single;
import com.ch.passmodel.Mc_lactation;
import com.ch.passmodel.Mc_lactation_cls;
import com.ch.passmodel.Mc_modulename;
import com.ch.passmodel.Mc_pediatric;
import com.ch.passmodel.Mc_pregnancy1;
import com.ch.passmodel.Mc_pregnancy2;
import com.ch.passmodel.Mc_pregnancy_cls;
import com.ch.passmodel.Mc_rendos_scrdtl;
import com.ch.passmodel.Mc_route_drug;
import com.ch.passmodel.Mc_route_drugvalid;
import com.ch.passmodel.Mc_route_form;
import com.ch.passmodel.Mc_route_name;
import com.ch.passmodel.Mc_sex;
import com.ch.unit.Ehcache;

//按顺序对比缓存数据和数据库数据是否一致

public class Sddbzong {
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		String err=null;
		List list=new ArrayList();
		
		Ehcache ehcache=new Ehcache();
		
		ehcache.setEh(1);
		CacheManager manager=ehcache.getManager();
		
		Mc_drugs mc_drugs=new Mc_drugs();
		err=mc_drugs.getDuibierr();
		list.add(err);
		
		Mc_route_name mc_route_name=new Mc_route_name();
		err=mc_route_name.getDuibierr();
		list.add(err);
		
		Mc_lactation mc_lactation=new Mc_lactation();
		err=mc_lactation.getDuibierr();
		list.add(err);
		
		Mc_lactation_cls mc_lactation_cls=new Mc_lactation_cls();
		err=mc_lactation_cls.getDuibierr();
		list.add(err);
		
		Mc_adr mc_adr=new Mc_adr();
		err=mc_adr.getDuibierr();
		list.add(err);
		
		Mc_adr_disicd mc_adr_disicd=new Mc_adr_disicd();
		err=mc_adr_disicd.getDuibierr();
		list.add(err);
		
	
		Mc_indic_disicd_valid mc_indic_disicd_valid=new Mc_indic_disicd_valid();
		err=mc_indic_disicd_valid.getDuibierr();
		list.add(err);
		
		Mc_indic_qrydtl mc_indic_qrydtl=new Mc_indic_qrydtl();
		err=mc_indic_qrydtl.getDuibierr();
		list.add(err);
		
		Mc_indic_scr mc_indic_scr=new Mc_indic_scr();
		err=mc_indic_scr.getDuibierr();
		list.add(err);
		
		Mc_adult mc_adult=new Mc_adult();
		err=mc_adult.getDuibierr();
		list.add(err);
		
		Mc_pediatric mc_pediatric=new Mc_pediatric();
		err=mc_pediatric.getDuibierr();
		list.add(err);
		
		Mc_hepdos_scrdtl mc_hepdos_scrdtl=new Mc_hepdos_scrdtl();
		err=mc_hepdos_scrdtl.getDuibierr();
		list.add(err);
		
		Mc_route_drug mc_route_drug=new Mc_route_drug();
		err=mc_route_drug.getDuibierr();
		list.add(err);
		
		Mc_route_drugvalid mc_route_drugvalid=new Mc_route_drugvalid();
		err=mc_route_drugvalid.getDuibierr();
		list.add(err);
		
		Mc_route_form mc_route_form=new Mc_route_form();
		err=mc_route_form.getDuibierr();
		list.add(err);
		
		manager.removeAllCaches();
		manager.shutdown();
		
		ehcache.setEh(2);
		CacheManager manager1=ehcache.getManager();
		
		Mc_modulename mc_modulename=new Mc_modulename();
		err=mc_modulename.getDuibierr();
		list.add(err);
		
		Mc_dosage_detail mc_dosage_detail=new Mc_dosage_detail();
		err=mc_dosage_detail.getDuibierr();
		list.add(err);
		
		Mc_dosage_link mc_dosage_link=new Mc_dosage_link();
		err=mc_dosage_link.getDuibierr();
		list.add(err);
		
		Mc_dosage_scr mc_dosage_scr=new Mc_dosage_scr();
		err=mc_dosage_scr.getDuibierr();
		list.add(err);
		
		Mc_brief_content mc_brief_content=new Mc_brief_content();
		err=mc_brief_content.getDuibierr();
		list.add(err);
		
		Mc_geriatric mc_geriatric=new Mc_geriatric();
		err=mc_geriatric.getDuibierr();
		list.add(err);
		
		Mc_druglevel_link mc_druglevel_link=new Mc_druglevel_link();
		err=mc_druglevel_link.getDuibierr();
		list.add(err);
		
		Mc_druglevel_scrqry mc_druglevel_scrqry=new Mc_druglevel_scrqry();
		err=mc_druglevel_scrqry.getDuibierr();
		list.add(err);

		Mc_pregnancy_cls mc_pregnancy_cls=new Mc_pregnancy_cls();
		err=mc_pregnancy_cls.getDuibierr();
		list.add(err);
		
		Mc_pregnancy1 mc_pregnancy1=new Mc_pregnancy1();
		err=mc_pregnancy1.getDuibierr();
		list.add(err);
		
		Mc_pregnancy2 mc_pregnancy2=new Mc_pregnancy2();
		err=mc_pregnancy2.getDuibierr();
		list.add(err);
		
		Mc_rendos_scrdtl mc_rendos_scrdtl=new Mc_rendos_scrdtl();
		err=mc_rendos_scrdtl.getDuibierr();
		list.add(err);
		
		Mc_iv_link mc_iv_link=new Mc_iv_link();
		err=mc_iv_link.getDuibierr();
		list.add(err);
		
		Mc_iv_scrqry mc_iv_scrqry=new Mc_iv_scrqry();
		err=mc_iv_scrqry.getDuibierr();
		list.add(err);
		
		Mc_iv_single mc_iv_single=new Mc_iv_single();
		err=mc_iv_single.getDuibierr();
		list.add(err);
		
		Mc_inter_genlink mc_inter_genlink=new Mc_inter_genlink();
		err=mc_inter_genlink.getDuibierr();
		list.add(err);
		
		Mc_inter_genlink2 mc_inter_genlink2=new Mc_inter_genlink2();
		err=mc_inter_genlink2.getDuibierr();
		list.add(err);
		
		Mc_inter_scrqry mc_inter_scrqry=new Mc_inter_scrqry();
		err=mc_inter_scrqry.getDuibierr();
		list.add(err);
		
		Mc_sex mc_sex=new Mc_sex();
		err=mc_sex.getDuibierr();
		list.add(err);
		
		Mc_inter_genlink genlink=new Mc_inter_genlink();
		err=genlink.getDuibierr();
		list.add(err);
		
		Mc_allergroup_bingid mc_allergroup_bingid=new Mc_allergroup_bingid();
		err=mc_allergroup_bingid.getDuibierr();
		list.add(err);
		
		Mc_drug_ing0 mc_drug_ing0=new Mc_drug_ing0();
		err=mc_drug_ing0.getDuibierr();
		list.add(err);
		
	
		Mc_drug_ing1 mc_drug_ing1=new Mc_drug_ing1();
		err=mc_drug_ing1.getDuibierr();
		list.add(err);
		
	
		Mc_contr_disicd mc_contr_disicd=new Mc_contr_disicd();
		err=mc_contr_disicd.getDuibierr();
		list.add(err);
		
	
		Mc_contr_scr mc_contr_scr=new Mc_contr_scr();
		err=mc_contr_scr.getDuibierr();
		list.add(err);
		
	
		Mc_drug_ing2 mc_drug_ing2=new Mc_drug_ing2();
		err=mc_drug_ing2.getDuibierr();
		list.add(err);
		
	
		Mc_drug_ing3 mc_drug_ing3=new Mc_drug_ing3();
		err=mc_drug_ing3.getDuibierr();
		list.add(err);
		
	
		Mc_duptherapy mc_duptherapy=new Mc_duptherapy();
		err=mc_duptherapy.getDuibierr();
		list.add(err);
		
		manager1.removeAllCaches();
		manager1.shutdown();
		
		System.out.println("--------------------------------------------");
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
}
