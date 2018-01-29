package com.ch.log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class Test {
	private static Logger log=Logger.getLogger(Test.class);
	
	public static void main(String args[]) throws ParseException{
		System.out.println("����һ��log");
		log.info("����һ��log");
		log.debug("����һ��log1");
		log.error("����һ��log2");
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		Date d1 = df.parse("2016-08-04 14:07:47 354");
	    Date d2 = df.parse("2016-08-04 14:07:45 600");
	    long diff = d1.getTime() - d2.getTime();
	    System.out.println(diff);
	}
}
