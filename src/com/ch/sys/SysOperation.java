package com.ch.sys;

/**
 * 
 * <ul>
 * <li>椤圭洰鍚嶇О锛歊edisInit </li>
 * <li>绫诲悕绉帮細  SysOperation </li>
 * <li>绫绘弿杩帮細   鎵嬫湳淇℃伅</li>
 * <li>鍒涘缓浜猴細鍛ㄥ簲寮�</li>
 * <li>鍒涘缓鏃堕棿锛�016骞�鏈�9鏃�</li>
 * <li>淇敼澶囨敞锛�/li>
 * </ul>
 */
public class SysOperation implements java.io.Serializable{
	
	private int matchscheme;
	
	private String operationCode;
	
	private String operationName;
	
	private int useanti;
	
	private int drugTime;
	
	private double premomentLow;
	
	private double premomentHigh;

	public int getMatchscheme() {
		return matchscheme;
	}

	public void setMatchscheme(int matchscheme) {
		this.matchscheme = matchscheme;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public int getUseanti() {
		return useanti;
	}

	public void setUseanti(int useanti) {
		this.useanti = useanti;
	}

	public int getDrugTime() {
		return drugTime;
	}

	public void setDrugTime(int drugTime) {
		this.drugTime = drugTime;
	}

	public double getPremomentLow() {
		return premomentLow;
	}

	public void setPremomentLow(double premomentLow) {
		this.premomentLow = premomentLow;
	}

	public double getPremomentHigh() {
		return premomentHigh;
	}

	public void setPremomentHigh(double premomentHigh) {
		this.premomentHigh = premomentHigh;
	}
	
}
