package com.ch.etl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 计算脚本导数据条数
 * @author 陈辉
 *
 */
public class Datarun {
	public static List list;
	public static String sql=null;
	public static PreparedStatement pst;
	public static Statement st;
	public static ResultSet rs;
	public static int sum;
	public static int sum1;
	
	public static void T_mz(String starttime,String endtime) throws ClassNotFoundException, SQLException{
		System.out.println("===========��ʼ�Ա�������ʱ��===========");
		Jdbcconnection jdbc=new Jdbcconnection();
		Connection conn=jdbc.getConn();
		
		Mysqlconnection jdbc1=new Mysqlconnection();
		Connection conn1=jdbc1.getConn();
		
		//�������ԭ��ʱ��-ԭʼ����
		sql="select 'Mz'||a.patientid||'_'||a.clinicno as caseid , a.* from mdc3_mz_allergen_view a  inner join mdc3_mz_cost_view  b on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.clinicno=b.clinicno where   b.costtime>=? and b.costtime<?";
		pst=conn.prepareStatement(sql);
		pst.setString(1, starttime);
		pst.setString(2, endtime);
		rs=pst.executeQuery();
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_clinic_allergen";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("����ԭ��ʱ��:t_mc_clinic_allergen");
		System.out.println(sum+":"+sum1);
		
		//���Ｒ����ʱ��-ԭʼ����
		sql="select 'Mz'||a.patientid||'_'||a.clinicno as caseid , a.* from mdc3_mz_disease_view a  inner join mdc3_mz_cost_view  b on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.clinicno=b.clinicno where   b.costtime>=? and b.costtime<? ";
		pst=conn.prepareStatement(sql);
		pst.setString(1, starttime);
		pst.setString(2, endtime);
		rs=pst.executeQuery();
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_clinic_disease";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("����ԭ��ʱ��:t_mc_clinic_disease");
		System.out.println(sum+":"+sum1);
		
		//����exam��ʱ��-ԭʼ����
		sql="select 'Mz'||a.patientid||'_'||a.clinicno as caseid , a.* from mdc3_mz_exam_view a  inner join mdc3_mz_cost_view  b on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.clinicno=b.clinicno where   b.costtime>=? and b.costtime<? ";
		pst=conn.prepareStatement(sql);
		pst.setString(1, starttime);
		pst.setString(2, endtime);
		rs=pst.executeQuery();
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_clinic_exam";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("����ԭ��ʱ��:t_mc_clinic_exam");
		System.out.println(sum+":"+sum1);
		
		//����lab��ʱ��-ԭʼ����
		sql="select 'Mz'||a.patientid||'_'||a.clinicno as caseid , a.* from mdc3_mz_lab_view a  inner join mdc3_mz_cost_view  b on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.clinicno=b.clinicno where   b.costtime>=? and b.costtime<? ";
		pst=conn.prepareStatement(sql);
		pst.setString(1, starttime);
		pst.setString(2, endtime);
		rs=pst.executeQuery();
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_clinic_lab";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("����ԭ��ʱ��:t_mc_clinic_lab");
		System.out.println(sum+":"+sum1);
		
		//����ҽ����ʱ��-ԭʼ����
		sql="select 'Mz'||a.patientid||'_'||a.clinicno as caseid , 0 as is_allow,a.* from mdc3_mz_orders_view a inner join mdc3_mz_cost_view  b on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.clinicno=b.clinicno where   b.costtime>=? and b.costtime<? ";
		pst=conn.prepareStatement(sql);
		pst.setString(1, starttime);
		pst.setString(2, endtime);
		rs=pst.executeQuery();
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_clinic_order";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("����ԭ��ʱ��:t_mc_clinic_order");
		System.out.println(sum+":"+sum1);
		
		//���ﲡ����ʱ��-ԭʼ����
		sql="select 'Mz'||a.patientid||'_'||a.clinicno as caseid , a.* from mdc3_mz_patient_view a  inner join mdc3_mz_cost_view  b on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.clinicno=b.clinicno where   b.costtime>=? and b.costtime<? ";
		pst=conn.prepareStatement(sql);
		pst.setString(1, starttime);
		pst.setString(2, endtime);
		rs=pst.executeQuery();
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_clinic_patient";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("����ԭ��ʱ��:t_mc_clinic_patient");
		System.out.println(sum+":"+sum1);
		
		rs.close();
		pst.close();
		conn.close();
		st.close();
		conn1.close();
	}

	public static void T_zy(String starttime,String endtime) throws ClassNotFoundException, SQLException{
		System.out.println("===========��ʼ�Ա�סԺ��ʱ��===========");
		Jdbcconnection jdbc=new Jdbcconnection();
		Connection conn=jdbc.getConn();
		
		Mysqlconnection jdbc1=new Mysqlconnection();
		Connection conn1=jdbc1.getConn();
		
		//סԺ����ԭ��ʱ��-ԭʼ����
		sql="select 'Zy'||a.patientid||'_'||a.visitid as caseid , a.* from mdc3_zy_allergen_view a  inner join  mdc3_zy_patient_view  b  on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.visitid=b.visitid where b.i_in=1 ";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_inhosp_allergen";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("����ԭ��ʱ��:t_mc_inhosp_allergen");
		System.out.println(sum+":"+sum1);
		
		//סԺ������ʱ��-ԭʼ����
		sql="select 'Zy'||a.patientid||'_'||a.visitid as caseid , a.* from mdc3_zy_disease_view a  inner join  mdc3_zy_patient_view  b  on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.visitid=b.visitid where b.i_in=1";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_inhosp_disease";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("����ԭ��ʱ��:t_mc_inhosp_disease");
		System.out.println(sum+":"+sum1);
		
		//סԺexam��ʱ��-ԭʼ����
		sql="select 'Zy'||a.patientid||'_'||a.visitid as caseid , a.* from mdc3_zy_exam_view a  inner join  mdc3_zy_patient_view  b  on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.visitid=b.visitid where b.i_in=1";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_inhosp_exam";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("����ԭ��ʱ��:t_mc_inhosp_exam");
		System.out.println(sum+":"+sum1);
		
		//סԺlab��ʱ��-ԭʼ����
		sql="select 'Zy'||a.patientid||'_'||a.visitid as caseid , a.* from mdc3_ZY_LAB_VIEW a  inner join  mdc3_zy_patient_view  b  on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.visitid=b.visitid where b.i_in=1";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_inhosp_lab";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("����ԭ��ʱ��:t_mc_inhosp_lab");
		System.out.println(sum+":"+sum1);
		
		//סԺҽ����ʱ��-ԭʼ����
		sql="select 'Zy'||a.patientid||'_'||a.visitid as caseid , a.* from mdc3_zy_orders_view a  inner join  mdc3_zy_patient_view  b  on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.visitid=b.visitid where b.i_in=1";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_inhosp_order";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("����ԭ��ʱ��:t_mc_inhosp_order");
		System.out.println(sum+":"+sum1);
		
		//סԺ������ʱ��-ԭʼ����
		sql="select 'Zy'||a.patientid||'_'||a.visitid as caseid , a.* from mdc3_zy_patient_view a where a.i_in=1 ";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_inhosp_patient";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("������ʱ��:t_mc_inhosp_patient");
		System.out.println(sum+":"+sum1);
		
		//סԺ������ʱ��-ԭʼ����
		sql="select 'Zy'||a.patientid||'_'||a.visitid as caseid , a.* from mdc3_zy_operation_view a  inner join  mdc3_zy_patient_view  b  on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.visitid=b.visitid where b.i_in=1";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_inhosp_operation";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("������ʱ��:t_mc_inhosp_operation");
		System.out.println(sum+":"+sum1);
				
		rs.close();
		conn.close();
		conn1.close();
	}
	
	public static void T_cy(String starttime,String endtime) throws ClassNotFoundException, SQLException{
		System.out.println("===========��ʼ�Աȳ�Ժ��ʱ��===========");
		Jdbcconnection jdbc=new Jdbcconnection();
		Connection conn=jdbc.getConn();
		
		Mysqlconnection jdbc1=new Mysqlconnection();
		Connection conn1=jdbc1.getConn();
		
		//��Ժ����ԭ��ʱ��-ԭʼ����
		sql="select 'Zy'||a.patientid||'_'||a.visitid as caseid , a.* from mdc3_zy_allergen_view  a  inner join  mdc3_zy_patient_view  b  on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.visitid=b.visitid where b.i_in=0 and b.enddate>=? and b.enddate<?";
		pst=conn.prepareStatement(sql);
		pst.setString(1, starttime);
		pst.setString(2, endtime);
		rs=pst.executeQuery();
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_outhosp_allergen";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("����ԭ��ʱ��:t_mc_outhosp_allergen");
		System.out.println(sum+":"+sum1);
		
		//��Ժ������ʱ��-ԭʼ����
		sql="select 'Zy'||a.patientid||'_'||a.visitid as caseid , a.* from mdc3_zy_disease_view a  inner join  mdc3_zy_patient_view  b  on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.visitid=b.visitid where b.i_in=0 and b.enddate>=? and b.enddate<?";
		pst=conn.prepareStatement(sql);
		pst.setString(1, starttime);
		pst.setString(2, endtime);
		rs=pst.executeQuery();
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_outhosp_disease";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("����ԭ��ʱ��:t_mc_outhosp_disease");
		System.out.println(sum+":"+sum1);
		
		//��Ժexam��ʱ��-ԭʼ����
		sql="select 'Zy'||a.patientid||'_'||a.visitid as caseid , a.* from mdc3_zy_exam_view  a  inner join  mdc3_zy_patient_view  b  on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.visitid=b.visitid where b.i_in=0 and b.enddate>=? and b.enddate<? ";
		pst=conn.prepareStatement(sql);
		pst.setString(1, starttime);
		pst.setString(2, endtime);
		rs=pst.executeQuery();
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_outhosp_exam";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("����ԭ��ʱ��:t_mc_outhosp_exam");
		System.out.println(sum+":"+sum1);
		
		//��Ժlab��ʱ��-ԭʼ����
		sql="select 'Zy'||a.patientid||'_'||a.visitid as caseid , a.* from mdc3_zy_lab_view a  inner join  mdc3_zy_patient_view  b  on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.visitid=b.visitid where b.i_in=0 and b.enddate>=? and b.enddate<? ";
		pst=conn.prepareStatement(sql);
		pst.setString(1, starttime);
		pst.setString(2, endtime);
		rs=pst.executeQuery();
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_outhosp_lab";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("����ԭ��ʱ��:t_mc_outhosp_lab");
		System.out.println(sum+":"+sum1);
		
		//��Ժҽ����ʱ��-ԭʼ����
		sql="select 'Zy'||a.patientid||'_'||a.visitid as caseid , a.* from mdc3_zy_orders_view a  inner join  mdc3_zy_patient_view  b  on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.visitid=b.visitid where b.i_in=0 and b.enddate>=? and b.enddate<? ";
		pst=conn.prepareStatement(sql);
		pst.setString(1, starttime);
		pst.setString(2, endtime);
		rs=pst.executeQuery();
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_outhosp_order";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("����ԭ��ʱ��:t_mc_outhosp_order");
		System.out.println(sum+":"+sum1);
		
		//��Ժ������ʱ��-ԭʼ����
		sql="select 'Zy'||a.patientid||'_'||a.visitid as caseid , a.* from   mdc3_zy_patient_view a where a.i_in=0 and a.enddate>=? and a.enddate<? ";
		pst=conn.prepareStatement(sql);
		pst.setString(1, starttime);
		pst.setString(2, endtime);
		rs=pst.executeQuery();
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_outhosp_patient";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("������ʱ��:t_mc_outhosp_patient");
		System.out.println(sum+":"+sum1);
		
		//��Ժ������ʱ��-ԭʼ����
		sql="select 'Zy'||a.patientid||'_'||a.visitid as caseid , a.* from mdc3_zy_operation_view a  inner join  mdc3_zy_patient_view  b  on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.visitid=b.visitid where b.i_in=0 and b.enddate>=? and b.enddate<? ";
		pst=conn.prepareStatement(sql);
		pst.setString(1, starttime);
		pst.setString(2, endtime);
		rs=pst.executeQuery();
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_outhosp_operation";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("������ʱ��:t_mc_outhosp_operation");
		System.out.println(sum+":"+sum1);
				
		rs.close();
		pst.close();
		conn.close();
		st.close();
		conn1.close();
	}
	
	public static void T_zdb(String starttime,String endtime) throws ClassNotFoundException, SQLException{
		System.out.println("===========��ʼ�Ա��ֵ����ʱ��===========");
		Jdbcconnection jdbc=new Jdbcconnection();
		Connection conn=jdbc.getConn();
		
		Mysqlconnection jdbc1=new Mysqlconnection();
		Connection conn1=jdbc1.getConn();
		
		//����ԭ��ʱ��-ԭʼ����
		sql="SELECT 0 as is_save , a.* FROM mdc3_dict_allergen_view a";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_dict_allergen";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("����ԭ��ʱ��:t_mc_dict_allergen");
		System.out.println(sum+":"+sum1);
		
		//������ʱ��-ԭʼ����
		sql="SELECT -1 as type,-1 as is_mxb,0 as is_save ,a.* FROM mdc3_dict_disease_view a";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_dict_disease";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("������ʱ��:t_mc_dict_disease");
		System.out.println(sum+":"+sum1);
		
		//costitem��ʱ��-ԭʼ����
		sql="SELECT 0 as is_save , a.* FROM mdc3_dict_cost_item_view a";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_dict_costitem";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("costitem��ʱ��:t_mc_dict_costitem");
		System.out.println(sum+":"+sum1);
		
		//������ʱ��-ԭʼ����
		sql="SELECT 0 as is_save ,a.* FROM mdc3_dict_dept_view a";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_dict_dept";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("������ʱ��:t_mc_dict_dept");
		System.out.println(sum+":"+sum1);
		
		//ҽ����ʱ��-ԭʼ����
		sql="SELECT 0 as is_save , a.* FROM mdc3_dict_doctor_view a";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_dict_doctor";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("ҽ����ʱ��:t_mc_dict_doctor");
		System.out.println(sum+":"+sum1);
		
		//ҩƷ��ʱ��-ԭʼ����
		sql="SELECT '' as socialsecurity_ratio ,0 as is_save , a.* FROM mdc3_dict_drug_view a";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_dict_drug";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("ҩƷ��ʱ��:t_mc_dict_drug");
		System.out.println(sum+":"+sum1);
		
		//exam��ʱ��-ԭʼ����
		sql="SELECT * FROM mdc3_dict_exam_view";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_dict_exam";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("exam��ʱ��:t_mc_dict_exam");
		System.out.println(sum+":"+sum1);
				
		//Ƶ����ʱ��-ԭʼ����
		sql="SELECT 0 as is_save ,a.* FROM mdc3_dict_frequency_view a";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_dict_frequency";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("Ƶ����ʱ��:t_mc_dict_frequency");
		System.out.println(sum+":"+sum1);
			
		//lab��ʱ��-ԭʼ����
		sql="SELECT 0 as is_save, a.* FROM mdc3_dict_lab_view a";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_dict_lab";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("lab��ʱ��:t_mc_dict_lab");
		System.out.println(sum+":"+sum1);
				
		//labsub��ʱ��-ԭʼ����
		sql="SELECT 0 as is_save ,a.* FROM mdc3_dict_lab_item_view a";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_dict_labsub";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("labsub��ʱ��:t_mc_dict_labsub");
		System.out.println(sum+":"+sum1);		
				
		//������ʱ��-ԭʼ����
		sql="SELECT 0 as is_save,a.* FROM mdc3_dict_operation_view a";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_dict_operation";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("������ʱ��:t_mc_dict_operation");
		System.out.println(sum+":"+sum1);				
				
		//��ҩ;����ʱ��-ԭʼ����
		sql="SELECT 0 as is_save , a.* FROM mdc3_dict_route_view a";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		sum=0;
		while(rs.next()){
			sum=sum+1;
		}
		
		sql="select count(*) from t_mc_dict_route";
		st=conn1.createStatement();
		rs=st.executeQuery(sql);
		rs.next();
		sum1=Integer.parseInt(rs.getObject(1).toString());
		
		System.out.println("��ҩ;����ʱ��:t_mc_dict_route");
		System.out.println(sum+":"+sum1);	
				
		rs.close();
		conn.close();
		conn1.close();
	}
	
//	public static void Zsb_mz() throws ClassNotFoundException, SQLException{
//		System.out.println("===========��ʼ�Ա�������ʱ��===========");
//		Jdbcconnection jdbc=new Jdbcconnection();
//		Connection conn=jdbc.getConn();
//		
//		Mysqlconnection jdbc1=new Mysqlconnection();
//		Connection conn1=jdbc1.getConn();
//		
//		//�������ԭ��ʱ��-ԭʼ����
//		sql="select 'Mz'||a.patientid||'_'||a.clinicno as caseid , a.* from mdc3_mz_allergen_view a  inner join mdc3_mz_cost_view  b on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.clinicno=b.clinicno where   b.costtime>=? and b.costtime<?";
//		pst=conn.prepareStatement(sql);
//		pst.setString(1, starttime);
//		pst.setString(2, endtime);
//		rs=pst.executeQuery();
//		sum=0;
//		while(rs.next()){
//			sum=sum+1;
//		}
//		
//		sql="select count(*) from t_mc_clinic_allergen";
//		st=conn1.createStatement();
//		rs=st.executeQuery(sql);
//		rs.next();
//		sum1=Integer.parseInt(rs.getObject(1).toString());
//		
//		System.out.println("����ԭ��ʱ��:t_mc_clinic_allergen");
//		System.out.println(sum+":"+sum1);
//		
//		//���Ｒ����ʱ��-ԭʼ����
//		sql="select 'Mz'||a.patientid||'_'||a.clinicno as caseid , a.* from mdc3_mz_disease_view a  inner join mdc3_mz_cost_view  b on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.clinicno=b.clinicno where   b.costtime>=? and b.costtime<? ";
//		pst=conn.prepareStatement(sql);
//		pst.setString(1, starttime);
//		pst.setString(2, endtime);
//		rs=pst.executeQuery();
//		sum=0;
//		while(rs.next()){
//			sum=sum+1;
//		}
//		
//		sql="select count(*) from t_mc_clinic_disease";
//		st=conn1.createStatement();
//		rs=st.executeQuery(sql);
//		rs.next();
//		sum1=Integer.parseInt(rs.getObject(1).toString());
//		
//		System.out.println("����ԭ��ʱ��:t_mc_clinic_disease");
//		System.out.println(sum+":"+sum1);
//		
//		//����exam��ʱ��-ԭʼ����
//		sql="select 'Mz'||a.patientid||'_'||a.clinicno as caseid , a.* from mdc3_mz_exam_view a  inner join mdc3_mz_cost_view  b on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.clinicno=b.clinicno where   b.costtime>=? and b.costtime<? ";
//		pst=conn.prepareStatement(sql);
//		pst.setString(1, starttime);
//		pst.setString(2, endtime);
//		rs=pst.executeQuery();
//		sum=0;
//		while(rs.next()){
//			sum=sum+1;
//		}
//		
//		sql="select count(*) from t_mc_clinic_exam";
//		st=conn1.createStatement();
//		rs=st.executeQuery(sql);
//		rs.next();
//		sum1=Integer.parseInt(rs.getObject(1).toString());
//		
//		System.out.println("����ԭ��ʱ��:t_mc_clinic_exam");
//		System.out.println(sum+":"+sum1);
//		
//		//����lab��ʱ��-ԭʼ����
//		sql="select 'Mz'||a.patientid||'_'||a.clinicno as caseid , a.* from mdc3_mz_lab_view a  inner join mdc3_mz_cost_view  b on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.clinicno=b.clinicno where   b.costtime>=? and b.costtime<? ";
//		pst=conn.prepareStatement(sql);
//		pst.setString(1, starttime);
//		pst.setString(2, endtime);
//		rs=pst.executeQuery();
//		sum=0;
//		while(rs.next()){
//			sum=sum+1;
//		}
//		
//		sql="select count(*) from t_mc_clinic_lab";
//		st=conn1.createStatement();
//		rs=st.executeQuery(sql);
//		rs.next();
//		sum1=Integer.parseInt(rs.getObject(1).toString());
//		
//		System.out.println("����ԭ��ʱ��:t_mc_clinic_lab");
//		System.out.println(sum+":"+sum1);
//		
//		//����ҽ����ʱ��-ԭʼ����
//		sql="select 'Mz'||a.patientid||'_'||a.clinicno as caseid , 0 as is_allow,a.* from mdc3_mz_orders_view a inner join mdc3_mz_cost_view  b on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.clinicno=b.clinicno where   b.costtime>=? and b.costtime<? ";
//		pst=conn.prepareStatement(sql);
//		pst.setString(1, starttime);
//		pst.setString(2, endtime);
//		rs=pst.executeQuery();
//		sum=0;
//		while(rs.next()){
//			sum=sum+1;
//		}
//		
//		sql="select count(*) from t_mc_clinic_order";
//		st=conn1.createStatement();
//		rs=st.executeQuery(sql);
//		rs.next();
//		sum1=Integer.parseInt(rs.getObject(1).toString());
//		
//		System.out.println("����ԭ��ʱ��:t_mc_clinic_order");
//		System.out.println(sum+":"+sum1);
//		
//		//���ﲡ����ʱ��-ԭʼ����
//		sql="select 'Mz'||a.patientid||'_'||a.clinicno as caseid , a.* from mdc3_mz_patient_view a  inner join mdc3_mz_cost_view  b on  a.hiscode=b.hiscode and a.patientid=b.patientid and a.clinicno=b.clinicno where   b.costtime>=? and b.costtime<? ";
//		pst=conn.prepareStatement(sql);
//		pst.setString(1, starttime);
//		pst.setString(2, endtime);
//		rs=pst.executeQuery();
//		sum=0;
//		while(rs.next()){
//			sum=sum+1;
//		}
//		
//		sql="select count(*) from t_mc_clinic_patient";
//		st=conn1.createStatement();
//		rs=st.executeQuery(sql);
//		rs.next();
//		sum1=Integer.parseInt(rs.getObject(1).toString());
//		
//		System.out.println("����ԭ��ʱ��:t_mc_clinic_patient");
//		System.out.println(sum+":"+sum1);
//		
//		rs.close();
//		pst.close();
//		conn.close();
//		st.close();
//		conn1.close();
//	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String stime="2012-05-07";
		String etime="2012-05-08";
		T_mz(stime,etime);
		T_zy(stime,etime);
		T_cy(stime,etime);
		T_zdb(stime,etime);
	}
}
