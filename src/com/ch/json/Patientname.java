package com.ch.json;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.ch.jdbc.Jdbcconnection;

//获取输入json串的案例名，jmeter方便使用

public class Patientname {
	String sql;
	List listname1;
	
	
	public String getSql() {
		return sql;
	}


	public void setSql(String sql) {
		this.sql = sql;
	}


	public List getListname1() {
		return listname1;
	}


	public void setListname1(List listname) {
		this.listname1 = listname;
	}


	public String execute() throws ClassNotFoundException, SQLException{
		System.out.println(sql);
//		String sql="select gatherbaseinfo from sa_gather_info where gatherbaseinfo like '%特殊字符%' and gatherbaseinfo like '%哺乳用药0%' and inserttime>'2016-04-22 15:50:00' order by inserttime asc";
		Jdbcconnection jdbc=new Jdbcconnection();
		Connection conn=jdbc.getConn();
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		
		ResultSetMetaData rsmd=rs.getMetaData();
		int len=rsmd.getColumnCount();
		List listrs=new ArrayList();
		while(rs.next()){
			for(int j=1;j<=len;j++){
				listrs.add(rs.getObject(j));
			}
		}
		
		int sum=listrs.size();
		List listname=new ArrayList();
		for(int i=0;i<sum;i++){
			String jsonstr=listrs.get(i).toString();
//			System.out.println(jsonstr);
//			System.out.println(i);
			JSONObject json=JSONObject.fromObject(jsonstr);
			JSONObject Patient=json.getJSONObject("Patient");
//			System.out.println(Patient);
			String Name=Patient.get("Name").toString();
//			System.out.println(Name);
			listname.add(Name);
		}
		setListname1(listname);
		return "success";
	}
}
