package com.ch.sys;

/**
 * 
 * <ul>
 * <li>椤圭洰鍚嶇О锛歱ass_lanservice </li>
 * <li>绫诲悕绉帮細  RouteDictionary </li>
 * <li>绫绘弿杩帮細   缁欒嵂閫斿緞瀛楀吀琛�/li>
 * <li>鍒涘缓浜猴細鍛ㄥ簲寮�</li>
 * <li>鍒涘缓鏃堕棿锛�016骞�鏈�鏃�</li>
 * <li>淇敼澶囨敞锛�/li>
 * </ul>
 */
public class RouteDictionary implements java.io.Serializable{
	
	private int matchscheme;
	
	private String routecode;
	
	private String routename;
	
	private int routetype;
	
	private int isskintest;

	public int getMatchscheme() {
		return matchscheme;
	}

	public void setMatchscheme(int matchscheme) {
		this.matchscheme = matchscheme;
	}

	public String getRoutecode() {
		return routecode;
	}

	public void setRoutecode(String routecode) {
		this.routecode = routecode;
	}

	public String getRoutename() {
		return routename;
	}

	public void setRoutename(String routename) {
		this.routename = routename;
	}

	public int getRoutetype() {
		return routetype;
	}

	public void setRoutetype(int routetype) {
		this.routetype = routetype;
	}

	public int getIsskintest() {
		return isskintest;
	}

	public void setIsskintest(int isskintest) {
		this.isskintest = isskintest;
	}
	
	

}
