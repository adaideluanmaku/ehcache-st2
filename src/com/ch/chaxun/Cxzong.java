package com.ch.chaxun;

import java.util.ArrayList;
import java.util.List;

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

//随机从ehcache所有缓存中获取数据

public class Cxzong {
	private String err=null;
	private List errlist=new ArrayList();
	
	public String getErr() {
		return err;
	}
	
	public List getErrlist() {
		return errlist;
	}

	public String execute(){
		Mc_drugs mc_drugs=new Mc_drugs();
		err=mc_drugs.getCxerr();
		errlist.add("mc_drugs--"+err);
		
		Mc_route_name mc_route_name=new Mc_route_name();
		err=mc_route_name.getCxerr();
		errlist.add("mc_route_name--"+err);
		
		Mc_lactation mc_lactation=new Mc_lactation();
		err=mc_lactation.getCxerr();
		errlist.add("mc_lactation--"+err);
		
		Mc_lactation_cls mc_lactation_cls=new Mc_lactation_cls();
		err=mc_lactation_cls.getCxerr();
		errlist.add("mc_lactation_cls--"+err);
		
		Mc_adr mc_adr=new Mc_adr();
		err=mc_adr.getCxerr();
		errlist.add("mc_adr--"+err);
		
		Mc_adr_disicd mc_adr_disicd=new Mc_adr_disicd();
		err=mc_adr_disicd.getCxerr();
		errlist.add("mc_adr_disicd--"+err);
		
		Mc_indic_disicd_valid mc_indic_disicd_valid=new Mc_indic_disicd_valid();
		err=mc_indic_disicd_valid.getCxerr();
		errlist.add("mc_indic_disicd_valid--"+err);
		
		Mc_indic_qrydtl mc_indic_qrydtl=new Mc_indic_qrydtl();
		err=mc_indic_qrydtl.getCxerr();
		errlist.add("mc_indic_qrydtl--"+err);
		
		Mc_indic_scr mc_indic_scr=new Mc_indic_scr();
		err=mc_indic_scr.getCxerr();
		errlist.add("mc_indic_scr--"+err);
		
		Mc_adult mc_adult=new Mc_adult();
		err=mc_adult.getCxerr();
		errlist.add("mc_adult--"+err);
		
		Mc_pediatric mc_pediatric=new Mc_pediatric();
		err=mc_pediatric.getCxerr();
		errlist.add("mc_pediatric--"+err);
		
		Mc_hepdos_scrdtl mc_hepdos_scrdtl=new Mc_hepdos_scrdtl();
		err=mc_hepdos_scrdtl.getCxerr();
		errlist.add("mc_hepdos_scrdtl--"+err);
		
		Mc_route_drug mc_route_drug=new Mc_route_drug();
		err=mc_route_drug.getCxerr();
		errlist.add("mc_route_drug--"+err);
		
		Mc_route_drugvalid mc_route_drugvalid=new Mc_route_drugvalid();
		err=mc_route_drugvalid.getCxerr();
		errlist.add("mc_route_drugvalid--"+err);
		
		Mc_route_form mc_route_form=new Mc_route_form();
		err=mc_route_form.getCxerr();
		errlist.add("mc_route_form--"+err);
		
		Mc_modulename mc_modulename=new Mc_modulename();
		err=mc_modulename.getCxerr();
		errlist.add("mc_modulename--"+err);
		
		Mc_dosage_detail mc_dosage_detail=new Mc_dosage_detail();
		err=mc_dosage_detail.getCxerr();
		errlist.add("mc_dosage_detail--"+err);
		
		Mc_dosage_link mc_dosage_link=new Mc_dosage_link();
		err=mc_dosage_link.getCxerr();
		errlist.add("mc_dosage_link--"+err);
		
		Mc_dosage_scr mc_dosage_scr=new Mc_dosage_scr();
		err=mc_dosage_scr.getCxerr();
		errlist.add("mc_dosage_scr--"+err);
		
		Mc_brief_content mc_brief_content=new Mc_brief_content();
		err=mc_brief_content.getCxerr();
		errlist.add("mc_brief_content--"+err);
		
		Mc_geriatric mc_geriatric=new Mc_geriatric();
		err=mc_geriatric.getCxerr();
		errlist.add("mc_geriatric--"+err);
		
		Mc_druglevel_link mc_druglevel_link=new Mc_druglevel_link();
		err=mc_druglevel_link.getCxerr();
		errlist.add("mc_druglevel_link--"+err);
		
		Mc_druglevel_scrqry mc_druglevel_scrqry=new Mc_druglevel_scrqry();
		err=mc_druglevel_scrqry.getCxerr();
		errlist.add("mc_druglevel_scrqry--"+err);
		
		Mc_pregnancy_cls mc_pregnancy_cls=new Mc_pregnancy_cls();
		err=mc_pregnancy_cls.getCxerr();
		errlist.add("mc_pregnancy_cls--"+err);
		
		Mc_pregnancy1 mc_pregnancy1=new Mc_pregnancy1();
		err=mc_pregnancy1.getCxerr();
		errlist.add("mc_pregnancy1--"+err);
		
		Mc_pregnancy2 mc_pregnancy2=new Mc_pregnancy2();
		err=mc_pregnancy2.getCxerr();
		errlist.add("mc_pregnancy2--"+err);
		
		Mc_rendos_scrdtl mc_rendos_scrdtl=new Mc_rendos_scrdtl();
		err=mc_rendos_scrdtl.getCxerr();
		errlist.add("mc_rendos_scrdtl--"+err);
		
		Mc_iv_link mc_iv_link=new Mc_iv_link();
		err=mc_iv_link.getCxerr();
		errlist.add("mc_iv_link--"+err);
		
		Mc_iv_scrqry mc_iv_scrqry=new Mc_iv_scrqry();
		err=mc_iv_scrqry.getCxerr();
		errlist.add("mc_iv_scrqry--"+err);
		
		Mc_iv_single mc_iv_single=new Mc_iv_single();
		err=mc_iv_single.getCxerr();
		errlist.add("mc_iv_single--"+err);
		
		Mc_inter_genlink mc_inter_genlink=new Mc_inter_genlink();
		err=mc_inter_genlink.getCxerr();
		errlist.add("mc_inter_genlink--"+err);
		
		Mc_inter_scrqry mc_inter_scrqry=new Mc_inter_scrqry();
		err=mc_inter_scrqry.getCxerr();
		errlist.add("mc_inter_scrqry--"+err);
		
		Mc_sex mc_sex=new Mc_sex();
		err=mc_sex.getCxerr();
		errlist.add("mc_sex--"+err);
		
		Mc_inter_genlink genlink=new Mc_inter_genlink();
		err=genlink.getCxerr();
		errlist.add("genlink--"+err);
		
		Mc_allergroup_bingid mc_allergroup_bingid=new Mc_allergroup_bingid();
		err=mc_allergroup_bingid.getCxerr();
		errlist.add("mc_allergroup_bingid--"+err);
		
		Mc_drug_ing0 mc_drug_ing0=new Mc_drug_ing0();
		err=mc_drug_ing0.getCxerr();
		errlist.add("mc_drug_ing0--"+err);
		
		Mc_drug_ing1 mc_drug_ing1=new Mc_drug_ing1();
		err=mc_drug_ing1.getCxerr();
		errlist.add("mc_drug_ing1--"+err);
		
		Mc_contr_disicd mc_contr_disicd=new Mc_contr_disicd();
		err=mc_contr_disicd.getCxerr();
		errlist.add("mc_contr_disicd--"+err);
		
		Mc_contr_scr mc_contr_scr=new Mc_contr_scr();
		err=mc_contr_scr.getCxerr();
		errlist.add("mc_contr_scr--"+err);
		
		Mc_drug_ing2 mc_drug_ing2=new Mc_drug_ing2();
		err=mc_drug_ing2.getCxerr();
		errlist.add("mc_drug_ing2--"+err);
		
		Mc_drug_ing3 mc_drug_ing3=new Mc_drug_ing3();
		err=mc_drug_ing3.getCxerr();
		errlist.add("mc_drug_ing3--"+err);
		
		Mc_duptherapy mc_duptherapy=new Mc_duptherapy();
		err=mc_duptherapy.getCxerr();
		errlist.add("mc_duptherapy--"+err);
		
		errlist.add("磁盘总数是："+errlist.size());
		return "success";
	}
}
