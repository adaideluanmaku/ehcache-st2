package com.ch.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serversocket {
	public static void main(String args[]) throws IOException {
		//����������socket�˿�
		ServerSocket server=new ServerSocket(4700);
		//��ʼ��socket����
		Socket socket= null;
		while(true){
			//���տͻ�������
			socket=server.accept();
			System.out.println(socket.getInetAddress());
			//������������������󣬽��տͻ��˴���������
			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str=in.readLine();
			System.out.println(str);
			
			//�����������������󣬷������ݸ��ͻ���
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
