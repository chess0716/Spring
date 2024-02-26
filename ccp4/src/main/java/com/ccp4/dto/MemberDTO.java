package com.ccp4.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDTO {
	private String id;
	private String password;
	private String auth;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
}
