package com.ch.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Clientsocket {
	public static void main(String args[]) throws UnknownHostException, IOException{
		//�����ͻ�����������������ʹ�ã���ϵͳ������������
		BufferedReader sysin=new BufferedReader(new InputStreamReader(System.in));
		String str="";
		while(true){
			str=sysin.readLine();
			while(str.length()>0){//���������Ϊ����ִ��socket����
				//���ӷ�����socket
				Socket socket=new Socket("172.18.3.146",4700);
				//�����ͻ�����������󣬷��Ϳͻ������ݸ������
				PrintWriter ou=new PrintWriter(socket.getOutputStream());
				ou.println(str);
				ou.flush();
				System.out.println("�ͻ���1���ͣ�"+str);
				
				ou.close();
				socket.close();
				str="";
			}
		}
//		sysin.close();
	}
}
