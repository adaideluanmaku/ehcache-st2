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

public class Route {
	int sum=0;
	public int Quchong(String routecode) throws ClassNotFoundException, SQLException{
		Jdbcconnection1 jdbc1=new Jdbcconnection1();
		Connection conn1=jdbc1.getConn();
		
		String sql="select count(*) from mc_dict_route where routecode='"+routecode+"'";
		Statement st=conn1.createStatement();
		ResultSet rs=st.executeQuery(sql);
		rs.next();
		sum=Integer.parseInt(rs.getObject(1).toString());
		rs.close();
		st.close();
		conn1.close();
		return sum;
	}
	public int Quchong1(String routecode,int match_scheme) throws ClassNotFoundException, SQLException{
		Mysqlconnection jdbc2=new Mysqlconnection();
		Connection conn2=jdbc2.getConn();
		
		String sql="select count(*) from mc_dict_route where routecode='"+routecode+"' and match_scheme="+match_scheme;
		Statement st=conn2.createStatement();
		ResultSet rs=st.executeQuery(sql);
		rs.next();
		sum=Integer.parseInt(rs.getObject(1).toString());
		rs.close();
		st.close();
		conn2.close();
		return sum;
	}
	public void All(String routecode,int match_scheme) throws ClassNotFoundException, SQLException{
		if("".equals(routecode)){
			return;
		}
		Jdbcconnection jdbc=new Jdbcconnection();
		Connection conn=jdbc.getConn();
		
		Jdbcconnection1 jdbc1=new Jdbcconnection1();
		Connection conn1=jdbc1.getConn();
		
		Mysqlconnection jdbc2=new Mysqlconnection();
		Connection conn2=jdbc2.getConn();
		
		String sql;
		sql="select * from mc_route_name where routeid='"+routecode+"'";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		List rslist=jdbc.getlist(rs);
		if(rslist.size()==0){
			System.out.println("mc_allergen未找到过敏史"+routecode);
		}
		for(int i=0;i<rslist.size();i++){
			Map map=(Map)rslist.get(i);
			//导入测试工具案例库,sqlserver
			sum=0;
			sum=Quchong(routecode);
			if(sum==0){
				sql="insert into mc_dict_route(routecode,routename,searchcode,is_save) values(?,?,?,?)";
				PreparedStatement st1=conn1.prepareStatement(sql);
				st1.setString(1, map.get("routeid").toString());
				st1.setString(2, map.get("route_name").toString());
				st1.setString(3, map.get("search_code").toString());
				st1.setInt(4, 0);
				boolean stat=st1.execute();
				if(!stat){
					System.out.println("测试工具案例库导入数据成功：");
				}else{
					System.out.println("测试工具案例库导入数据失败：");
				}
				st1.close();
			}else{
				System.out.println("测试工具案例库过敏原已经存在："+routecode);
			}
			
			//导入配对表,mysql
			sum=0;
			sum=Quchong1(routecode,match_scheme);
			if(sum==0){
				sql="insert into mc_dict_route(routecode,routename,searchcode,is_save,match_scheme,pass_routeid) values(?,?,?,?,?,?)";
				PreparedStatement st2=conn2.prepareStatement(sql);
				st2.setString(1, map.get("routeid").toString());
				st2.setString(2, map.get("route_name").toString());
				st2.setString(3, map.get("search_code").toString());
				st2.setInt(4, 0);
				st2.setInt(5, match_scheme);
				st2.setString(6, map.get("routeid").toString());
				boolean stat1=st2.execute();
				if(!stat1){
					System.out.println("配对表导入数据成功：");
				}else{
					System.out.println("配对表导入数据失败：");
				}
				st2.close();
			}else{
				System.out.println("配对表过敏源已经存在："+routecode);
			}
		}
		
		st.close();
		rs.close();
		conn.close();
		conn1.close();
		conn2.close();
	}
}
