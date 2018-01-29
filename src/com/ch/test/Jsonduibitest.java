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
			System.out.println("数据输入不完整");
			listerr.add("数据输入不完整");
//			setListerr1(listerr);
//			return "success";
		}
//		System.out.println(json);
//		System.out.println(json1);
		//将字符串转成JSON串,声明两个输入JSON
		JSONObject jsonin=JSONObject.fromObject(json);//正确的json
		JSONObject jsonin1=JSONObject.fromObject(json1);//需要验证的json
//		System.out.println(jsonin);
//		System.out.println(jsonin1);
		
		if(!jsonin.get("HighestSlcode").equals(jsonin1.get("HighestSlcode"))){
			System.out.println("断言：一级目录正确的节点是HighestSlcode："+jsonin.get("HighestSlcode"));
			System.out.println("响应：一级目录错误的节点是HighestSlcode："+jsonin1.get("HighestSlcode"));
			listerr.add("断言：一级目录正确的节点是：HighestSlcode："+jsonin.get("HighestSlcode"));
			listerr.add("响应：一级目录错误的节点是：HighestSlcode："+jsonin1.get("HighestSlcode"));
		}
		if(!jsonin.get("InUseModules").equals(jsonin1.get("InUseModules"))){
			System.out.println("断言：一级目录正确的节点是InUseModules："+jsonin.get("InUseModules"));
			System.out.println("响应：一级目录错误的节点是InUseModules："+jsonin1.get("InUseModules"));
			listerr.add("断言：一级目录正确的节点是InUseModules："+jsonin.get("InUseModules"));
			listerr.add("响应：一级目录错误的节点是InUseModules："+jsonin1.get("InUseModules"));
		}
		
		JSONArray ScreenResultDrugslist=jsonin.getJSONArray("ScreenResultDrugs");
		JSONArray ScreenResultDrugslist1=jsonin1.getJSONArray("ScreenResultDrugs");
		if(ScreenResultDrugslist.size()>=ScreenResultDrugslist1.size()){
			for(int i=0;i<ScreenResultDrugslist.size();i++){
				JSONObject ScreenResultDrugslistn=ScreenResultDrugslist.getJSONObject(i);//Ajson第一个
				int state=0;
				for(int j=0;j<ScreenResultDrugslist1.size();j++){
					JSONObject ScreenResultDrugslistn1=ScreenResultDrugslist1.getJSONObject(j);//Bjson第一个
					if(ScreenResultDrugslistn1.get("DrugIndex").equals(ScreenResultDrugslistn.get("DrugIndex"))){
						String DrugIndex=ScreenResultDrugslistn.get("DrugIndex").toString();
						state=1;
						if(!ScreenResultDrugslistn1.get("DrugName").equals(ScreenResultDrugslistn.get("DrugName"))){
							System.out.println("断言：DrugIndex为"+DrugIndex+"的正确节点是DrugName："+ScreenResultDrugslistn.get("DrugName"));
							System.out.println("响应：DrugIndex为"+DrugIndex+"的错误节点是DrugName："+ScreenResultDrugslistn1.get("DrugName"));
							listerr.add("断言：DrugIndex为"+DrugIndex+"的正确节点是DrugName："+ScreenResultDrugslistn.get("DrugName"));
							listerr.add("响应：DrugIndex为"+DrugIndex+"的错误节点是DrugName："+ScreenResultDrugslistn1.get("DrugName"));
						}
						if(!ScreenResultDrugslistn1.get("Slcode").equals(ScreenResultDrugslistn.get("Slcode"))){
							System.out.println("断言：DrugIndex为"+DrugIndex+"的正确节点是Slcode："+ScreenResultDrugslistn.get("Slcode"));
							System.out.println("响应：DrugIndex为"+DrugIndex+"的错误节点是Slcode："+ScreenResultDrugslistn1.get("Slcode"));
							listerr.add("断言：DrugIndex为"+DrugIndex+"的正确节点是Slcode："+ScreenResultDrugslistn.get("Slcode"));
							listerr.add("响应：DrugIndex为"+DrugIndex+"的错误节点是Slcode："+ScreenResultDrugslistn1.get("Slcode"));
						}
						if(!ScreenResultDrugslistn1.get("MenuLabel").equals(ScreenResultDrugslistn.get("MenuLabel"))){
							System.out.println("断言：DrugIndex为"+DrugIndex+"的正确节点是MenuLabel："+ScreenResultDrugslistn.get("MenuLabel"));
							System.out.println("响应：DrugIndex为"+DrugIndex+"的错误节点是MenuLabel："+ScreenResultDrugslistn1.get("MenuLabel"));
							listerr.add("断言：DrugIndex为"+DrugIndex+"的正确节点是MenuLabel："+ScreenResultDrugslistn.get("MenuLabel"));
							listerr.add("响应：DrugIndex为"+DrugIndex+"的错误节点是MenuLabel："+ScreenResultDrugslistn1.get("MenuLabel"));
						}
						
						//ScreenResults节点的判断
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
							System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults总数："+ScreenResultslist.size()+"条,"+list1);
							System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults总数："+ScreenResultslist1.size()+"条存在问题,"+list2);
							listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults总数："+ScreenResultslist.size()+"条,"+list1);
							listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults总数："+ScreenResultslist1.size()+"条存在问题,"+list2);
						}
//						if(ScreenResultslist.size()>ScreenResultslist1.size()&&ScreenResultslist1.size()==0){
//							System.out.println("断言：DrugIndex为"+DrugIndex+"的ScreenResults节点有数据");
//							System.out.println("响应：DrugIndex为"+DrugIndex+"的ScreenResults节点缺少内容");
//							listerr.add("断言：DrugIndex为"+DrugIndex+"的ScreenResults节点有数据");
//							listerr.add("响应：DrugIndex为"+DrugIndex+"的ScreenResults节点缺少内容");
//						}else{
							if(ScreenResultslist.size()>=ScreenResultslist1.size()&&ScreenResultslist1.size()!=0){
								for(int k=0;k<ScreenResultslist.size();k++){
									JSONObject ScreenResultslistn=ScreenResultslist.getJSONObject(k);//ajson数据
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
										JSONObject ScreenResultslistn1=ScreenResultslist1.getJSONObject(z);//bjson数据
//										System.out.println(ScreenResultslistn.get("Slcode")+"："+ScreenResultslistn1.get("Slcode"));\
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
																System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是"+name+"："+ScreenResultslistn.get(name));
																System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的错误节点是"+name+"："+ScreenResultslistn1.get(name));
																listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是"+name+"："+ScreenResultslistn.get(name));
																listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的错误节点是"+name+"："+ScreenResultslistn1.get(name));
															}
														}
													}else{
//														OtherInfostate=OtherInfostate+1;
														if(OtherInfostate==0&&(z+1)==ScreenResultslist1.size()){
															System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是OtherInfo："+ScreenResultslistn.get("OtherInfo"));
															System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的错误节点是OtherInfo："+ScreenResultslistn1.get("OtherInfo"));
															listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是OtherInfo："+ScreenResultslistn.get("OtherInfo"));
															listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的错误节点是OtherInfo："+ScreenResultslistn1.get("OtherInfo"));
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
//														System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》没有Warning：");
//														System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》Warning："+ScreenResultslistn1.get("Warning"));
//														listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》没有Warning：");
//														listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》Warning："+ScreenResultslistn1.get("Warning"));
//													}
													if(Warningstate==0&&(z+1)==ScreenResultslist1.size()){
														System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是Warning："+ScreenResultslistn.get("Warning"));
														System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的缺少节点是Warning："+ScreenResultslistn1.get("Warning"));
														listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是Warning："+ScreenResultslistn.get("Warning"));
														listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的缺少节点是Warning："+ScreenResultslistn1.get("Warning"));
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
													System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》没有Severity：");
													System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》Severity："+ScreenResultslistn1.get("Severity"));
													listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》没有Severity：");
													listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》Severity："+ScreenResultslistn1.get("Severity"));
												}
												if(Severitystate==0&&(z+1)==ScreenResultslist1.size()){
													System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是Severity："+ScreenResultslistn.get("Severity"));
													System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的缺少节点是Severity："+ScreenResultslistn1.get("Severity"));
													listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是Severity："+ScreenResultslistn.get("Severity"));
													listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的缺少节点是Severity："+ScreenResultslistn1.get("Severity"));
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
												System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--没有ModuleName：《》");
												System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
												listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--没有ModuleName：《》");
												listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
											}
											if((ModuleIDstate==0&&(z+1)==ScreenResultslist1.size())){
												System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--有ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》");
												System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--缺少ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》");
												listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--有ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》");
												listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--缺少ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》");
											}
//											if(ModuleIDstate==0&&(z+1)==ScreenResultslist1.size()&&(18==Integer.parseInt(ScreenResultslistn.get("ModuleID").toString())||4==Integer.parseInt(ScreenResultslistn.get("ModuleID").toString()))){
//												System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--有ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》");
//												System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--缺少ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》");
//												listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--有ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》");
//												listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--缺少ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》");
//											}
										}
									}
//									if(state1==0){
//										System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults正确的节点是ModuleName："+ScreenResultslistn.get("ModuleName"));
//										System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults缺少《"+ScreenResultslistn.get("ModuleName")+"》的结果");
//										listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults正确的节点是ModuleName："+ScreenResultslistn.get("ModuleName"));
//										listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults《"+ScreenResultslistn.get("ModuleName")+"》的结果");
//									}
								}
							}else{
								for(int k=0;k<ScreenResultslist1.size();k++){
									JSONObject ScreenResultslistn1=ScreenResultslist1.getJSONObject(k);//ajson数据
//									int state1=0;
									int ModuleIDstate=0;
									int Warningstate=0;
									int OtherInfostate=0;
									int Warningsum=0;
									int ModuleIDsum=0;
									int Severitysum=0;
									int Severitystate=0;
									for(int z=0;z<ScreenResultslist.size();z++){
										JSONObject ScreenResultslistn=ScreenResultslist.getJSONObject(z);//bjson数据
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
																System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是："+ScreenResultslistn.get(name));
																System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的错误节点是："+ScreenResultslistn1.get(name));
																listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是："+ScreenResultslistn.get(name));
																listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的错误节点是："+ScreenResultslistn1.get(name));
															}
														}
													}else{
//														OtherInfostate=OtherInfostate+1;
														if(OtherInfostate==0){
															System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是OtherInfo："+ScreenResultslistn.get("OtherInfo"));
															System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的错误节点是OtherInfo："+ScreenResultslistn1.get("OtherInfo"));
															listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是OtherInfo："+ScreenResultslistn.get("OtherInfo"));
															listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的错误节点是OtherInfo："+ScreenResultslistn1.get("OtherInfo"));
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
														System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》Warning："+ScreenResultslistn.get("Warning"));
														System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》没有Warning：");
														listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》Warning："+ScreenResultslistn.get("Warning"));
														listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》没有Warning：");
													}
													if(Warningstate==0){
														System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是Warning："+ScreenResultslistn.get("Warning"));
														System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的缺少节点是Warning："+ScreenResultslistn1.get("Warning"));
														listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是Warning："+ScreenResultslistn.get("Warning"));
														listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的缺少节点是Warning："+ScreenResultslistn1.get("Warning"));
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
													System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》Severity："+ScreenResultslistn.get("Severity"));
													System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》没有Severity：");
													listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》Severity："+ScreenResultslistn.get("Severity"));
													listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》没有Severity：");
												}
												if(Severitystate==0&&(z+1)==ScreenResultslist1.size()){
													System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是Severity："+ScreenResultslistn.get("Severity"));
													System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的缺少节点是Severity："+ScreenResultslistn1.get("Severity"));
													listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是Severity："+ScreenResultslistn.get("Severity"));
													listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的缺少节点是Severity："+ScreenResultslistn1.get("Severity"));
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
												System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》");
												System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--没有ModuleName：《》");
												listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》");
												listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--没有ModuleName：《》");
											}
											if(ModuleIDstate==0&&(z+1)==ScreenResultslist.size()){
												System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--有ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
												System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--缺少ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
												listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--有ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
												listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--缺少ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
											}
//											if(18==Integer.parseInt(ScreenResultslistn1.get("ModuleID").toString())||4==Integer.parseInt(ScreenResultslistn1.get("ModuleID").toString())){
//												System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--有ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
//												System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--缺少ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
//												listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--有ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
//												listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--缺少ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
//											}
										}
									}
//									if(state1==0){
//										System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults的正确节点是ModuleName：空");
//										System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults多余的节点是ModuleName："+ScreenResultslistn1.get("ModuleName"));
//										listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults的正确节点是ModuleName：空");
//										listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults多余的节点是ModuleName："+ScreenResultslistn1.get("ModuleName"));
//									}
								}
							}
//						}
					}
				}
				if(state==0){
					System.out.println("断言：有药品DrugIndex："+ScreenResultDrugslistn.get("DrugIndex"));
					System.out.println("响应：缺少药品DrugIndex："+ScreenResultDrugslistn.get("DrugIndex"));
					listerr.add("断言：有药品DrugIndex："+ScreenResultDrugslistn.get("DrugIndex"));
					listerr.add("响应：缺少药品DrugIndex："+ScreenResultDrugslistn.get("DrugIndex"));
				}
			}
		}else{
			for(int i=0;i<ScreenResultDrugslist1.size();i++){
				JSONObject ScreenResultDrugslistn1=ScreenResultDrugslist1.getJSONObject(i);//Ajson第一个
				int state=0;
				for(int j=0;j<ScreenResultDrugslist.size();j++){
					JSONObject ScreenResultDrugslistn=ScreenResultDrugslist.getJSONObject(j);//Bjson第一个
					if(ScreenResultDrugslistn1.get("DrugIndex").equals(ScreenResultDrugslistn.get("DrugIndex"))){
						String DrugIndex=ScreenResultDrugslistn.get("DrugIndex").toString();
						state=1;
						if(!ScreenResultDrugslistn1.get("DrugName").equals(ScreenResultDrugslistn.get("DrugName"))){
							System.out.println("断言：DrugIndex为"+DrugIndex+"的正确节点是DrugName："+ScreenResultDrugslistn.get("DrugName"));
							System.out.println("响应：DrugIndex为"+DrugIndex+"的错误节点是DrugName："+ScreenResultDrugslistn1.get("DrugName"));
							listerr.add("断言：DrugIndex为"+DrugIndex+"的正确节点是DrugName："+ScreenResultDrugslistn.get("DrugName"));
							listerr.add("响应：DrugIndex为"+DrugIndex+"的错误节点是DrugName："+ScreenResultDrugslistn1.get("DrugName"));
						}
						if(!ScreenResultDrugslistn1.get("Slcode").equals(ScreenResultDrugslistn.get("Slcode"))){
							System.out.println("断言：DrugIndex为"+DrugIndex+"的正确节点是Slcode："+ScreenResultDrugslistn.get("Slcode"));
							System.out.println("响应：DrugIndex为"+DrugIndex+"的错误节点是Slcode："+ScreenResultDrugslistn.get("Slcode"));
							listerr.add("断言：DrugIndex为"+DrugIndex+"的正确节点是Slcode："+ScreenResultDrugslistn.get("Slcode"));
							listerr.add("响应：DrugIndex为"+DrugIndex+"的错误节点是Slcode："+ScreenResultDrugslistn.get("Slcode"));
						}
						if(!ScreenResultDrugslistn1.get("MenuLabel").equals(ScreenResultDrugslistn.get("MenuLabel"))){
							System.out.println("断言：DrugIndex为"+DrugIndex+"的正确节点是MenuLabel："+ScreenResultDrugslistn.get("MenuLabel"));
							System.out.println("响应：DrugIndex为"+DrugIndex+"的错误节点是MenuLabel："+ScreenResultDrugslistn1.get("MenuLabel"));
							listerr.add("断言：DrugIndex为"+DrugIndex+"的正确节点是MenuLabel："+ScreenResultDrugslistn.get("MenuLabel"));
							listerr.add("响应：DrugIndex为"+DrugIndex+"的错误节点是MenuLabel："+ScreenResultDrugslistn1.get("MenuLabel"));
						}
						//ScreenResults节点的判断
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
							System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults总数："+ScreenResultslist.size()+"条,"+list1);
							System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults总数："+ScreenResultslist1.size()+"条存在问题,"+list2);
							listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults总数："+ScreenResultslist.size()+"条,"+list1);
							listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults总数："+ScreenResultslist1.size()+"条存在问题,"+list2);
						}
//						if(ScreenResultslist.size()>ScreenResultslist1.size()&&ScreenResultslist1.size()==0){
//							System.out.println("断言：DrugIndex为"+DrugIndex+"的ScreenResults节点有数据");
//							System.out.println("响应：DrugIndex为"+DrugIndex+"的ScreenResults节点缺少内容");
//							listerr.add("断言：DrugIndex为"+DrugIndex+"的ScreenResults节点有数据");
//							listerr.add("响应：DrugIndex为"+DrugIndex+"的ScreenResults节点缺少内容");
//						}else{
							if(ScreenResultslist.size()>=ScreenResultslist1.size()&&ScreenResultslist1.size()!=0){
								for(int k=0;k<ScreenResultslist.size();k++){
									JSONObject ScreenResultslistn=ScreenResultslist.getJSONObject(k);//ajson数据
//									int state1=0;
									int ModuleIDstate=0;
									int Warningstate=0;
									int OtherInfostate=0;
									int Warningsum=0;
									int ModuleIDsum=0;
									int Severitysum=0;
									int Severitystate=0;
									for(int z=0;z<ScreenResultslist1.size();z++){
										JSONObject ScreenResultslistn1=ScreenResultslist1.getJSONObject(z);//bjson数据
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
																System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是"+name+"："+ScreenResultslistn.get(name));
																System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的错误节点是"+name+"："+ScreenResultslistn1.get(name));
																listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是"+name+"："+ScreenResultslistn.get(name));
																listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的错误节点是"+name+"："+ScreenResultslistn1.get(name));
															}
														}
													}else{
//														OtherInfostate=OtherInfostate+1;
														if(OtherInfostate==0){
															System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是OtherInfo："+ScreenResultslistn.get("OtherInfo"));
															System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的错误节点是OtherInfo："+ScreenResultslistn1.get("OtherInfo"));
															listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是OtherInfo："+ScreenResultslistn.get("OtherInfo"));
															listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的错误节点是OtherInfo："+ScreenResultslistn1.get("OtherInfo"));
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
														System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》没有Warning：");
														System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》Warning："+ScreenResultslistn1.get("Warning"));
														listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》没有Warning：");
														listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》多余Warning："+ScreenResultslistn1.get("Warning"));
													}
													if(Warningstate==0){
														System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是Warning："+ScreenResultslistn.get("Warning"));
														System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的缺少节点是Warning："+ScreenResultslistn1.get("Warning"));
														listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是Warning："+ScreenResultslistn.get("Warning"));
														listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的缺少节点是Warning："+ScreenResultslistn1.get("Warning"));
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
													System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》没有Severity：");
													System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》Severity："+ScreenResultslistn1.get("Severity"));
													listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》没有Severity：");
													listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》多余Severity："+ScreenResultslistn1.get("Severity"));
												}
												if(Severitystate==0&&(z+1)==ScreenResultslist1.size()){
													System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是Severity："+ScreenResultslistn.get("Severity"));
													System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的缺少节点是Severity："+ScreenResultslistn1.get("Severity"));
													listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是Severity："+ScreenResultslistn.get("Severity"));
													listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的缺少节点是Severity："+ScreenResultslistn1.get("Severity"));
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
												System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--没有ModuleName：《》");
												System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
												listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--没有ModuleName：《》");
												listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
											}
											if(ModuleIDstate==0&&(z+1)==ScreenResultslist1.size()){
												System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--有ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》");
												System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--缺少ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》");
												listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--有ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》");
												listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--缺少ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》");
//											if(18==Integer.parseInt(ScreenResultslistn.get("ModuleID").toString())||4==Integer.parseInt(ScreenResultslistn.get("ModuleID").toString())){
//													System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--有ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》");
//													System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--缺少ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》");
//													listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--有ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》");
//													listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--缺少ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》");
//												}
											}
										}											
										
									}
//									if(state1==0){
//										System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults的正确节点是ModuleName："+ScreenResultslistn.get("ModuleName"));
//										System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults缺少严重级别ModuleName");
//										listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults的正确节点是ModuleName："+ScreenResultslistn.get("ModuleName"));
//										listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults缺少严重级别ModuleName");
//									}
								}
							}else{
								for(int k=0;k<ScreenResultslist1.size();k++){
									JSONObject ScreenResultslistn1=ScreenResultslist1.getJSONObject(k);//ajson数据
//									int state1=0;
									int ModuleIDstate=0;
									int Warningstate=0;
									int OtherInfostate=0;
									int Warningsum=0;
									int ModuleIDsum=0;
									int Severitysum=0;
									int Severitystate=0;
									for(int z=0;z<ScreenResultslist.size();z++){
										JSONObject ScreenResultslistn=ScreenResultslist.getJSONObject(z);//bjson数据
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
																System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的正确节点是"+name+"："+ScreenResultslistn.get(name));
																System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的错误节点是"+name+"："+ScreenResultslistn1.get(name));
																listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的正确节点是"+name+"："+ScreenResultslistn.get(name));
																listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的错误节点是"+name+"："+ScreenResultslistn1.get(name));
															}
														}
													}else{
//														OtherInfostate=OtherInfostate+1;
														if(OtherInfostate==0){
															System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的正确节点是OtherInfo："+ScreenResultslistn.get("OtherInfo"));
															System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的错误节点是OtherInfo："+ScreenResultslistn1.get("OtherInfo"));
															listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的正确节点是OtherInfo："+ScreenResultslistn.get("OtherInfo"));
															listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的错误节点是OtherInfo："+ScreenResultslistn1.get("OtherInfo"));
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
														System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》Warning："+ScreenResultslistn.get("Warning"));
														System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》没有Warning：");
														listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》Warning："+ScreenResultslistn.get("Warning"));
														listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》没有Warning：");
													}
													if(Warningstate==0){
														System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的正确节点是Warning："+ScreenResultslistn.get("Warning"));
														System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的缺少节点是Warning："+ScreenResultslistn1.get("Warning"));
														listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的正确节点是Warning："+ScreenResultslistn.get("Warning"));
														listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的缺少节点是Warning："+ScreenResultslistn1.get("Warning"));
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
													System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》Severity："+ScreenResultslistn.get("Severity"));
													System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》没有Severity：");
													listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》Severity："+ScreenResultslistn.get("Severity"));
													listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》没有Severity：");
												}
												if(Severitysum==0&&(z+1)==ScreenResultslist.size()){
													System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是Severity："+ScreenResultslistn.get("Severity"));
													System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的缺少节点是Severity："+ScreenResultslistn1.get("Severity"));
													listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn.get("ModuleName")+"》的正确节点是Severity："+ScreenResultslistn.get("Severity"));
													listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》的缺少节点是Severity："+ScreenResultslistn1.get("Severity"));
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
												System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--没有ModuleName：《》");
												System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
												listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--没有ModuleName：《》");
												listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
											}
											if(ModuleIDstate==0&&(z+1)==ScreenResultslist.size()){
												System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--有ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
												System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--缺少ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
												listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--有ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
												listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--缺少ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
											}
//											if(18==Integer.parseInt(ScreenResultslistn1.get("ModuleID").toString())||4==Integer.parseInt(ScreenResultslistn1.get("ModuleID").toString())){
//												System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults--有ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
//												System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults--缺少ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
//												listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults--有ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
//												listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults--缺少ModuleName：《"+ScreenResultslistn1.get("ModuleName")+"》");
//											}
										}
									}
//									if(state1==0){
//										System.out.println("断言：DrugIndex为"+DrugIndex+",ScreenResults的正确节点是ModuleName：");
//										System.out.println("响应：DrugIndex为"+DrugIndex+",ScreenResults多余严重级别ModuleName："+ScreenResultslistn1.get("ModuleName"));
//										listerr.add("断言：DrugIndex为"+DrugIndex+",ScreenResults的正确节点是ModuleName：");
//										listerr.add("响应：DrugIndex为"+DrugIndex+",ScreenResults多余严重级别ModuleName"+ScreenResultslistn1.get("ModuleName"));
//									}
								}
							}
//						}
					}
				}
				if(state==0){
					System.out.println("断言：无药品DrugIndex："+ScreenResultDrugslistn1.get("DrugIndex"));
					System.out.println("响应：多出药品DrugIndex："+ScreenResultDrugslistn1.get("DrugIndex"));
					listerr.add("断言：无药品DrugIndex："+ScreenResultDrugslistn1.get("DrugIndex"));
					listerr.add("响应：多出药品DrugIndex："+ScreenResultDrugslistn1.get("DrugIndex"));
				}
			}
		}
		if(listerr.size()!=0){
			listerrsum=1;
		}
//		setListerr1(listerr);
	}
}
