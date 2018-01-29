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

public class Mysqlconnection {
	private static String url="jdbc:mysql://172.18.7.159:3306/passpa2db_1609_rh_v5";
	private static String username="root";
	private static String password="zfxmz";
	private static String driver="com.mysql.jdbc.Driver";
	
	public Connection getConn() throws ClassNotFoundException, SQLException{
		Class.forName(driver);  
		return DriverManager.getConnection(url, username, password); 
	}
	
	public List getlist(ResultSet rs) throws SQLException{
		ResultSetMetaData rsmd=rs.getMetaData();
		int len=rsmd.getColumnCount();
		List list=new ArrayList();
		while(rs.next()){
			Map map=new HashMap();
			for(int i=1;i<=len;i++){
				map.put(rsmd.getColumnName(i), rs.getObject(i));
			}
			list.add(map);
		}
		return list;
	}
}
