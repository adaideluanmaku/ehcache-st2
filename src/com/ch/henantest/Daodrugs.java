package com.ch.henantest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.ch.henantest.Jdbcconnection;
import com.ch.henantest.Jdbcconnection1;
import com.ch.henantest.Mysqlconnection;

public class Daodrugs {
	int sum=0;
	public int Quchong(String drugcode) throws ClassNotFoundException, SQLException{
		Jdbcconnection1 jdbc1=new Jdbcconnection1();
		Connection conn1=jdbc1.getConn();
		
		String sql="select count(*) from mc_dict_drug_pass where drugcode='"+drugcode+"' and drug_unique_code='"+drugcode+"'";
		Statement st=conn1.createStatement();
		ResultSet rs=st.executeQuery(sql);
		rs.next();
		sum=Integer.parseInt(rs.getObject(1).toString());
		rs.close();
		st.close();
		conn1.close();
		return sum;
	}
	public int Quchong1(String drugcode,int match_scheme) throws ClassNotFoundException, SQLException{
		Mysqlconnection jdbc2=new Mysqlconnection();
		Connection conn2=jdbc2.getConn();
		
		String sql="select count(*) from mc_dict_drug_pass where drugcode='"+drugcode+"' and match_scheme="+ match_scheme;
		Statement st=conn2.createStatement();
		ResultSet rs=st.executeQuery(sql);
		rs.next();
		sum=Integer.parseInt(rs.getObject(1).toString());
		rs.close();
		st.close();
		conn2.close();
		return sum;
	}
	public int Quchong2(String drugcode,int match_scheme) throws ClassNotFoundException, SQLException{
		Mysqlconnection jdbc2=new Mysqlconnection();
		Connection conn2=jdbc2.getConn();
		
		String sql="select count(*) from mc_dict_drug where drugcode='"+drugcode+"' and match_scheme="+ match_scheme;
		Statement st=conn2.createStatement();
		ResultSet rs=st.executeQuery(sql);
		rs.next();
		sum=Integer.parseInt(rs.getObject(1).toString());
		rs.close();
		st.close();
		conn2.close();
		return sum;
	}
	public void Drug(String drugcode, int match_scheme) throws ClassNotFoundException, SQLException{
		if("".equals(drugcode)){
			return;
		}
		//从字典表里面去数据
		Jdbcconnection jdbc=new Jdbcconnection();
		Connection conn=jdbc.getConn();
		
		//写入客户端案例库
		Jdbcconnection1 jdbc1=new Jdbcconnection1();
		Connection conn1=jdbc1.getConn();
		
		//写入JAVA案例库
		Mysqlconnection jdbc2=new Mysqlconnection();
		Connection conn2=jdbc2.getConn();
		
		String sql;
		sql="select * from mc_drugs where drugcode='"+drugcode+"'";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List rslist=jdbc.getlist(rs);
		if(rslist.size()==0){
			System.out.println("mc_drugsδ�ҵ�ҩƷ"+drugcode);
		}
		for(int i=0;i<rslist.size();i++){
			Map map=(Map)rslist.get(i);
			//������Թ��߰�����,sqlserver
			sum=0;
			sum=Quchong(drugcode);
			if(sum==0){
				sql="insert into mc_dict_drug_pass(drug_unique_code,drugcode,drugname,drugform,drugspec,comp_name,approvalcode,searchcode,doseunit) values(?,?,?,?,?,?,?,?,?)";
				PreparedStatement st1=conn1.prepareStatement(sql);
				st1.setString(1, map.get("drugcode").toString());
				st1.setString(2, map.get("drugcode").toString());
				st1.setString(3, map.get("drugname").toString());
				st1.setString(4, map.get("form_name").toString());
				st1.setString(5, map.get("st_strength").toString());
				st1.setString(6, map.get("st_comp_name").toString());
				st1.setString(7, map.get("approvalcode").toString());
				st1.setString(8, map.get("form_search_code").toString());
				st1.setString(9, map.get("doseunit").toString());
				boolean stat=st1.execute();
				if(!stat){
					System.out.println("���Թ��߰����⵼�����ݳɹ���");
				}else{
					System.out.println("���Թ��߰����⵼������ʧ�ܣ�");
				}
				st1.close();
			}else{
				System.out.println("���Թ��߰�����ҩƷ�Ѿ����ڣ�"+drugcode);
			}
			
			//������Ա�,mysql
			sum=0;
			sum=Quchong1(drugcode,match_scheme);
			if(sum==0){
				sql="insert into mc_dict_drug_pass(drug_unique_code,drugcode,drugname,drugform,drugspec,comp_name,approvalcode,searchcode,doseunit,match_scheme,pass_drugcode,pass_doseunit,pass_drugname) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement st2=conn2.prepareStatement(sql);
				st2.setString(1, map.get("drugcode").toString());
				st2.setString(2, map.get("drugcode").toString());
				st2.setString(3, map.get("drugname").toString());
				st2.setString(4, map.get("form_name").toString());
				st2.setString(5, map.get("st_strength").toString());
				st2.setString(6, map.get("st_comp_name").toString());
				st2.setString(7, map.get("approvalcode").toString());
				st2.setString(8, map.get("form_search_code").toString());
				st2.setString(9, map.get("doseunit").toString());
				st2.setInt(10, match_scheme);
				st2.setString(11, map.get("drugcode").toString());
				st2.setString(12, map.get("doseunit").toString());
				st2.setString(13, map.get("drugname").toString());
				boolean stat1=st2.execute();
				if(!stat1){
					System.out.println("mc_dict_drug_pass��Ա������ݳɹ���");
				}else{
					System.out.println("mc_dict_drug_pass��Ա�������ʧ�ܣ�");
				}
				st2.close();
			}else{
				System.out.println("mc_dict_drug_pass��Ա�ҩƷ�Ѿ����ڣ�"+drugcode);
			}
			
			//������Ա�,mysql
			sum=0;
			sum=Quchong2(drugcode,match_scheme);
			if(sum==0){
				sql="insert into mc_dict_drug(drugcode,drugname,drugform,searchcode,match_scheme) values(?,?,?,?,?)";
				PreparedStatement st2=conn2.prepareStatement(sql);
				st2.setString(1, map.get("drugcode").toString());
				st2.setString(2, map.get("drugname").toString());
				st2.setString(3, map.get("form_name").toString());
				st2.setString(4, map.get("form_search_code").toString());
				st2.setInt(5, match_scheme);
				boolean stat1=st2.execute();
				if(!stat1){
					System.out.println("mc_dict_drug��Ա������ݳɹ���");
				}else{
					System.out.println("mc_dict_drug��Ա�������ʧ�ܣ�");
				}
				st2.close();
			}else{
				System.out.println("mc_dict_drug��Ա�ҩƷ�Ѿ����ڣ�"+drugcode);
			}
		}
		
		st.close();
		rs.close();
		conn.close();
		conn1.close();
		conn2.close();
	}
}
