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

//pass��鷵�ص�json����java-pass���ص�json���Ա�

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
		ip=ServletActionContext.getRequest().getRemoteAddr();//��ȡ�ͻ���IP
		List listerr=new ArrayList();
		
		//���봮Ϊ���˳�
		if("".equals(json)||"".equals(json1)){
			listerrsum=1;
			System.out.println("����������벻����");
			listerr.add("����������벻����");
			setListerr1(listerr);
			Date data=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("IP:"+ip+"��"+sf.format(data));
			return "success";
		}
		
		try{//��ֹ�����json�����쳣
			JSONObject jsonin=JSONObject.fromObject(json);//��ȷ��json
			JSONObject jsonin1=JSONObject.fromObject(json1);//��Ҫ��֤��json
//			System.out.println(jsonin);
//			System.out.println(jsonin1);
			
			//�Ա�HighestSlcode�ڵ�
			if(!jsonin.get("HighestSlcode").equals(jsonin1.get("HighestSlcode"))&&moduleidstr==-1){
				System.out.println("���ԣ�һ��Ŀ¼��ȷ�Ľڵ���HighestSlcode��"+jsonin.get("HighestSlcode"));
				System.out.println("��Ӧ��һ��Ŀ¼����Ľڵ���HighestSlcode��"+jsonin1.get("HighestSlcode"));
				listerr.add("���ԣ�һ��Ŀ¼��ȷ�Ľڵ��ǣ�HighestSlcode��"+jsonin.get("HighestSlcode"));
				listerr.add("��Ӧ��һ��Ŀ¼����Ľڵ��ǣ�HighestSlcode��"+jsonin1.get("HighestSlcode"));
//				return "success";
			}
			
			//�Ա�InUseModules�ڵ�
			if(!jsonin.get("InUseModules").equals(jsonin1.get("InUseModules"))){
				System.out.println("���ԣ�һ��Ŀ¼��ȷ�Ľڵ���InUseModules��"+jsonin.get("InUseModules"));
				System.out.println("��Ӧ��һ��Ŀ¼����Ľڵ���InUseModules��"+jsonin1.get("InUseModules"));
				listerr.add("���ԣ�һ��Ŀ¼��ȷ�Ľڵ���InUseModules��"+jsonin.get("InUseModules"));
				listerr.add("��Ӧ��һ��Ŀ¼����Ľڵ���InUseModules��"+jsonin1.get("InUseModules"));
//				return "success";
			}
			
			//�Ա�ScreenResultDrugs�ڵ㣬���ҩƷ������ͬ���˳�
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
				System.out.println("���ԣ�һ��Ŀ¼��ȷ�Ľڵ���ScreenResultDrugs��"+ScreenResultDrugs.size()+"��,ҩƷ����ǣ�"+drugnames);
				System.out.println("��Ӧ��һ��Ŀ¼����Ľڵ���ScreenResultDrugs��"+ScreenResultDrugs1.size()+"��,ҩƷ����ǣ�"+drugnames1);
				listerr.add("���ԣ�һ��Ŀ¼��ȷ�Ľڵ���ScreenResultDrugs��"+ScreenResultDrugs.size()+"��,ҩƷ����ǣ�"+drugnames);
				listerr.add("��Ӧ��һ��Ŀ¼����Ľڵ���ScreenResultDrugs��"+ScreenResultDrugs1.size()+"��,ҩƷ����ǣ�"+drugnames1);
				if(listerr.size()!=0){
					listerrsum=1;
				}
				setListerr1(listerr);
				Date data=new Date();
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("IP:"+ip+"��"+sf.format(data));
				return "success";
			}
			
			//A��ѭ��ȡScreenResultDrugs�ڵ��ҩƷ
			for(int i=0;i<ScreenResultDrugs.size();i++){
				JSONObject ScreenResultDrugsn=ScreenResultDrugs.getJSONObject(i);//Ajson��һ��ScreenResultDrugs
				
				JSONArray ScreenResultsdan=ScreenResultDrugsn.getJSONArray("ScreenResults");//ȡ��A��ҩƷ�����������
//				int Slcodesum=100;
//				for(int i1=0;i1<ScreenResultsdan.size();i1++){//����ͬģ��ScreenResults����С��Slcode
//					JSONObject obj=ScreenResultsdan.getJSONObject(i1);
//					if(obj.getInt("Slcode")<Slcodesum&&(moduleidstr==obj.getInt("ModuleID"))){
//						Slcodesum=obj.getInt("Slcode");
//					}
//				}
				
				int DrugIndexstate=0;//��ʼ��״̬��Ĭ��B���е�ҩƷδ�ҵ�
				//B��ѭ��ȡScreenResultDrugs�ڵ��ҩƷ
				for(int j=0;j<ScreenResultDrugs1.size();j++){
					JSONObject ScreenResultDrugsn1=ScreenResultDrugs1.getJSONObject(j);//Bjson��һ��ScreenResultDrugs
					if(ScreenResultDrugsn.get("DrugIndex").equals(ScreenResultDrugsn1.get("DrugIndex"))){
						DrugIndexstate=1;
						
						//�Ա�DrugName�ڵ�
						if(!ScreenResultDrugsn.get("DrugName").equals(ScreenResultDrugsn1.get("DrugName"))){
							System.out.println("���ԣ�DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--DrugName��"+ScreenResultDrugsn.get("DrugName"));
							System.out.println("��Ӧ��DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--DrugName��"+ScreenResultDrugsn1.get("DrugName"));
							listerr.add("���ԣ�DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--DrugName��"+ScreenResultDrugsn.get("DrugName"));
							listerr.add("��Ӧ��DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--DrugName��"+ScreenResultDrugsn1.get("DrugName"));
						}
						
						JSONArray ScreenResultsdan1=ScreenResultDrugsn1.getJSONArray("ScreenResults");//ȡ��B��ҩƷ�����������
//							int Slcodesum1=100;
//							for(int i1=0;i1<ScreenResultsdan1.size();i1++){//����ͬģ��ScreenResults����С��Slcode
//								JSONObject obj1=ScreenResultsdan1.getJSONObject(i1);
//								if(obj1.getInt("Slcode")<Slcodesum1&&(moduleidstr==obj1.getInt("ModuleID"))){
//									Slcodesum1=obj1.getInt("Slcode");
//								}
//							}
//							if(moduleidstr!=-1){//1
//								if(Slcodesum!=Slcodesum1&&Slcodesum!=100&&Slcodesum1!=100){//ʹ��AND�Ĺ�ϵ��Ϊ��Slcode��ͬʱ������ͺ����ظ���ʾ
//									System.out.println("���ԣ�DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode��"+Slcodesum);
//									System.out.println("��Ӧ��DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode��"+Slcodesum1);
//									listerr.add("���ԣ�DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode��"+Slcodesum);
//									listerr.add("��Ӧ��DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode��"+Slcodesum1);
//								}
//								if(!ScreenResultDrugsn.get("Slcode").equals(ScreenResultDrugsn1.get("Slcode"))&&ScreenResultsdan.size()==0&&ScreenResultsdan1.size()==0&&Slcodesum==100&&Slcodesum1==100){
//									System.out.println("���ԣ�DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode��"+ScreenResultDrugsn.get("Slcode"));
//									System.out.println("��Ӧ��DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode��"+ScreenResultDrugsn1.get("Slcode"));
//									listerr.add("���ԣ�DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode��"+ScreenResultDrugsn.get("Slcode"));
//									listerr.add("��Ӧ��DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode��"+ScreenResultDrugsn1.get("Slcode"));
//								}
//							}else{
//								if(!ScreenResultDrugsn.get("Slcode").equals(ScreenResultDrugsn1.get("Slcode"))){
//									System.out.println("���ԣ�DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode��"+ScreenResultDrugsn.get("Slcode"));
//									System.out.println("��Ӧ��DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode��"+ScreenResultDrugsn1.get("Slcode"));
//									listerr.add("���ԣ�DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode��"+ScreenResultDrugsn.get("Slcode"));
//									listerr.add("��Ӧ��DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode��"+ScreenResultDrugsn1.get("Slcode"));
//								}
//							}
						
						//�Ա�Slcode�ڵ�
						if(moduleidstr==-1||(ScreenResultsdan.size()==0&&ScreenResultsdan1.size()==0)){//���������Ϊ�յ�ʱ��ŶԱ�DrugIndex�µ�Slcode
							if(!ScreenResultDrugsn.get("Slcode").equals(ScreenResultDrugsn1.get("Slcode"))){
								System.out.println("���ԣ�DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode��"+ScreenResultDrugsn.get("Slcode"));
								System.out.println("��Ӧ��DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode��"+ScreenResultDrugsn1.get("Slcode"));
								listerr.add("���ԣ�DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode��"+ScreenResultDrugsn.get("Slcode"));
								listerr.add("��Ӧ��DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--Slcode��"+ScreenResultDrugsn1.get("Slcode"));
							}
						}
						
						//�Ա�MenuLabel�ڵ�
						if(!ScreenResultDrugsn.get("MenuLabel").equals(ScreenResultDrugsn1.get("MenuLabel"))){
							System.out.println("���ԣ�DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--MenuLabel��"+ScreenResultDrugsn.get("MenuLabel"));
							System.out.println("��Ӧ��DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--MenuLabel��"+ScreenResultDrugsn1.get("MenuLabel"));
							listerr.add("���ԣ�DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--MenuLabel��"+ScreenResultDrugsn.get("MenuLabel"));
							listerr.add("��Ӧ��DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--MenuLabel��"+ScreenResultDrugsn1.get("MenuLabel"));
						}
						
						//�Ա�AB��ScreenResults�ڵ���������ģ�������Ƿ���ͬ
						JSONArray ScreenResults=ScreenResultDrugsn.getJSONArray("ScreenResults");
						JSONArray ScreenResults1=ScreenResultDrugsn1.getJSONArray("ScreenResults");
						
						//����A�����������
						List drugnames=new ArrayList();						
						for(int namesum=0;namesum<ScreenResults.size();namesum++){
							JSONObject obj=ScreenResults.getJSONObject(namesum);
							String name=null;
							if(moduleidstr==Integer.parseInt(obj.get("ModuleID").toString())||moduleidstr==-1){
								name=obj.getString("ModuleID")+":"+obj.getString("ModuleName");
								drugnames.add(name);
							}
						}
						//����B�����������
						List drugnames1=new ArrayList();
						for(int namesum1=0;namesum1<ScreenResults1.size();namesum1++){
							JSONObject obj1=ScreenResults1.getJSONObject(namesum1);
							String name1=null;
							if(moduleidstr==obj1.getInt("ModuleID")||moduleidstr==-1){
								name1=obj1.getString("ModuleID")+":"+obj1.getString("ModuleName");
								drugnames1.add(name1);
							}
						}
						//�Ա�AB�����������
						if(drugnames.size()!=drugnames1.size()){
							System.out.println("���ԣ�DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--ScreenResults��"+drugnames.size()+"��,ģ��ID�ǣ�"+drugnames);
							System.out.println("��Ӧ��DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--ScreenResults��"+drugnames1.size()+"��,ģ��ID�ǣ�"+drugnames1);
							listerr.add("���ԣ�DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--ScreenResults��"+drugnames.size()+"��,ģ��ID�ǣ�"+drugnames);
							listerr.add("��Ӧ��DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+"--ScreenResults��"+drugnames1.size()+"��,ģ��ID�ǣ�"+drugnames1);
							if(drugnames.size()==0||drugnames1.size()==0||moduleidstr==-1){
//									if(listerr.size()!=0){
//										listerrsum=1;
//									}
//									setListerr1(listerr);
//									Date data=new Date();
//									SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//									System.out.println("IP:"+ip+"��"+sf.format(data));
								break;//���AB���������Ϊ�գ����ٽ������ڵ�Ա�
							}
						}
						
						//ȡB��������ڵ�
						List klist=new ArrayList();//��¼Bjson�Ѿ��Աȳɹ����λ��
						for(int z=0;z<ScreenResults.size();z++){
							JSONObject ScreenResult=ScreenResults.getJSONObject(z);//Ajson��һ��ScreenResults
							
							//ȫ������ָ����ģ��Ա�ModuleID�ڵ�
							if(moduleidstr==Integer.parseInt(ScreenResult.get("ModuleID").toString())||moduleidstr==-1){
								int sum=0;//��ͬ�̶�ƥ������ʼֵΪ0
								String err=null;
								String err1=null;
								int ksum=0;//B����ʼλ��Ϊ0
								for(int k=0;k<ScreenResults1.size();k++){
									//��ѯB���Ƿ��Ѿ�ƥ���
									int kstate=0;//Ĭ��Bjsonδ�Աȹ�
									for(int k1=0;k1<klist.size();k1++){
										if(k==Integer.parseInt(klist.get(k1).toString())){
											kstate=1;//Bjson�ѶԱȹ�
										}
									}
									
									//B��δ�Աȹ�
									if(kstate==0){
										int sum1=0;
										JSONObject ScreenResult1=ScreenResults1.getJSONObject(k);//Bjson��һ��ScreenResults
										
										//ȫ������ָ����ģ��Ա�ModuleID�ڵ�
										if(moduleidstr==Integer.parseInt(ScreenResult.get("ModuleID").toString())||moduleidstr==-1){
											
											//ȷ���Ƿ���ͬģ����
											if(ScreenResult.get("ModuleID").equals(ScreenResult1.get("ModuleID"))){
												sum1=sum1+1;
											}
											
											//ȷ���Ƿ���ͬ������
											if(ScreenResult.get("Slcode").equals(ScreenResult1.get("Slcode"))){
												sum1=sum1+1;
											}
											
											//ȷ���Ƿ���ͬ��ʾ���
											if(ScreenResult.get("Warning").equals(ScreenResult1.get("Warning"))){
												sum1=sum1+1;
											}
											
											//�Ա����нڵ�
											for(int o=0;o<ScreenResult.size();o++){
												String name=ScreenResult.names().getString(o);
												if(ScreenResult.get(name).equals(ScreenResult1.get(name))){
													sum1=sum1+1;
												}
											}
											
											//��¼ƥ�����ʹ�����ʾ
											if(sum1>sum){
												sum=sum1;
												ksum=k;//��Bjson�Աȹ���λ�ü�¼��
												System.out.println(ScreenResult.toString());
												System.out.println(ScreenResult1.toString());
												err=ScreenResult.toString();
												err1=ScreenResult1.toString();
											}
										}
									}
								}
								
								//��¼Bjson�����ƥ������ߵ�
								klist.add(ksum);
								
								//���ȫ���ڵ�ȫ�ڶԱȴ���ģ���¼����
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
									listerr.add("DrugIndexΪ"+ScreenResultDrugsn.get("DrugIndex")+",ScreenResults��������������������������������������������������");
									listerr.add("��������Ľڵ��У�"+errnamelist);
									listerr.add("----���ԣ�"+err);
									listerr.add("----��Ӧ��"+err1);
								}
							}
						}
					}
					
					//���B���Ҳ���A��ҩƷ����ʾ����
					if(DrugIndexstate==0&&(j+1)==ScreenResultDrugs1.size()){
						System.out.println("���ԣ�DrugIndexΪ��"+ScreenResultDrugsn.get("DrugIndex"));
						System.out.println("��Ӧ��DrugIndexΪ����");
						listerr.add("���ԣ�DrugIndexΪ��"+ScreenResultDrugsn.get("DrugIndex"));
						listerr.add("��Ӧ��DrugIndexΪ����");
					}
				}
			}
			
			//����д��󣬴���״̬�ĳ�1
			if(listerr.size()!=0){
				listerrsum=1;
			}
			
			//������д���list
			setListerr1(listerr);
			Date data=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("IP:"+ip+"��"+sf.format(data));
			return "success";
		}catch(Exception ex){
			listerrsum=1;
			System.out.println("json���ڵ����ƿ��ܴ�������");
			listerr.add("json���ڵ����ƿ��ܴ�������");
			setListerr1(listerr);
			Date data=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("IP:"+ip+"��"+sf.format(data));
			return "success";
		}
	}
}
