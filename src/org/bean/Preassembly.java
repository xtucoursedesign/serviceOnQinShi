package org.bean;

public class Preassembly {
	// ¶þ´Î×°Åä
	private String paid;
	private String assdate;
	private String mainid;
	private Integer complete;
	private String require;
	private String proid;
	
	public Preassembly() {
		super();
	}

	public Preassembly(String paid, String assdate, String mainid, Integer complete, String require, String proid) {
		super();
		this.paid = paid;
		this.assdate = assdate;
		this.mainid = mainid;
		this.complete = complete;
		this.require = require;
		this.proid = proid;
	}

	public String getPaid() {
		return paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public String getAssdate() {
		return assdate;
	}

	public void setAssdate(String assdate) {
		this.assdate = assdate;
	}

	public String getMainid() {
		return mainid;
	}

	public void setMainid(String mainid) {
		this.mainid = mainid;
	}

	public Integer getComplete() {
		return complete;
	}

	public void setComplete(Integer complete) {
		this.complete = complete;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public String getProid() {
		return proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}

	@Override
	public String toString() {
		return "Preassembly [paid=" + paid + ", assdate=" + assdate + ", mainid=" + mainid + ", complete=" + complete
				+ ", require=" + require + ", proid=" + proid + "]";
	}
}
