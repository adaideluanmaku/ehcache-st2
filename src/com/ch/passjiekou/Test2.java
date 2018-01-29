package com.ch.passjiekou;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

public class Test2 {
	public static void main(String[] args) throws UnsupportedEncodingException, TimeoutException {
		// TODO Auto-generated method stub
		Passservice passweb=new Passservice();
		String result=passweb.getPassResult("1f89b225e5ccb80521bf696c578a5860d9d108fe7b0d994656b831a5ab874d4d");
		System.out.println(result);
	}
}
