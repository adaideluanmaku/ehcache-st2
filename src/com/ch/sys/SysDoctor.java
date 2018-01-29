package com.ch.sys;

/**
 * 
 * <ul>
 * <li>椤圭洰鍚嶇О锛歊edisInit </li>
 * <li>绫诲悕绉帮細  Doctor </li>
 * <li>绫绘弿杩帮細   鍖荤敓鍩烘湰淇℃伅琛�/li>
 * <li>鍒涘缓浜猴細鍛ㄥ簲寮�</li>
 * <li>鍒涘缓鏃堕棿锛�016骞�鏈�1鏃�</li>
 * <li>淇敼澶囨敞锛�/li>
 * </ul>
 */
public class SysDoctor implements java.io.Serializable{
	
	private int matchscheme;
	
	private String doctorcode;
	
	private String doctorname;
	
	private int prespriv;
	
	private int ilevel;
	
	private int antilevel;

	public int getMatchscheme() {
		return matchscheme;
	}

	public void setMatchscheme(int matchscheme) {
		this.matchscheme = matchscheme;
	}

	public String getDoctorcode() {
		return doctorcode;
	}

	public void setDoctorcode(String doctorcode) {
		this.doctorcode = doctorcode;
	}

	public String getDoctorname() {
		return doctorname;
	}

	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}

	public int getPrespriv() {
		return prespriv;
	}

	public void setPrespriv(int prespriv) {
		this.prespriv = prespriv;
	}

	public int getIlevel() {
		return ilevel;
	}

	public void setIlevel(int ilevel) {
		this.ilevel = ilevel;
	}

	public int getAntilevel() {
		return antilevel;
	}

	public void setAntilevel(int antilevel) {
		this.antilevel = antilevel;
	}
	

}
