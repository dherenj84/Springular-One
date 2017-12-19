package com.mycomp.dashboard.web;

import java.util.List;

public class AjaxResponse {
	private String status;
	private Object data;
	private List<Error> error;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<Error> getError() {
		return error;
	}

	public void setError(List<Error> error) {
		this.error = error;
	}

}
