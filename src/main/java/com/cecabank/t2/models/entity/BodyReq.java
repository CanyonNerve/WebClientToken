package com.cecabank.t2.models.entity;

public class BodyReq {
	
	private String grant_type="client credentials";
	
	private String scope = "create read";

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	
	
	

}
