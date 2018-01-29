package com.ch.henantest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jdbcconnection {
	static private int len;
	public int getLen(){
		return len;
	}
	public Connection getConn() throws ClassNotFoundException, SQLException{
		//sqlserver����7.85
		String driver= "com.microsoft.sqlserver.jdbc.SQLServerDriver";    
		String url = "jdbc:sqlserver://172.18.3.150;databaseName=PASS4DB_411609";  
		String name="sa";
		String password="zhouzou@0422";
//		String url="jdbc:mysql://172.18.7.159:3306/pass4db1606";
//		String name="root";
//		String password="zfxmz";
//		String driver="com.mysql.jdbc.Driver";
		
		Class.forName(driver);  
		System.out.println("����:"+url);
		return DriverManager.getConnection(url, name, password);  
	}
	
	public List getlist(ResultSet rs) throws SQLException{
		ResultSetMetaData rsmd=rs.getMetaData();
		this.len=rsmd.getColumnCount();
		List list=new ArrayList();
		while(rs.next()){
			Map map=new HashMap();
			for(int i=1;i<=len;i++){	
				map.put(rsmd.getColumnName(i).toLowerCase(), rs.getObject(i));
			}
			list.add(map);
		}
		return list;
	}
}
