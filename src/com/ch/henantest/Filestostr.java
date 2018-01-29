package com.ch.henantest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.LinkedList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.DocumentException;

public class Filestostr {
	public void filetojson(String add,int match_scheme) throws IOException, ClassNotFoundException, SQLException{
		 //��ȡ�ļ�����
        InputStreamReader read = new InputStreamReader(new FileInputStream(add));//���ǵ������ʽnew InputStreamReader(new FileInputStream(add),"utf-8")
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt = null;
        while((lineTxt = bufferedReader.readLine()) != null){
//            System.out.println(lineTxt);
            String json=lineTxt;
            JSONObject obj=JSONObject.fromObject(json);
            
            JSONObject ScreenAllergenList=obj.getJSONObject("ScreenAllergenList");
            JSONArray ScreenAllergens=ScreenAllergenList.getJSONArray("ScreenAllergens");
            for(int i=0;i<ScreenAllergens.size();i++){
            	JSONObject ScreenAllergen=(JSONObject)ScreenAllergens.get(i);
            	String AllerCode=ScreenAllergen.getString("AllerCode");
//            	System.out.println(AllerCode);
            	Allergen all=new Allergen();
        		all.All(AllerCode,match_scheme);
            }
            
            JSONObject ScreenMedCondList=obj.getJSONObject("ScreenMedCondList");
            JSONArray ScreenMedConds=ScreenMedCondList.getJSONArray("ScreenMedConds");
            for(int i=0;i<ScreenMedConds.size();i++){
            	JSONObject ScreenMedCond=(JSONObject)ScreenMedConds.get(i);
            	String DiseaseCode=ScreenMedCond.getString("DiseaseCode");
//            	System.out.println(DiseaseCode);
            	Daodis dis=new Daodis();
        		dis.Dis(DiseaseCode,match_scheme);
            }
            
            JSONObject ScreenOperationList=obj.getJSONObject("ScreenOperationList");
            JSONArray ScreenOperations=ScreenOperationList.getJSONArray("ScreenOperations");
            for(int i=0;i<ScreenOperations.size();i++){
            	JSONObject ScreenOperation=(JSONObject)ScreenOperations.get(i);
            }
            
            JSONObject ScreenDrugList=obj.getJSONObject("ScreenDrugList");
            JSONArray ScreenDrugs=ScreenDrugList.getJSONArray("ScreenDrugs");
            for(int i=0;i<ScreenDrugs.size();i++){
            	JSONObject ScreenDrug=(JSONObject)ScreenDrugs.get(i);
            	String DrugUniqueCode=ScreenDrug.getString("DrugUniqueCode");
            	Daodrugs drug=new Daodrugs();
        		drug.Drug(DrugUniqueCode,match_scheme);
//            	System.out.println(DrugUniqueCode);
            	String RouteCode=ScreenDrug.getString("RouteCode");
//            	System.out.println(RouteCode);
            	Route Route=new Route();
        		Route.All(RouteCode, match_scheme);
            }
        }
        read.close();
	}
	public void traverseFolder1(String path,int match_scheme) throws DocumentException, IOException, ClassNotFoundException, SQLException {
        int fileNum = 0, folderNum = 0;
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
          //ѭ�������ļ�
            for (File file2 : files) {
            	//������ļ��У������list��֮����ɨ��
                if (file2.isDirectory()) {
//                  System.out.println("�ļ���:" + file2.getAbsolutePath());
                    list.add(file2);
                    folderNum++;
                } else {
                	System.out.println("�ļ�:" + file2.getAbsolutePath());
                    fileNum++;
                    
                    //�ļ����ݲ�������
                    filetojson(file2.getAbsolutePath(),match_scheme);
                }
            }
            File temp_file;
            //ѭ��list������ļ��У����ļ����е��ļ�ɨ�����
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
//                        System.out.println("�ļ���:" + file2.getAbsolutePath());
                        list.add(file2);
                        folderNum++;
                    } else {
                    	System.out.println("�ļ�:" + file2.getAbsolutePath());
                      	fileNum++;
                      	
	                    //�ļ����ݲ�������
	                    filetojson(file2.getAbsolutePath(),match_scheme);
                    }
                }
            }
        } else {
            System.out.println("�ļ�������!");
        }
//        System.out.println("�ļ��й���:" + folderNum + ",�ļ�����:" + fileNum);

    }
}
