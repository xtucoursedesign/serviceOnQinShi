package org.bean;

public class Layoff {
	// обао
	private String lid;
	private String baitdate;
	private String partid;
	private Integer count;
	private String mainid;
	private Integer complete;
	private String proid;
	private String weight;
	private String unit;
	private String note;
	
	public Layoff() {
		super();
	}

	public Layoff(String lid, String baitdate, String partid, Integer count, String mainid, Integer complete,
			String proid, String weight, String unit, String note) {
		super();
		this.lid = lid;
		this.baitdate = baitdate;
		this.partid = partid;
		this.count = count;
		this.mainid = mainid;
		this.complete = complete;
		this.proid = proid;
		this.weight = weight;
		this.unit = unit;
		this.note = note;
	}

	public String getLid() {
		return lid;
	}

	public void setLid(String lid) {
		this.lid = lid;
	}

	public String getBaitdate() {
		return baitdate;
	}

	public void setBaitdate(String baitdate) {
		this.baitdate = baitdate;
	}

	public String getPartid() {
		return partid;
	}

	public void setPartid(String partid) {
		this.partid = partid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	public String getProid() {
		return proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Layoff [lid=" + lid + ", baitdate=" + baitdate + ", partid=" + partid + ", count=" + count + ", mainid="
				+ mainid + ", complete=" + complete + ", proid=" + proid + ", weight=" + weight + ", unit=" + unit
				+ ", note=" + note + "]";
	}
}
