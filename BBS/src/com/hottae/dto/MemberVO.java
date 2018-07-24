package com.hottae.dto;

public class MemberVO {
	private String memID;
	private String memPW;
	private String memName;
	private String memGender;
	private String memEmail;
	private String NickName;
	
	public String getNickName() {
		return NickName;
	}
	public void setNickName(String NickName) {
		this.NickName = NickName;
	}
	public String getMemID() {
		return memID;
	}
	public void setMemID(String memID) {
		this.memID = memID;
	}
	public String getMemPW() {
		return memPW;
	}
	public void setMemPW(String memPW) {
		this.memPW = memPW;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemGender() {
		return memGender;
	}
	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
}
