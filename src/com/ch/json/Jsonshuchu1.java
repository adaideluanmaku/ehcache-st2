package com.ch.json;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.mysql.fabric.xmlrpc.base.Data;

import freemarker.template.SimpleDate;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


//被jsonshuchu。java调用进行总数不同的具体对比
public class Jsonshuchu1 {
	private JSONArray json=null;
	private JSONArray json1=null;
	private JSONArray ScreenResults=null;
	private JSONArray ScreenResults1=null;
	private List listerr1;
	private int listerrsum=0;
	private int moduleidstr=-1;
	private int duibistate=1;
	private String ip;
	public String drugindex=null;

	public void setDrugname(String drugindex) {
		this.drugindex = drugindex;
	}

	public void setListerr1(List listerr1) {
		this.listerr1 = listerr1;
	}

	public List getListerr1() {
		return listerr1;
	}

	public void setJson(JSONArray json) {
		this.json = json;
	}

	public void setJson1(JSONArray json1) {
		this.json1 = json1;
	}

	public void setModuleidstr(int moduleidstr) {
		this.moduleidstr = moduleidstr;
	}

	public List Jsonrequest(){
		List listerr=new ArrayList();
		
		List klist=new ArrayList();//记录Bjson已经对比成功后的位置
		if(json.size()<json1.size()){
			ScreenResults=json;
			ScreenResults1=json1;
			for(int z=0;z<ScreenResults.size();z++){
				JSONObject ScreenResult=ScreenResults.getJSONObject(z);//Ajson第一个ScreenResults
				
				//全部或者指定的模块对比ModuleID节点
				int sum=0;//相同程度匹配数初始值为0
				String err=null;
				String err1=null;
				int ksum=0;//B串起始位置为0
				if(moduleidstr==Integer.parseInt(ScreenResult.get("ModuleID").toString())||moduleidstr==-1){
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
					
					//如果全部节点存在对比错误的，记录错误
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
						listerr.add("DrugIndex为"+drugindex+",ScreenResults又有新问题啦！！！！！！！！！！！！！！！！！！！");
						listerr.add("存在问题的节点有："+errnamelist);
						listerr.add("----断言："+err);
						listerr.add("----响应："+err1);
					}
					
					//记录Bjson审查结果匹配数最高的位置
					klist.add(ksum);
				}
			}
			
			//B串多余的审查结果
			List ScreenResults1re=new ArrayList();
			for(int i=0;i<klist.size();i++){
				ScreenResults1re.add(ScreenResults1.get((int)klist.get(i)));
			}
			for(int i=0;i<ScreenResults1re.size();i++){
				ScreenResults1.remove(ScreenResults1re.get(i));
			}
			for(int i=0;i<ScreenResults1.size();i++){
				listerr.add("----断言：DrugIndex为"+drugindex);
				listerr.add("----响应：DrugIndex为"+drugindex+"--多余结果，定位提示："+ScreenResults1.getJSONObject(i));
			}
		}else{
			ScreenResults=json1;
			ScreenResults1=json;
			for(int z=0;z<ScreenResults.size();z++){
				JSONObject ScreenResult=ScreenResults.getJSONObject(z);//Ajson第一个ScreenResults
				
				//全部或者指定的模块对比ModuleID节点
				int sum=0;//相同程度匹配数初始值为0
				String err=null;
				String err1=null;
				int ksum=0;//B串起始位置为0
				if(moduleidstr==Integer.parseInt(ScreenResult.get("ModuleID").toString())||moduleidstr==-1){
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
					
					//如果全部节点存在对比错误的，记录错误
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
						listerr.add("DrugIndex为"+drugindex+",ScreenResults又有新问题啦！！！！！！！！！！！！！！！！！！！");
						listerr.add("存在问题的节点有："+errnamelist);
						listerr.add("----断言："+err1);
						listerr.add("----响应："+err);
					}
					
					//记录Bjson审查结果匹配数最高的位置
					klist.add(ksum);
				}
			}
			
			//B串多余的审查结果
			List ScreenResults1re=new ArrayList();
			//从klist中获取B串已经对比过的数据位置，将数据放入list
			for(int i=0;i<klist.size();i++){
				ScreenResults1re.add(ScreenResults1.get((int)klist.get(i)));
			}
			//从list中中找到B串数据，并删除
			for(int i=0;i<ScreenResults1re.size();i++){
				ScreenResults1.remove(ScreenResults1re.get(i));
			}
			//B串剩下的数据为A串中没有的数据
			for(int i=0;i<ScreenResults1.size();i++){
				listerr.add("----断言：DrugIndex为"+drugindex+"--结果，定位提示："+ScreenResults1.getJSONObject(i));
				listerr.add("----响应：DrugIndex为"+drugindex+"--缺少结果");
			}
		}
		//输出所有错误list
		setListerr1(listerr);
		Date data=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("IP:"+ip+"，"+sf.format(data));
		return listerr1;
	}
}