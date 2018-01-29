package com.ch.oracle;

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
		//sqlserver连接7.85
//		String driver= "com.microsoft.sqlserver.jdbc.SQLServerDriver";    
//		String url = "jdbc:sqlserver://172.18.7.85;instanceName=sql2008;databaseName=PASSPA2DB_22";  
//		String name="medicom";
//		String password="medic0m";
		
		//sqlserver连接,张明海演示地址
//		String driver= "com.microsoft.sqlserver.jdbc.SQLServerDriver";    
//		String url = "jdbc:sqlserver://localhost;databaseName=PASSPA2DB_22";  
//		String name="sa";
//		String password="123";
		
		//oracl连接7.154
		String driver= "oracle.jdbc.driver.OracleDriver";    
		String url = "jdbc:oracle:thin:@172.18.7.58:1521:ORCL"; 
//		String url = "jdbc:oracle:thin:@//172.18.7.58:1521/orcl.medicom.net";
		String name="c##passpa_his";
		String password="c##passpa_his";	
		
		
		Class.forName(driver);  
		System.out.println("连接:"+url);
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
