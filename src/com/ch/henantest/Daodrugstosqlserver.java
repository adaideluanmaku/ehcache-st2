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

public class Daodrugstosqlserver {
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
	public int Quchong1(String drugcode) throws ClassNotFoundException, SQLException{
		Mysqlconnection jdbc2=new Mysqlconnection();
		Connection conn2=jdbc2.getConn();
		
		String sql="select count(*) from mc_dict_drug_pass where drugcode='"+drugcode+"' and match_scheme=1";
		Statement st=conn2.createStatement();
		ResultSet rs=st.executeQuery(sql);
		rs.next();
		sum=Integer.parseInt(rs.getObject(1).toString());
		rs.close();
		st.close();
		conn2.close();
		return sum;
	}
	public void Drug(String drug_unique_code) throws ClassNotFoundException, SQLException{
		if("".equals(drug_unique_code)){
			return;
		}
		Jdbcconnection1 jdbc1=new Jdbcconnection1();
		Connection conn1=jdbc1.getConn();
		
		Mysqlconnection1 jdbc2=new Mysqlconnection1();
		Connection conn2=jdbc2.getConn();
		
		String sql;
		sql="select * from mc_dict_drug_pass where drug_unique_code='"+drug_unique_code+"'";
		Statement st=conn2.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List rslist=jdbc2.getlist(rs);
		if(rslist.size()==0){
			System.out.println("mc_dict_drug_pass未找到药品"+drug_unique_code);
		}
		for(int i=0;i<rslist.size();i++){
			Map map=(Map)rslist.get(i);
			//导入测试工具案例库,sqlserver
			sum=0;
			sum=Quchong(drug_unique_code);
			if(sum==0){
//				System.out.println(map);
				sql="insert into mc_dict_drug_pass(drug_unique_code,drugcode,drugname,drugform,drugspec,comp_name,approvalcode,searchcode,doseunit) values(?,?,?,?,?,?,?,?,?)";
				PreparedStatement st1=conn1.prepareStatement(sql);
				st1.setString(1, map.get("drug_unique_code").toString());
				st1.setString(2, map.get("drugcode").toString());
				st1.setString(3, map.get("drugname").toString());
				if(map.get("drugform")==null || "".equals(map.get("drugform"))){
					st1.setString(4, "");
				}else{
					st1.setString(4, map.get("drugform").toString());
				}
				st1.setString(5, map.get("drugspec").toString());
				st1.setString(6, map.get("comp_name").toString());
				if(map.get("approvalcode")==null || "".equals(map.get("approvalcode"))){
					st1.setString(7, "");
				}else{
					st1.setString(7, map.get("approvalcode").toString());
				}
				if(map.get("searchcode")==null || "".equals(map.get("searchcode"))){
					st1.setString(8, "");
				}else{
					st1.setString(8, map.get("searchcode").toString());
				}
				st1.setString(9, map.get("doseunit").toString());
				boolean stat=st1.execute();
				if(!stat){
					System.out.println("测试工具案例库导入数据成功：");
				}else{
					System.out.println("测试工具案例库导入数据失败：");
				}
				st1.close();
			}else{
				System.out.println("测试工具案例库药品已经存在："+drug_unique_code);
			}
		}
		
		st.close();
		rs.close();
		conn1.close();
		conn2.close();
	}
}
