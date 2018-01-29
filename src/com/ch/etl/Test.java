package com.ch.etl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.annotations.Step;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.repository.RepositoryDirectory;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepInitThread;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaDataCombi;
import org.pentaho.di.trans.steps.mappingoutput.MappingOutput;

public class Test {
	public static void main(String[] args) {
		/**
		*java 读取kettle 文件
		*/
		// TODO Auto-generated method stub
		try {
			//��ʼ�� 
			KettleEnvironment.init();
			EnvUtil.environmentInit();
			
			TransMeta transMeta = new TransMeta("C:/kettle/t_mc_clinic_allergen.ktr");
			System.out.println(transMeta);
			// ת��Ԫ����  
			Trans trans = new Trans(transMeta);
			System.out.println(trans);
			// ִ��ת��  
//			trans.execute(null); 
//			// �ȴ�ת��ִ�н���  
//			trans.waitUntilFinished();
//			
//          if (trans.getErrors() > 0) {  
//                System.out.println(trans.getErrors());
//                throw new KettleException();
//			}
		} catch (KettleException e) {
			e.printStackTrace();
			System.out.println(e); 
		}
	}
}
