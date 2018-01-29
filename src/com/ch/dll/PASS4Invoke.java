package com.ch.dll;

import java.io.BufferedReader;
import java.io.FileInputStream;


import java.io.FileReader;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

//灏嗛渶瑕佽璋冪敤鐨凞LL鏂囦欢鏀惧叆SRC鏍圭洰褰曚笅
public interface PASS4Invoke extends Library {
	//鍔犺浇DLL鏂囦欢
//	PASS4Invoke Instance=(PASS4Invoke) Native.loadLibrary((Platform.isWindows() ? "PASS4Invoke" : "c"),PASS4Invoke.class);
//	PASS4Invoke Instance1=(PASS4Invoke) Native.loadLibrary((Platform.isWindows() ? "PASSWeb" : "c"),PASS4Invoke.class);
	PASS4Invoke instanceDll=(PASS4Invoke) Native.loadLibrary("PASS4Invoke",PASS4Invoke.class);
	PASS4Invoke instanceDll1=(PASS4Invoke) Native.loadLibrary("PASSWeb",PASS4Invoke.class);
//	String configpath = "PASS4Invoke.ini";  
	
	//鐢虫槑鍒濆鍖栨帴鍙ｆ柟娉曪紝鍜孌LL閲岄潰鐨勬柟娉曚竴鑷�
	public int MDC_Init(String pcCheckMode,	String pcHisCode, String pcDoctorCode);
	public String MDC_GetLastError ();
//	//鐥呬汉鍩烘湰璁板綍淇℃伅鎺ュ彛
//	public int MDC_SetPatient(String pcPatCode,
//			String pcInHospNo,
//			String pcVisitCode,
//			String pcName,
//			String pcSex,
//			String pcBirthday,
//			String pcHeightCM,
//			String pcWeighKG,
//			String pcDeptCode,
//			String pcDeptName,
//			String pcDoctorCode,
//			String pcDoctorName,
//			int piPatStatus,
//			int piIsLactation,
//			int piIsPregnancy,
//			String pcPregStartDate,
//			int piHepDamageDegree,
//			int piRenDamageDegree);
//
//	//鐥呬汉鑽搧璁板綍淇℃伅鎺ュ彛
//	public int MDC_AddScreenDrug(String pcIndex,
//			int    piOrderNo,
//			String pcDrugUniqueCode,
//			String pcDrugName,
//			String pcDosePerTime,
//			String pcDoseUnit,
//			String pcFrequency,
//			String pcRouteCode,
//			String pcRouteName,
//			String pcStartTime,
//			String pcEndTime,
//			String pcExecuteTime,
//			String pcGroupTag,
//			String pcIsTempDrug,
//			String pcOrderType,
//			String pcDeptCode,
//			String pcDeptName,
//			String pcDoctorCode,
//			String pcDoctorName,
//			String pcRecipNo,
//			String pcNum,
//			String pcNumUnit);
//
//	//鐥呬汉杩囨晱鍙茶褰曚俊鎭帴鍙�
//	public int MDC_AddAller(String pcIndex,
//			String pcAllerCode,
//			String pcAllerName,
//			String pcAllerSymptom);
//	
//	//鐥呬汉璇婃柇璁板綍淇℃伅鎺ュ彛
//	public int MDC_AddMedCond(String pcIndex,
//			String pcDiseaseCode,
//			String pcDiseaseName,
//			String pcRecipNo);
//
//	//鐥呬汉鎵嬫湳璁板綍淇℃伅鎺ュ彛
//	public int MDC_AddOperation(String pcIndex,
//			String pcOprCode,
//			String pcOprName,
//			String pcOprStartDateTime,
//			String pcOprEndDateTime);


}
