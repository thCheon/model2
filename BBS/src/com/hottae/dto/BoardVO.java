package com.hottae.dto;

public class BoardVO {
	private int bdId;
	private String bdTitle;
	private String memId;
	private String bdDate;
	private String bdUsed;
	private String bdContent;
	private String bdIp;
	private String fileName;
	private String nickName;
	private int bdMgr;
	private int readCount;
	private int bdRow;
	
	public int getBdId() {
		return bdId;
	}
	public void setBdId(int bdId) {
		this.bdId = bdId;
	}
	public String getBdTitle() {
		return bdTitle;
	}
	public void setBdTitle(String bdTitle) {
		this.bdTitle = bdTitle;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getBdDate() {
		return bdDate;
	}
	public void setBdDate(String bdDate) {
		this.bdDate = bdDate;
	}
	public String getBdUsed() {
		return bdUsed;
	}
	public void setBdUsed(String bdUsed) {
		this.bdUsed = bdUsed;
	}
	public String getBdContent() {
		return bdContent;
	}
	public void setBdContent(String bdContent) {
		this.bdContent = bdContent;
	}
	public String getBdIp() {
		return bdIp;
	}
	public void setBdIp(String bdIp) {
		this.bdIp = bdIp;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getBdMgr() {
		return bdMgr;
	}
	public void setBdMgr(int bdMgr) {
		this.bdMgr = bdMgr;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getBdRow() {
		return bdRow;
	}
	public void setBdRow(int bdRow) {
		this.bdRow = bdRow;
	}
	
}
