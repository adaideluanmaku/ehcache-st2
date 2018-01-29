package com.ch.test;

public class DoubleTest {

	public static void main(String[] args) {
		double dd = 1E-10;
		System.out.println(dd);
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.######");// 保留六位小数
		dd = Double.valueOf(df.format(dd));
		System.out.println("slope:" + df.format(dd));
		System.out.println("slope:" + dd);

	}

}
