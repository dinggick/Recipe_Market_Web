package com.recipe.vo;

public class RD {
	private String rdEmail;
	private String rdPwd;
	private String rdManagerName;
	private String rdTeamName;
	private String rdPhone;

	public RD() {}
	
	public RD(String rdEmail, String rdPwd, String rdManagerName, String rdTeamName, String rdPhone) {
		this.rdEmail = rdEmail;
		this.rdPwd = rdPwd;
		this.rdManagerName = rdManagerName;
		this.rdTeamName = rdTeamName;
		this.rdPhone = rdPhone;
	}

	public String getRdEmail() {
		return rdEmail;
	}

	public void setRdEmail(String rdEmail) {
		this.rdEmail = rdEmail;
	}

	public String getRdPwd() {
		return rdPwd;
	}

	public void setRdPwd(String rdPwd) {
		this.rdPwd = rdPwd;
	}

	public String getRdManagerName() {
		return rdManagerName;
	}

	public void setRdManagerName(String rdManagerName) {
		this.rdManagerName = rdManagerName;
	}

	public String getRdTeamName() {
		return rdTeamName;
	}

	public void setRdTeamName(String rdTeamName) {
		this.rdTeamName = rdTeamName;
	}

	public String getRdPhone() {
		return rdPhone;
	}

	public void setRdPhone(String rdPhone) {
		this.rdPhone = rdPhone;
	}
}
