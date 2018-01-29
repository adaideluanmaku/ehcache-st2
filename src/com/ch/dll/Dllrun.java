package com.ch.dll;

import java.io.IOException;

public class Dllrun {
//	public static int errsum=0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//鐩存帴璋冪敤PASS4Invoke.java閲岄潰鐨勬柟娉曪紝杩斿洖鐘舵�鍙傛暟
		//读取INI配置
//		Passini ini=new Passini();
//		ini.hashINI();
//		System.out.println(ini.getHashValue("PassServer", "WebServiceURL"));
		
//		int a=PASS4Invoke.Instance.MDC_Init("zy","1303","");
		int a=PASS4Invoke.instanceDll.MDC_Init("zy","1303","");
		System.out.println("返回结果："+a);
//		String b=PASS4Invoke.instanceDll.MDC_GetLastError();
//		System.out.println("返回结果："+b);
//		errsum=0;
//    	for(int i=0;i<10;i++){
//    		new Thread(new Runnable(){
//    			public void run(){
//    				int a=PASS4Invoke.instanceDll.MDC_Init("zy","001001","");
//    				System.out.println("返回结果："+a);
//    		    	if(a!=1){
//    		    		errsum=1;
//    		    	}
//    			}
//    		}).start();
//    		System.out.println("循环次数："+i);
//    	}
    	
//    	System.out.println("错误总数是："+errsum);
		
//		int a=Helloword.instanceDll;
	}
}
