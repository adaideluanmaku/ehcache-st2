package com.ch.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ch.json.Jsonguifan;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Jsonduibitest {
	public static void main(String args[]) throws IOException{
		int listerrsum=0;
		InputStream in=Jsonguifan.class.getClassLoader().getResourceAsStream("json.properties");
		Properties pro=new Properties();
		pro.load(in);
		String json=pro.getProperty("json");
		String json1=pro.getProperty("json1");
		
		List listerr=new ArrayList();
		if("".equals(json)||"".equals(json1)){
			listerrsum=1;
			System.out.println("�������벻����");
			listerr.add("�������벻����");
//			setListerr1(listerr);
//			return "success";
		}
//		System.out.println(json);
//		System.out.println(json1);
		//���ַ���ת��JSON��,������������JSON
		JSONObject jsonin=JSONObject.fromObject(json);//��ȷ��json
		JSONObject jsonin1=JSONObject.fromObject(json1);//��Ҫ��֤��json
//		System.out.println(jsonin);
//		System.out.println(jsonin1);
		
		if(!jsonin.get("HighestSlcode").equals(jsonin1.get("HighestSlcode"))){
			System.out.println("���ԣ�һ��Ŀ¼��ȷ�Ľڵ���HighestSlcode��"+jsonin.get("HighestSlcode"));
			System.out.println("��Ӧ��һ��Ŀ¼����Ľڵ���HighestSlcode��"+jsonin1.get("HighestSlcode"));
			listerr.add("���ԣ�һ��Ŀ¼��ȷ�Ľڵ��ǣ�HighestSlcode��"+jsonin.get("HighestSlcode"));
			listerr.add("��Ӧ��һ��Ŀ¼����Ľڵ��ǣ�HighestSlcode��"+jsonin1.get("HighestSlcode"));
		}
		if(!jsonin.get("InUseModules").equals(jsonin1.get("InUseModules"))){
			System.out.println("���ԣ�һ��Ŀ¼��ȷ�Ľڵ���InUseModules��"+jsonin.get("InUseModules"));
			System.out.println("��Ӧ��һ��Ŀ¼����Ľڵ���InUseModules��"+jsonin1.get("InUseModules"));
			listerr.add("���ԣ�һ��Ŀ¼��ȷ�Ľڵ���InUseModules��"+jsonin.get("InUseModules"));
			listerr.add("��Ӧ��һ��Ŀ¼����Ľڵ���InUseModules��"+jsonin1.get("InUseModules"));
		}
		
		JSONArray ScreenResultDrugslist=jsonin.getJSONArray("ScreenResultDrugs");
		JSONArray ScreenResultDrugslist1=jsonin1.getJSONArray("ScreenResultDrugs");
		if(ScreenResultDrugslist.size()>=ScreenResultDrugslist1.size()){
			for(int i=0;i<ScreenResultDrugslist.size();i++){
				JSONObject ScreenResultDrugslistn=ScreenResultDrugslist.getJSONObject(i);//Ajson��һ��
				int state=0;
				for(int j=0;j<ScreenResultDrugslist1.size();j++){
					JSONObject ScreenResultDrugslistn1=ScreenResultDrugslist1.getJSONObject(j);//Bjson��һ��
					if(ScreenResultDrugslistn1.get("DrugIndex").equals(ScreenResultDrugslistn.get("DrugIndex"))){
						String DrugIndex=ScreenResultDrugslistn.get("DrugIndex").toString();
						state=1;
						if(!ScreenResultDrugslistn1.get("DrugName").equals(ScreenResultDrugslistn.get("DrugName"))){
							System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+"����ȷ�ڵ���DrugName��"+ScreenResultDrugslistn.get("DrugName"));
							System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+"�Ĵ���ڵ���DrugName��"+ScreenResultDrugslistn1.get("DrugName"));
							listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+"����ȷ�ڵ���DrugName��"+ScreenResultDrugslistn.get("DrugName"));
							listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+"�Ĵ���ڵ���DrugName��"+ScreenResultDrugslistn1.get("DrugName"));
						}
						if(!ScreenResultDrugslistn1.get("Slcode").equals(ScreenResultDrugslistn.get("Slcode"))){
							System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+"����ȷ�ڵ���Slcode��"+ScreenResultDrugslistn.get("Slcode"));
							System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+"�Ĵ���ڵ���Slcode��"+ScreenResultDrugslistn1.get("Slcode"));
							listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+"����ȷ�ڵ���Slcode��"+ScreenResultDrugslistn.get("Slcode"));
							listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+"�Ĵ���ڵ���Slcode��"+ScreenResultDrugslistn1.get("Slcode"));
						}
						if(!ScreenResultDrugslistn1.get("MenuLabel").equals(ScreenResultDrugslistn.get("MenuLabel"))){
							System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+"����ȷ�ڵ���MenuLabel��"+ScreenResultDrugslistn.get("MenuLabel"));
							System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+"�Ĵ���ڵ���MenuLabel��"+ScreenResultDrugslistn1.get("MenuLabel"));
							listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+"����ȷ�ڵ���MenuLabel��"+ScreenResultDrugslistn.get("MenuLabel"));
							listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+"�Ĵ���ڵ���MenuLabel��"+ScreenResultDrugslistn1.get("MenuLabel"));
						}
						
						//ScreenResults�ڵ���ж�
						JSONArray ScreenResultslist=ScreenResultDrugslistn.getJSONArray("ScreenResults");
						JSONArray ScreenResultslist1=ScreenResultDrugslistn1.getJSONArray("ScreenResults");
						if(ScreenResultslist.size()!=ScreenResultslist1.size()){
							List list1=new ArrayList();
							List list2=new ArrayList();
							for(int i1=0;i1<ScreenResultslist.size();i1++){
								JSONObject names1=ScreenResultslist.getJSONObject(i1);
								String name1=names1.getString("ModuleName");
								list1.add(name1);
							}
							for(int i2=0;i2<ScreenResultslist1.size();i2++){
								JSONObject names2=ScreenResultslist1.getJSONObject(i2);
								String name2=names2.getString("ModuleName");
								list2.add(name2);
							}
							System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults������"+ScreenResultslist.size()+"��,"+list1);
							System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults������"+ScreenResultslist1.size()+"����������,"+list2);
							listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults������"+ScreenResultslist.size()+"��,"+list1);
							listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults������"+ScreenResultslist1.size()+"����������,"+list2);
						}
//						if(ScreenResultslist.size()>ScreenResultslist1.size()&&ScreenResultslist1.size()==0){
//							System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+"��ScreenResults�ڵ�������");
//							System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+"��ScreenResults�ڵ�ȱ������");
//							listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+"��ScreenResults�ڵ�������");
//							listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+"��ScreenResults�ڵ�ȱ������");
//						}else{
							if(ScreenResultslist.size()>=ScreenResultslist1.size()&&ScreenResultslist1.size()!=0){
								for(int k=0;k<ScreenResultslist.size();k++){
									JSONObject ScreenResultslistn=ScreenResultslist.getJSONObject(k);//ajson����
//									int state1=0;
									int ModuleIDstate=0;
									int Warningstate=0;
									int OtherInfostate=0;
									int Warningsum=0;
									int ModuleIDsum=0;
									int Severitysum=0;
									int Severitystate=0;
									String a=null;
									String b=null;
									for(int z=0;z<ScreenResultslist1.size();z++){
										JSONObject ScreenResultslistn1=ScreenResultslist1.getJSONObject(z);//bjson����
//										System.out.println(ScreenResultslistn.get("Slcode")+"��"+ScreenResultslistn1.get("Slcode"));\
										if(ScreenResultslistn.get("ModuleID").equals(ScreenResultslistn1.get("ModuleID"))){
											ModuleIDstate=1;
											if(ScreenResultslistn.get("Severity").equals(ScreenResultslistn1.get("Severity"))){
												Severitystate=1;
												if(ScreenResultslistn.get("Warning").equals(ScreenResultslistn1.get("Warning"))){
													Warningstate=1;
//													state1=1;
													if(ScreenResultslistn.get("OtherInfo").equals(ScreenResultslistn1.get("OtherInfo"))){
//														Warningstate=Warningstate-1;
														OtherInfostate=1;
														for(int o=0;o<ScreenResultslistn.size();o++){
															String name=ScreenResultslistn.names().getString(o);
															if(!ScreenResultslistn.get(name).equals(ScreenResultslistn1.get(name))&&!"Warning".equals(ScreenResultslistn.get(name))&&!"OtherInfo".equals(ScreenResultslistn.get(name))){
																System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���"+name+"��"+ScreenResultslistn.get(name));
																System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"���Ĵ���ڵ���"+name+"��"+ScreenResultslistn1.get(name));
																listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���"+name+"��"+ScreenResultslistn.get(name));
																listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"���Ĵ���ڵ���"+name+"��"+ScreenResultslistn1.get(name));
															}
														}
													}else{
//														OtherInfostate=OtherInfostate+1;
														if(OtherInfostate==0&&(z+1)==ScreenResultslist1.size()){
															System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���OtherInfo��"+ScreenResultslistn.get("OtherInfo"));
															System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"���Ĵ���ڵ���OtherInfo��"+ScreenResultslistn1.get("OtherInfo"));
															listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���OtherInfo��"+ScreenResultslistn.get("OtherInfo"));
															listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"���Ĵ���ڵ���OtherInfo��"+ScreenResultslistn1.get("OtherInfo"));
														}
													}
												}else{
//													Warningstate=Warningstate+1;
//													String Warning=ScreenResultslistn1.getString("Warning");
//													for(int i1=0;i1<ScreenResultslist.size();i1++){
//														JSONObject obj=ScreenResultslist.getJSONObject(i1);
//														if(Warning.equals(obj.getString("Warning"))){
//															Warningsum=1;
//														}
//													}
//													if(Warningsum==0){
//														System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"��û��Warning��");
//														System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��Warning��"+ScreenResultslistn1.get("Warning"));
//														listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"��û��Warning��");
//														listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��Warning��"+ScreenResultslistn1.get("Warning"));
//													}
													if(Warningstate==0&&(z+1)==ScreenResultslist1.size()){
														System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���Warning��"+ScreenResultslistn.get("Warning"));
														System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"����ȱ�ٽڵ���Warning��"+ScreenResultslistn1.get("Warning"));
														listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���Warning��"+ScreenResultslistn.get("Warning"));
														listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"����ȱ�ٽڵ���Warning��"+ScreenResultslistn1.get("Warning"));
													}
												}
											}else{
												String Severity=ScreenResultslistn1.getString("Severity");
												for(int i1=0;i1<ScreenResultslist.size();i1++){
													JSONObject obj=ScreenResultslist.getJSONObject(i1);
													if(Severity.equals(obj.getString("Severity"))){
														Severitysum=1;
													}
												}
												if(Severitysum==0){
													System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"��û��Severity��");
													System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��Severity��"+ScreenResultslistn1.get("Severity"));
													listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"��û��Severity��");
													listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��Severity��"+ScreenResultslistn1.get("Severity"));
												}
												if(Severitystate==0&&(z+1)==ScreenResultslist1.size()){
													System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���Severity��"+ScreenResultslistn.get("Severity"));
													System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"����ȱ�ٽڵ���Severity��"+ScreenResultslistn1.get("Severity"));
													listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���Severity��"+ScreenResultslistn.get("Severity"));
													listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"����ȱ�ٽڵ���Severity��"+ScreenResultslistn1.get("Severity"));
												}
											}
										}else{
											String ModuleID=ScreenResultslistn1.getString("ModuleID");
											for(int i1=0;i1<ScreenResultslist.size();i1++){
												JSONObject obj=ScreenResultslist.getJSONObject(i1);
												if(ModuleID.equals(obj.getString("ModuleID"))){
													ModuleIDsum=1;
												}
											}
											if(ModuleIDsum==0){
												System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--û��ModuleName������");
												System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
												listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--û��ModuleName������");
												listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
											}
											if((ModuleIDstate==0&&(z+1)==ScreenResultslist1.size())){
												System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--��ModuleName����"+ScreenResultslistn.get("ModuleName")+"��");
												System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ȱ��ModuleName����"+ScreenResultslistn.get("ModuleName")+"��");
												listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--��ModuleName����"+ScreenResultslistn.get("ModuleName")+"��");
												listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ȱ��ModuleName����"+ScreenResultslistn.get("ModuleName")+"��");
											}
//											if(ModuleIDstate==0&&(z+1)==ScreenResultslist1.size()&&(18==Integer.parseInt(ScreenResultslistn.get("ModuleID").toString())||4==Integer.parseInt(ScreenResultslistn.get("ModuleID").toString()))){
//												System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--��ModuleName����"+ScreenResultslistn.get("ModuleName")+"��");
//												System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ȱ��ModuleName����"+ScreenResultslistn.get("ModuleName")+"��");
//												listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--��ModuleName����"+ScreenResultslistn.get("ModuleName")+"��");
//												listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ȱ��ModuleName����"+ScreenResultslistn.get("ModuleName")+"��");
//											}
										}
									}
//									if(state1==0){
//										System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults��ȷ�Ľڵ���ModuleName��"+ScreenResultslistn.get("ModuleName"));
//										System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResultsȱ�١�"+ScreenResultslistn.get("ModuleName")+"���Ľ��");
//										listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults��ȷ�Ľڵ���ModuleName��"+ScreenResultslistn.get("ModuleName"));
//										listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults��"+ScreenResultslistn.get("ModuleName")+"���Ľ��");
//									}
								}
							}else{
								for(int k=0;k<ScreenResultslist1.size();k++){
									JSONObject ScreenResultslistn1=ScreenResultslist1.getJSONObject(k);//ajson����
//									int state1=0;
									int ModuleIDstate=0;
									int Warningstate=0;
									int OtherInfostate=0;
									int Warningsum=0;
									int ModuleIDsum=0;
									int Severitysum=0;
									int Severitystate=0;
									for(int z=0;z<ScreenResultslist.size();z++){
										JSONObject ScreenResultslistn=ScreenResultslist.getJSONObject(z);//bjson����
										if(ScreenResultslistn.get("ModuleID").equals(ScreenResultslistn1.get("ModuleID"))){
											ModuleIDstate=1;
											if(ScreenResultslistn.get("Severity").equals(ScreenResultslistn1.get("Severity"))){
												Severitystate=1;
												if(ScreenResultslistn.get("Warning").equals(ScreenResultslistn1.get("Warning"))){
//													state1=1;
													Warningstate=1;
													if(ScreenResultslistn.get("OtherInfo").equals(ScreenResultslistn1.get("OtherInfo"))){
														OtherInfostate=1;
														for(int o=0;o<ScreenResultslistn1.size();o++){
															String name=ScreenResultslistn1.names().getString(o);
															if(!ScreenResultslistn.get(name).equals(ScreenResultslistn1.get(name))&&!"Warning".equals(ScreenResultslistn.get(name))){
																System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ��ǣ�"+ScreenResultslistn.get(name));
																System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"���Ĵ���ڵ��ǣ�"+ScreenResultslistn1.get(name));
																listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ��ǣ�"+ScreenResultslistn.get(name));
																listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"���Ĵ���ڵ��ǣ�"+ScreenResultslistn1.get(name));
															}
														}
													}else{
//														OtherInfostate=OtherInfostate+1;
														if(OtherInfostate==0){
															System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���OtherInfo��"+ScreenResultslistn.get("OtherInfo"));
															System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"���Ĵ���ڵ���OtherInfo��"+ScreenResultslistn1.get("OtherInfo"));
															listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���OtherInfo��"+ScreenResultslistn.get("OtherInfo"));
															listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"���Ĵ���ڵ���OtherInfo��"+ScreenResultslistn1.get("OtherInfo"));
														}
													}
												}else{
//													Warningstate=Warningstate+1;
													String Warning=ScreenResultslistn.getString("Warning");
													for(int i1=0;i1<ScreenResultslist1.size();i1++){
														JSONObject obj=ScreenResultslist1.getJSONObject(i1);
														if(Warning.equals(obj.getString("Warning"))){
															Warningsum=1;
														}
													}
													if(Warningsum==0){
														System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"��Warning��"+ScreenResultslistn.get("Warning"));
														System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��û��Warning��");
														listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"��Warning��"+ScreenResultslistn.get("Warning"));
														listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��û��Warning��");
													}
													if(Warningstate==0){
														System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���Warning��"+ScreenResultslistn.get("Warning"));
														System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"����ȱ�ٽڵ���Warning��"+ScreenResultslistn1.get("Warning"));
														listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���Warning��"+ScreenResultslistn.get("Warning"));
														listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"����ȱ�ٽڵ���Warning��"+ScreenResultslistn1.get("Warning"));
													}
												}
											}else{
												String Severity=ScreenResultslistn.getString("Severity");
												for(int i1=0;i1<ScreenResultslist1.size();i1++){
													JSONObject obj=ScreenResultslist1.getJSONObject(i1);
													if(Severity.equals(obj.getString("Severity"))){
														Severitysum=1;
													}
												}
												if(Severitysum==0){
													System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"��Severity��"+ScreenResultslistn.get("Severity"));
													System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��û��Severity��");
													listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"��Severity��"+ScreenResultslistn.get("Severity"));
													listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��û��Severity��");
												}
												if(Severitystate==0&&(z+1)==ScreenResultslist1.size()){
													System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���Severity��"+ScreenResultslistn.get("Severity"));
													System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"����ȱ�ٽڵ���Severity��"+ScreenResultslistn1.get("Severity"));
													listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���Severity��"+ScreenResultslistn.get("Severity"));
													listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"����ȱ�ٽڵ���Severity��"+ScreenResultslistn1.get("Severity"));
												}
											}
										}else{
											String ModuleID=ScreenResultslistn.getString("ModuleID");
											for(int i1=0;i1<ScreenResultslist1.size();i1++){
												JSONObject obj=ScreenResultslist1.getJSONObject(i1);
												if(ModuleID.equals(obj.getString("ModuleID"))){
													ModuleIDsum=1;
												}
											}
											if(ModuleIDsum==0){
												System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"��");
												System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--û��ModuleName������");
												listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"��");
												listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--û��ModuleName������");
											}
											if(ModuleIDstate==0&&(z+1)==ScreenResultslist.size()){
												System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--��ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
												System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ȱ��ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
												listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--��ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
												listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ȱ��ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
											}
//											if(18==Integer.parseInt(ScreenResultslistn1.get("ModuleID").toString())||4==Integer.parseInt(ScreenResultslistn1.get("ModuleID").toString())){
//												System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--��ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
//												System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ȱ��ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
//												listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--��ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
//												listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ȱ��ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
//											}
										}
									}
//									if(state1==0){
//										System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults����ȷ�ڵ���ModuleName����");
//										System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults����Ľڵ���ModuleName��"+ScreenResultslistn1.get("ModuleName"));
//										listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults����ȷ�ڵ���ModuleName����");
//										listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults����Ľڵ���ModuleName��"+ScreenResultslistn1.get("ModuleName"));
//									}
								}
							}
//						}
					}
				}
				if(state==0){
					System.out.println("���ԣ���ҩƷDrugIndex��"+ScreenResultDrugslistn.get("DrugIndex"));
					System.out.println("��Ӧ��ȱ��ҩƷDrugIndex��"+ScreenResultDrugslistn.get("DrugIndex"));
					listerr.add("���ԣ���ҩƷDrugIndex��"+ScreenResultDrugslistn.get("DrugIndex"));
					listerr.add("��Ӧ��ȱ��ҩƷDrugIndex��"+ScreenResultDrugslistn.get("DrugIndex"));
				}
			}
		}else{
			for(int i=0;i<ScreenResultDrugslist1.size();i++){
				JSONObject ScreenResultDrugslistn1=ScreenResultDrugslist1.getJSONObject(i);//Ajson��һ��
				int state=0;
				for(int j=0;j<ScreenResultDrugslist.size();j++){
					JSONObject ScreenResultDrugslistn=ScreenResultDrugslist.getJSONObject(j);//Bjson��һ��
					if(ScreenResultDrugslistn1.get("DrugIndex").equals(ScreenResultDrugslistn.get("DrugIndex"))){
						String DrugIndex=ScreenResultDrugslistn.get("DrugIndex").toString();
						state=1;
						if(!ScreenResultDrugslistn1.get("DrugName").equals(ScreenResultDrugslistn.get("DrugName"))){
							System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+"����ȷ�ڵ���DrugName��"+ScreenResultDrugslistn.get("DrugName"));
							System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+"�Ĵ���ڵ���DrugName��"+ScreenResultDrugslistn1.get("DrugName"));
							listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+"����ȷ�ڵ���DrugName��"+ScreenResultDrugslistn.get("DrugName"));
							listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+"�Ĵ���ڵ���DrugName��"+ScreenResultDrugslistn1.get("DrugName"));
						}
						if(!ScreenResultDrugslistn1.get("Slcode").equals(ScreenResultDrugslistn.get("Slcode"))){
							System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+"����ȷ�ڵ���Slcode��"+ScreenResultDrugslistn.get("Slcode"));
							System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+"�Ĵ���ڵ���Slcode��"+ScreenResultDrugslistn.get("Slcode"));
							listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+"����ȷ�ڵ���Slcode��"+ScreenResultDrugslistn.get("Slcode"));
							listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+"�Ĵ���ڵ���Slcode��"+ScreenResultDrugslistn.get("Slcode"));
						}
						if(!ScreenResultDrugslistn1.get("MenuLabel").equals(ScreenResultDrugslistn.get("MenuLabel"))){
							System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+"����ȷ�ڵ���MenuLabel��"+ScreenResultDrugslistn.get("MenuLabel"));
							System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+"�Ĵ���ڵ���MenuLabel��"+ScreenResultDrugslistn1.get("MenuLabel"));
							listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+"����ȷ�ڵ���MenuLabel��"+ScreenResultDrugslistn.get("MenuLabel"));
							listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+"�Ĵ���ڵ���MenuLabel��"+ScreenResultDrugslistn1.get("MenuLabel"));
						}
						//ScreenResults�ڵ���ж�
						JSONArray ScreenResultslist=ScreenResultDrugslistn.getJSONArray("ScreenResults");
						JSONArray ScreenResultslist1=ScreenResultDrugslistn1.getJSONArray("ScreenResults");
						if(ScreenResultslist.size()!=ScreenResultslist1.size()){
							List list1=new ArrayList();
							List list2=new ArrayList();
							for(int i1=0;i1<ScreenResultslist.size();i1++){
								JSONObject names1=ScreenResultslist.getJSONObject(i1);
								String name1=names1.getString("ModuleName");
								list1.add(name1);
							}
							for(int i2=0;i2<ScreenResultslist1.size();i2++){
								JSONObject names2=ScreenResultslist1.getJSONObject(i2);
								String name2=names2.getString("ModuleName");
								list2.add(name2);
							}
							System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults������"+ScreenResultslist.size()+"��,"+list1);
							System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults������"+ScreenResultslist1.size()+"����������,"+list2);
							listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults������"+ScreenResultslist.size()+"��,"+list1);
							listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults������"+ScreenResultslist1.size()+"����������,"+list2);
						}
//						if(ScreenResultslist.size()>ScreenResultslist1.size()&&ScreenResultslist1.size()==0){
//							System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+"��ScreenResults�ڵ�������");
//							System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+"��ScreenResults�ڵ�ȱ������");
//							listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+"��ScreenResults�ڵ�������");
//							listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+"��ScreenResults�ڵ�ȱ������");
//						}else{
							if(ScreenResultslist.size()>=ScreenResultslist1.size()&&ScreenResultslist1.size()!=0){
								for(int k=0;k<ScreenResultslist.size();k++){
									JSONObject ScreenResultslistn=ScreenResultslist.getJSONObject(k);//ajson����
//									int state1=0;
									int ModuleIDstate=0;
									int Warningstate=0;
									int OtherInfostate=0;
									int Warningsum=0;
									int ModuleIDsum=0;
									int Severitysum=0;
									int Severitystate=0;
									for(int z=0;z<ScreenResultslist1.size();z++){
										JSONObject ScreenResultslistn1=ScreenResultslist1.getJSONObject(z);//bjson����
										if(ScreenResultslistn.get("ModuleID").equals(ScreenResultslistn1.get("ModuleID"))){
											ModuleIDstate=1;
											if(ScreenResultslistn.get("Severity").equals(ScreenResultslistn1.get("Severity"))){
												Severitystate=1;
												if(ScreenResultslistn.get("Warning").equals(ScreenResultslistn1.get("Warning"))){
//													state1=1;
													Warningstate=1;
													if(ScreenResultslistn.get("OtherInfo").equals(ScreenResultslistn1.get("OtherInfo"))){
														OtherInfostate=1;
														for(int o=0;o<ScreenResultslistn.size();o++){
															String name=ScreenResultslistn.names().getString(o);
															if(!ScreenResultslistn.get(name).equals(ScreenResultslistn1.get(name))&&!"Warning".equals(ScreenResultslistn.get(name))){
																System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���"+name+"��"+ScreenResultslistn.get(name));
																System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"���Ĵ���ڵ���"+name+"��"+ScreenResultslistn1.get(name));
																listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���"+name+"��"+ScreenResultslistn.get(name));
																listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"���Ĵ���ڵ���"+name+"��"+ScreenResultslistn1.get(name));
															}
														}
													}else{
//														OtherInfostate=OtherInfostate+1;
														if(OtherInfostate==0){
															System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���OtherInfo��"+ScreenResultslistn.get("OtherInfo"));
															System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"���Ĵ���ڵ���OtherInfo��"+ScreenResultslistn1.get("OtherInfo"));
															listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���OtherInfo��"+ScreenResultslistn.get("OtherInfo"));
															listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"���Ĵ���ڵ���OtherInfo��"+ScreenResultslistn1.get("OtherInfo"));
														}
													}
												}else{
//													Warningstate=Warningstate+1;
													String Warning=ScreenResultslistn1.getString("Warning");
													for(int i1=0;i1<ScreenResultslist.size();i1++){
														JSONObject obj=ScreenResultslist.getJSONObject(i1);
														if(Warning.equals(obj.getString("Warning"))){
															Warningsum=1;
														}
													}
													if(Warningsum==0){
														System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"��û��Warning��");
														System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��Warning��"+ScreenResultslistn1.get("Warning"));
														listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"��û��Warning��");
														listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"������Warning��"+ScreenResultslistn1.get("Warning"));
													}
													if(Warningstate==0){
														System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���Warning��"+ScreenResultslistn.get("Warning"));
														System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"����ȱ�ٽڵ���Warning��"+ScreenResultslistn1.get("Warning"));
														listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���Warning��"+ScreenResultslistn.get("Warning"));
														listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"����ȱ�ٽڵ���Warning��"+ScreenResultslistn1.get("Warning"));
													}
												}
											}else{
												String Severity=ScreenResultslistn1.getString("Severity");
												for(int i1=0;i1<ScreenResultslist.size();i1++){
													JSONObject obj=ScreenResultslist.getJSONObject(i1);
													if(Severity.equals(obj.getString("Severity"))){
														Severitysum=1;
													}
												}
												if(Severitysum==0){
													System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"��û��Severity��");
													System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��Severity��"+ScreenResultslistn1.get("Severity"));
													listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"��û��Severity��");
													listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"������Severity��"+ScreenResultslistn1.get("Severity"));
												}
												if(Severitystate==0&&(z+1)==ScreenResultslist1.size()){
													System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���Severity��"+ScreenResultslistn.get("Severity"));
													System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"����ȱ�ٽڵ���Severity��"+ScreenResultslistn1.get("Severity"));
													listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���Severity��"+ScreenResultslistn.get("Severity"));
													listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"����ȱ�ٽڵ���Severity��"+ScreenResultslistn1.get("Severity"));
												}
											}
										}else{
											String ModuleID=ScreenResultslistn1.getString("ModuleID");
											for(int i1=0;i1<ScreenResultslist.size();i1++){
												JSONObject obj=ScreenResultslist.getJSONObject(i1);
												if(ModuleID.equals(obj.getString("ModuleID"))){
													ModuleIDsum=1;
												}
											}
											if(ModuleIDsum==0){
												System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--û��ModuleName������");
												System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
												listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--û��ModuleName������");
												listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
											}
											if(ModuleIDstate==0&&(z+1)==ScreenResultslist1.size()){
												System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--��ModuleName����"+ScreenResultslistn.get("ModuleName")+"��");
												System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ȱ��ModuleName����"+ScreenResultslistn.get("ModuleName")+"��");
												listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--��ModuleName����"+ScreenResultslistn.get("ModuleName")+"��");
												listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ȱ��ModuleName����"+ScreenResultslistn.get("ModuleName")+"��");
//											if(18==Integer.parseInt(ScreenResultslistn.get("ModuleID").toString())||4==Integer.parseInt(ScreenResultslistn.get("ModuleID").toString())){
//													System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--��ModuleName����"+ScreenResultslistn.get("ModuleName")+"��");
//													System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ȱ��ModuleName����"+ScreenResultslistn.get("ModuleName")+"��");
//													listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--��ModuleName����"+ScreenResultslistn.get("ModuleName")+"��");
//													listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ȱ��ModuleName����"+ScreenResultslistn.get("ModuleName")+"��");
//												}
											}
										}											
										
									}
//									if(state1==0){
//										System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults����ȷ�ڵ���ModuleName��"+ScreenResultslistn.get("ModuleName"));
//										System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResultsȱ�����ؼ���ModuleName");
//										listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults����ȷ�ڵ���ModuleName��"+ScreenResultslistn.get("ModuleName"));
//										listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResultsȱ�����ؼ���ModuleName");
//									}
								}
							}else{
								for(int k=0;k<ScreenResultslist1.size();k++){
									JSONObject ScreenResultslistn1=ScreenResultslist1.getJSONObject(k);//ajson����
//									int state1=0;
									int ModuleIDstate=0;
									int Warningstate=0;
									int OtherInfostate=0;
									int Warningsum=0;
									int ModuleIDsum=0;
									int Severitysum=0;
									int Severitystate=0;
									for(int z=0;z<ScreenResultslist.size();z++){
										JSONObject ScreenResultslistn=ScreenResultslist.getJSONObject(z);//bjson����
										if(ScreenResultslistn.get("ModuleID").equals(ScreenResultslistn1.get("ModuleID"))){
											ModuleIDstate=1;
											if(ScreenResultslistn.get("Severity").equals(ScreenResultslistn1.get("Severity"))){
												Severitystate=1;
												if(ScreenResultslistn.get("Warning").equals(ScreenResultslistn1.get("Warning"))){
//													state1=1;
													if(ScreenResultslistn.get("OtherInfo").equals(ScreenResultslistn1.get("OtherInfo"))){
														Warningstate=Warningstate-1;
														for(int o=0;o<ScreenResultslistn.size();o++){
															String name=ScreenResultslistn1.names().getString(o);
															if(!ScreenResultslistn.get(name).equals(ScreenResultslistn1.get(name))&&!"Warning".equals(ScreenResultslistn.get(name))){
																System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"������ȷ�ڵ���"+name+"��"+ScreenResultslistn.get(name));
																System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"���Ĵ���ڵ���"+name+"��"+ScreenResultslistn1.get(name));
																listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"������ȷ�ڵ���"+name+"��"+ScreenResultslistn.get(name));
																listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"���Ĵ���ڵ���"+name+"��"+ScreenResultslistn1.get(name));
															}
														}
													}else{
//														OtherInfostate=OtherInfostate+1;
														if(OtherInfostate==0){
															System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"������ȷ�ڵ���OtherInfo��"+ScreenResultslistn.get("OtherInfo"));
															System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"���Ĵ���ڵ���OtherInfo��"+ScreenResultslistn1.get("OtherInfo"));
															listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"������ȷ�ڵ���OtherInfo��"+ScreenResultslistn.get("OtherInfo"));
															listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"���Ĵ���ڵ���OtherInfo��"+ScreenResultslistn1.get("OtherInfo"));
														}
													}
												}else{
//													Warningstate=Warningstate+1;
													String Warning=ScreenResultslistn.getString("Warning");
													for(int i1=0;i1<ScreenResultslist1.size();i1++){
														JSONObject obj=ScreenResultslist1.getJSONObject(i1);
														if(Warning.equals(obj.getString("Warning"))){
															Warningsum=1;
														}
													}
													if(Warningsum==0){
														System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��Warning��"+ScreenResultslistn.get("Warning"));
														System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"��û��Warning��");
														listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��Warning��"+ScreenResultslistn.get("Warning"));
														listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"��û��Warning��");
													}
													if(Warningstate==0){
														System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"������ȷ�ڵ���Warning��"+ScreenResultslistn.get("Warning"));
														System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"����ȱ�ٽڵ���Warning��"+ScreenResultslistn1.get("Warning"));
														listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"������ȷ�ڵ���Warning��"+ScreenResultslistn.get("Warning"));
														listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"����ȱ�ٽڵ���Warning��"+ScreenResultslistn1.get("Warning"));
													}
												}
											}else{
												String Severity=ScreenResultslistn.getString("Severity");
												for(int i1=0;i1<ScreenResultslist1.size();i1++){
													JSONObject obj=ScreenResultslist1.getJSONObject(i1);
													if(Severity.equals(obj.getString("Severity"))){
														Severitysum=1;
													}
												}
												if(Severitysum==0){
													System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"��Severity��"+ScreenResultslistn.get("Severity"));
													System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��û��Severity��");
													listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"��Severity��"+ScreenResultslistn.get("Severity"));
													listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��û��Severity��");
												}
												if(Severitysum==0&&(z+1)==ScreenResultslist.size()){
													System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���Severity��"+ScreenResultslistn.get("Severity"));
													System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"����ȱ�ٽڵ���Severity��"+ScreenResultslistn1.get("Severity"));
													listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn.get("ModuleName")+"������ȷ�ڵ���Severity��"+ScreenResultslistn.get("Severity"));
													listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"����ȱ�ٽڵ���Severity��"+ScreenResultslistn1.get("Severity"));
												}
											}
											
										}else{
											String ModuleID=ScreenResultslistn1.getString("ModuleID");
											for(int i1=0;i1<ScreenResultslist.size();i1++){
												JSONObject obj=ScreenResultslist.getJSONObject(i1);
												if(ModuleID.equals(obj.getString("ModuleID"))){
													ModuleIDsum=1;
												}
											}
											if(ModuleIDsum==0){
												System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--û��ModuleName������");
												System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
												listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--û��ModuleName������");
												listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
											}
											if(ModuleIDstate==0&&(z+1)==ScreenResultslist.size()){
												System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--��ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
												System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ȱ��ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
												listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--��ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
												listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ȱ��ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
											}
//											if(18==Integer.parseInt(ScreenResultslistn1.get("ModuleID").toString())||4==Integer.parseInt(ScreenResultslistn1.get("ModuleID").toString())){
//												System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--��ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
//												System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ȱ��ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
//												listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults--��ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
//												listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults--ȱ��ModuleName����"+ScreenResultslistn1.get("ModuleName")+"��");
//											}
										}
									}
//									if(state1==0){
//										System.out.println("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults����ȷ�ڵ���ModuleName��");
//										System.out.println("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults�������ؼ���ModuleName��"+ScreenResultslistn1.get("ModuleName"));
//										listerr.add("���ԣ�DrugIndexΪ"+DrugIndex+",ScreenResults����ȷ�ڵ���ModuleName��");
//										listerr.add("��Ӧ��DrugIndexΪ"+DrugIndex+",ScreenResults�������ؼ���ModuleName"+ScreenResultslistn1.get("ModuleName"));
//									}
								}
							}
//						}
					}
				}
				if(state==0){
					System.out.println("���ԣ���ҩƷDrugIndex��"+ScreenResultDrugslistn1.get("DrugIndex"));
					System.out.println("��Ӧ�����ҩƷDrugIndex��"+ScreenResultDrugslistn1.get("DrugIndex"));
					listerr.add("���ԣ���ҩƷDrugIndex��"+ScreenResultDrugslistn1.get("DrugIndex"));
					listerr.add("��Ӧ�����ҩƷDrugIndex��"+ScreenResultDrugslistn1.get("DrugIndex"));
				}
			}
		}
		if(listerr.size()!=0){
			listerrsum=1;
		}
//		setListerr1(listerr);
	}
}
