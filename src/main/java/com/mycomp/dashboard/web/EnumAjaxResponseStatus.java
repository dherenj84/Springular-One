package com.mycomp.dashboard.web;

public enum EnumAjaxResponseStatus {
	SUCCESS("success"), FAILURE("failure");
	private String val;

	EnumAjaxResponseStatus(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}

}
