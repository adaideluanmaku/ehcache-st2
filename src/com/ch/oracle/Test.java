package com.ch.oracle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		Jdbcconnection jdbc=new Jdbcconnection();
		Connection conn=jdbc.getConn();
		
		String sql="select count(*) from mc_clinic_disease";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		rs.next();
		System.out.println(rs);
	}
}
