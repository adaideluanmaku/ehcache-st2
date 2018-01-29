package com.ch.tongji;

import java.text.SimpleDateFormat;

public class Test {
	public static void main(String args[]){
		SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd");
		System.out.println(sf.format("2012-01-01"));
	}
}
