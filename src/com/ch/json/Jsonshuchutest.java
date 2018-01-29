package com.ch.json;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ch.clientIP.IpUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//pass审查返回的json串和java-pass返回的json串对比

public class Jsonshuchutest {
	private String json=null;
	private String json1=null;
	private List listerr1;
	private int listerrsum=0;
	private int moduleidstr=-1;
	private int duibistate=1;
	private String ip;
	
	public int getDuibistate() {
		return duibistate;
	}

	public void setDuibistate(int duibistate) {
		this.duibistate = duibistate;
	}

	public int getModuleidstr() {
		return moduleidstr;
	}

	public void setModuleidstr(int moduleidstr) {
		this.moduleidstr = moduleidstr;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getJson1() {
		return json1;
	}

	public void setJson1(String json1) {
		this.json1 = json1;
	}

	public List getListerr1() {
		return listerr1;
	}

	public void setListerr1(List listerr) {
		this.listerr1 = listerr;
	}

	public int getListerrsum() {
		return listerrsum;
	}

	public void setListerrsum(int listerrsum) {
		this.listerrsum = listerrsum;
	}

	public String execute(){
		ip=ServletActionContext.getRequest().getRemoteAddr();//获取客户端IP
		List listerr=new ArrayList();
		
		//输入串为空退出
		if("".equals(json)||"".equals(json1)){
			listerrsum=1;
			System.out.println("审查数据输入不完整");
			listerr.add("审查数据输入不完整");
			setListerr1(listerr);
			Date data=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("IP:"+ip+"，"+sf.format(data));
			return "success";
		}
		
		try{//防止错误的json导致异常
			JSONObject jsonin=JSONObject.fromObject(json);//正确的json
			JSONObject jsonin1=JSONObject.fromObject(json1);//需要验证的json
//			System.out.println(jsonin);
//			System.out.println(jsonin1);
			
			//对比HighestSlcode节点
			if(!jsonin.get("HighestSlcode").equals(jsonin1.get("HighestSlcode"))&&moduleidstr==-1){
				System.out.println("断言：一级目录正确的节点是HighestSlcode："+jsonin.get("HighestSlcode"));
				System.out.println("响应：一级目录错误的节点是HighestSlcode："+jsonin1.get("HighestSlcode"));
				listerr.add("断言：一级目录正确的节点是：HighestSlcode："+jsonin.get("HighestSlcode"));
				listerr.add("响应：一级目录错误的节点是：HighestSlcode："+jsonin1.get("HighestSlcode"));
//				return "success";
			}
			
			//对比InUseModules节点
			if(!jsonin.get("InUseModules").equals(jsonin1.get("InUseModules"))){
				System.out.println("断言：一级目录正确的节点是InUseModules："+jsonin.get("InUseModules"));
				System.out.println("响应：一级目录错误的节点是InUseModules："+jsonin1.get("InUseModules"));
				listerr.add("断言：一级目录正确的节点是InUseModules："+jsonin.get("InUseModules"));
				listerr.add("响应：一级目录错误的节点是InUseModules："+jsonin1.get("InUseModules"));
//				return "success";
			}
			
			//对比ScreenResultDrugs节点，如果药品数量不同则退出
			JSONArray ScreenResultDrugs=jsonin.getJSONArray("ScreenResultDrugs");
			JSONArray ScreenResultDrugs1=jsonin1.getJSONArray("ScreenResultDrugs");
			if(ScreenResultDrugs.size()!=ScreenResultDrugs1.size()){
				List drugnames=new ArrayList();
				for(int namesum=0;namesum<ScreenResultDrugs.size();namesum++){
					JSONObject obj=ScreenResultDrugs.getJSONObject(namesum);
					String name=obj.getString("DrugIndex");
					drugnames.add(name);
				}
				List drugnames1=new ArrayList();
				for(int namesum1=0;namesum1<ScreenResultDrugs1.size();namesum1++){
					JSONObject obj1=ScreenResultDrugs1.getJSONObject(namesum1);
					String name1=obj1.getString("DrugIndex");
					drugnames1.add(name1);
				}
				System.out.println("断言：一级目录正确的节点是ScreenResultDrugs："+ScreenResultDrugs.size()+"条,药品序号是："+drugnames);
				System.out.println("响应：一级目录错误的节点是ScreenResultDrugs："+ScreenResultDrugs1.size()+"条,药品序号是："+drugnames1);
				listerr.add("断言：一级目录正确的节点是ScreenResultDrugs："+ScreenResultDrugs.size()+"条,药品序号是："+drugnames);
				listerr.add("响应：一级目录错误的节点是ScreenResultDrugs："+ScreenResultDrugs1.size()+"条,药品序号是："+drugnames1);
				if(listerr.size()!=0){
					listerrsum=1;
				}
				setListerr1(listerr);
				Date data=new Date();
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("IP:"+ip+"，"+sf.format(data));
				return "success";
			}
			
			//A串循环取ScreenResultDrugs节点的药品
			for(int i=0;i<ScreenResultDrugs.size();i++){
				JSONObject ScreenResultDrugsn=ScreenResultDrugs.getJSONObject(i);//Ajson第一个ScreenResultDrugs
				
				JSONArray ScreenResultsdan=ScreenResultDrugsn.getJSONArray("ScreenResults");//取出A串药品的所有审查结果
//				int Slcodesum=100;
//				for(int i1=0;i1<ScreenResultsdan.size();i1++){//计算同模块ScreenResults中最小的Slcode
//					JSONObject obj=ScreenResultsdan.getJSONObject(i1);
//					if(obj.getInt("Slcode")<Slcodesum&&(moduleidstr==obj.getInt("ModuleID"))){
//						Slcodesum=obj.getInt("Slcode");
//					}
//				}
				
				int DrugIndexstate=0;//初始化状态，默认B串中的药品未找到
				//B串循环取ScreenResultDrugs节点的药品
				for(int j=0;j<ScreenResultDrugs1.size();j++){
					JSONObject ScreenResultDrugsn1=ScreenResultDrugs1.getJSONObject(j);//Bjson第一个ScreenResultDrugs
					if(ScreenResultDrugsn.get("DrugIndex").equals(ScreenResultDrugsn1.get("DrugIndex"))){
						DrugIndexstate=1;
						
						//对比DrugName节点
						if(!ScreenResultDrugsn.get("DrugName").equals(ScreenResultDrugsn1.get("DrugName"))){
							System.out.println("断言：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--DrugName："+ScreenResultDrugsn.get("DrugName"));
							System.out.println("响应：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--DrugName："+ScreenResultDrugsn1.get("DrugName"));
							listerr.add("断言：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--DrugName："+ScreenResultDrugsn.get("DrugName"));
							listerr.add("响应：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--DrugName："+ScreenResultDrugsn1.get("DrugName"));
						}
						
						JSONArray ScreenResultsdan1=ScreenResultDrugsn1.getJSONArray("ScreenResults");//取出B串药品的所有审查结果
//							int Slcodesum1=100;
//							for(int i1=0;i1<ScreenResultsdan1.size();i1++){//计算同模块ScreenResults中最小的Slcode
//								JSONObject obj1=ScreenResultsdan1.getJSONObject(i1);
//								if(obj1.getInt("Slcode")<Slcodesum1&&(moduleidstr==obj1.getInt("ModuleID"))){
//									Slcodesum1=obj1.getInt("Slcode");
//								}
//							}
//							if(moduleidstr!=-1){//1
//								if(Slcodesum!=Slcodesum1&&Slcodesum!=100&&Slcodesum1!=100){//使用AND的关系是为了Slcode不同时，避免和后面重复提示
//									System.out.println("断言：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode："+Slcodesum);
//									System.out.println("响应：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode："+Slcodesum1);
//									listerr.add("断言：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode："+Slcodesum);
//									listerr.add("响应：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode："+Slcodesum1);
//								}
//								if(!ScreenResultDrugsn.get("Slcode").equals(ScreenResultDrugsn1.get("Slcode"))&&ScreenResultsdan.size()==0&&ScreenResultsdan1.size()==0&&Slcodesum==100&&Slcodesum1==100){
//									System.out.println("断言：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode："+ScreenResultDrugsn.get("Slcode"));
//									System.out.println("响应：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode："+ScreenResultDrugsn1.get("Slcode"));
//									listerr.add("断言：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode："+ScreenResultDrugsn.get("Slcode"));
//									listerr.add("响应：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode："+ScreenResultDrugsn1.get("Slcode"));
//								}
//							}else{
//								if(!ScreenResultDrugsn.get("Slcode").equals(ScreenResultDrugsn1.get("Slcode"))){
//									System.out.println("断言：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode："+ScreenResultDrugsn.get("Slcode"));
//									System.out.println("响应：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode："+ScreenResultDrugsn1.get("Slcode"));
//									listerr.add("断言：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode："+ScreenResultDrugsn.get("Slcode"));
//									listerr.add("响应：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode："+ScreenResultDrugsn1.get("Slcode"));
//								}
//							}
						
						//对比Slcode节点
						if(moduleidstr==-1||(ScreenResultsdan.size()==0&&ScreenResultsdan1.size()==0)){//所有审查结果为空的时候才对比DrugIndex下的Slcode
							if(!ScreenResultDrugsn.get("Slcode").equals(ScreenResultDrugsn1.get("Slcode"))){
								System.out.println("断言：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode："+ScreenResultDrugsn.get("Slcode"));
								System.out.println("响应：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode："+ScreenResultDrugsn1.get("Slcode"));
								listerr.add("断言：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode："+ScreenResultDrugsn.get("Slcode"));
								listerr.add("响应：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode："+ScreenResultDrugsn1.get("Slcode"));
							}
						}
						
						//对比MenuLabel节点
						if(!ScreenResultDrugsn.get("MenuLabel").equals(ScreenResultDrugsn1.get("MenuLabel"))){
							System.out.println("断言：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--MenuLabel："+ScreenResultDrugsn.get("MenuLabel"));
							System.out.println("响应：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--MenuLabel："+ScreenResultDrugsn1.get("MenuLabel"));
							listerr.add("断言：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--MenuLabel："+ScreenResultDrugsn.get("MenuLabel"));
							listerr.add("响应：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--MenuLabel："+ScreenResultDrugsn1.get("MenuLabel"));
						}
						
						//对比AB串ScreenResults节点的所有审查模块总数是否相同
						JSONArray ScreenResults=ScreenResultDrugsn.getJSONArray("ScreenResults");
						JSONArray ScreenResults1=ScreenResultDrugsn1.getJSONArray("ScreenResults");
						
						//计算A串审查结果总数
						List drugnames=new ArrayList();						
						for(int namesum=0;namesum<ScreenResults.size();namesum++){
							JSONObject obj=ScreenResults.getJSONObject(namesum);
							String name=null;
							if(moduleidstr==Integer.parseInt(obj.get("ModuleID").toString())||moduleidstr==-1){
								name=obj.getString("ModuleID")+":"+obj.getString("ModuleName");
								drugnames.add(name);
							}
						}
						//计算B串审查结果总数
						List drugnames1=new ArrayList();
						for(int namesum1=0;namesum1<ScreenResults1.size();namesum1++){
							JSONObject obj1=ScreenResults1.getJSONObject(namesum1);
							String name1=null;
							if(moduleidstr==obj1.getInt("ModuleID")||moduleidstr==-1){
								name1=obj1.getString("ModuleID")+":"+obj1.getString("ModuleName");
								drugnames1.add(name1);
							}
						}
						//对比AB串审查结果总数
						if(drugnames.size()!=drugnames1.size()){
							System.out.println("断言：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--ScreenResults："+drugnames.size()+"条,模块ID是："+drugnames);
							System.out.println("响应：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--ScreenResults："+drugnames1.size()+"条,模块ID是："+drugnames1);
							listerr.add("断言：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--ScreenResults："+drugnames.size()+"条,模块ID是："+drugnames);
							listerr.add("响应：DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+"--ScreenResults："+drugnames1.size()+"条,模块ID是："+drugnames1);
							if(drugnames.size()==0||drugnames1.size()==0||moduleidstr==-1){
//									if(listerr.size()!=0){
//										listerrsum=1;
//									}
//									setListerr1(listerr);
//									Date data=new Date();
//									SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//									System.out.println("IP:"+ip+"，"+sf.format(data));
								break;//如果AB串的审查结果为空，则不再进行审查节点对比
							}
						}
						
						//取B串审查结果节点
						List klist=new ArrayList();//记录Bjson已经对比成功后的位置
						for(int z=0;z<ScreenResults.size();z++){
							JSONObject ScreenResult=ScreenResults.getJSONObject(z);//Ajson第一个ScreenResults
							
							//全部或者指定的模块对比ModuleID节点
							if(moduleidstr==Integer.parseInt(ScreenResult.get("ModuleID").toString())||moduleidstr==-1){
								int sum=0;//相同程度匹配数初始值为0
								String err=null;
								String err1=null;
								int ksum=0;//B串起始位置为0
								for(int k=0;k<ScreenResults1.size();k++){
									//查询B串是否已经匹配过
									int kstate=0;//默认Bjson未对比过
									for(int k1=0;k1<klist.size();k1++){
										if(k==Integer.parseInt(klist.get(k1).toString())){
											kstate=1;//Bjson已对比过
										}
									}
									
									//B串未对比过
									if(kstate==0){
										int sum1=0;
										JSONObject ScreenResult1=ScreenResults1.getJSONObject(k);//Bjson第一个ScreenResults
										
										//全部或者指定的模块对比ModuleID节点
										if(moduleidstr==Integer.parseInt(ScreenResult.get("ModuleID").toString())||moduleidstr==-1){
											
											//确认是否相同模块结果
											if(ScreenResult.get("ModuleID").equals(ScreenResult1.get("ModuleID"))){
												sum1=sum1+1;
											}
											
											//确认是否相同级别结果
											if(ScreenResult.get("Slcode").equals(ScreenResult1.get("Slcode"))){
												sum1=sum1+1;
											}
											
											//确认是否相同警示结果
											if(ScreenResult.get("Warning").equals(ScreenResult1.get("Warning"))){
												sum1=sum1+1;
											}
											
											//对比所有节点
											for(int o=0;o<ScreenResult.size();o++){
												String name=ScreenResult.names().getString(o);
												if(ScreenResult.get(name).equals(ScreenResult1.get(name))){
													sum1=sum1+1;
												}
											}
											
											//记录匹配数和错误提示
											if(sum1>sum){
												sum=sum1;
												ksum=k;//将Bjson对比过的位置记录下
												System.out.println(ScreenResult.toString());
												System.out.println(ScreenResult1.toString());
												err=ScreenResult.toString();
												err1=ScreenResult1.toString();
											}
										}
									}
								}
								
								//记录Bjson审查结果匹配数最高的
								klist.add(ksum);
								
								//如果全部节点全在对比错误的，记录错误
								if(sum<18&&sum>0){
									JSONObject errobj=JSONObject.fromObject(err);
									JSONObject errobj1=JSONObject.fromObject(err1);
									List errnamelist=new ArrayList();
									List errnamelist1=new ArrayList();
									for(int errn=0;errn<errobj.size();errn++){
										String errname=errobj.names().getString(errn);
										if(!errobj.get(errname).equals(errobj1.get(errname))){
											errnamelist.add(errname);
										}
									}
									listerr.add("DrugIndex为"+ScreenResultDrugsn.get("DrugIndex")+",ScreenResults又有新问题啦！！！！！！！！！！！！！！！！！！！");
									listerr.add("存在问题的节点有："+errnamelist);
									listerr.add("----断言："+err);
									listerr.add("----响应："+err1);
								}
							}
						}
					}
					
					//如果B串找不到A串药品，提示错误
					if(DrugIndexstate==0&&(j+1)==ScreenResultDrugs1.size()){
						System.out.println("断言：DrugIndex为："+ScreenResultDrugsn.get("DrugIndex"));
						System.out.println("响应：DrugIndex为：空");
						listerr.add("断言：DrugIndex为："+ScreenResultDrugsn.get("DrugIndex"));
						listerr.add("响应：DrugIndex为：空");
					}
				}
			}
			
			//如果有错误，错误状态改成1
			if(listerr.size()!=0){
				listerrsum=1;
			}
			
			//输出所有错误list
			setListerr1(listerr);
			Date data=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("IP:"+ip+"，"+sf.format(data));
			return "success";
		}catch(Exception ex){
			listerrsum=1;
			System.out.println("json审查节点名称可能存在问题");
			listerr.add("json审查节点名称可能存在问题");
			setListerr1(listerr);
			Date data=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("IP:"+ip+"，"+sf.format(data));
			return "success";
		}
	}
}
