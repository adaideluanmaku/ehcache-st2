package com.ch.BStool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Sqlrun {
	public void Copydictdept() throws ClassNotFoundException, SQLException{
		Sqlserversqlconnection jdbc=new Sqlserversqlconnection();
		Connection sqlserverconn=jdbc.getConn();
		Statement st=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql=null;
		List list=null;
		
		sql="select * from mc_dict_dept where deptcode<>'-1' and match_scheme=0";
		st=sqlserverconn.createStatement();
		rs=st.executeQuery(sql);
		list=jdbc.getlist(rs);
		
		sql="insert into mc_dict_dept(inserttime, clinic_infusion, sortid, is_clinic, searchcode, deptcode, "
				+ "is_inhosp, match_scheme, is_emergency, is_save, deptname, status) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		pst=sqlserverconn.prepareStatement(sql);
		
		sqlserverconn.setAutoCommit(false);
		for(int j=0;j<10;j++){
			for(int i=0;i<list.size();i++){
				Map map=(Map)list.get(i);
				
				pst.setString(1,map.get("inserttime").toString());//inserttime
				pst.setString(2,map.get("clinic_infusion").toString());//clinic_infusion
				pst.setString(3,map.get("sortid").toString());//sortid
				pst.setString(4,map.get("is_clinic").toString());//is_clinic
				pst.setString(5,map.get("searchcode").toString());//searchcode
				pst.setString(6,map.get("deptcode").toString()+"_ch_"+j);//deptcode
				pst.setString(7,map.get("is_inhosp").toString());//is_inhosp
				pst.setString(8,map.get("match_scheme").toString());//match_scheme
				pst.setString(9,map.get("is_emergency").toString());//is_emergency
				pst.setString(10,map.get("is_save").toString());//is_save
				pst.setString(11,map.get("deptname").toString()+"_ch_"+j);//deptname
				pst.setString(12,map.get("status").toString());//status
				
				pst.addBatch();
			}
		}
		pst.executeBatch();
		sqlserverconn.commit();
		
		pst.close();
		st.close();
		rs.close();
		sqlserverconn.close();
	}
	
	public void Copydictdoctor() throws ClassNotFoundException, SQLException{
		Sqlserversqlconnection jdbc=new Sqlserversqlconnection();
		Connection sqlserverconn=jdbc.getConn();
		Statement st=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql=null;
		List list=null;
		
		sql="select * from mc_dict_doctor where doctorcode<>'-1' and match_scheme=0";
		st=sqlserverconn.createStatement();
		rs=st.executeQuery(sql);
		list=jdbc.getlist(rs);
		
		sql="insert into mc_dict_doctor(isselect, inserttime, is_clinic, mobile, deptcode, telephone, match_scheme, "
				+ "doctorname, doctorcode, is_save, deptname, doctorlevel, password, antilevel, prespriv, ilevel, "
				+ "searchcode, status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		pst=sqlserverconn.prepareStatement(sql);
		
		sqlserverconn.setAutoCommit(false);
		for(int j=0;j<10;j++){
			for(int i=0;i<list.size();i++){
				Map map=(Map)list.get(i);
				
				pst.setString(1,map.get("isselect").toString());//isselect
				pst.setString(2,map.get("inserttime").toString());//inserttime
				pst.setString(3,map.get("is_clinic").toString());//is_clinic
				pst.setString(4,map.get("mobile").toString());//mobile
				pst.setString(5,map.get("deptcode").toString()+"_ch_"+j);//deptcode
				pst.setString(6,map.get("telephone").toString());//telephone
				pst.setString(7,map.get("match_scheme").toString());//match_scheme
				pst.setString(8,map.get("doctorname").toString()+"_ch_"+j);//doctorname
				pst.setString(9,map.get("doctorcode").toString()+"_ch_"+j);//doctorcode
				pst.setString(10,map.get("is_save").toString());//is_save
				pst.setString(11,map.get("deptname").toString()+"_ch_"+j);//deptname
				pst.setString(12,map.get("doctorlevel").toString());//doctorlevel
				pst.setString(13,map.get("password").toString());//password
				pst.setString(14,map.get("antilevel").toString());//antilevel
				pst.setString(15,map.get("prespriv").toString());//prespriv
				pst.setString(16,map.get("ilevel").toString());//ilevel
				pst.setString(17,map.get("searchcode").toString());//searchcode
				pst.setString(18,map.get("status").toString());//status
				
				pst.addBatch();
			}
		}
		pst.executeBatch();
		sqlserverconn.commit();
		
		pst.close();
		st.close();
		rs.close();
		sqlserverconn.close();
	}
	
	public void Copydictallergen() throws ClassNotFoundException, SQLException{
		Sqlserversqlconnection jdbc=new Sqlserversqlconnection();
		Connection sqlserverconn=jdbc.getConn();
		Statement st=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql=null;
		List list=null;
		
		sql="select * from mc_dict_allergen where allercode<>'-1' and match_scheme=0";
		st=sqlserverconn.createStatement();
		rs=st.executeQuery(sql);
		list=jdbc.getlist(rs);
		
		sql="insert into mc_dict_allergen(unable_match, inserttime, pass_allertype, allercode, match_time, "
				+ "match_scheme, match_user, unable_match_desc, is_save, pass_allerid, pass_allername, "
				+ "allername, searchcode, order_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		pst=sqlserverconn.prepareStatement(sql);
		
		sqlserverconn.setAutoCommit(false);
		for(int j=0;j<10;j++){
			for(int i=0;i<list.size();i++){
				Map map=(Map)list.get(i);
				
				pst.setString(1,map.get("unable_match").toString());//unable_match
				pst.setString(2,map.get("inserttime").toString());//inserttime
				pst.setString(3,map.get("pass_allertype").toString());//pass_allertype
				pst.setString(4,map.get("allercode").toString()+"_ch_"+j);//allercode
				pst.setString(5,map.get("match_time").toString());//match_time
				pst.setString(6,map.get("match_scheme").toString());//match_scheme
				pst.setString(7,map.get("match_user").toString());//match_user
				pst.setString(8,map.get("unable_match_desc").toString());//unable_match_desc
				pst.setString(9,map.get("is_save").toString());//is_save
				pst.setString(10,map.get("pass_allerid").toString());//pass_allerid
				pst.setString(11,map.get("pass_allername").toString());//pass_allername
				pst.setString(12,map.get("allername").toString()+"_ch_"+j);//allername
				pst.setString(13,map.get("searchcode").toString());//searchcode
				if(map.get("order_id")==null || "".equals(map.get("order_id"))){
					pst.setString(14,"");//order_id
				}else{
					pst.setString(14,map.get("order_id").toString());//order_id
				}
				
				pst.addBatch();
			}
		}
		pst.executeBatch();
		sqlserverconn.commit();
		
		
		pst.close();
		st.close();
		rs.close();
		sqlserverconn.close();
	}
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		Sqlrun sqlrun=new Sqlrun();
		
		//制造科室
//		sqlrun.Copydictdept();
		
		//制造医生
//		sqlrun.Copydictdoctor();
		
		//过敏原
		sqlrun.Copydictallergen();
	}
}
