package com.ch.sys;

/**
 * 
 * <ul>
 * <li>椤圭洰鍚嶇О锛歊edisInit </li>
 * <li>绫诲悕绉帮細  SysParams </li>
 * <li>绫绘弿杩帮細  绯荤粺璁剧疆鐨勬暟鎹�</li>
 * <li>鍒涘缓浜猴細鍛ㄥ簲寮�</li>
 * <li>鍒涘缓鏃堕棿锛�016骞�鏈�9鏃�</li>
 * <li>淇敼澶囨敞锛�/li>
 * </ul>
 */
public class SysParams implements java.io.Serializable{
	
	private int pkid;
	
	private String checkModel;
	
	private String checkModeName;
	
	private String scrfilters;
	
	private int issave;
	
	private Integer isscroutDrug;
	
	private Integer isscrExactlying;
	
	private Integer isscrDupuniquecode;
	
	private Integer isUseim;

	public int getPkid() {
		return pkid;
	}

	public void setPkid(int pkid) {
		this.pkid = pkid;
	}

	public String getCheckModel() {
		return checkModel;
	}

	public void setCheckModel(String checkModel) {
		this.checkModel = checkModel;
	}

	public String getCheckModeName() {
		return checkModeName;
	}

	public void setCheckModeName(String checkModeName) {
		this.checkModeName = checkModeName;
	}

	public String getScrfilters() {
		return scrfilters;
	}

	public void setScrfilters(String scrfilters) {
		this.scrfilters = scrfilters;
	}

	public int getIssave() {
		return issave;
	}

	public void setIssave(int issave) {
		this.issave = issave;
	}

	public Integer getIsscroutDrug() {
		return isscroutDrug;
	}

	public void setIsscroutDrug(Integer isscroutDrug) {
		this.isscroutDrug = isscroutDrug;
	}

	public Integer getIsscrExactlying() {
		return isscrExactlying;
	}

	public void setIsscrExactlying(Integer isscrExactlying) {
		this.isscrExactlying = isscrExactlying;
	}

	public Integer getIsscrDupuniquecode() {
		return isscrDupuniquecode;
	}

	public void setIsscrDupuniquecode(Integer isscrDupuniquecode) {
		this.isscrDupuniquecode = isscrDupuniquecode;
	}

	public Integer getIsUseim() {
		return isUseim;
	}

	public void setIsUseim(Integer isUseim) {
		this.isUseim = isUseim;
	}
}
