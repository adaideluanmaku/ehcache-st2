package com.ch.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serversocket {
	public static void main(String args[]) throws IOException {
		//开启服务器socket端口
		ServerSocket server=new ServerSocket(4700);
		//初始化socket连接
		Socket socket= null;
		while(true){
			//接收客户端连接
			socket=server.accept();
			System.out.println(socket.getInetAddress());
			//创建服务端输入流对象，接收客户端传来的数据
			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str=in.readLine();
			System.out.println(str);
			
			//创建服务端输出流对象，发送数据给客户端
//			PrintWriter ou=new PrintWriter(socket.getOutputStream());
//			ou.println(str);
//			ou.flush();
			
			in.close();
//			ou.close();
			socket.close();
		}
//		server.close();
	}
}
