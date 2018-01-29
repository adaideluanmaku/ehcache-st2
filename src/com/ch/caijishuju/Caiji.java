package com.ch.caijishuju;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ch.jdbc.Mysqlconnection;
import com.ch.jdbc.Mysqlconnection159;

public class Caiji {
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		Mysqlconnection159 jdbc159=new Mysqlconnection159();
		Connection conn159=jdbc159.getConn();
//		String sql159="select * from sa_gather_log group by caseid";
		String sql159="select * from sa_gather_log_copy where logid='7b78d561-a3f4-4089-8eb2-1e3d86438599'";
		Statement st159=conn159.createStatement();
		ResultSet rs159=st159.executeQuery(sql159);
		List list159=jdbc159.getlist(rs159);
		
		Mysqlconnection jdbc115=new Mysqlconnection();
		Connection conn115=jdbc115.getConn();
		// Ĭ�������ÿִ��һ��sql�ύһ�� ,�ر��Զ��ύ�������.commit()ʵ������ִ��sql
		conn115.setAutoCommit(false);
		
		String sql115="insert into sa_gather_log (logid,reqtype,gatherbaseinfo,gatherresult,clientip,reqtime,endtime,issucess,caseid) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst115=conn115.prepareStatement(sql115);
		
		Date time=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println("��ʼ�������ݲ�����"+sdf.format(time));
		//50000�����ݣ�����27��
//		for(int i=0;i<1900;i++){
//			System.out.println("ѭ����"+i+"��");
//			for(int i1=0;i1<list159.size();i1++){
////				System.out.println(i1);
//				Map map159=(Map)list159.get(i1);
//				pst115.setString(1, String.valueOf(map159.get("logid")+"_"+(i+1)));
//				pst115.setInt(2, (int)map159.get("reqtype"));
//				pst115.setString(3, (String)map159.get("gatherbaseinfo"));
//				pst115.setString(4, (String)map159.get("gatherresult"));
//				pst115.setString(5, (String)map159.get("clientip"));
//				pst115.setString(6, (String)map159.get("reqtime"));
//				pst115.setString(7, (String)map159.get("endtime"));
//				pst115.setInt(8, (int)map159.get("issucess"));
//				pst115.setString(9, (String)map159.get("caseid"));
//				pst115.addBatch();
//			}
//		}
		
		//50000�����ݣ��޸�CASEID�����Ƕ�������
		for(int i=0;i<20000;i++){
			System.out.println("ѭ����"+i+"��");
			for(int i1=0;i1<list159.size();i1++){
//				System.out.println(i1);
				Map map159=(Map)list159.get(i1);
				pst115.setString(1, String.valueOf(map159.get("logid")+"_"+(i+1)));
				pst115.setInt(2, (int)map159.get("reqtype"));
				pst115.setString(3, (String)map159.get("gatherbaseinfo"));
				pst115.setString(4, (String)map159.get("gatherresult"));
				pst115.setString(5, (String)map159.get("clientip"));
				pst115.setString(6, (String)map159.get("reqtime"));
				pst115.setString(7, (String)map159.get("endtime"));
				pst115.setInt(8, (int)map159.get("issucess"));
				pst115.setString(9, (String)map159.get("caseid")+"_"+(i+1));
				pst115.addBatch();
			}
		}
		pst115.executeBatch();
		//���setAutoCommit(false)����������ִ��SQL
		conn115.commit();  
		Date time1=new Date();
		System.out.println("���ݵ���ɹ�"+sdf.format(time1));
		pst115.close();
		conn115.close();
		rs159.close();
		st159.close();
		conn159.close();
	}
}
