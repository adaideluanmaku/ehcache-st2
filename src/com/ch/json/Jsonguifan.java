package com.ch.json;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;




//PASS审查输入JSON串规范
public class Jsonguifan {
	public static void main(String args[]) throws IOException{
		InputStream in=Jsonguifan.class.getClassLoader().getResourceAsStream("json.properties");
		Properties pro=new Properties();
		pro.load(in);
		String jsonstr=pro.getProperty("json3");
		
		//将字符串转成JSON串,声明一个输入JSON和输出JSON
		JSONObject jsonin=JSONObject.fromObject(jsonstr);
		JSONObject jsonout=new JSONObject();
		System.out.println(jsonstr);
		System.out.println(jsonout);

		//拆分JSON串的PassClient属性和组装JSON串的PassClient属性
		JSONObject PassClient=jsonin.getJSONObject("PassClient");
		String HospID=PassClient.getString("HospID");
		String HospName=PassClient.getString("HospName");
		String UserID=PassClient.getString("UserID");
		String UserName=PassClient.getString("UserName");
		String DeptID=PassClient.getString("DeptID");
		String DeptName=PassClient.getString("DeptName");
		String IP=PassClient.getString("IP");
		String PCName=PassClient.getString("PCName");
		String OSInfo=PassClient.getString("OSInfo");
		String Resolution=PassClient.getString("Resolution");
		String PassVersion=PassClient.getString("PassVersion");
		String CheckMode=PassClient.getString("CheckMode");
		
		JSONObject PassClientout=new JSONObject();
		PassClientout.element("HospID", HospID);
		PassClientout.element("HospName", HospName);
		PassClientout.element("UserID", UserID);
		PassClientout.element("UserName", UserName);
		PassClientout.element("DeptID", DeptID);
		PassClientout.element("DeptName", DeptName);
		PassClientout.element("IP", IP);
		PassClientout.element("PCName", PCName);
		PassClientout.element("OSInfo", OSInfo);
		PassClientout.element("Resolution", Resolution);
		PassClientout.element("PassVersion", PassVersion);
		PassClientout.element("CheckMode", CheckMode);
		
		jsonout.element("PassClient", PassClientout);
//		System.out.println(jsonout);
		
		//拆分JSON串的Patient属性和组装JSON串的PassClient属性
		JSONObject Patient=jsonin.getJSONObject("Patient");
		String PatCode=Patient.getString("PatCode");
		String InHospNo=Patient.getString("InHospNo");
		String VisitCode=Patient.getString("VisitCode");
		String Name=Patient.getString("Name");
		String Sex=Patient.getString("Sex");
		String Birthday=Patient.getString("Birthday");
		String HeightCM=Patient.getString("HeightCM");
		String WeighKG=Patient.getString("WeighKG");
		String DeptCode=Patient.getString("DeptCode");
		String DeptName1=Patient.getString("DeptName");
		String DoctorCode=Patient.getString("DoctorCode");
		String DoctorName=Patient.getString("DoctorName");
		int PatStatus=Patient.getInt("PatStatus");
		int IsLactation=Patient.getInt("IsLactation");
		int IsPregnancy=Patient.getInt("IsPregnancy");
		String PregStartDate=Patient.getString("PregStartDate");
		int HepDamageDegree=Patient.getInt("HepDamageDegree");
		int RenDamageDegree=Patient.getInt("RenDamageDegree");
		String UseTime=Patient.getString("UseTime");
		String CheckMode1=Patient.getString("CheckMode");
		int IsDoSave=Patient.getInt("IsDoSave");
		String Age=Patient.getString("Age");
		String PayClass=Patient.getString("PayClass");
		int IsTestEtiology=Patient.getInt("IsTestEtiology");
		String InHospDate=Patient.getString("InHospDate");
		String OutHospDate=Patient.getString("OutHospDate");
		String IDCard=Patient.getString("IDCard");
		String Telephone=Patient.getString("Telephone");
		
		JSONObject Patientout=new JSONObject();
		Patientout.element("PatCode", PatCode);
		Patientout.element("InHospNo", InHospNo);
		Patientout.element("VisitCode", VisitCode);
		Patientout.element("Name", Name);
		Patientout.element("Sex", Sex);
		Patientout.element("Birthday", Birthday);
		Patientout.element("HeightCM", HeightCM);
		Patientout.element("WeighKG", WeighKG);
		Patientout.element("DeptCode", DeptCode);
		Patientout.element("DeptName", DeptName1);
		Patientout.element("DoctorCode", DoctorCode);
		Patientout.element("DoctorName", DoctorName);
		Patientout.element("PatStatus", PatStatus);
		Patientout.element("IsLactation", IsLactation);
		Patientout.element("IsPregnancy", IsPregnancy);
		Patientout.element("PregStartDate", PregStartDate);
		Patientout.element("HepDamageDegree", HepDamageDegree);
		Patientout.element("RenDamageDegree", RenDamageDegree);
		Patientout.element("UseTime", UseTime);
		Patientout.element("CheckMode", CheckMode1);
		Patientout.element("IsDoSave", IsDoSave);
		Patientout.element("Age", Age);
		Patientout.element("PayClass", PayClass);
		Patientout.element("IsTestEtiology", IsTestEtiology);
		Patientout.element("InHospDate", InHospDate);
		Patientout.element("OutHospDate", OutHospDate);
		Patientout.element("IDCard", IDCard);
		Patientout.element("Telephone", Telephone);
		
		jsonout.element("Patient", Patientout);
//		System.out.println(jsonout);
		
		//拆分JSON串的ScreenAllergenList属性和组装JSON串的ScreenAllergenList属性
		JSONObject ScreenAllergenList=jsonin.getJSONObject("ScreenAllergenList");
		JSONArray ScreenAllergens=ScreenAllergenList.getJSONArray("ScreenAllergens");
		int ScreenAllergenscount=ScreenAllergens.size();
		
		JSONObject ScreenAllergenListout=new JSONObject();
		JSONArray ScreenAllergensout=new JSONArray();
		
		for(int i=0;i<=ScreenAllergenscount;i++){
			ScreenAllergenListout.element("ScreenAllergens", ScreenAllergensout);
			jsonout.element("ScreenAllergenList", ScreenAllergenListout);
		}
//		System.out.println(jsonout);
		
		//拆分JSON串的ScreenMedCondList属性和组装JSON串的ScreenMedCondList属性
		JSONObject ScreenMedCondList=jsonin.getJSONObject("ScreenMedCondList");
		JSONArray ScreenMedConds=ScreenMedCondList.getJSONArray("ScreenMedConds");
		int ScreenMedCondscount=ScreenMedConds.size();
		
		JSONObject ScreenMedCondListout=new JSONObject();
		JSONArray ScreenMedCondsout=new JSONArray();
		
		for(int i=0;i<=ScreenMedCondscount;i++){
			ScreenMedCondListout.element("ScreenMedConds", ScreenMedCondsout);
			jsonout.element("ScreenMedCondList", ScreenMedCondListout);
		}
//		System.out.println(jsonout);
		
		//拆分JSON串的ScreenOperationList属性和组装JSON串的ScreenOperationList属性
		JSONObject ScreenOperationList=jsonin.getJSONObject("ScreenOperationList");
		JSONArray ScreenOperations=ScreenOperationList.getJSONArray("ScreenOperations");
		int ScreenOperationscount=ScreenOperations.size();
		
		JSONObject ScreenOperationListout=new JSONObject();
		JSONArray ScreenOperationsout=new JSONArray();
		
		for(int i=0;i<=ScreenOperationscount;i++){
			ScreenOperationListout.element("ScreenOperations", ScreenOperationsout);
			jsonout.element("ScreenOperationList", ScreenOperationListout);
		}
//		System.out.println(jsonout);
		//拆分JSON串的ScreenDrugList属性和组装JSON串的ScreenDrugList属性
		JSONObject ScreenDrugList=jsonin.getJSONObject("ScreenDrugList");
		JSONArray ScreenDrugs=ScreenDrugList.getJSONArray("ScreenDrugs");
		int ScreenDrugscount=ScreenDrugs.size();
		
		JSONObject ScreenDrugListout=new JSONObject();
		JSONArray ScreenDrugsout=new JSONArray();
		
		for(int i=0;i<ScreenDrugscount;i++){
			JSONObject ScreenDrug=ScreenDrugs.getJSONObject(i);
			String RecipNo=ScreenDrug.getString("RecipNo");
			String Index=ScreenDrug.getString("Index");
			int OrderNo=ScreenDrug.getInt("OrderNo");
			String DrugSource=ScreenDrug.getString("DrugSource");
			String DrugUniqueCode=ScreenDrug.getString("DrugUniqueCode");
			String DrugCode=ScreenDrug.getString("DrugCode");
			String DrugName=ScreenDrug.getString("DrugName");
			String DoseUnit=ScreenDrug.getString("DoseUnit");
			String Form=ScreenDrug.getString("Form");
			String Strength=ScreenDrug.getString("Strength");
			String CompName=ScreenDrug.getString("CompName");
			String RouteSource=ScreenDrug.getString("RouteSource");
			String RouteCode=ScreenDrug.getString("RouteCode");
			String RouteName=ScreenDrug.getString("RouteName");
			String FreqSource=ScreenDrug.getString("FreqSource");
			String Frequency=ScreenDrug.getString("Frequency");
			String DosePerTime=ScreenDrug.getString("DosePerTime");
			String StartTime=ScreenDrug.getString("StartTime");
			String EndTime=ScreenDrug.getString("EndTime");
			String ExecuteTime=ScreenDrug.getString("ExecuteTime");
			String DeptCode1=ScreenDrug.getString("DeptCode");
			String DeptName2=ScreenDrug.getString("DeptName");
			String DoctorCode1=ScreenDrug.getString("DoctorCode");
			String DoctorName1=ScreenDrug.getString("DoctorName");
			String GroupTag=ScreenDrug.getString("GroupTag");
			String IsTempDrug=ScreenDrug.getString("IsTempDrug");
			String OrderType=ScreenDrug.getString("OrderType");
			String Pharmacists=ScreenDrug.getString("Pharmacists");
			String Pharmacists_=ScreenDrug.getString("Pharmacists_");
			String Num=ScreenDrug.getString("Num");
			String NumUnit=ScreenDrug.getString("NumUnit");
			String Cost=ScreenDrug.getString("Cost");
			String Purpose=ScreenDrug.getString("Purpose");
			String OprCode=ScreenDrug.getString("OprCode");
			String MediTime=ScreenDrug.getString("MediTime");
			String Remark=ScreenDrug.getString("Remark");
//			System.out.println(DrugName);
			
			JSONObject ScreenDrugout=ScreenDrugs.getJSONObject(i);
			ScreenDrugout.element("ScreenDrug", RecipNo);
			ScreenDrugout.element("Index", Index);
			ScreenDrugout.element("OrderNo", OrderNo);
			ScreenDrugout.element("DrugSource", DrugSource);
			ScreenDrugout.element("DrugUniqueCode", DrugUniqueCode);
			ScreenDrugout.element("DrugCode", DrugCode);
			ScreenDrugout.element("DrugName", DrugName);
			ScreenDrugout.element("DoseUnit", DoseUnit);
			ScreenDrugout.element("Form", Form);
			ScreenDrugout.element("Strength", Strength);
			ScreenDrugout.element("CompName", CompName);
			ScreenDrugout.element("RouteSource", RouteSource);
			ScreenDrugout.element("RouteCode", RouteCode);
			ScreenDrugout.element("RouteName", RouteName);
			ScreenDrugout.element("FreqSource", FreqSource);
			ScreenDrugout.element("Frequency", Frequency);
			ScreenDrugout.element("DosePerTime", DosePerTime);
			ScreenDrugout.element("StartTime", StartTime);
			ScreenDrugout.element("EndTime", EndTime);
			ScreenDrugout.element("ExecuteTime", ExecuteTime);
			ScreenDrugout.element("DeptCode", DeptCode1);
			ScreenDrugout.element("DeptName", DeptName2);
			ScreenDrugout.element("DoctorCode", DoctorCode1);
			ScreenDrugout.element("DoctorName", DoctorName1);
			ScreenDrugout.element("GroupTag", GroupTag);
			ScreenDrugout.element("IsTempDrug", IsTempDrug);
			ScreenDrugout.element("OrderType", OrderType);
			ScreenDrugout.element("Pharmacists", Pharmacists);
			ScreenDrugout.element("Pharmacists_", Pharmacists_);
			ScreenDrugout.element("Num", Num);
			ScreenDrugout.element("NumUnit", NumUnit);
			ScreenDrugout.element("Cost", Cost);
			ScreenDrugout.element("Purpose", Purpose);
			ScreenDrugout.element("OprCode", OprCode);
			ScreenDrugout.element("MediTime", MediTime);
			ScreenDrugout.element("Remark", Remark);
			
			ScreenDrugsout.element( ScreenDrugout);
			ScreenDrugListout.element("ScreenDrugList", ScreenDrugsout);
		}
		jsonout.element("ScreenDrugList", ScreenDrugListout);
		System.out.println(jsonout);
	}
}
