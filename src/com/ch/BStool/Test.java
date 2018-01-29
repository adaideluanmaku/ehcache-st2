package com.ch.BStool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test {
	public static void main(String args[]) throws ClassNotFoundException, SQLException, IOException{
		Sqlserversqlconnection jdbc=new Sqlserversqlconnection();
		Connection sqlserverconn=jdbc.getConn();
		Statement st=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql=null;
		List list=null;		
		
		//打印字段
//		sql="select * from t_mc_clinic_allergen where rownum = 1 ";//oracle
		sql="select top 1 * from mc_dict_allergen ";//sqlserver
		st=sqlserverconn.createStatement();
		rs=st.executeQuery(sql);
		list=jdbc.getlist(rs);
		Map map=(Map) list.get(0);
		String a=map.keySet().toString();
		System.out.println(a);
		String[] b=a.substring(1,a.length()-1).split(",");
		System.out.println(b.length);
		String c="";
		int d=0;
		for(int i=0;i<b.length;i++){
			d=d+1;
			//去除没用字段
			if("inserttime1111".equals(b[i].trim())){
				d=d-1;
			}else{
				c=c+"?,";
				System.out.println("pst.setString("+(d)+",map.get(\""+b[i].trim()+"\").toString());//"+b[i].trim());
//				System.out.println("pst.setString("+(d)+",ScreenDrug.getString(\""+b[i].trim()+"\"));//"+b[i].trim());
//				System.out.println("pst.setString("+(d)+",Patient.getString(\""+b[i].trim()+"\"));//"+b[i].trim());
			}
		}
		System.out.println(c);
		
		rs.close();
		st.close();
		sqlserverconn.close();
	}
}
