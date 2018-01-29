package com.ch.unit;

import java.io.InputStream;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

//≥ı ºªØehcache≈‰÷√

public class Ehcache {
	private static CacheManager manager = null;
	private static int eh=0;
	
	
	public void setEh(int eh) {
		if(eh==0){
//			InputStream is = Ehcache.class.getClassLoader().getResourceAsStream(
//					"ehcache.xml");
			manager = CacheManager.create("src/ehcache.xml");
		}
		if(eh==1){
			InputStream is = Ehcache.class.getClassLoader().getResourceAsStream(
					"ehcache1.xml");
			manager = CacheManager.create(is);
		}
		if(eh==2){
			InputStream is = Ehcache.class.getClassLoader().getResourceAsStream(
					"ehcache2.xml");
			manager = CacheManager.create(is);
		}
	}


	public CacheManager getManager() {	
		if (manager == null) {
			setEh(eh);
		}
		return manager;
	}
}
