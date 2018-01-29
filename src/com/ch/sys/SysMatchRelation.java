package com.ch.sys;

/**
 * 
 * <ul>
 * <li>椤圭洰鍚嶇О锛歊edisInit </li>
 * <li>绫诲悕绉帮細  SysMatchRelation </li>
 * <li>绫绘弿杩帮細   </li>
 * <li>鍒涘缓浜猴細鍛ㄥ簲寮�</li>
 * <li>鍒涘缓鏃堕棿锛�016骞�鏈�9鏃�</li>
 * <li>淇敼澶囨敞锛�/li>
 * </ul>
 */
public class SysMatchRelation implements java.io.Serializable{
	
	private Long mhiscode;
	
	private String hiscode;
	
	private String hisname;
	
	private String searchcode;
	
	private int drugmatch;
	
	private int allermatch;
	
	private int dismatch;
	
	private int frematch;
	
	private int routematch;
	
	private int doctormatch;
	
	private int oprmatch; //鎵嬫湳淇℃伅閰嶅鏂规
	
	
	//costitemmatch_scheme,deptmatch_scheme,exammatch_scheme,labmatch_scheme,labsubmatch_scheme,doctorgroupmatch_scheme,wardmatch_scheme	
	private int costitemmatch;
	
	private int deptmatch;
	
	private int exammatch;
	
	private int labmatch;
	
	private int labsubmatch;
	
	private int doctorgroupmatch;
	
	private int wardmatch;
	
	public int getCostitemmatch() {
		return costitemmatch;
	}

	public void setCostitemmatch(int costitemmatch) {
		this.costitemmatch = costitemmatch;
	}

	public int getDeptmatch() {
		return deptmatch;
	}

	public void setDeptmatch(int deptmatch) {
		this.deptmatch = deptmatch;
	}

	public int getExammatch() {
		return exammatch;
	}

	public void setExammatch(int exammatch) {
		this.exammatch = exammatch;
	}

	public int getLabmatch() {
		return labmatch;
	}

	public void setLabmatch(int labmatch) {
		this.labmatch = labmatch;
	}

	public int getLabsubmatch() {
		return labsubmatch;
	}

	public void setLabsubmatch(int labsubmatch) {
		this.labsubmatch = labsubmatch;
	}

	public int getDoctorgroupmatch() {
		return doctorgroupmatch;
	}

	public void setDoctorgroupmatch(int doctorgroupmatch) {
		this.doctorgroupmatch = doctorgroupmatch;
	}

	public int getWardmatch() {
		return wardmatch;
	}

	public void setWardmatch(int wardmatch) {
		this.wardmatch = wardmatch;
	}

	public String getHiscode() {
		return hiscode;
	}

	public void setHiscode(String hiscode) {
		this.hiscode = hiscode;
	}

	public String getHisname() {
		return hisname;
	}

	public void setHisname(String hisname) {
		this.hisname = hisname;
	}

	public String getSearchcode() {
		return searchcode;
	}

	public void setSearchcode(String searchcode) {
		this.searchcode = searchcode;
	}

	public int getDrugmatch() {
		return drugmatch;
	}

	public void setDrugmatch(int drugmatch) {
		this.drugmatch = drugmatch;
	}

	public int getAllermatch() {
		return allermatch;
	}

	public void setAllermatch(int allermatch) {
		this.allermatch = allermatch;
	}

	public int getDismatch() {
		return dismatch;
	}

	public void setDismatch(int dismatch) {
		this.dismatch = dismatch;
	}

	public int getFrematch() {
		return frematch;
	}

	public void setFrematch(int frematch) {
		this.frematch = frematch;
	}

	public int getRoutematch() {
		return routematch;
	}

	public void setRoutematch(int routematch) {
		this.routematch = routematch;
	}

	public int getDoctormatch() {
		return doctormatch;
	}

	public void setDoctormatch(int doctormatch) {
		this.doctormatch = doctormatch;
	}

	public int getOprmatch() {
		return oprmatch;
	}

	public void setOprmatch(int oprmatch) {
		this.oprmatch = oprmatch;
	}

	public Long getMhiscode() {
		return mhiscode;
	}

	public void setMhiscode(Long mhiscode) {
		this.mhiscode = mhiscode;
	}
	
	
	
}
