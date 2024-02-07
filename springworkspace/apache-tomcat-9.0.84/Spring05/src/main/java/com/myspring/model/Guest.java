package com.myspring.model;

public class Guest {
	private int number;
	private String name;
	private String content;
	private String grade;
	private String created;
	private String ipAddr;
	public String getipAddr() {
		return ipAddr;
	}
	public void setipAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int num) {
		this.number = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	
	
}
