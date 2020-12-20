package org.bean;

import java.io.Serializable;

public class Factory implements Serializable{

	private static final long serialVersionUID = 1L;
	private String bfid;
	private String name;
	public Factory() {
		super();
	}
	public Factory(String bfid, String name) {
		super();
		this.bfid = bfid;
		this.name = name;
	}
	public String getBfid() {
		return bfid;
	}
	public void setBfid(String bfid) {
		this.bfid = bfid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Factory [bfid=" + bfid + ", name=" + name + "]";
	}
}
