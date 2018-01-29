package com.ch.passthread;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.CacheManager;

import com.ch.passmodel.Mc_adr;
import com.ch.passmodel.Mc_adr_disicd;
import com.ch.passmodel.Mc_adult;
import com.ch.passmodel.Mc_aller_scr;
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

//多线程
public class Passthread implements Runnable {//创建一个线程类，继承runnable
	String name;
	String err;
	public Thread t;
	static String webrsst;
	
	public String getWebrsint() {
		return webrsst;
	}

	public void setWebrsst(String webrsst) {
		this.webrsst = webrsst;
	}

	public String getErr() {
		return err;
	}

	public Passthread(String name) {//创建一个线程
		this.name=name;
		t=new Thread(this,name);
		t.start();
	}
	
	public void run() {//线程运行体
		if("mc_drugs".equals(name)){
			Mc_drugs mc_drugs=new Mc_drugs();
			try {
				err=mc_drugs.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_route_name".equals(name)){
			Mc_route_name mc_route_name=new Mc_route_name();
			try {
				err=mc_route_name.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_lactation".equals(name)){
			Mc_lactation mc_lactation=new Mc_lactation();
			try {
				err=mc_lactation.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_lactation_cls".equals(name)){
			Mc_lactation_cls mc_lactation_cls=new Mc_lactation_cls();
			try {
				err=mc_lactation_cls.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_adr".equals(name)){
			Mc_adr mc_adr=new Mc_adr();
			try {
				err=mc_adr.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_adr_disicd".equals(name)){
			Mc_adr_disicd mc_adr_disicd=new Mc_adr_disicd();
			try {
				err=mc_adr_disicd.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_aller_scr".equals(name)){
			Mc_aller_scr mc_aller_scr=new Mc_aller_scr();
			try {
				err=mc_aller_scr.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_indic_disicd_valid".equals(name)){
			Mc_indic_disicd_valid mc_indic_disicd_valid=new Mc_indic_disicd_valid();
			try {
				err=mc_indic_disicd_valid.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_indic_qrydtl".equals(name)){
			Mc_indic_qrydtl mc_indic_qrydtl=new Mc_indic_qrydtl();
			try {
				err=mc_indic_qrydtl.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_indic_scr".equals(name)){
			Mc_indic_scr mc_indic_scr=new Mc_indic_scr();
			try {
				err=mc_indic_scr.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_adult".equals(name)){
			Mc_adult mc_adult=new Mc_adult();
			try {
				err=mc_adult.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_pediatric".equals(name)){
			Mc_pediatric mc_pediatric=new Mc_pediatric();
			try {
				err=mc_pediatric.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_hepdos_scrdtl".equals(name)){
			Mc_hepdos_scrdtl mc_hepdos_scrdtl=new Mc_hepdos_scrdtl();
			try {
				err=mc_hepdos_scrdtl.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_route_drug".equals(name)){
			Mc_route_drug mc_route_drug=new Mc_route_drug();
			try {
				err=mc_route_drug.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_route_drugvalid".equals(name)){
			Mc_route_drugvalid mc_route_drugvalid=new Mc_route_drugvalid();
			try {
				err=mc_route_drugvalid.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_route_form".equals(name)){
			Mc_route_form mc_route_form=new Mc_route_form();
			try {
				err=mc_route_form.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_modulename".equals(name)){
			Mc_modulename mc_modulename=new Mc_modulename();
			try {
				err=mc_modulename.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_dosage_detail".equals(name)){
			Mc_dosage_detail mc_dosage_detail=new Mc_dosage_detail();
			try {
				err=mc_dosage_detail.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_dosage_link".equals(name)){
			Mc_dosage_link mc_dosage_link=new Mc_dosage_link();
			try {
				err=mc_dosage_link.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_dosage_scr".equals(name)){
			Mc_dosage_scr mc_dosage_scr=new Mc_dosage_scr();
			try {
				err=mc_dosage_scr.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_brief_content".equals(name)){
			Mc_brief_content mc_brief_content=new Mc_brief_content();
			try {
				err=mc_brief_content.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_geriatric".equals(name)){
			Mc_geriatric mc_geriatric=new Mc_geriatric();
			try {
				err=mc_geriatric.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_druglevel_link".equals(name)){
			Mc_druglevel_link mc_druglevel_link=new Mc_druglevel_link();
			try {
				err=mc_druglevel_link.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_druglevel_scrqry".equals(name)){
			Mc_druglevel_scrqry mc_druglevel_scrqry=new Mc_druglevel_scrqry();
			try {
				err=mc_druglevel_scrqry.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_pregnancy_cls".equals(name)){
			Mc_pregnancy_cls mc_pregnancy_cls=new Mc_pregnancy_cls();
			try {
				err=mc_pregnancy_cls.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_pregnancy1".equals(name)){
			Mc_pregnancy1 mc_pregnancy1=new Mc_pregnancy1();
			try {
				err=mc_pregnancy1.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_pregnancy2".equals(name)){
			Mc_pregnancy2 mc_pregnancy2=new Mc_pregnancy2();
			try {
				err=mc_pregnancy2.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_rendos_scrdtl".equals(name)){
			Mc_rendos_scrdtl mc_rendos_scrdtl=new Mc_rendos_scrdtl();
			try {
				err=mc_rendos_scrdtl.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_iv_link".equals(name)){
			Mc_iv_link mc_iv_link=new Mc_iv_link();
			try {
				err=mc_iv_link.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_iv_scrqry".equals(name)){
			Mc_iv_scrqry mc_iv_scrqry=new Mc_iv_scrqry();
			try {
				err=mc_iv_scrqry.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_iv_single".equals(name)){
			Mc_iv_single mc_iv_single=new Mc_iv_single();
			try {
				err=mc_iv_single.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_inter_genlink".equals(name)){
			Mc_inter_genlink mc_inter_genlink=new Mc_inter_genlink();
			try {
				err=mc_inter_genlink.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_inter_genlink2".equals(name)){
			Mc_inter_genlink2 mc_inter_genlink2=new Mc_inter_genlink2();
			try {
				err=mc_inter_genlink2.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_inter_scrqry".equals(name)){
			Mc_inter_scrqry mc_inter_scrqry=new Mc_inter_scrqry();
			try {
				err=mc_inter_scrqry.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_sex".equals(name)){
			Mc_sex mc_sex=new Mc_sex();
			try {
				err=mc_sex.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_allergroup_bingid".equals(name)){
			Mc_allergroup_bingid mc_allergroup_bingid=new Mc_allergroup_bingid();
			try {
				err=mc_allergroup_bingid.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_drug_ing0".equals(name)){
			Mc_drug_ing0 mc_drug_ing0=new Mc_drug_ing0();
			try {
				err=mc_drug_ing0.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_drug_ing1".equals(name)){
			Mc_drug_ing1 mc_drug_ing1=new Mc_drug_ing1();
			try {
				err=mc_drug_ing1.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_contr_disicd".equals(name)){
			Mc_contr_disicd mc_contr_disicd=new Mc_contr_disicd();
			try {
				err=mc_contr_disicd.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_contr_scr".equals(name)){
			Mc_contr_scr mc_contr_scr=new Mc_contr_scr();
			try {
				err=mc_contr_scr.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_drug_ing2".equals(name)){
			Mc_drug_ing2 mc_drug_ing2=new Mc_drug_ing2();
			try {
				err=mc_drug_ing2.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_drug_ing3".equals(name)){
			Mc_drug_ing3 mc_drug_ing3=new Mc_drug_ing3();
			try {
				err=mc_drug_ing3.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("mc_duptherapy".equals(name)){
			Mc_duptherapy mc_duptherapy=new Mc_duptherapy();
			try {
				err=mc_duptherapy.getDuibierr();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}