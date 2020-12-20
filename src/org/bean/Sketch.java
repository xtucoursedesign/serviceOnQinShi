package org.bean;

public class Sketch {
	private String step;
	private Integer status;
	
	public Sketch() {
		super();
	}
	
	public Sketch(String step, Integer status) {
		super();
		this.step = step;
		this.status = status;
	}
	
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Sketch [step=" + step + ", status=" + status + "]";
	}
}
