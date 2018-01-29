package com.ch.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timetest {
	public static void main(String args[]) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		Date date1=sdf.parse("1952-10-14");
		Date date2=sdf.parse("2012-10-14");
		
		long ss=date2.getTime()-date1.getTime();
		long yy=ss;
		
		System.out.println(yy);
	}
}
