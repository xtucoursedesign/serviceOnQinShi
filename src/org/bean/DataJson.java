package org.bean;

import java.util.Map;

public class DataJson {
	private int status;
	private Map<String,?> data;
	public DataJson(int status, Map<String,?> map) {
		super();
		this.status = status;
		this.data = map;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Map<String, ?> getMap() {
		return data;
	}
	public void setMap(Map<String,?> map) {
		this.data = map;
	}
	@Override
	public String toString() {
		return "DataJson [status=" + status + ", map=" + data + "]";
	}
}
 