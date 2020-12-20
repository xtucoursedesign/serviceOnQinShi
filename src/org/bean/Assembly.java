package org.bean;

public class Assembly {
	// ¶þ´Î×°Åä
	private String aid;
	private String secdate;
	private String partid;
	private Integer complete;
	private String require;
	private String proid;
	private String mainid;
	private Integer hole;
	private String shotdate;
	private Integer shotcomp;
	
	public Assembly() {
		super();
	}

	public Assembly(String aid, String secdate, String partid, Integer complete, String require, String proid,
			String mainid, Integer hole, String shotdate, Integer shotcomp) {
		super();
		this.aid = aid;
		this.secdate = secdate;
		this.partid = partid;
		this.complete = complete;
		this.require = require;
		this.proid = proid;
		this.mainid = mainid;
		this.hole = hole;
		this.shotdate = shotdate;
		this.shotcomp = shotcomp;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getSecdate() {
		return secdate;
	}

	public void setSecdate(String secdate) {
		this.secdate = secdate;
	}

	public String getPartid() {
		return partid;
	}

	public void setPartid(String partid) {
		this.partid = partid;
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

	public String getMainid() {
		return mainid;
	}

	public void setMainid(String mainid) {
		this.mainid = mainid;
	}

	public Integer getHole() {
		return hole;
	}

	public void setHole(Integer hole) {
		this.hole = hole;
	}

	public String getShotdate() {
		return shotdate;
	}

	public void setShotdate(String shotdate) {
		this.shotdate = shotdate;
	}

	public Integer getShotcomp() {
		return shotcomp;
	}

	public void setShotcomp(Integer shotcomp) {
		this.shotcomp = shotcomp;
	}

	@Override
	public String toString() {
		return "Assembly [aid=" + aid + ", secdate=" + secdate + ", partid=" + partid + ", complete=" + complete
				+ ", require=" + require + ", proid=" + proid + ", mainid=" + mainid + ", hole=" + hole + ", shotdate="
				+ shotdate + ", shotcomp=" + shotcomp + "]";
	}
}
