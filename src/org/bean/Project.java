package org.bean;

public class Project {
	private String pid;
	private String name;
	private String faid;
	private String investid;
	private String investname;
	private String approdate;
	private String spededate;
	private String actdate;
	
	public Project() {
		super();
	}

	public Project(String pid, String name, String faid, String investid, String investname, String approdate,
			String spededate, String actdate) {
		super();
		this.pid = pid;
		this.name = name;
		this.faid = faid;
		this.investid = investid;
		this.investname = investname;
		this.approdate = approdate;
		this.spededate = spededate;
		this.actdate = actdate;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFaid() {
		return faid;
	}

	public void setFaid(String faid) {
		this.faid = faid;
	}

	public String getInvestid() {
		return investid;
	}

	public void setInvestid(String investid) {
		this.investid = investid;
	}

	public String getInvestname() {
		return investname;
	}

	public void setInvestname(String investname) {
		this.investname = investname;
	}

	public String getApprodate() {
		return approdate;
	}

	public void setApprodate(String approdate) {
		this.approdate = approdate;
	}

	public String getSpededate() {
		return spededate;
	}

	public void setSpededate(String spededate) {
		this.spededate = spededate;
	}

	public String getActdate() {
		return actdate;
	}

	public void setActdate(String actdate) {
		this.actdate = actdate;
	}

	@Override
	public String toString() {
		return "Project [pid=" + pid + ", name=" + name + ", faid=" + faid + ", investid=" + investid + ", investname="
				+ investname + ", approdate=" + approdate + ", spededate=" + spededate + ", actdate=" + actdate + "]";
	}
}
