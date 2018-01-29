package com.ch.CCDsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Oraclesqlconnection {
	private static String url="jdbc:oracle:thin:@172.18.7.154:1521:Orcl";
	private static String username="CFDI_ADMIN";
	private static String password="123";
	private static String driver="oracle.jdbc.driver.OracleDriver";
	
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
