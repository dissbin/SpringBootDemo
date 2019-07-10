package com.AIfntech.service.pojo;

public class Result {
	public Result(boolean success,String info) {
		this.info = info;
		this.success = success;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	private boolean success;
	private String info;
}
