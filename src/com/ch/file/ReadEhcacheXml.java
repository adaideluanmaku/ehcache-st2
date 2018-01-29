package com.ch.file;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

//Դ�ļ�������Ŀ���ļ�

public class ReadEhcacheXml {

	static Map<String, Long> map = new HashMap<String, Long>();

	public static void main(String[] args) {
		//ԭ�ļ�
		String path = "D:/ehcachetest/bak/local0328/local";
		String path1 = "D:\\file\\";
		try {
//			readFile(path);
			readFile1(path1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readFile(String path) throws IOException {
		System.out.println("�ļ��У�" + path);
		File file = new File(path);
		File[] tempList = file.listFiles();
		// System.out.println("��Ŀ¼�¶��������" + tempList.length);
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				System.out.println("filename is：" + tempList[i]);
				map.put(tempList[i].getName(), file.length());
				//FileUtils.copyDirectory(tempList[i], new File("D:\\ehcachetest"));
				FileUtils.copyFile(tempList[i], new File("D:\\ehcachetest\\"+tempList[i].getName()));
			}
			if (tempList[i].isDirectory()) {
				// System.out.println("filework is：" + tempList[i]);
				//
				File file2 = tempList[i];
				readFile(file2.getPath());
			}
		}
		System.out.println("copy����");
	}
	
	//递归读取所有文件
	public static void readFile1(String path) throws IOException {
		System.out.println("path:" + path);
		File file = new File(path);
		File[] tempList = file.listFiles();
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				System.out.println("filename is：" + tempList[i]);
			}
			if (tempList[i].isDirectory()) {
				System.out.println("filework is：" + tempList[i]);
				File file2 = tempList[i];
				readFile1(file2.getPath());
			}
		}
	}
}
