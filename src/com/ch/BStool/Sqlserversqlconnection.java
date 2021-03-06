package com.ch.BStool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sqlserversqlconnection {
	private static String url="jdbc:sqlserver://172.18.3.145;databaseName=PASSPA2DB";
	private static String username="sa";
	private static String password="123";
	private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
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
