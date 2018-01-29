package com.ch.datatojson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ch.jdbc.Jdbcconnection;

public class Test {
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		Jdbcconnection jdbc=new Jdbcconnection();
		Connection conn=jdbc.getConn();
		String sql=null;
		Statement st=null;
		ResultSet rs=null;
		
		sql="select distinct severity,orderno,drugmaxwarn,recipeno,visitcode,drugname,drug_unique_code,usetime,"
				+ "costunit,moduleitem,patstatus,modulename,warning,slcode,moduleid,cid,drugspec from "
				+ "t_pharm_screenresults ";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		System.out.println(list);
		
		Map map1=null;
		List list1=new ArrayList();
		for(int i=0;i<17;i++){
			list.add(map1);
		}
		System.out.println(list1);
		for(int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			if(map.containsKey("severity")){
				list1.set(1, map);
			}
			
		}
		System.out.println(list1);
	}
}
