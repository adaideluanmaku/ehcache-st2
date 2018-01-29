package com.ch.datatojson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import com.ch.jdbc.Jdbcconnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//将PA的审查数据转成JSON串
public class DatatoJson {
	public String visitcode=null;
	public String result=null;
	public String getVisitcode() {
		return visitcode;
	}

	public void setVisitcode(String visitcode) {
		this.visitcode = visitcode;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String execute() throws ClassNotFoundException, SQLException{
		Jdbcconnection jdbc=new Jdbcconnection();
		Connection conn=jdbc.getConn();
		String sql=null;
		Statement st=null;
		ResultSet rs=null;
		
		sql="select distinct severity,orderno,drugmaxwarn,recipeno,visitcode,drugname,drug_unique_code,usetime,"
				+ "costunit,moduleitem,patstatus,modulename,warning,slcode,moduleid,cid,drugspec from "
				+ "t_pharm_screenresults where visitcode='"+visitcode+"'";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		List list=jdbc.getlist(rs);
		System.out.println(list);
		
//		List list1=new ArrayList(17);
//		for(int i=0;i<list.size();i++){
//			
//		}
		
		JSONArray jsonarray=new JSONArray();
//		JSONObject json=new JSONObject();
		for(int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			JSONObject json=JSONObject.fromObject(map);
			jsonarray.add(json);
		}
		System.out.println(jsonarray);
		result=jsonarray.toString();
		
		sql="delete from t_pharm_screenresults where visitcode='"+visitcode+"'";
		st=conn.createStatement();
		st.executeUpdate(sql);
		
		rs.close();
		st.close();
		conn.close();
		return "success";
	}
}
