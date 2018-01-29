package com.ch.henantest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.LinkedList;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class Daorun {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, DocumentException, IOException {
		//�Զ������ݲ���ʾ�����ȡ��Ŀ�갸����
		String path="E:/chenhui/�����ĵ�������/PASS-JAVA/PASS-windows/PASS��װ����Ͱ�����/1609/��ʾ����_���ٷ�/PASS40��鰸��-201609/PASS40��鰸��-201609";
		Filestostr Filestostr=new Filestostr();
		Filestostr.traverseFolder1(path,5);//traverseFolder1(String path,int match_scheme)
		
		/*
		 * �ֶ������ݵ�Ŀ���
		 * */
//		//(����1)PASS4DB-mc_drugs����PASSPA2DB-mysql��sqlserver
		Daodrugs drug=new Daodrugs();
		drug.Drug("1021",5);
		drug.Drug("996",5);
//		drug.Drug("11973",5);
//		drug.Drug("102146",5);
//		drug.Drug("105250",5);
//		Daodis dis=new Daodis();
//		dis.Dis("Hep_ser",5);
//		dis.Dis("J18.901",5);
//		Allergen all=new Allergen();
//		all.All("A01.014");
//		all.All("9000158");
//		Route Route=new Route();
//		Route.All("129", 5);
//		Route.All("3", 5);
		
		//(����2)PASSPA2DB-mysql��PASSPA2DB-sqlserver_CLIENT
//		Daodrugstosqlserver Daodrugstosqlserver=new Daodrugstosqlserver();
//		Daodrugstosqlserver.Drug("000149x");
	}
}
