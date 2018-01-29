package com.ch.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Clientsocket {
	public static void main(String args[]) throws UnknownHostException, IOException{
		//创建客户端输入流，开发中使用，在系统窗口输入数据
		BufferedReader sysin=new BufferedReader(new InputStreamReader(System.in));
		String str="";
		while(true){
			str=sysin.readLine();
			while(str.length()>0){//输入的数据为空则不执行socket连接
				//连接服务器socket
				Socket socket=new Socket("172.18.3.146",4700);
				//创建客户端输出流对象，发送客户端数据给服务端
				PrintWriter ou=new PrintWriter(socket.getOutputStream());
				ou.println(str);
				ou.flush();
				System.out.println("客户端1发送："+str);
				
				ou.close();
				socket.close();
				str="";
			}
		}
//		sysin.close();
	}
}
