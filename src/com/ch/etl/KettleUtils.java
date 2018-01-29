package com.ch.etl;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

/**
 * 运行kettle脚本
 * @author 陈辉
 *
 */
public class KettleUtils {

	public void clinic(String[] params){
		String path = "c:/kettle/t_mc_clinic_allergen.ktr";//
		String path1 = "c:/kettle/t_mc_clinic_disease.ktr";  //
		String path2 = "c:/kettle/t_mc_clinic_exam.ktr";  //
		String path3 = "c:/kettle/t_mc_clinic_lab.ktr";  
		String path4 = "c:/kettle/t_mc_clinic_order.ktr";  //
		String path5 = "c:/kettle/t_mc_clinic_patient.ktr";  //
		
		KettleUtils sp = new KettleUtils();
		System.out.println("-----------------0");
		sp.runTransfer(params, path);  
		System.out.println("-----------------1");
		sp.runTransfer(params, path1);  
		System.out.println("-----------------2");
		sp.runTransfer(params, path2);  
		System.out.println("-----------------3");
		sp.runTransfer(params, path3);  
		System.out.println("-----------------4");
		sp.runTransfer(params, path4);  
		System.out.println("-----------------5");
		sp.runTransfer(params, path5);  
	}
	
	public static void testClinic(){
		String[] params = {"20140401000001", "20140401000001", "0"}; // 浼犻�鍙傛暟   
		String path = "c:/kettle/t_mc_clinic_allergen.ktr";//瑙嗗浘鏆傛椂涓虹┖
		
		String[] params1 = {"0001126479", "130619084111003315", "0"}; // 浼犻�鍙傛暟   
		String path1 = "c:/kettle/t_mc_clinic_disease.ktr";  //8
		
		String[] params2 = {"0001709582", "130801075146003286", "0"}; // 浼犻�鍙傛暟   
		String path2 = "c:/kettle/t_mc_clinic_exam.ktr";  //2
		
		String[] params3 = {"20140401000001", "20140401000001", "0"}; // 浼犻�鍙傛暟   
		String path3 = "c:/kettle/t_mc_clinic_lab.ktr";  
		
		String[] params4 = {"090401090515003300", "130601000623003932", "0"}; // 浼犻�鍙傛暟   
		String path4 = "c:/kettle/t_mc_clinic_order.ktr";  //17
		
		
		String[] params5 = {"0000000503", "130725073656003309", "0"}; // 浼犻�鍙傛暟   
		String path5 = "c:/kettle/t_mc_clinic_patient.ktr";  //1
		
		KettleUtils sp = new KettleUtils();
		System.out.println("-----------------0");
		sp.runTransfer(params, path);  
		System.out.println("-----------------1");
		sp.runTransfer(params, path1);  
		System.out.println("-----------------2");
		sp.runTransfer(params, path2);  
		System.out.println("-----------------3");
		sp.runTransfer(params, path3);  
		System.out.println("-----------------4");
		sp.runTransfer(params, path4);  
		System.out.println("-----------------5");
		sp.runTransfer(params, path5);  
		
	} 

	public static void testInhosp(){
		String[] params = {"", "", "0"}; // 浼犻�鍙傛暟   
		String path = "c:/kettle/t_mc_inhosp_allergen.ktr";//瑙嗗浘鏆傛椂涓虹┖
		String path_ = "c:/kettle/t_mc_outhosp_allergen.ktr";//
		
		String[] params1 = {"110208083009004176", "01012346201307240000K", "0"}; // 浼犻�鍙傛暟   
		String path1 = "c:/kettle/t_mc_inhosp_disease.ktr";  //2
		String path1_ = "c:/kettle/t_mc_outhosp_disease.ktr";  //2
		
		String[] params2 = {"130629133811000670", "01012346201306290001M", "0"}; // 浼犻�鍙傛暟   
		String path2 = "c:/kettle/t_mc_inhosp_exam.ktr";  //7
		String path2_ = "c:/kettle/t_mc_outhosp_exam.ktr";  //7
		
		String[] params4 = {"121116135047003270", "01013265201211160000P", "0"}; // 浼犻�鍙傛暟   
		String path4 = "c:/kettle/t_mc_inhosp_order.ktr";  //219
		String path4_ = "c:/kettle/t_mc_outhosp_order.ktr";  //219
		
		String[] params5 = {"0000000503", "01012346201306180003R", "0"}; // 浼犻�鍙傛暟   
		String path5 = "c:/kettle/t_mc_inhosp_patient.ktr";  //1
		String path5_ = "c:/kettle/t_mc_outhosp_patient.ktr";  //1
		
		String[] params6 = {"090708155446004153", "010191202012101200001", "0"}; // 浼犻�鍙傛暟   
		String path6 = "c:/kettle/t_mc_inhosp_operation.ktr";  //13
		String path6_ = "c:/kettle/t_mc_outhosp_operation.ktr";  //13
		
		KettleUtils sp = new KettleUtils();
		
//		//杩欎釜鏁版嵁澶锛屽崟鐙祴璇�
//		String[] params3 = {"", "3", "0"}; // 浼犻�鍙傛暟   
//		String path3 = "c:/kettle/t_mc_inhosp_lab.ktr";  //24806
//		String path3_ = "c:/kettle/t_mc_outhosp_lab.ktr";  //24806
//		sp.runTransfer(params3, path3);  
		
		System.out.println("-----------------1");
		sp.runTransfer(params1, path1_);  
		System.out.println("-----------------2");
		sp.runTransfer(params2, path2_);  
		System.out.println("-----------------4");
		sp.runTransfer(params4, path4_);  
		System.out.println("-----------------5");
		sp.runTransfer(params5, path5);  
		System.out.println("-----------------6");
		sp.runTransfer(params6, path6_);  
	} 
	
	public static void main(String arg[]) throws KettleException 
	{
		
//		testClinic();
//		testInhosp();
		
//		String[] params = {"0000826579", "01012346201307240000V", "0"}; // 浼犻�鍙傛暟   
//		String path = "c:/kettle/t_mc_inhosp_disease.ktr";  
//
		
		String path = "c:/kettle/job_clinic.kjb"; 
		String[] params = {"20140401000001", "20140401000001", "0"}; // 浼犻�鍙傛暟   
		KettleUtils sp = new KettleUtils();
//		sp.runJob(params, path);  
		sp.clinic(params);
		
//		System.out.println("*******begin:"+System.currentTimeMillis());
//		KettleUtils sp = new KettleUtils();
//		System.out.println(sp.runTran("c:/kettle/t_mc_inhosp_disease.ktr"));
//		System.out.println(sp.runTran("c:/kettle/kettle_mc_dict_dept.ktr"));
		
//		sp.runTran("D:/study/kettle/test/test_orcle.ktr");
//		sp.runTran("D:/study/kettle/test_sqlserver.ktr");
//		sp.runTran("D:/study/kettle/test_mysql.ktr");

//		sp.runTran("C:/kettle/t_mc_dict_dept.ktr");
//		sp.runTran("D:/study/kettle/mc_dict_dept(oracle_to_mysql).ktr");
		
	}
	
	/**
	 * java璋冪敤tran
	 * 
	 * @param filename 杞崲鐨勮剼鏈矾寰勫強鍚嶇О
	 */
	public boolean runTran(String filename) {
		boolean is_success = false;
		try {
			KettleEnvironment.init();
			EnvUtil.environmentInit();
			TransMeta transMeta = new TransMeta(filename);
			Trans trans = new Trans(transMeta);
			trans.execute(null); 
			trans.waitUntilFinished();
//			
//			System.out.println(trans.getRowsets());
//			
//			System.out.println("status:"+trans.getStatus());
			
//			Result result = trans.getResult();
//			System.out.println("鏉℃暟锛�+result.getRows());
//			System.out.println("鎴愬姛锛�+result.getResult());
//			System.out.println("1:"+result.getXML());
//			System.out.println("2:"+result.getExitStatus());
//			System.out.println("3:"+result.getLogText());
//			System.out.println("4:"+result.getResultFiles());
			
			 // 鎶涘嚭寮傚父  
            if (trans.getErrors() > 0) {  
                System.out.println(trans.getErrors());
                throw new KettleException();
            } 
            is_success = true;
		} catch (KettleException e) {
			e.printStackTrace();
			System.out.println(e); 
			is_success = false;
		}
		return is_success;
	}

	/**
	 * java 璋冪敤job
	 * 
	 * @param fileName job鐨勮剼鏈矾寰勫強鍚嶇О
	 */
	public void runJob(String fileName) {  
		try {  
			KettleEnvironment.init();
			JobMeta jobMeta = new JobMeta(fileName, null);  
			Job job = new Job(null, jobMeta);
			job.start();  
			job.waitUntilFinished();  
			if (job.getErrors() > 0) {  
				System.out.println("decompress fail!");  
			}
			job.getResult();
		} catch (KettleException e) {  
			System.out.println(e);  
		}  
	}  
	
	  /**  
     * 杩愯杞崲鏂囦欢鏂规硶 
     * @param params 澶氫釜鍙傛暟鍙橀噺鍊�
     * @param ktrPath 杞崲鏂囦欢鐨勮矾寰勶紝鍚庣紑ktr 
     */  
    public boolean runTransfer(String[] params, String ktrPath) {  
    	boolean is_success = false;
    	Trans trans = null;  
        try {  
            // 鍒濆鍖� 
            // 杞崲鍏冨璞� 
            KettleEnvironment.init();// 鍒濆鍖� 
            EnvUtil.environmentInit();  
            TransMeta transMeta = new TransMeta(ktrPath);  
            // 杞崲  
            trans = new Trans(transMeta);  
              
            // 鎵ц杞崲  
            trans.execute(params);  
            // 绛夊緟杞崲鎵ц缁撴潫  
            trans.waitUntilFinished();  
            // 鎶涘嚭寮傚父  
            if (trans.getErrors() > 0) {  
                throw new Exception(  
                        "There are errors during transformation exception!(浼犺緭杩囩▼涓彂鐢熷紓甯�");  
            }  
            is_success = true;
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return is_success;
    }  
  
    /** 
     * java 璋冪敤 kettle 鐨刯ob 
     *  
     * @param jobname 
     *            濡傦細 String fName= "D:\\kettle\\informix_to_am_4.ktr"; 
     */  
    public static void runJob(String[] params, String jobPath) {  
        try {  
            KettleEnvironment.init();  
            // jobname 鏄疛ob鑴氭湰鐨勮矾寰勫強鍚嶇О  
            JobMeta jobMeta = new JobMeta(jobPath, null);  
            Job job = new Job(null, jobMeta);  
            // 鍚慗ob 鑴氭湰浼犻�鍙傛暟锛岃剼鏈腑鑾峰彇鍙傛暟鍊硷細${鍙傛暟鍚峿  
            // job.setVariable(paraname, paravalue);  
            job.setVariable("patientid", params[0]);  
            job.setVariable("clinicno", params[1]);  
            job.setVariable("hiscode", params[2]);  
            job.start();  
            job.waitUntilFinished();  	
            if (job.getErrors() > 0) {  
                throw new Exception(  
                        "There are errors during job exception!(鎵цjob鍙戠敓寮傚父)");  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  

	
}
